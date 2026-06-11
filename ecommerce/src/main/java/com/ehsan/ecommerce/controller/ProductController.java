
package com.ehsan.ecommerce.controller;

import com.ehsan.ecommerce.dto.ProductRequestDTO;
import com.ehsan.ecommerce.dto.ProductResponseDTO;
import com.ehsan.ecommerce.entity.Product;
import com.ehsan.ecommerce.service.ProductService;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

        import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    public Product addProduct(@Valid @RequestBody Product product) {
        return productService.addProduct(product);
    }

    @GetMapping
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("/{id}")
    public Product getProductById(@PathVariable Long id) {
        return productService.getProductById(id);
    }

    @PutMapping("/{id}")
    public Product updateProduct(@PathVariable Long id,
                                 @Valid @RequestBody Product product) {
        return productService.updateProduct(id, product);
    }

    @DeleteMapping("/{id}")
    public String deleteProduct(@PathVariable Long id) {

        productService.deleteProduct(id);

        return "Product Deleted Successfully";
    }
    @GetMapping("/search")
    public List<Product> searchProduct(
            @RequestParam String name) {

        return productService.searchProduct(name);
    }
    @GetMapping("/page")
    public Page<Product> getProductsWithPagination(
            @RequestParam int page,
            @RequestParam int size) {

        return productService.getProductsWithPagination(page, size);
    }
    @GetMapping("/sort")
    public List<Product> getProductsSorted(
            @RequestParam String field) {

        return productService.getProductsSorted(field);
    }
    @PostMapping("/dto")
    public ProductResponseDTO addProductDTO(
            @Valid @RequestBody ProductRequestDTO dto) {

        return productService.addProductDTO(dto);
    }
}