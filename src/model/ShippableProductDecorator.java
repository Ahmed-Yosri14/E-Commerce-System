package model;

public class ShippableProductDecorator implements Product {
    private Product product;
    private double weight;

    public ShippableProductDecorator(Product product, double weight) {
        this.product = product;
        this.weight = weight;
    }

    public String getName() { return product.getName(); }
    public double getPrice() { return product.getPrice(); }
    public int getQuantity() { return product.getQuantity(); }
    public void setQuantity(int quantity) { product.setQuantity(quantity); }
    public boolean isExpired() { return product.isExpired(); }
    public boolean isShippable() { return true; }
    public double getWeight() { return weight; }
} 