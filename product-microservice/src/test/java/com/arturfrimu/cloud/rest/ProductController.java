package com.arturfrimu.cloud.rest;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import static lombok.AccessLevel.PRIVATE;

@Slf4j
@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
@FieldDefaults(level = PRIVATE, makeFinal = true)
public class ProductController {

    private static final Map<UUID, Product> products = new HashMap<>();

    @PostConstruct
    public void postConstruct() {
        UUID prod1 = UUID.randomUUID();
        UUID prod2 = UUID.randomUUID();
        UUID prod3 = UUID.randomUUID();
        products.put(prod1, new Product(prod1, "Product 1"));
        products.put(prod2, new Product(prod2, "Product 2"));
        products.put(prod3, new Product(prod3, "Product 3"));
    }

    @GetMapping
    public List<Product> products() {
        return products.values().stream().toList();
    }

    @GetMapping("/{uuid}")
    public Product product(@PathVariable UUID uuid) {
        return products.get(uuid);
    }

    @PostMapping
    public void add(@RequestBody String productName) {
        UUID uuid = UUID.randomUUID();
        products.put(uuid, new Product(uuid, productName));
    }

    @DeleteMapping("/{uuid}")
    public void delete(@PathVariable UUID uuid) {
        products.remove(uuid);
    }

    public record Product(UUID uuid, String name) {
    }
}
