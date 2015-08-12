/*
    Dan Hogan
    ISY00246
    Assignment 1
    Part 1a
    Purpose: read integers 2 at a time
 */

package part1;

import java.util.Scanner;

public class Part1a {
    public static void main(String[] args) {
        int[] numArray = new int[10];
        int currentArrayIndex = 0;
        int maxNum;
        int minNum;
        Scanner input = new Scanner(System.in);
        String inputAsString;
        String inputStringArray[];
        
        // print instructions
        System.out.println("Welcome. When prompted, type two whole numbers "
                + "separated by a single space.");
        
        do {          
            // prompt for numbers
            System.out.print("Enter two (whole) numbers: ");
            inputAsString = input.nextLine(); // scanner input as string
            inputStringArray = inputAsString.split(" "); // put in String array
         
            // if input IS NOT empty & array EQUALS two Strings
            if ((!inputAsString.isEmpty()) && (inputStringArray.length == 2)) {
                try {
                    // validate numbers
                    int firstNum = Integer.parseInt(inputStringArray[0]);
                    int secondNum = Integer.parseInt(inputStringArray[1]);
                    
                    // put numbers into int array
                    numArray[currentArrayIndex] = firstNum;
                    currentArrayIndex++;
                    numArray[currentArrayIndex] = secondNum;
                    currentArrayIndex++;
                }
                catch (NumberFormatException e) {
                    System.out.println("Can only use (whole) numbers.");
                }
            }
            else if (inputAsString.isEmpty()) {
                input.close(); // close scanner
            }
            else {
                System.out.println("You can only enter two (whole) numbers "
                        + "separated by a single space.");
            }
        }
        while ((!inputAsString.isEmpty()) && 
                (currentArrayIndex < numArray.length));

        // check for max amount of numbers entered
        if (currentArrayIndex == numArray.length) {
            System.out.println("The maximum amount of numbers "
                    + "has been entered.");
            input.close(); // close scanner
        }

        // find max and min numbers
        maxNum = numArray[0]; // initialize as first array int to compare
        minNum = numArray[0]; // initialize as first array int to compare
        // iterate through input numbers
        for (int i = 0; i < currentArrayIndex; i++) {
            if (numArray[i] > maxNum) {
                maxNum = numArray[i];
            }
            if (numArray[i] < minNum) {
                minNum = numArray[i];
            }
        }
        
        // print array details
        System.out.format("%nThere were %s numbers entered.%n", 
                currentArrayIndex);
        System.out.format("Maximum = %s%n", maxNum);
        System.out.format("Minimum = %s%n", minNum);
    }
}
