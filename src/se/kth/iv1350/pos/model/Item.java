package se.kth.iv1350.pos.model;

/**
 * Represents an item in the store's inventory.
 */
public class Item {
    private final String id;
    private final String name;
    private final double price;
    private final double vat;
    private final String description;

    public Item(String id, String name, double price, double vat, String description) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.vat = vat;
        this.description = description;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public double getVat() {
        return vat;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return String.format("Item ID: %s\nItem name: %s\nItem cost: %.2f SEK\nVAT: %.0f%%\nItem description: %s",
                id, name, price, vat, description);
    }
}
