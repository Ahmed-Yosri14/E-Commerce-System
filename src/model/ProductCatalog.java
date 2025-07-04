package model;

import java.util.*;

public class ProductCatalog {
    private Map<String, Product> products = new HashMap<>();

    public void addProduct(Product product) {
        products.put(product.getName(), product);
    }

    public Product getProduct(String name) {
        return products.get(name);
    }

    public Collection<Product> getAllProducts() {
        return products.values();
    }
} 