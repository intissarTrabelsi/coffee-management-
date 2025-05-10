package com.example.demo.service;

import com.example.demo.dto.QuantityUpdateDTO;
import com.example.demo.dto.StockDTO;
import com.example.demo.entity.Stock;
import com.example.demo.repository.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StockService {

    @Autowired
    private StockRepository stockRepository;

    public List<StockDTO> getAllStockItems() {
        return stockRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public StockDTO getStockItem(Long id) {
        return stockRepository.findById(id)
                .map(this::convertToDTO)
                .orElseThrow(() -> new RuntimeException("Stock item not found"));
    }

    @Transactional
    public StockDTO createStockItem(StockDTO stockDTO) {
        Stock stock = convertToEntity(stockDTO);
        return convertToDTO(stockRepository.save(stock));
    }

    @Transactional
    public StockDTO updateStockItem(Long id, StockDTO stockDTO) {
        Stock existingStock = stockRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Stock item not found"));
        
        updateStockFromDTO(existingStock, stockDTO);
        return convertToDTO(stockRepository.save(existingStock));
    }

    @Transactional
    public void deleteStockItem(Long id) {
        stockRepository.deleteById(id);
    }

    @Transactional
    public StockDTO updateQuantity(Long id, QuantityUpdateDTO quantityUpdate) {
        Stock stock = stockRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Stock item not found"));
        
        stock.setQuantity(quantityUpdate.getQuantity());
        return convertToDTO(stockRepository.save(stock));
    }

    private StockDTO convertToDTO(Stock stock) {
        StockDTO dto = new StockDTO();
        dto.setId(stock.getId());
        dto.setName(stock.getName());
        dto.setCategory(stock.getCategory());
        dto.setQuantity(stock.getQuantity());
        dto.setUnit(stock.getUnit());
        dto.setReorderLevel(stock.getReorderLevel());
        dto.setLastUpdated(stock.getLastUpdated());
        return dto;
    }

    private Stock convertToEntity(StockDTO dto) {
        Stock stock = new Stock();
        stock.setName(dto.getName());
        stock.setCategory(dto.getCategory());
        stock.setQuantity(dto.getQuantity());
        stock.setUnit(dto.getUnit());
        stock.setReorderLevel(dto.getReorderLevel());
        return stock;
    }

    private void updateStockFromDTO(Stock stock, StockDTO dto) {
        stock.setName(dto.getName());
        stock.setCategory(dto.getCategory());
        stock.setQuantity(dto.getQuantity());
        stock.setUnit(dto.getUnit());
        stock.setReorderLevel(dto.getReorderLevel());
    }
} 