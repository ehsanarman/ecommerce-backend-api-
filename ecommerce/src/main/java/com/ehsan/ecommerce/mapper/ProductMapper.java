package com.ehsan.ecommerce.mapper;

import com.ehsan.ecommerce.dto.ProductRequestDTO;
import com.ehsan.ecommerce.dto.ProductResponseDTO;
import com.ehsan.ecommerce.entity.Product;

public class ProductMapper {

    public static Product toEntity(ProductRequestDTO dto) {

        Product product = new Product();

        product.setName(dto.getName());
        product.setDescription(dto.getDescription());
        product.setPrice(dto.getPrice());
        product.setQuantity(dto.getQuantity());

        return product;
    }

    public static ProductResponseDTO toDTO(Product product) {

        return new ProductResponseDTO(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getPrice(),
                product.getQuantity()
        );
    }
}