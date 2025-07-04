package services;

import models.*;

public class CheckoutService {
    private static final double SHIPPING_FEE = 30.0;

    public void checkout(Customer customer, Cart cart, ShippingService shippingService) {
        if (cart.isEmpty()) throw new IllegalArgumentException("Cart is empty.");

        for (CartItem item : cart.getItems()) {
            if (item.product().isExpirable() && item.product().isExpired()) {
                throw new IllegalArgumentException("Product " + item.product().getName() + " is expired.");
            }
        }

        double subtotal = cart.getSubtotal();
        double shipping = cart.getShippableItems().isEmpty() ? 0 : SHIPPING_FEE;
        double totalAmount = subtotal + shipping;

        if (customer.getBalance() < totalAmount) throw new IllegalArgumentException("Customer has insufficient balance.");

        shippingService.ship(cart.getShippableItems());

        System.out.println("\n- * Checkout receipt **\n");
        for (CartItem item : cart.getItems()) {
            System.out.printf("%dx %-12s %.0f\n", item.quantity(), item.product().getName(), item.getTotalPrice());
        }

        System.out.println("- ---------------------");
        System.out.printf("Subtotal         %.0f\n", subtotal);
        System.out.printf("Shipping         %.0f\n", shipping);
        System.out.printf("Amount           %.0f\n", totalAmount);

        for (CartItem item : cart.getItems()) {
            item.product().decreaseQuantity(item.quantity());
        }
        customer.pay(totalAmount);

        System.out.printf("Customer balance %.0f\n", customer.getBalance());
    }    
}
