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
import com.delivery.models.Car;
import com.delivery.repositories.CarsRepository;

@RestController
@RequestMapping("/api")
public class CarsController {

	@Autowired
    CarsRepository dao;
	
	@GetMapping("/cars")
    public List<Car> getCars() {
        List<Car> foundCars = dao.findAll();
        return foundCars;
    }
	
	@GetMapping("/cars/{id}")
    public ResponseEntity<Car> getCar(@PathVariable(value="id") Integer id) {
        Car foundCar = dao.findById(id).orElse(null);

        if(foundCar == null) {
            return ResponseEntity.notFound().header("Car","Nothing found with that ID").build();
        }
        return ResponseEntity.ok(foundCar);
    }
	
	@PostMapping("/cars")
    public ResponseEntity<Car> postCar(@RequestBody Car car) {

		System.out.println(car.getVIN());
        // Saving to DB using an instance of the repo interface.
        Car createdCar = dao.save(car);

        // RespEntity crafts response to include correct status codes.
        return ResponseEntity.ok(createdCar);
    }
	
	@DeleteMapping("/cars/{id}")
    public ResponseEntity<Car> deleteCar(@PathVariable(value="id") Integer id) {
        Car foundCar = dao.findById(id).orElse(null);

        if(foundCar == null) {
            return ResponseEntity.notFound().header("Car","Nothing found with that plate").build();
        }else {
            dao.delete(foundCar);
        }
        return ResponseEntity.ok().build();
    }
    
    @PutMapping("/cars/{id}")
    public ResponseEntity<Car> updateCar(@RequestBody Car car, @PathVariable(value="id") Integer id)
    {
    	Car foundCar = dao.findById(id).orElse(null);
    	
    	if (foundCar == null)
    	{
		return ResponseEntity.notFound().header("Car", "Nothing found with that ID").build();
    	} else
    	{
    		foundCar.setColor(car.getColor());
    		foundCar.setDrivers_id(car.getDrivers_id());
    		foundCar.setInsurance_verified_YorN(car.getInsurance_verified_YorN());
    		foundCar.setMake(car.getMake());
    		foundCar.setModel(car.getModel());
    		foundCar.setPlate_number(car.getPlate_number());
    		foundCar.setVIN(car.getVIN());
    		dao.save(foundCar);
    	}
    	return ResponseEntity.ok().header("Car", "Properly updated car").build();
    }
}
