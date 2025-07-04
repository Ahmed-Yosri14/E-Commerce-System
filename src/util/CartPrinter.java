package util;

import model.Cart;
import model.CartItem;
import model.Product;

public class CartPrinter {
    public static void printCartDetails(String customerName, Cart cart) {
        System.out.println();
        System.out.println("=".repeat(50));
        System.out.println("Cart details for " + customerName + ":");
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