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

    public void printCartDetails() {
        System.out.println();
        System.out.println("=".repeat(50));
        System.out.println("Cart details for " + name + ":");
        if (cart.isEmpty()) {
            System.out.println("Cart is empty.");
            System.out.println("=".repeat(50));
            System.out.println();
            return;
        }
        double total = 0;
        for (CartItem item : cart.getItems()) {
            Product p = item.getProduct();
            int qty = item.getQuantity();
            double price = p.getPrice();
            double subtotal = price * qty;
            total += subtotal;
            System.out.printf("%dx %s @ %.2f each = %.2f\n", qty, p.getName(), price, subtotal);
        }
        System.out.printf("Cart subtotal: %.2f\n", total);
        System.out.println("=".repeat(50));
        System.out.println();
    }
} 