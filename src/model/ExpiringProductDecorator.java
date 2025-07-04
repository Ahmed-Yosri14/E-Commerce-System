package model;

import java.time.LocalDate;

public class ExpiringProductDecorator implements Product {
    private Product product;
    private LocalDate expiryDate;

    public ExpiringProductDecorator(Product product, LocalDate expiryDate) {
        this.product = product;
        this.expiryDate = expiryDate;
    }

    public String getName() { return product.getName(); }
    public double getPrice() { return product.getPrice(); }
    public int getQuantity() { return product.getQuantity(); }
    public void setQuantity(int quantity) { product.setQuantity(quantity); }
    public boolean isExpired() { return expiryDate.isBefore(LocalDate.now()); }
    public boolean isShippable() { return product.isShippable(); }
    public double getWeight() { return product.getWeight(); }
} 