package se.kth.iv1350.pos.view;

import se.kth.iv1350.pos.controller.Controller;
import se.kth.iv1350.pos.integration.InventoryDatabaseException;
import se.kth.iv1350.pos.integration.ItemNotFoundException;
import se.kth.iv1350.pos.model.Item;
import se.kth.iv1350.pos.model.SoldItem;

import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * Simulates the user interface.
 */
public class View {
    private final Controller controller;

    public View(Controller controller) {
        this.controller = controller;
    }

    public void runFakeExecution() {
        System.out.println("Startar kassasystem...");

        tryRegister("banana");
        tryRegister("crash999");
        tryRegister("unknownItem");
        tryRegister("bread");

        controller.completeSale(); 

        System.out.println(generateReceipt());
    }

    private void tryRegister(String itemId) {
    Item item = null; // Gör item tillgänglig överallt i metoden
    try {
        item = controller.registerItem(itemId);
        System.out.println("\n>>> Artikel tillagd:");
        System.out.printf("Artikel tillagd: %s (%.2f SEK, %.2f%% moms)\n",
            item.getName(), item.getPrice(), item.getVat() );
    } catch (InventoryDatabaseException e) {
        System.out.println("[FEL] Ett tekniskt fel inträffade. Försök igen senare.");
    } catch (ItemNotFoundException e) {
        System.out.println("[INFO] Artikel finns inte: " + e.getItemId());
    }
}


    public String generateReceipt() {
        StringBuilder receipt = new StringBuilder();
        var timeOfSale = controller.getTimeOfSale();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

        receipt.append("\n------------------ Begin receipt -------------------\n");
        receipt.append("Time of Sale: ").append(timeOfSale.format(formatter)).append("\n\n");

        List<SoldItem> items = controller.getSoldItems();
        for (SoldItem soldItem : items) {
            Item item = soldItem.getItem();
            int quantity = soldItem.getQuantity();
            double price = item.getPrice();
            double totalItemPrice = quantity * price;

            receipt.append(String.format("%s  %d x %.2f  %.2f SEK\n",
                    item.getName(), quantity, price, totalItemPrice));
        }

        double total = controller.getTotal();
        double vat = controller.getVAT();

        receipt.append(String.format("%nTotal: %.2f SEK%n", total));
        receipt.append(String.format("VAT: %.2f%n", vat));
        receipt.append("Cash: 100 SEK\n");
        receipt.append(String.format("Change: %.2f SEK%n", 100 - total));
        receipt.append("------------------ End receipt ---------------------\n");

        return receipt.toString();
    }
}
