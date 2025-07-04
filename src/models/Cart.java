package models;

import interfaces.Shippable;

import java.util.ArrayList;
import java.util.List;

public class Cart {
    private final List<CartItem> items = new ArrayList<>();

    public void add(Product product, int quantity) {
        if (quantity > product.getQuantity()) {
            throw new IllegalArgumentException("The quantity you requested of " + product.getName() + " exceeds available stock.");
        }
        else {
            items.add(new CartItem(product, quantity));
        }
    }

    public List<CartItem> getItems() { return items; }

    public boolean isEmpty() { return items.isEmpty(); }

    public double getSubtotal() {
        return items.stream().mapToDouble(CartItem::getTotalPrice).sum();
    }

    public List<Shippable> getShippableItems() {
        List<Shippable> shippableItems = new ArrayList<>();
        for (CartItem item : items) {
            if (item.product().isShippable()) {
                for (int i = 0; i < item.quantity(); i++) {
                    shippableItems.add(item.product());
                }
            }
        }
        return shippableItems;
    }
}
