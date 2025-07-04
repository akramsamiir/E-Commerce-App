package services;

import interfaces.*;
import java.util.*;

public class ShippingService {
    public void ship(List<Shippable> items) {
        if (items.isEmpty()) return;

        System.out.println("\n- ** Shipment notice **\n");

        Map<String, Double> weightByName = new LinkedHashMap<>();
        for (Shippable item : items) {
            weightByName.put(item.getName(), weightByName.getOrDefault(item.getName(), 0.0) + item.getWeight());
        }

        double totalWeight = 0;
        for (var entry : weightByName.entrySet()) {
            System.out.printf("1x %-12s %.0fg\n", entry.getKey(), entry.getValue() * 1000);
            totalWeight += entry.getValue();
        }

        System.out.printf("\nTotal package weight %.1fkg\n", totalWeight);
    }
}
