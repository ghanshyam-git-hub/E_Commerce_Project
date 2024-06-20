package dev.ghanshyam.productservice.services;

import dev.ghanshyam.productservice.dto.FakeStoreProductDto;
import dev.ghanshyam.productservice.dto.GenericProductDto;
import dev.ghanshyam.productservice.exceptions.NotFoundException;

import java.util.ArrayList;

public interface ProductServices {
    public GenericProductDto getProductById(long id) throws NotFoundException;
    public GenericProductDto add_product(FakeStoreProductDto fakeStoreProductDto);
    public ArrayList<GenericProductDto> getAllProducts();
    public GenericProductDto partial_update_product(FakeStoreProductDto fakeStoreProductDto,Long id);
    public GenericProductDto delete_product(Long id);
    public GenericProductDto full_update_product(FakeStoreProductDto fakeStoreProductDto,Long id);
}

