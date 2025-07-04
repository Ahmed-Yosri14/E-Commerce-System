package model;

public interface Product {
    String getName();
    double getPrice();
    int getQuantity();
    void setQuantity(int quantity);
    boolean isExpired();
    boolean isShippable();
    double getWeight();
}
