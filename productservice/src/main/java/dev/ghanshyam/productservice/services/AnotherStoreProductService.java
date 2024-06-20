package dev.ghanshyam.productservice.services;

import dev.ghanshyam.productservice.dto.FakeStoreProductDto;
import dev.ghanshyam.productservice.dto.GenericProductDto;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class AnotherStoreProductService implements ProductServices{
    @Override
    public GenericProductDto getProductById(long id) {
        return null;
    }

    @Override
    public GenericProductDto add_product(FakeStoreProductDto fakeStoreProductDto) {
        return null;
    }

    public ArrayList<GenericProductDto> getAllProducts(){return null;}

    @Override
    public GenericProductDto partial_update_product(FakeStoreProductDto fakeStoreProductDto, Long id) {
        return null;
    }

    public GenericProductDto add_product(GenericProductDto genericProductDto){
        return null;
    }

    public GenericProductDto partial_update_product(GenericProductDto genericProductDto,Long id){
        return null;
    }

    public GenericProductDto full_update_product(GenericProductDto genericProductDto,Long id){return null;}

    public GenericProductDto delete_product(Long id){return null;}

    @Override
    public GenericProductDto full_update_product(FakeStoreProductDto fakeStoreProductDto, Long id) {
        return null;
    }
}
