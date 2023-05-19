package com.example.service.productMedia;
import com.example.model.ProductMedia;
import com.example.repository.ProductMediaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ProductMediaServiceIml implements IProductMediaService {
    @Autowired
    private ProductMediaRepository productMediaRepository;
    @Override
    public List<ProductMedia> findAll() {
        return productMediaRepository.findAll();
    }

    @Override
    public Optional<ProductMedia> findById(String id) {
        return productMediaRepository.findById(id);
    }

    @Override
    public Boolean existById(String id) {
        return productMediaRepository.existsById(id);
    }

    @Override
    public ProductMedia save(ProductMedia productMedia) {
        return null;
    }

    @Override
    public void delete(ProductMedia productMedia) {
        productMediaRepository.delete(productMedia);
    }

    @Override
    public void deleteById(String id) {
        productMediaRepository.deleteById(id);
    }
}
