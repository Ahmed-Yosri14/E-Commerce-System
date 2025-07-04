package model;

public class Customer {
    private String name;
    private double balance;
    private Cart cart;

    public Customer(String name, double balance) {
        this.name = name;
        this.balance = balance;
        this.cart = new Cart();
    }

    public String getName() {
        return name;
    }

    public double getBalance() {
        return balance;
    }

    public Cart getCart() {
        return cart;
    }

    public void deductBalance(double amount) {
        this.balance -= amount;
    }

    public void addToCart(Product product, int quantity) throws Exception {
        this.cart.add(product, quantity);
    }

    public void removeFromCart(Product product, int quantity) throws Exception {
        this.cart.remove(product, quantity);
    }

    public void removeAllFromCart(Product product) throws Exception {
        this.cart.removeAll(product);
    }

    public void clearCart() {
        this.cart.clear();
    }
} 