package com.delivery.controllers;

import java.sql.Timestamp;
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

import com.delivery.repositories.RatingHistoryRepository;
import com.delivery.models.RatingHistory;

@RestController
@RequestMapping("/api")
public class RatingHistoryController {

	@Autowired
	RatingHistoryRepository dao;
	
	@GetMapping("/rating-history")
	public List<RatingHistory> getRatingHistory()
	{
		List<RatingHistory> foundHistory = dao.findAll();
		return foundHistory;
	}
	
	@GetMapping("/rating-history/{rating_id")
	public ResponseEntity<RatingHistory> getRecord(@PathVariable(value="rating_id") Integer id)
	{
		RatingHistory history = dao.findById(id).orElse(null);
		if (history == null)
			return ResponseEntity.notFound().header("Rating History", "Nothing found with that id").build();
		return ResponseEntity.ok(history);
	}
	
	@GetMapping("/rating-history/businesses/{businesses_id}")
	public List<RatingHistory> getHistoryByBusiness(@PathVariable(value="businesses_id") Iterable<Integer> id)
	{
		List<RatingHistory> history = dao.findAllById(id);
		return history;
	}
	
	@PostMapping("/rating-history")
	public ResponseEntity<RatingHistory> postRating(@RequestBody RatingHistory history)
	{
		RatingHistory createdRecord = dao.save(history);
		return ResponseEntity.ok(createdRecord);
	}
	
	@DeleteMapping("rating-history/{rating_id}")
	public ResponseEntity<RatingHistory> deleteRecord(@PathVariable(value="rating_id") Integer id)
	{
		RatingHistory history = dao.findById(id).orElse(null);
		if (history == null)
			return ResponseEntity.notFound().header("Rating History", "Nothing found with that id").build();
		else
			dao.delete(history);
		return ResponseEntity.ok().build();
	}
	
	@PutMapping("rating-history/{rating_id}")
	public ResponseEntity<RatingHistory> updateRecord(@RequestBody RatingHistory history, @PathVariable(value="rating_id") Integer id)
	{
		RatingHistory foundHistory = dao.findById(id).orElse(null);
		
		if (foundHistory == null)
			return ResponseEntity.notFound().header("Rating History", "Nothing found with that id").build();
		else
		{
			foundHistory.setBusinesses_id(history.getBusinesses_id());
			foundHistory.setDescription(history.getDescription());
			foundHistory.setDrivers_id(history.getDrivers_id());
			foundHistory.setPersons_id(history.getPersons_id());
			foundHistory.setRating(history.getRating());
//			foundHistory.setRating_id(history.getRating());
			foundHistory.setTimestamp(new Timestamp(System.currentTimeMillis()));
			dao.save(foundHistory);
		}
		return ResponseEntity.ok().header("Rating History", "Properly updated History record").build();
	}

}
