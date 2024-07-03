package dev.ghanshyam.productservice.models;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.*;

import java.util.List;
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Category extends BaseModel {

    private String category_name;

    @OneToMany(mappedBy = "category", cascade = CascadeType.REMOVE)
    private List<Products> productsList;

}
