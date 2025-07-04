public abstract class Product {
    private String name;
    private double price;
    private int quantity;
    ExpirationStrategy expirationStrategy;
    ShippingStrategy shippingStrategy;

    public Product(String name , double price,int quantity, ExpirationStrategy expirationStrategy, ShippingStrategy shippingStrategy) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.expirationStrategy = expirationStrategy;
        this.shippingStrategy = shippingStrategy;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    boolean isExpired() {
        return expirationStrategy.isExpired();
    }

    boolean isShippable(){
        return shippingStrategy.isShippable();
    }

}
