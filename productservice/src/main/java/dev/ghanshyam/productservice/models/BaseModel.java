package dev.ghanshyam.productservice.models;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import org.hibernate.annotations.GenericGenerator;

import java.util.UUID;

@MappedSuperclass
public class BaseModel {
    @Id
    @GeneratedValue(generator = "abc_generator")
    @GenericGenerator(name = "abc_generator" , strategy = "uuid2")
    @Column(name = "id", columnDefinition = "binary(16)" , nullable = false,updatable = false)
    private UUID uuid;
}
