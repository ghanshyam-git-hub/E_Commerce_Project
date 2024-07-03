package dev.ghanshyam.productservice;

import dev.ghanshyam.productservice.models.Category;
import dev.ghanshyam.productservice.models.Price;
import dev.ghanshyam.productservice.models.Products;
import dev.ghanshyam.productservice.repositories.CategoryRepository;
import dev.ghanshyam.productservice.repositories.PriceRepository;
import dev.ghanshyam.productservice.repositories.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.UUID;

@SpringBootApplication
public class ProductserviceApplication implements CommandLineRunner {

	private final CategoryRepository categoryRepository;
	private final ProductRepository productRepository;
	private final PriceRepository priceRepository;

	public ProductserviceApplication(CategoryRepository categoryRepository,
									 ProductRepository productRepository, PriceRepository priceRepository) {
		this.categoryRepository = categoryRepository;
		this.productRepository = productRepository;
		this.priceRepository = priceRepository;
	}

	public static void main(String[] args) {
		SpringApplication.run(ProductserviceApplication.class, args);
	}
		@Override
		public void run(String... args) throws Exception {


			//categoryRepository.save(category);
			//priceRepository.save(price);

//			Products products1 = new Products();
//			products1.setTitle("Apple pro");
//			products1.setDescription("Best smartphone");
//
//			Category category = new Category();
//			category.setCategory_name("Apple");
//			products1.setCategory(category);
//
//			Price price = new Price(50000.0,"Rupee");
//			products1.setPrice(price);
//
//			products1.setPid(1);
//			productRepository.save(products1);
//
//
//
//			Products products2 = new Products();
//			products2.setTitle("samsung pro");
//			products2.setDescription("Good smartphone");
//
//			Category category2 = new Category();
//			category2.setCategory_name("Samsung");
//			products2.setCategory(category2);
//
//			Price price2 = new Price(500.0,"Dollar");
//			products2.setPrice(price2);
//
//			products2.setPid(2);
//			productRepository.save(products2);
//
//			Products products3 = new Products();
//			products3.setTitle("samsung pro");
//			products3.setDescription("Good smartphone");
//
//			Category category3 = new Category();
//			category3.setCategory_name("Nokia");
//			products3.setCategory(category3);
//
//			Price price3 = new Price(500.0,"Pound");
//			products3.setPrice(price3);
//
//			products3.setPid(3);
//			productRepository.save(products3);
//			//categoryRepository.deleteById(UUID.fromString("cc865ef8-648a-4e70-a03c-9b8368d25f4b"));


		}
}









