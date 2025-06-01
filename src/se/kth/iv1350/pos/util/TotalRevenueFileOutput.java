package se.kth.iv1350.pos.util;

import se.kth.iv1350.pos.model.RevenueObserver;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class TotalRevenueFileOutput implements RevenueObserver {
    private final PrintWriter writer;

    public TotalRevenueFileOutput() {
        PrintWriter tempWriter;
        try {
            tempWriter = new PrintWriter(new FileWriter("total-revenue.txt", true));
        } catch (IOException e) {
            tempWriter = null;
            System.out.println("Kunde inte skapa loggfil för total försäljning.");
        }
        this.writer = tempWriter;
    }

    @Override
    public void newRevenue(double totalRevenue) {
        if (writer != null) {
            writer.println("[Observer] Total försäljning hittills: " + totalRevenue + " kr");
            writer.flush();
        }
    }
}
