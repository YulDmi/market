package com.geekbrain.market;

import com.geekbrain.market.dto.CategoryDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.security.test.context.support.WithMockUser;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)

public class FullServerRunTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    @WithMockUser(username = "user", authorities = "USER")
    public void fullRestTest(){

      List<CategoryDto> category = restTemplate.getForObject("/api/v1/categories", List.class);
        assertThat(category).isNotNull();

}
}
