package dev.ghanshyam.productservice.dto;

import dev.ghanshyam.productservice.models.Category;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GenericProductDto {
    private Long id;
    private String title;
    private double price;
    private String description;
    private String image;
    private String category;

}
