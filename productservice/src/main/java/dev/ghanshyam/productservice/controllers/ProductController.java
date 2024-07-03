package dev.ghanshyam.productservice.controllers;

import dev.ghanshyam.productservice.dto.*;
import dev.ghanshyam.productservice.exceptions.NotFoundException;
import dev.ghanshyam.productservice.services.ProductServices;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
    private ProductServices productServices;

    public ProductController(@Qualifier("RealStoreProductService") ProductServices productServices) {
        this.productServices = productServices;
    }

    @GetMapping("/{id}")
    public GenericProductDto getProductById(@PathVariable ("id") long id) throws NotFoundException {
            return productServices.getProductById(id);
    }

    @GetMapping
    public ArrayList<GenericProductDto> getAllProducts(){
        return productServices.getAllProducts();
    }

    @GetMapping("/categories")
    public List<CategoriesDto> getAllCategories(){
        return productServices.getAllCategories();
    }

    @GetMapping("/category/{category_name}")
    public List<GenericProductDto> getProductsByCategory(@PathVariable ("category_name") String category_name){
        return productServices.getAllProductsByCategory(category_name);
    }

// 2 ways to handle exceptions
    // 1st way is below - at the controller level use @ExceptionHandler for the methods handling a particular exception
   /* @ExceptionHandler(NotFoundException.class)
    private ResponseEntity<ExceptionDto> notfoundexception(NotFoundException n){
        ResponseEntity<ExceptionDto> response = new ResponseEntity(
                new ExceptionDto(HttpStatus.BAD_REQUEST, n.getMessage()), HttpStatus.BAD_REQUEST
                );

       return response;
    }*/

    //2nd way -  to move this method with @ExceptionHanler outside controller in a separate class and annotate that class as @ControllerAdvice



//    @PostMapping
//    public GenericProductDto add_product(@RequestBody FakeStoreProductDto fakeStoreProductDto){
//        return productServices.add_product(fakeStoreProductDto);
//    }

    @PostMapping
    public GenericProductDto addProduct(@RequestBody AddProductDto addProductDto){
        return productServices.add_product(addProductDto);
    }

    @DeleteMapping("/{id}")
    public void deleteProductById(@PathVariable("id") long id) throws NotFoundException {
        productServices.delete_product(id);
    }
//    @DeleteMapping("/{id}")
//    public ResponseEntity<GenericProductDto> delete_product(@PathVariable ("id") Long id){
//        ResponseEntity<GenericProductDto> response = new ResponseEntity<>(
//                productServices.delete_product(id),
//                HttpStatus.BAD_REQUEST
//                );
//
//        return response;
//
//    }

    @PatchMapping("/{id}")
    public GenericProductDto partial_update_product(@RequestBody FakeStoreProductDto fakeStoreProductDto, @PathVariable ("id") Long id){
        return productServices.partial_update_product(fakeStoreProductDto,id);
    }

//    @PutMapping("/{id}")
//    public GenericProductDto full_update_product(@RequestBody FakeStoreProductDto fakeStoreProductDto, @PathVariable ("id") Long id){
//        return productServices.full_update_product(fakeStoreProductDto,id);
//    }
    @PutMapping("/{id}")
    public void full_update_product(@RequestBody UpdateProductDto updateProductDto, @PathVariable("id") long id){
        productServices.full_update_product(updateProductDto,id);
    }
}
