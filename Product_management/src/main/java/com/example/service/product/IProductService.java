package com.example.service.product;

import com.example.model.Category;
import com.example.model.DTO.*;
import com.example.model.Product;
import com.example.service.IGeneralService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface IProductService extends IGeneralService<Product> {
    List<ProductDTO> findAllProductDTOs();
    ProductCreateResDTO create(Category category, ProductCreateReqDTO productCreateReqDTO);
    List<ProductDTO> getAllProductsDTODeletedIsFalse();
    List<ProductResDTO> getAllProductResDTODeletedIsFalse();

    Page<ProductResDTO> findAllPagesByDeletedIsFalse(Pageable pageable);

    Page<ProductResDTO> findAllPagesByKeySearchAndDeletedIsFalse(String keySearch, Pageable pageable);

    Optional<ProductResDTO> getProductResDTOByIdDeletedIsFalse(Long id);

    List<Product> getProductByDeletedIsFalseAndProductNameLike(String name);
    List<ProductResDTO> getProductByDeletedIsFalseAndNameLike(String name);
    ProductUpdateResDTO update(Product product);
    ProductUpdateResDTO updateWithMedia(MultipartFile MediaFile,Long id, ProductUpdateReqDTO productUpdateReqDTO) throws IOException;

    Page<ProductResDTO> findAllProductPageable(String search, Pageable pageable);

    List<ProductResDTO> findAllProductWithSearch(String search);



}
