package com.example.repository;

import com.example.model.DTO.IProductDTO;
import com.example.model.DTO.ProductDTO;
import com.example.model.DTO.ProductResDTO;
import com.example.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {



    @Query("SELECT NEW com.example.model.DTO.ProductResDTO (" +
                "pro.id, " +
                "pro.productName, " +
                "pro.price, " +
                "pro.quantity, " +
                "pro.description, " +
                "pra.id," +
                "pra.fileFolder," +
                "pra.fileName," +
                "pra.fileUrl," +
                "pro.category" +
            ") " +
            "FROM Product AS pro " +
            "JOIN ProductMedia AS pra " +
            "ON pro.productMedia = pra " +
            "WHERE (pro.productName LIKE :search " +
            "OR pro.description LIKE :search " +
            "OR pro.category.categoryName LIKE :search) AND pro.deleted = FALSE "
    )
    Page<ProductResDTO> findAllProductPageable(String search, Pageable pageable);

    @Query("SELECT NEW com.example.model.DTO.ProductResDTO (" +
            "pro.id, " +
            "pro.productName, " +
            "pro.price, " +
            "pro.quantity, " +
            "pro.description, " +
            "pra.id," +
            "pra.fileFolder," +
            "pra.fileName," +
            "pra.fileUrl," +
            "pro.category" +
            ") " +
            "FROM Product AS pro " +
            "JOIN ProductMedia AS pra " +
            "ON pro.productMedia = pra " +
            "WHERE (pro.productName LIKE :search " +
            "OR pro.description LIKE :search " +
            "OR pro.category.categoryName LIKE :search) AND pro.deleted = FALSE"
    )
    List<ProductResDTO> findAllProductWithSearch(String search);

    List<Product> findAllByDeletedIsFalse(Pageable pageable);

    List<Product> getProductByDeletedIsFalse();

    @Query("SELECT NEW com.example.model.DTO.ProductResDTO (" +
                "pro.id, " +
                "pro.productName, " +
                "pro.price, " +
                "pro.quantity, " +
                "pro.description, " +
                "pra.id," +
                "pra.fileFolder," +
                "pra.fileName," +
                "pra.fileUrl," +
                "pro.category" +
            ") " +
            "FROM Product AS pro " +
            "JOIN ProductMedia AS pra " +
            "ON pro.productMedia = pra"
    )
    Page<ProductResDTO> findAllPagesByDeletedIsFalse(Pageable pageable);


    @Query("SELECT NEW com.example.model.DTO.ProductResDTO (" +
                "pro.id, " +
                "pro.productName, " +
                "pro.price, " +
                "pro.quantity, " +
                "pro.description, " +
                "pra.id," +
                "pra.fileFolder," +
                "pra.fileName," +
                "pra.fileUrl," +
                "pro.category" +
            ") " +
            "FROM Product AS pro " +
            "JOIN ProductMedia AS pra " +
            "ON pro.productMedia = pra " +
            "WHERE ((:keySearch is null) or (pro.productName LIKE :keySearch " +
            "OR pro.description LIKE :keySearch " +
            "OR pro.category.categoryName LIKE :keySearch )) AND pro.deleted = FALSE"
    )
    Page<ProductResDTO> findAllPagesByKeySearchAndDeletedIsFalse(@Param("keySearch") String keySearch, Pageable pageable);


    @Query("SELECT NEW com.example.model.DTO.ProductDTO (" +
                "p.id, " +
                "p.productName, " +
                "p.price, " +
                "p.quantity, " +
                "p.description, " +
                "p.category" +
            ") " +
            "FROM Product AS p " +
            "WHERE p.deleted = false"
    )
    List<ProductDTO> getAllProductsDTODeletedIsFalse();

    @Query("SELECT NEW com.example.model.DTO.ProductResDTO (" +
                "p.id, " +
                "p.productName, " +
                "p.price, " +
                "p.quantity, " +
                "p.description, " +
                "pa.id," +
                "pa.fileFolder, " +
                "pa.fileName, " +
                "pa.fileUrl, " +
                "p.category" +
            ") " +
            "FROM Product AS p " +
            "LEFT JOIN ProductMedia AS pa " +
            "ON p.productMedia = pa " +
            "WHERE p.deleted = false "
    )
    List<ProductResDTO> getAllProductResDTODeletedIsFalse();

    @Query(value = "SELECT NEW com.example.model.DTO.ProductResDTO (" +
                "p.id, " +
                "p.productName, " +
                "p.price, " +
                "p.quantity, " +
                "p.description, " +
                "pa.id," +
                "pa.fileFolder, " +
                "pa.fileName, " +
                "pa.fileUrl, " +
                "p.category" +
            ") " +
            "FROM Product AS p " +
            "LEFT JOIN ProductMedia AS pa " +
            "ON p.productMedia = pa " +
            "WHERE p.deleted = false " +
            "AND p.id = :id"
    )
    Optional<ProductResDTO> getProductResDTOByIdDeletedIsFalse(@Param("id") Long id);

    @Query("SELECT NEW com.example.model.DTO.ProductResDTO (" +
                "p.id, " +
                "p.productName, " +
                "p.price, " +
                "p.quantity, " +
                "p.description, " +
                "pa.id," +
                "pa.fileFolder, " +
                "pa.fileName, " +
                "pa.fileUrl, " +
                "p.category" +
            ") " +
            "FROM Product AS p " +
            "LEFT JOIN ProductMedia AS pa " +
            "ON p.productMedia = pa " +
            "WHERE p.deleted = false " +
            "AND p.productName LIKE CONCAT('%',:name, '%') "
    )
    List<ProductResDTO> getProductByDeletedIsFalseAndNameLike(String name);

    List<Product> getProductByDeletedIsFalseAndProductNameLike(String name);



}
