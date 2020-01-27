// Patrick Temple
// Prof. Whitener
// CSCI165
// 26 January 2020

// Lab 1: Shape Maker.
// Purpose: makes specific shapes.

import java.util.Scanner; // for getting number of rows for right triangle

public class Shapes {

    public static void main(String[] args) {
        // create a reader object to get a number of rows for the triangle
        Scanner reader = new Scanner(System.in);
        System.out.print("Enter the number of rows for your right triangle: ");
        
        // how to read input, **specifically for numbers**
        int triangleRows = Integer.parseInt(reader.nextLine());
        System.out.println("Triangle with " + triangleRows + " rows:\n\n");
        
        //for loop: triangle
        for(int i = triangleRows; i >= 1; i--)
        {
            for(int numOfStarsInRow = i; numOfStarsInRow >= 1; numOfStarsInRow--)
            {
                System.out.print("*");
            }
            System.out.print("\n");
        }
        
        System.out.println("\nRectangle:"); // make the rectangle
        System.out.println("*****");
        System.out.println("*   *");
        System.out.println("*   *");
        System.out.println("*   *");
        System.out.println("*   *");
        System.out.println("*****");
        
        System.out.println("\nHourglass:"); // make the hourglass
        System.out.println("*******");
        System.out.println(" * * * ");
        System.out.println("  * *  ");
        System.out.println(" * * * ");
        System.out.println("*******");
    }

}
