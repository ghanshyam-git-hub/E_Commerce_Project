package dev.ghanshyam.productservice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateProductDto {
    String title;
    double price;
    String description;
    String imageurl;
    String category;
}
