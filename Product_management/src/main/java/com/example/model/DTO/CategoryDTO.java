package com.example.model.DTO;

import com.example.model.Category;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CategoryDTO {

    private Long id;

    private String categoryName;

    public Category toCategory() {
        return new Category(this.id, this.categoryName);
    }
}
