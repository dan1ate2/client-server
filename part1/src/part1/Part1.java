/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package part1;

import java.util.Scanner;
import java.util.Arrays;

/**
 *
 * @author Ðan
 */
public class Part1 {

    /**
     * @param args the command line arguments
     */
    @SuppressWarnings("empty-statement")
    public static void main(String[] args) {
        int[] numArray = new int[10];
        int currentArrayIndex = 0;
        int maxNum = 0;
        int minNum = 0;
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
            inputStringArray = inputAsString.split(" ");
         
            // if input IS NOT empty & array EQUALS 2 Strings
            if ((!inputAsString.isEmpty()) && (inputStringArray.length == 2)) {
                try {
                    // validate numbers
                    int firstNum = Integer.parseInt(inputStringArray[0]);
                    int secondNum = Integer.parseInt(inputStringArray[1]);
                    
                    // put numbers into array
                    numArray[currentArrayIndex] = firstNum;
                    currentArrayIndex++;
                    numArray[currentArrayIndex] = secondNum;
                    currentArrayIndex++;
                }
                catch (NumberFormatException e) {
                    System.out.println("Can only use (whole) numbers "
                            + "separated by a single space.");
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
        while ((!inputAsString.isEmpty()) && (currentArrayIndex < numArray.length));
//        numArray[1] = input.nextInt();

        // check max numbers
        if (currentArrayIndex == numArray.length) {
            System.out.println("The maximum amount of numbers has been entered.");
            input.close(); // close scanner
        }

        // find max and min
        for (int i = 0; i < numArray.length; i++) {
            if (numArray[i] > maxNum) {
                maxNum = numArray[i];
            }
            if (numArray[i] < minNum) {
                minNum = numArray[i];
            }
        }
        
        // print array details
        System.out.format("There were %s numbers entered:", currentArrayIndex++);
        System.out.format(Arrays.toString(numArray)); // REMOVE AFTER TESTING!!!
        System.out.format("%nMaximum = %s", maxNum);
        System.out.format("%nMinimum = %s%n", minNum);
    }
    
}
