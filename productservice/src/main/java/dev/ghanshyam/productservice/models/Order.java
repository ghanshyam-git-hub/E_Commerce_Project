package dev.ghanshyam.productservice.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity(name = "orders") // this is becoz in MySQL order is a reserved keyword, hence without renaming this table it was giving an error
@Setter
@Getter
public class Order extends BaseModel {
    @ManyToMany
    @JoinTable(
        name = "product_orders",
        joinColumns = @JoinColumn (name = "order_id"),
        inverseJoinColumns = @JoinColumn(name = "product_id")
    )
    private List<Products> products;

}
