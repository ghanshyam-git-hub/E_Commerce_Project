package dev.ghanshyam.productservice.repositories;

import dev.ghanshyam.productservice.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface CategoryRepository extends JpaRepository<Category, UUID> {
    @Query("select c from Category c")
    List<Category> findAllCategories();
}
