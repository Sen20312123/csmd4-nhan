package com.example.model.DTO;

import com.example.model.Category;

import java.math.BigDecimal;

public interface IProductDTO {

    Long getId();

    String getProductName();

    BigDecimal getPrice();

    Long getQuantity();

    String getDescription();

    Category getCategory();

    ProductMediaDTO getProductMedia();
}
