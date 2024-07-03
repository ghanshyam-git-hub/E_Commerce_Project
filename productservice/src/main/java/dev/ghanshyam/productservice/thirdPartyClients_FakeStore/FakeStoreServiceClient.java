package dev.ghanshyam.productservice.thirdPartyClients_FakeStore;

import dev.ghanshyam.productservice.dto.FakeStoreProductDto;
import dev.ghanshyam.productservice.dto.GenericProductDto;
import dev.ghanshyam.productservice.exceptions.NotFoundException;
import dev.ghanshyam.productservice.services.FakeStoreProductService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.ResponseExtractor;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
/*
The 3rd party services provide us with apis and also the Client Classes and methods to send the requests to these apis
like Razorpay provides the api links and also gives us the CLient classes containing the methods to send requests to thos apis
So this class serves that purpose - the purpose of actually sending the requests to the apis
 */
@Component

public class FakeStoreServiceClient {
    /*
    Setting the api urls. @Value fetches the constants set in the application.properties file.
    This is used to facilitate change in 3rd party apis in future, it will be easy to just change the values in the application.properties file
     */
    @Value("${fakestore.api.url}")
    private String fakeStoreApiUrl;

    @Value("${fakestore.api.product.path}")
    private String fakeStoreProductPath;

    private String productsUrl;

    private String specificProductsUrl;

    private RestTemplateBuilder restTemplateBuilder; // not required now as we are craeting a RestTemplate bean in the ApplicationConfiguration.java file in config folder

    @Autowired
    private RestTemplate restTemplate;

    public FakeStoreServiceClient(RestTemplateBuilder restTemplateBuilder,
                                  @Value("${fakestore.api.url}") String fakeStoreApiUrl,
                                  @Value("${fakestore.api.product.path}") String fakeStoreProductPath) {
        this.restTemplateBuilder = restTemplateBuilder;
        this.productsUrl = fakeStoreApiUrl + fakeStoreProductPath;
        this.specificProductsUrl = fakeStoreApiUrl + fakeStoreProductPath + "/{id}";
    }

    public FakeStoreProductDto getProductById(long id) throws NotFoundException
    {
        // RestTemplate object is now autowired . This bean is created in the @ Configuration file which is ApplicationCongiguration.java in config folder in this project
        //RestTemplate restTemplate = restTemplateBuilder.build(); // for now we are not configuring anything here like setting parameters etc
        ResponseEntity<FakeStoreProductDto> response =
                restTemplate.getForEntity(specificProductsUrl, FakeStoreProductDto.class,id);  // id is the uri variable , if we would have {id}/{name} int he uri - we would have given id,"naman"  in this method.

        FakeStoreProductDto productdto = response.getBody(); // getForEntity method we had specified that the response object we want is of productDto class
        // we were previously wanting to capture this DTO in Products, but category was another object in that, so we made this another GenericDTO class with same datamembers as Products except the category is String instead of Categorgy object
        if(productdto==null){
            throw new NotFoundException("This product id is not found");
        }
        return productdto;
    }

    public FakeStoreProductDto[] getAllProducts(){
        ArrayList<FakeStoreProductDto> FakeProductList = new ArrayList<>();
        //RestTemplate restTemplate = restTemplateBuilder.build();
        /* Note when we want to send a list, better to get the array of the MainDTO in the response type instead of ArrayList"
        Read RunTime Type Erasure - https://www.baeldung.com/java-type-erasure
        */
        ResponseEntity<FakeStoreProductDto[]> response = restTemplate.getForEntity(productsUrl, FakeStoreProductDto[].class);
        return response.getBody();
    }

    public FakeStoreProductDto add_product(FakeStoreProductDto fakeStoreProductDto){
        //RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductDto> response = restTemplate.postForEntity(productsUrl, fakeStoreProductDto, FakeStoreProductDto.class);
        return response.getBody();
    }

    public FakeStoreProductDto partial_update_product(FakeStoreProductDto fakeStoreProductDto,Long id){
        //RestTemplate restTemplate = restTemplateBuilder.build();
        /*
         This didnt worked , used the getForEntity inside methods to implement this. just in the case of deletebyId
        GenericProductDto updatedProduct = restTemplate.patchForObject(updateProductByIdUrl,genericProductDto,GenericProductDto.class,id);
        RequestCallback requestCallback = restTemplate.httpEntityCallback(genericProductDto, productDto.class);
        HttpMessageConverterExtractor<ResponseEntity<productDto>> responseExtractor = new HttpMessageConverterExtractor(ResponseEntity.class, restTemplate.getMessageConverters());
        ResponseEntity<productDto> response = restTemplate.execute(updateProductByIdUrl, HttpMethod.PATCH, requestCallback, responseExtractor, id);
        return convertToGenericProduct(response.getBody());
        */
        RequestCallback requestCallback = restTemplate.acceptHeaderRequestCallback(FakeStoreProductDto.class);
        ResponseExtractor<ResponseEntity<FakeStoreProductDto>> responseExtractor =
                restTemplate.responseEntityExtractor(FakeStoreProductDto.class);
        ResponseEntity<FakeStoreProductDto> response =  restTemplate.execute(specificProductsUrl, HttpMethod.GET, requestCallback, responseExtractor, id);

        return response.getBody();
    }

    public FakeStoreProductDto full_update_product(FakeStoreProductDto fakeStoreProductDto,Long id){
        //RestTemplate restTemplate = restTemplateBuilder.build();
        RequestCallback requestCallback = restTemplate.acceptHeaderRequestCallback(FakeStoreProductDto.class);
        ResponseExtractor<ResponseEntity<FakeStoreProductDto>> responseExtractor =
                restTemplate.responseEntityExtractor(FakeStoreProductDto.class);
        ResponseEntity<FakeStoreProductDto> response =  restTemplate.execute(specificProductsUrl, HttpMethod.GET, requestCallback, responseExtractor, id);

        return response.getBody();
    }

    public FakeStoreProductDto delete_product(Long id){

        //RestTemplate restTemplate = restTemplateBuilder.build();
        /*
        restTemplate.delete(); cant use the delete method becoz it is returning void but our requirement at fakestore is to return the Object deleted with status code ok (200)
        so we have to work around and get the requested object by id back scene and return to the controller
        so we will use the code in getForEntity as it is and paste here.
        we cant call getForEntity here becoz that will violate the HTTP call this method will be invoked form
        Delete request of HTTP , inside this we cant invoke get method
        so get the code from getFor Entity and paste here
         */
        RequestCallback requestCallback = restTemplate.acceptHeaderRequestCallback(FakeStoreProductDto.class);
        ResponseExtractor<ResponseEntity<FakeStoreProductDto>> responseExtractor =
                restTemplate.responseEntityExtractor(FakeStoreProductDto.class);
        ResponseEntity<FakeStoreProductDto> response =  restTemplate.execute(specificProductsUrl, HttpMethod.DELETE, requestCallback, responseExtractor, id);

        return (response.getBody());
    }


}
