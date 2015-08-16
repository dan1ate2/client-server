/*
    Dan Hogan
    ISY00246
    Assignment 1
    Part 1d
    Purpose: Confectionary class
 */

public class Confectionary implements Stock{
    final private int itemId;
    final private String itemDesc;
    private int itemQuantity;
    final private String itemExpiry;
    
    // constructor
    public Confectionary (int id, String desc, int quantity, String exp) {
        itemId = id;
        itemDesc = desc;
        itemQuantity = quantity;
        itemExpiry = exp;
    }
    
    // get stock ID
    @Override
    public int getID() {
        return itemId;
    }
    
    // get stock description
    @Override
    public String getDescription() {
        return itemDesc;
    }
    
    // get number of items in stock
    @Override
    public int number() {
        return itemQuantity;
    }
    
    // update number of items in stock
    @Override
    public void setNumber(int num) {
        this.itemQuantity = num;
    }
    
    // get item expiry date
    public String getExpiry() {
        return itemExpiry;
    }
    
    // formatted output
    @Override
    public String toString() {
        return getID() + ", " + getDescription() + ", " + number() + ", " + 
            getExpiry() + ".";
    }
}
