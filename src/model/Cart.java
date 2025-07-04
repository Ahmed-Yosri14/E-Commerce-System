package model;

import java.util.*;

public class Cart {
    private List<CartItem> items = new ArrayList<>();

    public void add(Product product , int quantity) throws Exception {
        // Validate quantity is positive
        if (quantity <= 0) {
            throw new Exception("Quantity to add must be positive");
        }
        
        // Check if product is expired
        if (product.isExpired()) {
            throw new Exception("Cannot add expired product: " + product.getName());
        }
        
        // Check if requested quantity exceeds available stock
        if (product.getQuantity() < quantity) {
            throw new Exception("Insufficient stock for " + product.getName() + ". Available: " + product.getQuantity() + ", Requested: " + quantity);
        }
        
        for (CartItem item : items) {
            if (item.getProduct().equals(product)) {
                // Check if adding to existing item would exceed stock
                int newTotalQuantity = item.getQuantity() + quantity;
                if (product.getQuantity() < newTotalQuantity) {
                    throw new Exception("Insufficient stock for " + product.getName() + ". Available: " + product.getQuantity() + ", Requested total: " + newTotalQuantity);
                }
                item.setQuantity(newTotalQuantity);
                return;
            }
        }
        items.add(new CartItem(product, quantity));
    }
    public void remove(Product product,int quantity) throws Exception {
        if (quantity <= 0) {
            throw new Exception("Quantity to remove must be positive");
        }

        CartItem itemToRemove = null;
        for (CartItem item : items) {
            if (item.getProduct() == product) {
                itemToRemove = item;
                break;
            }
        }

        if (itemToRemove == null) {
            throw new Exception(product.getName() + " is not in cart");
        }

        if (quantity > itemToRemove.getQuantity()) {
            throw new Exception("Cannot remove " + quantity + " of " + product.getName() + ". Only " + itemToRemove.getQuantity() + " in cart");
        }

        int newQuantity = itemToRemove.getQuantity() - quantity;
        if (newQuantity == 0) {
            items.remove(itemToRemove);
        } else {
            itemToRemove.setQuantity(newQuantity);
        }
    }
    public void removeAll(Product product) throws Exception {
        CartItem itemToRemove = null;
        for (CartItem item : items) {
            if (item.getProduct() == product) {
                itemToRemove = item;
                break;
            }
        }

        if (itemToRemove == null) {
            throw new Exception(product.getName() + " is not in cart");
        }

        items.remove(itemToRemove);
    }

    public void clear() {
        items.clear();
    }

    public List<CartItem> getItems() {
        return items;
    }

    public boolean isEmpty() {
        return items.isEmpty();
    }

    public int getItemCount() {
        return items.size();
    }

    public boolean contains(Product product) {
        for (CartItem item : items) {
            if (item.getProduct() == product) {
                return true;
            }
        }
        return false;
    }
}
