package dev.ghanshyam.productservice.controllers;

import dev.ghanshyam.productservice.dto.FakeStoreProductDto;
import dev.ghanshyam.productservice.dto.GenericProductDto;
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

    public ProductController(@Qualifier("fakeStoreProductService") ProductServices productServices) {
        this.productServices = productServices;
    }

    @GetMapping
    public ArrayList<GenericProductDto> getAllProducts(){
        return productServices.getAllProducts();
    }

    @GetMapping("{id}")
    public GenericProductDto getProductById(@PathVariable ("id") long id) throws NotFoundException {
            return productServices.getProductById(id);
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

    @GetMapping("categories")
    public List<String> getAllCategaories(){
        List<String> categories = new ArrayList<>();
        categories.add("electronics");
        categories.add("stationary");
        categories.add("beauty");
        categories.add("health");

        return categories;
    }

    @PostMapping
    public GenericProductDto add_product(@RequestBody FakeStoreProductDto fakeStoreProductDto){
        return productServices.add_product(fakeStoreProductDto);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<GenericProductDto> delete_product(@PathVariable ("id") Long id){
        ResponseEntity<GenericProductDto> response = new ResponseEntity<>(
                productServices.delete_product(id),
                HttpStatus.BAD_REQUEST
                );

        return response;

    }

    @PatchMapping("{id}")
    public GenericProductDto partial_update_product(@RequestBody FakeStoreProductDto fakeStoreProductDto, @PathVariable ("id") Long id){
        return productServices.partial_update_product(fakeStoreProductDto,id);
    }

    @PutMapping("{id}")
    public GenericProductDto full_update_product(@RequestBody FakeStoreProductDto fakeStoreProductDto, @PathVariable ("id") Long id){
        return productServices.full_update_product(fakeStoreProductDto,id);
    }
}
