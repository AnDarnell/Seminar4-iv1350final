package se.kth.iv1350.pos.integration;

/*
 * Undantag som kastas när databasen inte kan nås.
 */
public class InventoryDatabaseException extends RuntimeException {
    public InventoryDatabaseException(String message) {
        super(message);
    }
}
