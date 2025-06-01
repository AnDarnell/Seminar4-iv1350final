package se.kth.iv1350.pos.model;

public class SoldItem {
    private final Item item;
    private int quantity;

    public SoldItem(Item item) {
        this.item = item;
        this.quantity = 1;
    }

    public void incrementQuantity() {
        quantity++;
    }

    public Item getItem() {
        return item;
    }

    public double getTotalPrice() {
        return item.getPrice() * quantity * (1 + item.getVat() / 100);
    }

    public double getVAT() {
        return item.getPrice() * quantity * (item.getVat() / 100);
    }

    @Override
    public String toString() {
        return String.format("%s  %d x %.2f  %.2f SEK",
            item.getName(), quantity, item.getPrice(), quantity * item.getPrice());
    }
}
