package com.deliver.api.auth;

import java.security.Key;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.SecureRandom;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;

import com.delivery.auth.MySQLUserDetailService;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.JwtParserBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

public class JwtTokenProvider {

	private PrivateKey key;
	private SecretKey secretKey;

	@Value("${security.jwt.token.secret-key:secret}")
	private String secret = "secret";

	@Value("${security.jwt.token.expire-length: 3600000}")
	private long validityInMilliseconds = 3600000;

	@Autowired
	private MySQLUserDetailService userDetailsService;

	public JwtTokenProvider() {
		init();
		if (secretKey == null)
			System.out.println("Error generating private key");
	}

	@PostConstruct
	protected void init() {
		try {
//			KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance("RSA");
//			keyPairGen.initialize(2048);
//			KeyPair pair = keyPairGen.generateKeyPair();
//			key = pair.getPrivate();
//			secretKey = key
			KeyGenerator keyGen = KeyGenerator.getInstance("HmacSHA256");
			SecureRandom secRandom = new SecureRandom();
			keyGen.init(secRandom);
			secretKey = keyGen.generateKey();
		} catch (NoSuchAlgorithmException e) {
			System.out.println("NoSuchAlgorithmException thrown");
		}
	}

	@SuppressWarnings("deprecation")
	public String createToken(String username, List<String> roles) {
		Claims claims = Jwts.claims().setSubject(username);
		claims.put("roles", roles);

		Date now = new Date();
		Date validity = new Date(now.getTime() + validityInMilliseconds);

		return Jwts.builder().setClaims(claims).setIssuedAt(now).setExpiration(validity)
				.signWith(SignatureAlgorithm.HS256, secretKey).compact();
	}

	public Authentication getAuthentication(String token) {
		UserDetails userDetails = this.userDetailsService.loadUserByUsername(getUsername(token));
		return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
	}

	public String getUsername(String token) {
		JwtParserBuilder builder = Jwts.parserBuilder();
		return builder.setSigningKey(secretKey).build().parseClaimsJws(token).getBody().getSubject();
	}

	public String resolveToken(HttpServletRequest req) {
		String bearerToken = req.getHeader("Authorization");
		if (bearerToken != null && bearerToken.startsWith("Bearer "))
			return bearerToken.substring(7, bearerToken.length());
		return null;
	}

	public boolean validateToken(String token) {
		try {
			Jws<Claims> claims = Jwts.parserBuilder().setSigningKey(secretKey).build().parseClaimsJws(token);
			if (claims.getBody().getExpiration().before(new Date()))
				return false;
			else
				return true;
		} catch (JwtException | IllegalArgumentException e) {
			throw new InsufficientAuthenticationException("Expired or invalid JWT token");
		}
	}
}
