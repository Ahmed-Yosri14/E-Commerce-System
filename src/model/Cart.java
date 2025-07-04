package model;

import java.util.*;

public class Cart {
    private List<CartItem> items = new ArrayList<>();

    public void add(Product product , int quantity) {
        for (CartItem item : items) {
            if (item.getProduct().equals(product)) {
                item.setQuantity(item.getQuantity() + quantity);
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
