package dev.ghanshyam.productservice.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Products extends BaseModel{

    private String title;
    private double price;
    private String description;
    private String imageurl;
    private Category category;



}
