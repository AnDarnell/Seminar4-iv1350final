package se.kth.iv1350.pos.integration;

/*
 * Undantag som kastas när en artikel inte finns i lagret.
 */
public class ItemNotFoundException extends Exception {
    private final String itemId;

    public ItemNotFoundException(String itemId) {
        super("Artikel med ID \"" + itemId + "\" finns inte.");
        this.itemId = itemId;
    }

    public String getItemId() {
        return itemId;
    }
}

