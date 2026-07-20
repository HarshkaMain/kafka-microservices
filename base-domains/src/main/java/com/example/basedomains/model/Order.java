package com.example.basedomains.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Order {


    private Long id;


    private String customerName;


    private String product;


    private Integer quantity;


}