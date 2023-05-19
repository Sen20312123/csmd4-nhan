package com.example.model.DTO;

import com.example.model.Category;
import com.example.model.ProductMedia;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ProductResDTO {
    private Long id;
    private String productName;
    private BigDecimal price;
    private Long quantity;
    private String description;

        private ProductMediaDTO productMedia;
    private String mediaId;
    private String fileFolder;
    private String fileName;
    private String fileUrl;
    private CategoryDTO category;

    public ProductResDTO(Long id, String productName, BigDecimal price, Long quantity, String description,
                         String mediaId, String fileFolder, String fileName, String fileUrl, Category category) {
        this.id = id;
        this.productName = productName;
        this.price = price;
        this.quantity = quantity;
        this.description = description;
        this.mediaId = mediaId;
        this.fileFolder = fileFolder;
        this.fileName = fileName;
        this.fileUrl = fileUrl;
        this.category = category.toDTO();
    }


}
