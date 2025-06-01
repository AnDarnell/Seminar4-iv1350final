package se.kth.iv1350.pos.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a sale transaction.
 */
public class Sale {
    private final List<SoldItem> items = new ArrayList<>();
    private final List<RevenueObserver> revenueObservers = new ArrayList<>();
    private double total = 0;
    private double vat = 0;
    private LocalDateTime timeOfSale;

    public void addItem(Item item) {
        for (SoldItem soldItem : items) {
            if (soldItem.getItem().getId().equals(item.getId())) {
                soldItem.incrementQuantity();
                updateTotals();
                return;
            }
        }
        items.add(new SoldItem(item));
        updateTotals();
    }

    private void updateTotals() {
        total = 0;
        vat = 0;
        for (SoldItem soldItem : items) {
            total += soldItem.getTotalPrice();
            vat += soldItem.getVAT();
        }
    }

    public void addRevenueObserver(RevenueObserver obs) {
        revenueObservers.add(obs);
    }

    public void completeSale() {
        this.timeOfSale = LocalDateTime.now();
        notifyObservers();
    }

    private void notifyObservers() {
        for (RevenueObserver obs : revenueObservers) {
            obs.newRevenue(total);
        }
    }

    // === Getters for View-layer formatting ===
    public List<SoldItem> getItems() {
        return items;
    }

    public double getTotal() {
        return total;
    }

    public double getVAT() {
        return vat;
    }

    public LocalDateTime getTimeOfSale() {
        return timeOfSale;
    }
}
