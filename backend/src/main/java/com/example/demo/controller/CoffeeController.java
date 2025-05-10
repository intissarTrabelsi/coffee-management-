package com.example.demo.controller;

import com.example.demo.model.Coffee;
import com.example.demo.repository.CoffeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/coffees")
public class CoffeeController {
    @Autowired
    private CoffeeRepository coffeeRepository;

    @GetMapping
    public List<Coffee> getAllCoffees() {
        return coffeeRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Coffee> getCoffeeById(@PathVariable Long id) {
        return coffeeRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Coffee createCoffee(@RequestBody Coffee coffee) {
        return coffeeRepository.save(coffee);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Coffee> updateCoffee(@PathVariable Long id, @RequestBody Coffee coffeeDetails) {
        return coffeeRepository.findById(id)
                .map(coffee -> {
                    coffee.setName(coffeeDetails.getName());
                    coffee.setDescription(coffeeDetails.getDescription());
                    coffee.setPrice(coffeeDetails.getPrice());
                    coffee.setStock(coffeeDetails.getStock());
                    return ResponseEntity.ok(coffeeRepository.save(coffee));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCoffee(@PathVariable Long id) {
        return coffeeRepository.findById(id)
                .map(coffee -> {
                    coffeeRepository.delete(coffee);
                    return ResponseEntity.ok().build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
} 