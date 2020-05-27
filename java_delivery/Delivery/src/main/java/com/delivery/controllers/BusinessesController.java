package com.delivery.controllers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.delivery.models.Business;

@Controller
@RequestMapping({ "/api" })
public class BusinessesController {
	@Value("${spring.datasource.url}")
	private String url;
	
	@Value("${spring.datasource.username}")
	private String username;
	
	@Value("${spring.datasource.password}")
	private String password;
	
	@GetMapping("/businesses")
	public String getAllBusinesses(Model model)
	{
		List<Business> businesses = new ArrayList<Business>();
		try (Connection con = DriverManager.getConnection(url, username, password))
		{
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM businesses");
			while (rs.next())
			{
				Business newBusiness = new Business();
				newBusiness.setId(rs.getInt("id"));
				newBusiness.setClose_time(rs.getTime("close_time"));
				newBusiness.setOpen_time(rs.getTime("open_time"));
				newBusiness.setPhone_number(rs.getString("phone_number"));
				newBusiness.setUrl(rs.getString("url"));
				newBusiness.setName(rs.getString("name"));
				newBusiness.setMailing_address(rs.getString("mailing_address"));
				newBusiness.setCustomer_rating(rs.getInt("customer_rating"));
				businesses.add(newBusiness);
			}
		} catch (SQLException e)
		{
			e.printStackTrace();
		}
		model.addAttribute("businesses", businesses);
		return "businesses";
	}
}
