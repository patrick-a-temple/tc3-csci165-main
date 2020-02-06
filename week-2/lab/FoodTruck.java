// Patrick Temple
// Prof. Whitener
// CSCI165
// 7 February 2020

// LAB 2 (indiv. assignment): FoodTruck
// Purpose: to process an order at a food truck
// based on the food names and prices in two separate
// text files and to make a receipt in its own text file.

// classes
import java.util.Scanner;                  // read input from terminal for order
import java.io.FileWriter;                 // read from files
import java.io.FileNotFoundException;      // have an exit in case files is not found
import java.io.File;                       // for File type
import java.io.IOException;                // have an exit in case files cannot be written
import java.time.format.DateTimeFormatter; // for formatting the time
import java.time.LocalDateTime;            // for getting the current time
import java.lang.StringBuilder;            // for concatenating strings
import java.text.DecimalFormat;            // for formatting decimals

public class FoodTruck {
    public static void main(String[] args) {

        try {
            // non-changing variables
            final double SALES_TAX = 0.0625; // sales tax rate

            // File variables
            File menuFile = new File("menu.txt");     // menu items
            File pricesFile = new File("prices.txt"); // prices for said items

            // Scanners for the files
            Scanner getMenuItems = new Scanner(menuFile); // for getting each menu item
            Scanner getPrices = new Scanner(pricesFile);  // for getting each price

            // Scanner for inputting into the program
            Scanner reader = new Scanner(System.in); // general input method

            // FileWriter for making the receipt
            FileWriter receipt = new FileWriter("receipt.txt"); // file created for receipt

            // get menu items and prices:
            // needs to be in an array
            String[] menu = new String[3];   // holds menu items
            double[] prices = new double[3]; // holds prices

            int index = 0; // indexing for arrays in following loop

            while(getMenuItems.hasNext() && getPrices.hasNext()) {
                // get menu item and price at line (index + 1) in
                // their respective, while there are still lines
                // in each of the files
                menu[index] = getMenuItems.nextLine();
                prices[index] = getPrices.nextDouble();
                index++; // increment index
            } // end while loop for getting items and prices

            // we are now done getting menu items,
            // so we can close the readers
            // for the menu items to avoid warnings
            getMenuItems.close();
            getPrices.close();

            // welcome, say the time and get name of customer
            System.out.println("\nWelcome to the CSCI165 Food Truck");

            // get time and date
            LocalDateTime timeData = LocalDateTime.now(); // request time data
            DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("MM/dd/yyyy HH:mm:ss");
            String rawClock = timeFormat.format(timeData); // example:  02/04/2020 11:02:38
            String date = rawClock.substring(0, 10);       // get date portion: 02/04/2020
            String time = rawClock.substring(11, 19);      // get time portion: 11:02:38

            // output date and time before name is inputted
            System.out.printf("Current date: %s%nCurrent time: %s%n", date, time);

            // get name and do special processing
            // for requested invoice ID style
            System.out.print("\nEnter your first and last name: ");
            String name = reader.nextLine(); // get name from customer
            int nameLength = name.length();  // calculate length of name for later

            // get the first two characters
            // of the first and last name
            String firstInitials = name.substring(0, 2).toUpperCase();
            int spaceHere = name.indexOf(' '); // find space in the name to separate
                                               // first and last name and store location
            String lastInitials = name.substring((spaceHere + 1), (spaceHere + 3)).toUpperCase();

            // concatenate the initials together
            StringBuilder combineInitials = new StringBuilder();
            combineInitials = combineInitials.append(firstInitials).append(lastInitials);
            String bothInitials = combineInitials.toString(); // store the concatenation result

            // get the ACSII value of the first and last initials
            int fNameValue = (int) firstInitials.charAt(0);
            int lNameValue = (int) lastInitials.charAt(0);

            // conbine the two numbers from above, then
            // multiply by the length of the name
            int bothLetterValues  = fNameValue + lNameValue;
            int lengthTimesValues = nameLength * bothLetterValues;

            // make an invoice date in this format: 0207
            StringBuilder makeInvDate = new StringBuilder();
            makeInvDate = makeInvDate.append(date.substring(0, 2)).append(date.substring(3, 5));
            String invoiceDate = makeInvDate.toString(); // store as a string

            // make an invoice time in this format: 0941
            StringBuilder makeInvTime = new StringBuilder();
            makeInvTime = makeInvTime.append(time.substring(0, 2)).append(time.substring(3, 5));
            String invoiceTime = makeInvTime.toString(); // store as a string too

            // combine the initials, result of ln. 98, the time and date
            StringBuilder makeInvoiceID = new StringBuilder();
            makeInvoiceID = makeInvoiceID.append(bothInitials).append(lengthTimesValues).append(invoiceDate).append(invoiceTime);
            String invoiceID = makeInvoiceID.toString(); // store as string

            // this is used to make dividing lines simplier
            // to add in the output for the file and the terminal
            String divider = "=============================================================\n";

            // take customer's order
            System.out.println("\nWhat would you like?");
            System.out.println("Enter the quantity of the items below.");
            System.out.println("=========================================\n");


            int quantities[] = new int[3];       // holds each item's order quantity
            double itemTotal[] = new double [3]; // hold the total of each item after
                                                 // multiplying quantity * price

            for(int i = 0; i <= 2; i++) { // loop to get orders
                System.out.printf("%s - $%.2f: ", menu[i], prices[i]); // show item and price
                quantities[i] = reader.nextInt();                      // get how many of each item they want
                itemTotal[i] = quantities[i] * prices[i];              // calculate item total
                System.out.printf("%n");                               // print a new line
            }

            System.out.println(divider); // draw a line (see line 122)

            // make a subtotal
            double subtotal = 0.00;

            for(int i = 0; i <= 2; i++) {
                subtotal += itemTotal[i]; // add each item total to subtotal
            }
            // calculate subtotal
            double salesTaxAdded = SALES_TAX * subtotal;
            DecimalFormat totalStyle = new DecimalFormat("$###,###.##"); // TODO
            // StringBuffer ** START HERE **

            double total = (1 + SALES_TAX) * subtotal;

            // make all the lines on the receipt strings
            // with special formatting
            // ( using https://www.dotnetperls.com/padding-java )
            // formatted like this: "%<arg. number>$<spacing, - is left justify> ...
            // <spacing number>.<floats: digits after decimal><data type>"

            // name, date, time
            String recName = String.format("%1$-10s %2$50s%n", "Name: ", name);
            String recDate = String.format("%1$-10s %2$50s%n", "Date: ", date);
            String recTime = String.format("%1$-10s %2$50s%n", "Time: ", time);

            // header of receipt, invoice ID
            String recHeader = String.format("%n%1$-30s %2$-10s %3$-11s %4$-10s%n", "Item", "Quantity", "Price", "Total");
            String recInvoiceID = String.format("%1$-10s %2$48s%n", "Invoice ID: ", invoiceID);

            // items and totals
            String recFirstItem = String.format("%1$-30s %2$-10d $%3$-10.2f $%4$-10.2f%n", menu[0], quantities[0], prices[0], itemTotal[0]);
            String recSecondItem = String.format("%1$-30s %2$-10d $%3$-10.2f $%4$-10.2f%n", menu[1], quantities[1], prices[1], itemTotal[1]);
            String recThirdItem = String.format("%1$-30s %2$-10d $%3$-10.2f $%4$-10.2f%n", menu[2], quantities[2], prices[2], itemTotal[2]);

            // receipt subtotal, sales tax, and final total
            String recSubtotal = String.format("%-53s $%-10.2f%n", "Subtotal: " , subtotal);
            String recSalesTax = String.format("%-54s $%-11.2f%n", "6.75% Sales Tax: " , salesTaxAdded);
            String recTotal = String.format("%-53s $%-10.2f", "Total: " , total);

            // print the receipt tot the total
            System.out.println(recName);       // name of client
            System.out.println(recDate);       // date of transaction
            System.out.println(recTime);       // time of receipt
            System.out.println(recInvoiceID);  // invoice ID
            System.out.println(recHeader);     // header
            System.out.println(divider);       // dividing line
            System.out.println(recFirstItem);  // information for items purchased
            System.out.println(recSecondItem);
            System.out.println(recThirdItem);
            System.out.println(divider);       // divider
            System.out.println(recSubtotal);   // subtotal
            System.out.println(recSalesTax);   // sales tax
            System.out.println(recTotal);      // total with sales tax

            // print the same items as above
            // to a receipt in a text file
            receipt.write(recName);
            receipt.write(recDate);
            receipt.write(recTime);
            receipt.write(recInvoiceID);
            receipt.write(recHeader);
            receipt.write(divider);
            receipt.write(recFirstItem);
            receipt.write(recSecondItem);
            receipt.write(recThirdItem);
            receipt.write(divider);
            receipt.write(recSubtotal);
            receipt.write(recSalesTax);
            receipt.write(recTotal);

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
