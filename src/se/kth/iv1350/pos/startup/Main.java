package se.kth.iv1350.pos.startup;

import se.kth.iv1350.pos.controller.Controller;
import se.kth.iv1350.pos.integration.ExternalInventorySystem;
import se.kth.iv1350.pos.util.TotalRevenueFileOutput;
import se.kth.iv1350.pos.view.TotalRevenueView;
import se.kth.iv1350.pos.view.View; 

/*
 * Programmet startar.
 */
public class Main {
    public static void main(String[] args) {
    ExternalInventorySystem inventory = new ExternalInventorySystem();
    Controller controller = new Controller(inventory);

    // Registrerar observers
    TotalRevenueView terminalObserver = new TotalRevenueView();
    TotalRevenueFileOutput fileObserver = new TotalRevenueFileOutput();
    controller.addRevenueObserver(terminalObserver);
    controller.addRevenueObserver(fileObserver);

    View view = new View(controller);
    view.runFakeExecution();
}

}
