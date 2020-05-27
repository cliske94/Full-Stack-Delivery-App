package com.delivery.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.delivery.models.Driver;
import com.delivery.repositories.DriversRepository;

@RestController
@RequestMapping("/api")
public class DriversController {

	@Autowired
    DriversRepository dao;
	
	@GetMapping("/drivers")
    public List<Driver> getDrivers() {
        List<Driver> foundDrivers = dao.findAll();
        return foundDrivers;
    }
	
	@GetMapping("/drivers/{persons_id}")
    public ResponseEntity<Driver> getDrivers(@PathVariable(value="persons_id") Integer id) {
        Driver foundDriver = dao.findById(id).orElse(null);

        if(foundDriver == null) {
            return ResponseEntity.notFound().header("Driver","Nothing found with that id").build();
        }
        return ResponseEntity.ok(foundDriver);
    }
	
	@PostMapping("/drivers/")
	public ResponseEntity<Driver> postDriver(@RequestBody Driver driver)
	{
		Driver newDriver = dao.save(driver);
		return ResponseEntity.ok(newDriver);
	}
	
	@DeleteMapping("/drivers/{persons_id}")
	public ResponseEntity<Driver> deleteDriver(@PathVariable(value="persons_id") Integer id)
	{
		Driver foundDriver = dao.findById(id).orElse(null);
		if(foundDriver == null)
			return ResponseEntity.notFound().header("Driver", "Nothing found with that id").build();
		else
		{
			dao.delete(foundDriver);
		}
		return ResponseEntity.ok().build();
	}
	
	@PutMapping
	public ResponseEntity<Driver> updateDriver(@RequestBody Driver driver, @PathVariable(value="persons_id") Integer id)
	{
		Driver foundDriver = dao.findById(id).orElse(null);
		if(foundDriver == null)
			return ResponseEntity.notFound().header("Driver", "Nothing found with that id").build();
		else
		{
			foundDriver.setDate_hired(driver.getDate_hired());
			foundDriver.setLicense_number(driver.getLicense_number());
			//foundDriver.setName(driver.getName());
			//   foundDriver.setPhone_number(driver.getPhone_number());
			foundDriver.setRating(driver.getRating());
			dao.save(foundDriver);
		}
		return ResponseEntity.ok().header("Driver", "Properly updated Driver record").build();
	}
}