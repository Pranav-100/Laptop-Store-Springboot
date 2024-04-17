package com.example.laptopstore.service;

import java.util.List;

import com.example.laptopstore.entity.Laptop;

public interface LaptopService {
    List<Laptop> getAllLaptops();
    Laptop getLaptopById(Long id);
    Laptop createLaptop(Laptop laptopDTO);
    Laptop updateLaptop(Long id, Laptop laptopDTO);
    boolean deleteLaptop(Long id);
    List<Laptop> searchLaptopsByName(String name);
    List<Laptop> searchLaptopsByPrice(Double price);
    List<Laptop> searchLaptopsByBrand(String brand);
    List<Laptop> searchLaptops(String name, Double price, String brand);
}

