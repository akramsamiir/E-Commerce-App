import models.*;
import services.*;

public class Main {
    public static void main(String[] args) {
        Product cheese = new Product("Cheese", 100, 5, 0.2, true, false);
        Product tv = new Product("TV", 5000, 10, 5.0, false, false);
        Product biscuits = new Product("Biscuits", 150, 3, 0.7, true, false);
        Product scratchCard = new Product("ScratchCard", 50, 20, 0, false, false);

        Customer customer = new Customer("John Doe", 1000);

        Cart cart = new Cart();
        cart.add(cheese, 2);
        cart.add(biscuits, 1);
        cart.add(scratchCard, 1);

        ShippingService shippingService = new ShippingService();
        CheckoutService checkoutService = new CheckoutService();

        try {
            checkoutService.checkout(customer, cart, shippingService);
        } catch (IllegalArgumentException e) {
            System.err.println("Checkout failed: " + e.getMessage());
        }
    }
}
