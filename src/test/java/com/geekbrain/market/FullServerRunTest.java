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
        не работает, ошибка
         org.springframework.web.client.RestClientException: Error while extracting response for type [interface java.util.List] and content type [application/json]; nested exception is org.springframework.http.converter.HttpMessageNotReadableException: JSON parse error: Cannot deserialize instance of `java.util.ArrayList<java.lang.Object>` out of START_OBJECT token; nested exception is com.fasterxml.jackson.databind.exc.MismatchedInputException: Cannot deserialize instance of `java.util.ArrayList<java.lang.Object>` out of START_OBJECT token
          at [Source: (PushbackInputStream); line: 1, column: 1]
        assertThat(category).isNotNull();


    }
}
