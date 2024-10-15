package com.arturfrimu.cloud.rest;

import com.arturfrimu.cloud.rest.ProductController.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Random;

import static java.lang.Long.MAX_VALUE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@SpringBootTest(webEnvironment = RANDOM_PORT)
class ProductControllerTest {

    private static final Random RANDOM = new Random();

    @LocalServerPort
    private Integer localServerPort;

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Test
    void fullCrud() {
        ResponseEntity<Void> postResponse = testRestTemplate.exchange(
                RequestEntity.post("http://localhost:%s/api/products".formatted(localServerPort))
                        .body("Product %s".formatted(RANDOM.nextLong(0, MAX_VALUE))),
                Void.class
        );

        assertThat(postResponse.getStatusCode().is2xxSuccessful()).isTrue();

        ResponseEntity<List<Product>> response = testRestTemplate.exchange(
                RequestEntity.get("http://localhost:%s/api/products".formatted(localServerPort))
                        .build(),
                new ParameterizedTypeReference<>() {
                }
        );

        assertThat(response.getStatusCode().is2xxSuccessful()).isTrue();
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody()).isNotEmpty();

        List<Product> products = response.getBody();

        for (var product : products) {
            ResponseEntity<Product> res = testRestTemplate.exchange(
                    RequestEntity.get("http://localhost:%s/api/products/%s".formatted(localServerPort, product.uuid())).build(),
                    new ParameterizedTypeReference<>() {
                    }
            );
            assertThat(res.getStatusCode().is2xxSuccessful()).isTrue();
            assertThat(res.getBody()).isNotNull();
        }

        for (var product : products) {
            ResponseEntity<Product> res = testRestTemplate.exchange(
                    RequestEntity.delete("http://localhost:%s/api/products/%s".formatted(localServerPort, product.uuid())).build(),
                    new ParameterizedTypeReference<>() {
                    }
            );
            assertThat(res.getStatusCode().is2xxSuccessful()).isTrue();
        }

        ResponseEntity<List<Product>> finalResponse = testRestTemplate.exchange(
                RequestEntity.get("http://localhost:%s/api/products".formatted(localServerPort)).build(),
                new ParameterizedTypeReference<>() {
                }
        );

        assertThat(finalResponse.getStatusCode().is2xxSuccessful()).isTrue();
        assertThat(finalResponse.getBody()).isNotNull();
        assertThat(finalResponse.getBody()).isEmpty();
    }
}