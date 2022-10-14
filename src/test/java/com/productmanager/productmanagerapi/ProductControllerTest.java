package com.productmanager.productmanagerapi;

import com.productmanager.productmanagerapi.dto.CategoryDTO;
import com.productmanager.productmanagerapi.dto.ProductDTO;
import com.productmanager.productmanagerapi.services.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Date;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest
//@ActiveProfiles("test")
public class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductService productService;

    private ProductDTO productDTO;
    private CategoryDTO categoryDTO;

    @BeforeEach
    public void setup() {
        categoryDTO = new CategoryDTO("Mobiles", "URL");
        productDTO = new ProductDTO(1L, "IPhone X", 100, 1000, true,
                "URL", categoryDTO, new Date(), "admin", "admin", new Date());
    }

    @Test
    @WithMockUser(username = "admin", roles = {"ROLE_USER", "ROLE_ADMIN"})
    public void shouldReturnProductById() throws Exception {
        given(productService.getProductDTO(anyLong())).willReturn(productDTO);

        this.mockMvc.perform(
                        MockMvcRequestBuilders.get("/api/products/{id}", 1L)
                                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(MockMvcResultMatchers.jsonPath("$.price").value(2000));

    }

}

