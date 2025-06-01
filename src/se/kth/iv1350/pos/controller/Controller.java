package se.kth.iv1350.pos.controller;

import se.kth.iv1350.pos.integration.ExternalInventorySystem;
import se.kth.iv1350.pos.integration.InventoryDatabaseException;
import se.kth.iv1350.pos.integration.ItemNotFoundException;
import se.kth.iv1350.pos.model.Item;
import se.kth.iv1350.pos.model.RevenueObserver;
import se.kth.iv1350.pos.model.Sale;
import se.kth.iv1350.pos.model.SoldItem;

import java.time.LocalDateTime;
import java.util.List;

/**
 * This class is the application's only controller. All calls to the model pass through here.
 */
public class Controller {
    private final ExternalInventorySystem inventorySystem;
    private Sale sale;

    public Controller(ExternalInventorySystem inventorySystem) {
        this.inventorySystem = inventorySystem;
        this.sale = new Sale();
    }

    public Item registerItem(String itemId) throws InventoryDatabaseException, ItemNotFoundException {
        Item item = inventorySystem.findItem(itemId);
        sale.addItem(item);
        return item;
    }

    public void completeSale() {
        sale.completeSale();
    }

    public void addRevenueObserver(RevenueObserver obs) {
        sale.addRevenueObserver(obs);
    }

    // === Data access for View ===

    public List<SoldItem> getSoldItems() {
        return sale.getItems();
    }

    public double getTotal() {
        return sale.getTotal();
    }

    public double getVAT() {
        return sale.getVAT();
    }

    public LocalDateTime getTimeOfSale() {
        return sale.getTimeOfSale();
    }
}
