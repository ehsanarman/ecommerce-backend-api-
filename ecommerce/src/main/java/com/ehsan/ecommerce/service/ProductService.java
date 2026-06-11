
package com.ehsan.ecommerce.service;

import com.ehsan.ecommerce.dto.ProductRequestDTO;
import com.ehsan.ecommerce.dto.ProductResponseDTO;
import com.ehsan.ecommerce.entity.Product;
import com.ehsan.ecommerce.exception.ResourceNotFoundException;
import com.ehsan.ecommerce.mapper.ProductMapper;
import com.ehsan.ecommerce.repository.ProductRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product addProduct(Product product) {
        return productRepository.save(product);
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product getProductById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Product Not Found With Id : " + id));
    }
    public Product updateProduct(Long id, Product product) {

        Product existingProduct = getProductById(id);

        existingProduct.setName(product.getName());
        existingProduct.setDescription(product.getDescription());
        existingProduct.setPrice(product.getPrice());
        existingProduct.setQuantity(product.getQuantity());

        return productRepository.save(existingProduct);
    }

    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }

    public List<Product> searchProduct(String name) {
        return productRepository.findByNameContainingIgnoreCase(name);
    }
    public Page<Product> getProductsWithPagination(int page, int size) {

        Pageable pageable = PageRequest.of(page, size);

        return productRepository.findAll(pageable);
    }
    public List<Product> getProductsSorted(String field) {

        return productRepository.findAll(
                Sort.by(Sort.Direction.ASC, field));
    }
    public ProductResponseDTO addProductDTO(ProductRequestDTO dto) {

        Product product = ProductMapper.toEntity(dto);

        Product savedProduct = productRepository.save(product);

        return ProductMapper.toDTO(savedProduct);
    }
}