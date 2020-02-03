// Patrick Temple
// Prof. Whitener
// CSCI165
// 7 February 2020

// LAB 2 (indiv. assignment): FoodTruck
// Purpose: to process an order at a food truck
// based on the food names and prices in two separate
// text files and to make a receipt in its own file.

// classes
import java.util.Scanner;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.io.File;
import java.io.IOException;

public class FoodTruck {
    public static void main(String[] args) {
        
        try {
            
            // sales tax rate
            final double SALES_TAX = 6.25;
            
            // File variables
            File menuItems = new File("menu.txt");
            File prices = new File("prices.txt");
            System.out.println("I see the files.");
            
            // Scanners for the files
            Scanner getMenuItems = new Scanner(menuItems);
            Scanner getPrices = new Scanner(prices);
            
            // Scanner for inputting into the program
            Scanner reader = new Scanner(System.in);
            
            // FileWriter for making the receipt
            FileWriter receipt = new FileWriter("receipt.txt");
            
            // get menu items and prices:
            String firstMenuItem = getMenuItems.nextLine();
            double firstItemPrice = getPrices.nextDouble();
            
            String secondMenuItem = getMenuItems.nextLine();
            double secondItemPrice = getPrices.nextDouble();
            
            String thirdMenuItem = getMenuItems.nextLine();
            double thirdItemPrice = getPrices.nextDouble();
            
            // variables for inputs and deriving values (like initials)
            String name = "";
            
            
            
            System.out.printf("Menu items:%n%s: %f%n", firstMenuItem, firstItemPrice);
            System.out.printf("%s: %f%n", secondMenuItem, secondItemPrice);
            System.out.printf("%s: %f%n", thirdMenuItem, thirdItemPrice);
            
            
            
            receipt.close();
            reader.close();
            
        } // end try
        
        
        
        // exceptions:
        // file not found in directory
        catch (FileNotFoundException e) {
            System.out.println("ERROR: The menu.txt and/or the prices.txt file is missing.");
        } // end missing file exception
        
        // cannot write a file
        catch (IOException e) {
            System.out.println("ERROR: Cannot write to a file.");
        } // end file writing exception
    } // end main
} // end class
