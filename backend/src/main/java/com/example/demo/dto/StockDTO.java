package com.example.demo.dto;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class StockDTO {
    private Long id;
    private String name;
    private String category;
    private Integer quantity;
    private String unit;
    private Integer reorderLevel;
    private LocalDateTime lastUpdated;
} 