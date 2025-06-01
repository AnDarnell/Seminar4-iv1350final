package se.kth.iv1350.pos.integration;

import se.kth.iv1350.pos.model.Item;

import java.util.HashMap;
import java.util.Map;

/*
 * Enkel lagersystemklass med artikeldata.
 */
public class ExternalInventorySystem {
    private final Map<String, Item> inventory = new HashMap<>();

    public ExternalInventorySystem() {
        inventory.put("banana", new Item("banana", "Banan", 5.0, 6.0, "Ekologisk banan, 1 st"));
        inventory.put("bread", new Item("bread", "Bröd", 20.0, 12.0, "Fullkornsbröd 500g"));
    }

    public Item findItem(String itemId) throws ItemNotFoundException {
    if ("crash999".equals(itemId)) {
        throw new InventoryDatabaseException("Kunde inte ansluta till lagersystem.");
    }
    Item item = inventory.get(itemId);
    if (item == null) {
        throw new ItemNotFoundException(itemId);
    }
    return item;
}
}
