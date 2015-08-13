/*
    Dan Hogan
    ISY00246
    Assignment 1
    Part 1b
    Purpose: Tests Stock interface & Confectionary class
 */
package part1b;

import java.util.ArrayList;

/**
 *
 * @author Ðan
 */
public class Part1b {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        // create array list of confectionary
        ArrayList<Confectionary> confecList = new ArrayList<Confectionary>() {};
        confecList.add(new Confectionary
            (11, "Red Liqorice", 5, "23rd June 2016"));
        confecList.add(new Confectionary
            (12, "Jelly Beans", 34, "15th December 2017"));
        confecList.add(new Confectionary
            (13, "Cherry Ripe", 200, "1st August 2016"));
        
        // print confectionary objects from array
        for (Confectionary i : confecList) {
            System.out.println(i);
        }
        
    }
    
}
