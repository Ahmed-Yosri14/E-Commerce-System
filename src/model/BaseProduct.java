package model;

public class BaseProduct implements Product {
    private String name;
    private double price;
    private int quantity;

    public BaseProduct(String name, double price, int quantity) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    public String getName() { return name; }
    public double getPrice() { return price; }
    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }
    public boolean isExpired() { return false; }
    public boolean isShippable() { return false; }
    public double getWeight() { return 0.0; }
} 