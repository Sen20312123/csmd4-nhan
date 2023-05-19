package com.example.model;


import com.example.model.DTO.ProductDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "products")
public class Product extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "product_name" , nullable = false)
    private String productName;

    @Column(name = "price" , nullable = false)
    private BigDecimal price;

    @Column(name = "quantity" , nullable = false)
    private Long quantity;

    @Column(name = "description" , nullable = false)
    private String description;

    @ManyToOne
    @JoinColumn(name = "category_id" , referencedColumnName = "id" , nullable = false)
    private Category category;

    @OneToOne
    @JoinColumn(name = "product_media_id" , referencedColumnName = "id" , nullable = false)
    private ProductMedia productMedia;

    public ProductDTO toProductDTO(){
        return new ProductDTO()
                .setId(id)
                .setProductName(productName)
                .setPrice(price)
                .setQuantity(quantity)
                .setDescription(description)
                .setCategory(category)
                .setProductMedia(productMedia.toProductMediaDTO())
                ;
    }

}
