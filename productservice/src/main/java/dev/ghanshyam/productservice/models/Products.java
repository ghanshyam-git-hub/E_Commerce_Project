package dev.ghanshyam.productservice.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Products extends BaseModel{
    private long pid;

    private String title;
    private String description;
    private String imageurl;

    @ManyToOne(cascade = {CascadeType.PERSIST})
    @JoinColumn
    private Category category; // instead of having category_id (which we do in tables, in classes we use the objects itself not the ids
    // then we have to give relation to the 2 objects which we do by @ManyToOne etc

    @OneToOne(cascade = CascadeType.PERSIST)
    private Price price;




}
