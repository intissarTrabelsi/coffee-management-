package com.example.demo.controller;

import com.example.demo.dto.QuantityUpdateDTO;
import com.example.demo.dto.StockDTO;
import com.example.demo.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/stock")
@CrossOrigin(origins = "http://localhost:4200")
public class StockController {

    @Autowired
    private StockService stockService;

    @GetMapping
    public ResponseEntity<List<StockDTO>> getAllStockItems() {
        return ResponseEntity.ok(stockService.getAllStockItems());
    }

    @GetMapping("/{id}")
    public ResponseEntity<StockDTO> getStockItem(@PathVariable Long id) {
        return ResponseEntity.ok(stockService.getStockItem(id));
    }

    @PostMapping
    public ResponseEntity<StockDTO> createStockItem(@RequestBody StockDTO stockDTO) {
        return ResponseEntity.ok(stockService.createStockItem(stockDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<StockDTO> updateStockItem(@PathVariable Long id, @RequestBody StockDTO stockDTO) {
        return ResponseEntity.ok(stockService.updateStockItem(id, stockDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStockItem(@PathVariable Long id) {
        stockService.deleteStockItem(id);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/{id}/quantity")
    public ResponseEntity<StockDTO> updateQuantity(@PathVariable Long id, @RequestBody QuantityUpdateDTO quantityUpdate) {
        return ResponseEntity.ok(stockService.updateQuantity(id, quantityUpdate));
    }
} 