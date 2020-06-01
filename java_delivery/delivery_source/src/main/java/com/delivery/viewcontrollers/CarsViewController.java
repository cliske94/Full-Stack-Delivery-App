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

import com.delivery.models.Car;

@Controller
@RequestMapping("/cars")
public class CarsViewController {

	@Value("${spring.datasource.url}")
	private String url;
	
	@Value("${spring.datasource.username}")
	private String username;
	
	@Value("${spring.datasource.password}")
	private String password;
	
	@GetMapping()
	public String getAllCars(Model model)
	{
		List<Car> cars = new ArrayList<Car>();
		try (Connection con = DriverManager.getConnection(url, username, password))
		{
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM cars");
			while (rs.next())
			{
				Car car = new Car();
				car.setDrivers_id(rs.getInt("Drivers_id"));
				car.setVIN(rs.getString("VIN"));
				car.setInsurance_verified_YorN(rs.getString("insurance_verified_YorN"));
				car.setColor(rs.getString("color"));
				car.setMake(rs.getString("make"));
				car.setModel(rs.getString("model"));
				car.setPlate_number(rs.getString("plate_number"));
				cars.add(car);
			}
		} catch (SQLException e)
		{
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		model.addAttribute("cars", cars);
		return "cars";
	}
}
