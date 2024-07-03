package dev.ghanshyam.productservice.services;

import dev.ghanshyam.productservice.dto.*;
import dev.ghanshyam.productservice.exceptions.NotFoundException;

import java.util.ArrayList;
import java.util.List;

public interface ProductServices {
    public GenericProductDto getProductById(long id) throws NotFoundException;
    public ArrayList<GenericProductDto> getAllProducts();
    public List<CategoriesDto>getAllCategories();
    public List<GenericProductDto>getAllProductsByCategory(String category_name);
    public GenericProductDto add_product(AddProductDto addProductDto);
    public void delete_product(Long id);
    public void full_update_product(UpdateProductDto updateProductDto, long id);

    public GenericProductDto partial_update_product(FakeStoreProductDto fakeStoreProductDto,Long id);


    public List<GenericProductDto> getProductByTitle(String title);
}

