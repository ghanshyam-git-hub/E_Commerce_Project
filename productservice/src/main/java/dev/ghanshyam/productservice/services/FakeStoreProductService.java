package dev.ghanshyam.productservice.services;

import dev.ghanshyam.productservice.dto.*;
import dev.ghanshyam.productservice.exceptions.NotFoundException;
import dev.ghanshyam.productservice.models.Products;
import dev.ghanshyam.productservice.repositories.ProductRepository;
import dev.ghanshyam.productservice.thirdPartyClients_FakeStore.FakeStoreServiceClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.ResponseExtractor;

import java.util.ArrayList;
import java.util.List;

@Primary
@Service
@Qualifier("FakeStoreProductService")
public class FakeStoreProductService implements ProductServices{
    FakeStoreServiceClient fakeStoreServiceClient;

    @Autowired   // no need to pass it in the constructor then like below
    ProductRepository productRepository;

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
//        List<Products> productsList = productRepository.findAllByPrice_Currency("Dollar");
//        for(Products product : productsList){
//            genericProductList.add(convertProductToGenericDTO(product));
//        }
        return genericProductList;
    }

    @Override
    public List<CategoriesDto> getAllCategories() {
        return List.of();
    }

    @Override
    public List<GenericProductDto> getAllProductsByCategory(String category_name) {
        return List.of();
    }

    @Override
    public GenericProductDto add_product(AddProductDto addProductDto) {
        return null;
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



    @Override
    public List<GenericProductDto> getProductByTitle(String title) {
        List<Products> productslist = productRepository.findByTitle(title);
        List<GenericProductDto> genericProductDtoList = new ArrayList<>();
        for(Products product : productslist){
            genericProductDtoList.add(convertProductToGenericDTO(product));
        }
        return genericProductDtoList;
    }

    public void delete_product(Long id){
         convertToGenericProduct(fakeStoreServiceClient.delete_product(id));
    }

    @Override
    public void full_update_product(UpdateProductDto updateProductDto, long id) {

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

    public GenericProductDto convertProductToGenericDTO (Products products){
        GenericProductDto genericProductDto = new GenericProductDto();
        genericProductDto.setId(products.getPid());
        genericProductDto.setTitle(products.getTitle());
        genericProductDto.setDescription(products.getDescription());
        genericProductDto.setPrice(products.getPrice().getPrice());
        genericProductDto.setCategory(products.getCategory().getCategory_name());
        genericProductDto.setImage(products.getImageurl());
        return genericProductDto;
    }
}
