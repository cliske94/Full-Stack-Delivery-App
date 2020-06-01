package com.delivery.viewcontrollers;

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

import com.delivery.models.Driver;
import com.delivery.models.Person;

@Controller
@RequestMapping("/drivers")
public class DriversViewController {

	@Value("${spring.datasource.url}")
	private String url;
	
	@Value("${spring.datasource.username}")
	private String username;
	
	@Value("${spring.datasource.password}")
	private String password;
	
	@GetMapping()
	public String getAllDrivers(Model model)
	{
		List<Driver> drivers = new ArrayList<Driver>();
		try (Connection con = DriverManager.getConnection(url, username, password))
		{
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT rating, persons.id, persons.first_name, persons.last_name, persons.phone_number FROM drivers"
					+ " JOIN persons ON drivers.Persons_id=persons.id");
			while (rs.next())
			{
				Driver driver = new Driver();
				Person person = new Person();
				driver.setRating(rs.getInt("rating"));
				person.setFirst_name(rs.getString("first_name"));
				person.setLast_name(rs.getString("last_name"));
				person.setPhone_number(rs.getString("phone_number"));
				System.out.println(person.getPhone_number());
//				driver.setName(person);
//				driver.setPhone_number(person);
				drivers.add(driver);
			}
		} catch (SQLException e)
		{
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		model.addAttribute("drivers", drivers);
		return "drivers";
	}
}
