package com.example.laptopstore.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.laptopstore.entity.Laptop;
//import com.example.laptopstore.entity.Laptop;
import com.example.laptopstore.exception.ResourceNotFoundException;
import com.example.laptopstore.repo.LaptopRepository;
import com.example.laptopstore.service.LaptopService;

@Service
public class LaptopServiceImpl implements LaptopService {
    private final LaptopRepository laptopRepository;

    @Autowired
    public LaptopServiceImpl(LaptopRepository laptopRepository) {
        this.laptopRepository = laptopRepository;
    }

    @Override
    public List<Laptop> getAllLaptops() {
        List<Laptop> laptops = laptopRepository.findAll();
        return mapLaptopsToDTOs(laptops);
    }

    @Override
    public Laptop getLaptopById(Long id) {
        Optional<Laptop> optionalLaptop = laptopRepository.findById(id);
        if (optionalLaptop.isPresent()) {
            Laptop laptop = optionalLaptop.get();
            return mapLaptopToDTO(laptop);
        }
        throw new ResourceNotFoundException("Laptop not found with id: " + id);
    }

    @Override
    public Laptop createLaptop(Laptop Laptop) {
        Laptop laptop = mapDTOToLaptop(Laptop);
        Laptop savedLaptop = laptopRepository.save(laptop);
        return mapLaptopToDTO(savedLaptop);
    }

    @Override
    public Laptop updateLaptop(Long id, Laptop Laptop) {
        Optional<Laptop> optionalLaptop = laptopRepository.findById(id);
        if (optionalLaptop.isPresent()) {
            Laptop laptop = optionalLaptop.get();
            laptop.setName(Laptop.getName());
            laptop.setPrice(Laptop.getPrice());
            laptop.setBrand(Laptop.getBrand());
            laptop.setStorage(Laptop.getStorage());
            laptop.setRam(Laptop.getRam());
            laptop.setProcessor(Laptop.getProcessor());

            Laptop updatedLaptop = laptopRepository.save(laptop);
            return mapLaptopToDTO(updatedLaptop);
        }
        throw new ResourceNotFoundException("Laptop not found with id: " + id);
    }

    @Override
    public boolean deleteLaptop(Long id) {
        if (!laptopRepository.existsById(id)) {
        	throw new ResourceNotFoundException("Laptop not found with id: " + id);
              }
        laptopRepository.deleteById(id);
        return true;
    }

    @Override
    public List<Laptop> searchLaptopsByName(String name) {
        List<Laptop> laptops = laptopRepository.findByName(name);
        return mapLaptopsToDTOs(laptops);
    }

    @Override
    public List<Laptop> searchLaptopsByPrice(Double price) {
        List<Laptop> laptops = laptopRepository.findByPrice(price);
        return mapLaptopsToDTOs(laptops);
    }

    @Override
    public List<Laptop> searchLaptopsByBrand(String brand) {
        List<Laptop> laptops = laptopRepository.findByBrand(brand);
        return mapLaptopsToDTOs(laptops);
    }

    private Laptop mapLaptopToDTO(Laptop laptop) {
        Laptop Laptop = new Laptop();
        Laptop.setId(laptop.getId());
        Laptop.setName(laptop.getName());
        Laptop.setPrice(laptop.getPrice());
        Laptop.setBrand(laptop.getBrand());
        Laptop.setStorage(laptop.getStorage());
        Laptop.setRam(laptop.getRam());
        Laptop.setProcessor(laptop.getProcessor());
        return Laptop;
    }

    private List<Laptop> mapLaptopsToDTOs(List<Laptop> laptops) {
        return laptops.stream()
                .map(this::mapLaptopToDTO)
                .collect(Collectors.toList());
    }

    private Laptop mapDTOToLaptop(Laptop Laptop) {
        Laptop laptop = new Laptop();
        laptop.setName(Laptop.getName());
        laptop.setPrice(Laptop.getPrice());
        laptop.setBrand(Laptop.getBrand());
        laptop.setStorage(Laptop.getStorage());
        laptop.setRam(Laptop.getRam());
        laptop.setProcessor(Laptop.getProcessor());
        return laptop;
    }
    
    @Override
    public List<Laptop> searchLaptops(String name, Double price, String brand) {
        List<Laptop> laptops = laptopRepository.findByNameOrPriceOrBrand(name, price, brand);
        return mapLaptopsToDTOs(laptops);
    }
}
