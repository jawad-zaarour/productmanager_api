package com.productmanager.productmanagerapi;

import com.productmanager.productmanagerapi.models.Category;
import com.productmanager.productmanagerapi.models.Product;
import com.productmanager.productmanagerapi.models.User;
import com.productmanager.productmanagerapi.repository.CategoryRepository;
import com.productmanager.productmanagerapi.repository.ProductRepository;
import com.productmanager.productmanagerapi.repository.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import java.util.Date;
import java.util.List;
import java.util.Set;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class ProductRepoTest {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private UserRepository userRepository;

    private Product product = new Product();
    private Product product2 = new Product();
    private Category category  = new Category();
    private Category category2  = new Category();
    private User user = new User();

    @BeforeEach
    void setUp() {

        category.setName("Books");
        category.setImageURL("Category Book Image URL");

        category2.setName("Mobiles");
        category2.setImageURL("Category Mobiles Image URL");

        user.setUserName("admin");
        user.setActive(true);
        user.setEmail("admin@mail.com");
        user.setPassword("123456");
        user.setUpdated_TS(new Date());
        user.setCreated_TS(new Date());
        user.setUpdated_By("admin");
        user.setCreated_By("admin");

        product.setName("IPhone 5");
        product.setAvailable(true);
        product.setPrice(1000);
        product.setImageURL("image URL");
        product.setUpdated_TS(new Date());
        product.setCreated_TS(new Date());
        product.setUpdated_By("admin");
        product.setCreated_By("admin");
        product.setCategory(category2);
        product.setUser(user);
        product.setQuantityInStock(10);

        product2.setName("IPhone X");
        product2.setAvailable(true);
        product2.setPrice(2000);
        product2.setImageURL("image URL");
        product2.setUpdated_TS(new Date());
        product2.setCreated_TS(new Date());
        product2.setUpdated_By("admin");
        product2.setCreated_By("admin");
        product2.setCategory(category2);
        product2.setUser(user);
        product2.setQuantityInStock(20);

        categoryRepository.save(category);
        categoryRepository.save(category2);
        userRepository.save(user);
        productRepository.save(product);
        productRepository.save(product2);



    }

    @AfterEach
    void tearDown() {
        productRepository.deleteAll();
        categoryRepository.deleteAll();
        userRepository.deleteAll();
    }
    
    @Test
    void getProductsByCategoryIdTest() {

        //when
        List<Product> expectedProduct = productRepository.findProductsByCategoryId(2L);
        //then
        assertThat(expectedProduct.size()).isEqualTo(2);
        assertThat(expectedProduct.get(0).getName()).isEqualTo("IPhone 5");
        assertThat(expectedProduct.get(0).getPrice()).isEqualTo(1000);
        assertThat(expectedProduct.get(0).getCategory().getName()).isEqualTo("Mobiles");

        assertThat(expectedProduct.get(1).getName()).isEqualTo("IPhone X");
        assertThat(expectedProduct.get(1).getPrice()).isEqualTo(2000);
        assertThat(expectedProduct.get(1).getCategory().getName()).isEqualTo("Mobiles");

        assertThat(expectedProduct.get(1).getUser().getUserName()).isEqualTo("admin");

    }

}
