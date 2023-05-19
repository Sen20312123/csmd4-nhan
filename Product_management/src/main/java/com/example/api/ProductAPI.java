package com.example.api;

import com.example.exception.DataInputException;
import com.example.exception.ResourceNotFoundException;
import com.example.model.Category;
import com.example.model.DTO.ProductResDTO;
import com.example.model.Product;
import com.example.model.ProductMedia;
import com.example.model.DTO.*;
import com.example.service.category.ICategoryService;
import com.example.service.product.IProductService;
import com.example.utils.AppUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/products")
public class ProductAPI {
    @Autowired
    private ICategoryService categoryService;

    @Autowired
    private IProductService productService;

    @Autowired
    private AppUtils appUtils;


    @GetMapping("/category")
    public ResponseEntity<?> getAllCategory() {
        List<Category> categories = categoryService.findAll();
        return new ResponseEntity<>(categories, HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<?> getAllProductByKeySearchPagingAndSorting(
            @RequestParam(value = "q", required = false ) String keySearch,
            @RequestParam(value = "sort", required = false) String sort,
            @RequestParam(value = "direction", required = false) String direction,
            @RequestParam(value = "page", required = false) Integer pageCurrent,
            @PageableDefault(sort = "id", direction = Sort.Direction.DESC, size = 5) Pageable pageable) {

        Sort sortable = null;
        if(direction.equals("asc")){
            sortable = Sort.by(sort).ascending();
        }else if(direction.equals("desc")){
            sortable = Sort.by(sort).descending();
        }
        if(!sort.equals("") && !direction.equals("")){
            pageable = PageRequest.of(pageCurrent, 5, sortable);
        }
        if(keySearch != null){
            keySearch = '%' + keySearch + '%';
        }
        Page<ProductResDTO> productResDTOS = productService.findAllPagesByKeySearchAndDeletedIsFalse(keySearch, pageable);
        return new ResponseEntity<>(productResDTOS, HttpStatus.OK);
    }


    @GetMapping("/{id}")
    public ResponseEntity<?> getProductById(@PathVariable Long id) {
        ProductResDTO productResDTO = productService
                .getProductResDTOByIdDeletedIsFalse(id)
                .orElseThrow(
                        ()-> new ResourceNotFoundException("Not found this product"));

        return new ResponseEntity<>(productResDTO, HttpStatus.OK);
    }

    @PostMapping()
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public ResponseEntity<?> create(@Validated ProductCreateReqDTO productCreateReqDTO, BindingResult bindingResult) {
        new ProductCreateReqDTO().validate(productCreateReqDTO, bindingResult);

        Optional<Category> categoryOptional = categoryService.findById(Long.parseLong(productCreateReqDTO.getCategoryId()));

        if (!categoryOptional.isPresent()) {
            throw new ResourceNotFoundException("Not found this category");
        }

        if (bindingResult.hasFieldErrors()) {
            return appUtils.mapErrorToResponse(bindingResult);
        }

        ProductCreateResDTO productCreateResDTO = productService.create(categoryOptional.get(), productCreateReqDTO);
        return new ResponseEntity<>(productCreateResDTO, HttpStatus.CREATED);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id,
                                    MultipartFile file,
                                    @Validated ProductUpdateReqDTO productUpdateReqDTO,
                                    BindingResult bindingResult
    ) throws IOException {
        new ProductUpdateReqDTO().validate(productUpdateReqDTO, bindingResult);

        Product productOptional = productService
                .findById(id)
                .orElseThrow(
                        () -> new ResourceNotFoundException("Not found this product to update")
                );

        Category categoryOptional = categoryService.findById(Long.parseLong(productUpdateReqDTO.getCategoryId()))
                .orElseThrow(
                        () -> new DataInputException("Not found this category")
                );

        if (bindingResult.hasFieldErrors()) {
            return appUtils.mapErrorToResponse(bindingResult);
        }

        ProductUpdateResDTO productUpdateResDTO;

        if (file == null) {
            ProductMedia productMedia = productOptional.getProductMedia();
            ProductDTO productDTO = productUpdateReqDTO.toProductDTO(categoryOptional, productMedia);
            Product product = productDTO
                    .toProduct()
                    .setId(id);
            productUpdateResDTO = productService.update(product);
        } else {
            productUpdateResDTO = productService.updateWithMedia(file, id, productUpdateReqDTO);
        }
        return new ResponseEntity<>(productUpdateResDTO, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public ResponseEntity<?> deleteProduct(@PathVariable Long id) {
        Product product = productService
                .findById(id)
                .orElseThrow(
                        () -> new ResourceNotFoundException("Not found this product to delete")
                );

        productService.delete(product);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/search/q={key}")
    public ResponseEntity<?> searchByProductName(@PathVariable("key") String key) {
        key = "%" + key + "%";
        List<Product> products = productService.getProductByDeletedIsFalseAndProductNameLike(key);
        List<ProductDTO> productDTOS = new ArrayList<>();

        products.forEach(item -> productDTOS.add(item.toProductDTO()));

        return new ResponseEntity<>(productDTOS, HttpStatus.OK);
    }

}
