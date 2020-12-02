package com.geekbrain.market;

import com.geekbrain.market.entities.Category;
import com.geekbrain.market.entities.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

@JsonTest
public class JsonTests {

    @Autowired
    private JacksonTester<Category> categories;

    @Autowired
    private JacksonTester<Product> products;

    @Test
    public void jsonSerializationCategory() throws IOException {
        Category category = new Category();
        category.setId(1L);
        category.setTitle("milk");

        assertThat(this.categories.write(category)).hasJsonPathNumberValue("$.id");
        assertThat(this.categories.write(category)).extractingJsonPathStringValue("$.title").isEqualTo("milk");
    }

    @Test
    public void jsonDeserializationTestCategory() throws Exception {
        String content = "{\"id\": 2,\"title\":\"vine\"}";
        Category category = new Category();
        category.setId(2L);
        category.setTitle("vine");

        assertThat(this.categories.parse(content)).isEqualTo(category);
        assertThat(this.categories.parseObject(content).getTitle()).isEqualTo("vine");
    }

    @Test
    public void jsonSerializationProduct() throws IOException {
        Product product = new Product();
        product.setId(1L);
        product.setName("bread black");
        product.setCost(56);

        assertThat(this.products.write(product)).hasJsonPathNumberValue("$.id");
        assertThat(this.products.write(product)).extractingJsonPathStringValue("$.name").isEqualTo("bread black");
        assertThat(this.products.write(product)).hasJsonPathNumberValue("$.cost");
    }

    @Test
    public void jsonDeserializationTestProduct() throws Exception {
        String content = "{\"id\": 2,\"name\":\"vine roja\", \"cost\": 1550}";
        Product product = new Product();
        product.setId(2L);
        product.setName("vine roja");
        product.setCost(1550);

        assertThat(this.products.parse(content)).isEqualTo(product);
        assertThat(this.products.parseObject(content).getName()).isEqualTo("vine roja");
        assertThat(this.products.parseObject(content).getCost()).isEqualTo(1550);
    }

}
