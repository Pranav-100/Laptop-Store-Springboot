package com.example.laptopstore.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.laptopstore.entity.Laptop;
import com.example.laptopstore.service.LaptopService;

@RestController
@RequestMapping("/laptops")
@CrossOrigin
public class LaptopController {
	private final LaptopService laptopService;

	@Autowired
	public LaptopController(LaptopService laptopService) {
		this.laptopService = laptopService;
	}

	@PostMapping
	public ResponseEntity<Laptop> createLaptop(@Valid @RequestBody Laptop Laptop) {
		Laptop savedLaptop = laptopService.createLaptop(Laptop);
		return new ResponseEntity<>(savedLaptop, HttpStatus.CREATED);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Laptop> getLaptopById(@PathVariable("id") Long id) {
		Laptop Laptop = laptopService.getLaptopById(id);
		return new ResponseEntity<>(Laptop, HttpStatus.OK);
	}

	@GetMapping
	public ResponseEntity<List<Laptop>> getAllLaptops() {
		List<Laptop> laptops = laptopService.getAllLaptops();
		return new ResponseEntity<>(laptops, HttpStatus.OK);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Laptop> updateLaptop(@PathVariable("id") Long id,
			@Valid @RequestBody Laptop Laptop) {
		Laptop updatedLaptop = laptopService.updateLaptop(id, Laptop);
		return new ResponseEntity<>(updatedLaptop, HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteLaptop(@PathVariable("id") Long id) {
		laptopService.deleteLaptop(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@GetMapping("/search")
	public ResponseEntity<List<Laptop>> searchLaptops(@RequestParam(value = "name", required = false) String name,
			@RequestParam(value = "price", required = false) Double price,
			@RequestParam(value = "brand", required = false) String brand) {
		List<Laptop> laptops = laptopService.searchLaptops(name, price, brand);
		return new ResponseEntity<>(laptops, HttpStatus.OK);
	}
}
