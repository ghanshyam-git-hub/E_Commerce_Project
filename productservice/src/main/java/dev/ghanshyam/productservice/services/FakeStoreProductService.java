package dev.ghanshyam.productservice.services;

import dev.ghanshyam.productservice.dto.GenericProductDto;
import dev.ghanshyam.productservice.dto.FakeStoreProductDto;
import dev.ghanshyam.productservice.exceptions.NotFoundException;
import dev.ghanshyam.productservice.thirdPartyClients_FakeStore.FakeStoreServiceClient;
import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.ResponseExtractor;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;

@Primary
@Service
public class FakeStoreProductService implements ProductServices{
    FakeStoreServiceClient fakeStoreServiceClient;

    public FakeStoreProductService(FakeStoreServiceClient fakeStoreServiceClient) {
        this.fakeStoreServiceClient = fakeStoreServiceClient;
    }

    /*
    This method fetches the data from the Client class and returns the data in the format
     required by us - GenericProductDto
     */
    @Override
    public GenericProductDto getProductById(long id) throws NotFoundException {
        return convertToGenericProduct(fakeStoreServiceClient.getProductById(id));
    }

    public ArrayList<GenericProductDto> getAllProducts(){

        ArrayList<GenericProductDto> genericProductList = new ArrayList<>();
        for(FakeStoreProductDto productdto : fakeStoreServiceClient.getAllProducts()){
            genericProductList.add(convertToGenericProduct(productdto));
        }
        return genericProductList;
    }

    public GenericProductDto add_product(FakeStoreProductDto fakeStoreProductDto){
        return convertToGenericProduct(fakeStoreServiceClient.add_product(fakeStoreProductDto));
    }

    public GenericProductDto partial_update_product(FakeStoreProductDto fakeStoreProductDto,Long id){
        return convertToGenericProduct(fakeStoreServiceClient.partial_update_product(fakeStoreProductDto,id));
    }


    public GenericProductDto full_update_product(FakeStoreProductDto fakeStoreProductDto,Long id){
       return convertToGenericProduct(fakeStoreServiceClient.full_update_product(fakeStoreProductDto,id));
    }

    public GenericProductDto delete_product(Long id){
        return convertToGenericProduct(fakeStoreServiceClient.delete_product(id));
    }

    public GenericProductDto convertToGenericProduct(FakeStoreProductDto productdto){
        GenericProductDto product = new GenericProductDto();
        product.setId(productdto.getId());
        product.setTitle(productdto.getTitle());
        product.setPrice(productdto.getPrice());
        product.setDescription(productdto.getDescription());
        product.setImage(productdto.getImage());
        product.setCategory(productdto.getCategory());

        return product;
    }
}
