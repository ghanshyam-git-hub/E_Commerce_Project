package dev.ghanshyam.productservice.repositories;

import dev.ghanshyam.productservice.dto.AddProductDto;
import dev.ghanshyam.productservice.models.Category;
import dev.ghanshyam.productservice.models.Price;
import dev.ghanshyam.productservice.models.Products;
import jakarta.persistence.CascadeType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
/*
This code snippet defines a Spring Data JPA repository interface for working with Product entities that
have UUID primary keys.
You don't need to provide an explicit implementation for this interface because Spring Data JPA will
automatically create one for you at runtime(create a bean). The repository will have all the standard CRUD
operations and can be easily used in your service layer to interact with the database.
 */
@Repository // worked without this also. Bean was created without this also- Spring Data JPA (and other Spring Data modules) have a special mechanism for automatically detecting repository interfaces.
//If your interface extends JpaRepository, CrudRepository, or other repository interfaces provided by Spring Data, it will be automatically recognized and a bean will be created for it.
public interface ProductRepository extends JpaRepository<Products, UUID> {
    Products findByPid(long pid);

    @Query("select p from Products p")
    List<Products> findAllProducts();

    @Query("select p from Products p where p.category.category_name=:category_name")
    List<Products> findAllProductsByCategory(String category_name);

    @Transactional
    void deleteProductsByPid(long id);

    @Transactional
    @Modifying
    @Query("update Products p set p.title=:title, p.description=:description , p.imageurl=:imageurl where p.pid=:pid")
    void updateProductsByPid(long pid,String title, String description, String imageurl);

    @Transactional
    @Modifying
    @Query("update Price pri set pri.price=:price where pri = (select p.price from Products p where p.pid=:pid)")
    void updatePrice(long pid,double price);

    @Transactional
    @Modifying
    @Query("update Category c set c.category_name=:category_name where c = (select p.category from Products p where p.pid=:pid)")
    void updateCategory(long pid, String category_name);

//    String getByTitle(String title);
//    List<Products> getByCategory(Category category);
//    Products getByCategoryAndDescriptionAndImageurlAndTitle(Category category,String description, String imageurl,String title);
//    Products getByPid(long id);
//
//    List<Products> findAllByPrice_Currency(String currency);
//    List<Products> findProductsByCategory(Category category);
//
//        @Query(value = "select * from products where title = :mytitle ", nativeQuery = true)
//    List<Products> findByTitle(String mytitle);
//
    @Query("select p from Products p where p.title= :mytitle")
    List<Products> findByTitle(String mytitle);

}
