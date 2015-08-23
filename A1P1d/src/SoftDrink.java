/*
    Dan Hogan
    dhogan15
    22108261
    ISY00246
    Assignment 1
    Part 1d
    Purpose: SoftDrink class
 */

public class SoftDrink implements Stock{
    final private int itemId;
    final private String itemDesc;
    private int itemQuantity;
    final private int packageSize;
    
    // constructor
    public SoftDrink(int id, String desc, int quantity, int size) {
        itemId = id;
        itemDesc = desc;
        itemQuantity = quantity;
        packageSize = size;
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
    
    public int getPackageSize() {
        return packageSize;
    }
    
    // formatted output
    @Override
    public String toString() {
            return getID() + ", " + getDescription() + ", " + number() + ", " + 
                getPackageSize() + ".";
    }
}
