package com.example.model;


import com.example.model.DTO.CategoryDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "category")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "category_name" , nullable = false)
    private String categoryName;

    public CategoryDTO toDTO() {
        return new CategoryDTO(this.id, this.categoryName);
    }

}
