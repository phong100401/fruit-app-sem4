package com.example.restapiprojectsem4.dto;

import com.example.restapiprojectsem4.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO{
    private Integer id;
    private String name;
    private String thumbnail;
    private int quantity;
    private double price;
    private String description;
    private String detail;
    private String status;
    private int categoryId;
    private CategoryDTO category;

    public static ProductDTO convertEntityToDTO(Product product){
        ProductDTO productDTO = new ProductDTO();
        CategoryDTO categoryDTO = CategoryDTO.convertEntityToDTO(product.getCategory());
        productDTO.setId(product.getId());
        productDTO.setName(product.getName());
        productDTO.setThumbnail(product.getThumbnail());
        productDTO.setPrice(product.getPrice());
        productDTO.setDescription(product.getDescription());
        productDTO.setQuantity(product.getQuantity());
        productDTO.setDetail(product.getDetail());
        if(product.getQuantity() == 0){
            productDTO.setStatus(product.getStatus());
        }else if(product.getQuantity() > 0){
            productDTO.setStatus(product.getStatus());
        }
        productDTO.setCategoryId(product.getCategoryId());
        productDTO.setCategory(categoryDTO);
        return productDTO;
    }
}
