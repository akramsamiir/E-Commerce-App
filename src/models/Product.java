package models;

import interfaces.Expirable;
import interfaces.Shippable;

public class Product implements Expirable, Shippable {
private final String name;
    private final double price;
    private int quantity;
    private final double weight;
    private final boolean expirable;
    private boolean expired;

    public Product(String name, double price, int quantity, double weight, boolean expirable, boolean expired) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.weight = weight;
        this.expirable = expirable;
        this.expired = expired;
    }

    public String getName() { return name; }
    public double getPrice() { return price; }
    public int getQuantity() { return quantity; }
    public double getWeight() { return weight; }

    public boolean isShippable() { return weight > 0; }

    public boolean isExpirable() { return expirable; }

    @Override
    public boolean isExpired() { return expired; }

    public void setExpired(boolean expired) {
        if (!expirable) throw new UnsupportedOperationException(name + " is not expirable.");
        this.expired = expired;
    }

    public void decreaseQuantity(int amount) {
        if (amount > quantity) throw new IllegalArgumentException("Not enough stock for " + name);
        quantity -= amount;
    }
}