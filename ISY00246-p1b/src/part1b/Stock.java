/*
    Dan Hogan
    ISY00246
    Assignment 1
    Part 1b
    Purpose: Stock interface
 */
package part1b;

/**
 *
 * @author Ðan
 */
public interface Stock {
    public int getID();
        // get stock ID
    public String getDescription();
        // get stock description
    public int number();
        // get number of items
    public void setNumber(int num);
        // update number in stock
}
