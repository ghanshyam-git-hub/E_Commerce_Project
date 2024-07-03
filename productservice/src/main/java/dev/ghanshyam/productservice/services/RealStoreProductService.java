package dev.ghanshyam.productservice.services;

import dev.ghanshyam.productservice.dto.*;
import dev.ghanshyam.productservice.exceptions.NotFoundException;
import dev.ghanshyam.productservice.models.Category;
import dev.ghanshyam.productservice.models.Price;
import dev.ghanshyam.productservice.models.Products;
import dev.ghanshyam.productservice.repositories.CategoryRepository;
import dev.ghanshyam.productservice.repositories.ProductRepository;
import dev.ghanshyam.productservice.thirdPartyClients_FakeStore.FakeStoreServiceClient;
import jakarta.persistence.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Qualifier("RealStoreProductService")
public class RealStoreProductService implements ProductServices{
    @Autowired
    ProductRepository productRepository;
    @Autowired
    CategoryRepository categoryRepository;

    @Override
    public GenericProductDto getProductById(long id) throws NotFoundException {
        return convertToGenericProduct(productRepository.findByPid(id));
    }

    public ArrayList<GenericProductDto> getAllProducts(){
        ArrayList<GenericProductDto> productDtoList = new ArrayList<>();
        List<Products> productsList = productRepository.findAllProducts();
        for(Products p : productsList){
            productDtoList.add(convertToGenericProduct(p));
        }
        return productDtoList;
    }

    public List<CategoriesDto>getAllCategories(){
        List<CategoriesDto> categoriesDtoList = new ArrayList<>();
        List<Category>categories = categoryRepository.findAllCategories();
        for(Category c : categories){
            categoriesDtoList.add(convertToCategoriesDto(c));
        }
        return categoriesDtoList;
    }

    public List<GenericProductDto>getAllProductsByCategory(String category_name){
        List<GenericProductDto> genericProductDtoList = new ArrayList<>();
        List<Products> productslist = productRepository.findAllProductsByCategory(category_name);
        for(Products p : productslist){
            genericProductDtoList.add(convertToGenericProduct(p));
        }
        return genericProductDtoList;
    }

    @Override
    public GenericProductDto add_product(AddProductDto addProductDto)
    {
        Products products = new Products();
        products.setTitle(addProductDto.getTitle());
        products.setDescription(addProductDto.getDescription());

        List<Products> producListInCategory = productRepository.findAllProductsByCategory(addProductDto.getCategory());
        products.setCategory(new Category(addProductDto.getCategory(),producListInCategory));

        products.setPid(productRepository.findAllProducts().size()+1);
        products.setImageurl(addProductDto.getImageurl());
        products.setPrice(new Price(addProductDto.getPrice(),"Rupee"));

        productRepository.save(products);
       return convertToGenericProduct(products);
    }

    public void delete_product(Long id){
        //GenericProductDto genericProductDto = new GenericProductDto();
        productRepository.deleteProductsByPid(id);
    }

    public void full_update_product(UpdateProductDto updateProductDto, long id){
        String title = updateProductDto.getTitle();
        String description = updateProductDto.getDescription();
        String imageurl = updateProductDto.getImageurl();
        Double price = updateProductDto.getPrice();
        String category = updateProductDto.getCategory();
        long pid = id;

        productRepository.updateProductsByPid( pid, title,  description,  imageurl);
        productRepository.updatePrice( pid, price);
        productRepository.updateCategory(pid,category);

    }

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


    @Override
    public List<GenericProductDto> getProductByTitle(String title) {
        return List.of();
    }


    public GenericProductDto convertToGenericProduct(Products products){
        GenericProductDto genericProductDto = new GenericProductDto();
        genericProductDto.setId(products.getPid());
        genericProductDto.setTitle(products.getTitle());
        genericProductDto.setPrice(products.getPrice().getPrice());
        genericProductDto.setDescription(products.getDescription());
        genericProductDto.setImage(products.getImageurl());
        genericProductDto.setCategory(products.getCategory().getCategory_name());

        return genericProductDto;
    }

    public CategoriesDto convertToCategoriesDto(Category category){
        CategoriesDto categoriesDto = new CategoriesDto();
        categoriesDto.setCategory_name(category.getCategory_name());
        return categoriesDto;
    }
}
