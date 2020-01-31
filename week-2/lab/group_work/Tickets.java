// Kaiya Hogg and Patrick Temple
// Prof. Whitener
// CSCI165
// 31 January 2019

// Week 2 Lab: Tickets
// Purpose: to validate tickets with a special algorithm

// Classes
import java.util.Scanner;             // for reading the list of incoming tickets
import java.io.FileWriter;            // to write to the valid_tix.txt file
import java.io.IOException;           // for problems writing to a file
import java.io.File;                  // for the object type File
import java.io.FileNotFoundException; // for when the text file does not exist

public class Tickets{

    public static void main(String[] args){

        /* 
            The following code steps you through the String processing
            and math neccessary to determine if a single ticket number is valid.
            Your job with this code is to:

            ~   Closely study the code. Reverse engineer it. Use the API for guidance
                to understand the classes and methods that are being used.
            ~   Add comments for each statement, describing in detail what the line is doing
                I want to know DETAILS, not generalizations. RESEARCH!
            ~   When you have completed that, add code to complete the following

                1) Using a Scanner, open the file: tickets.txt
                2) Using a while(hasNext) loop, read each ticket number
                3) Run the ticket number through the provided math and String processing
                4) If the ticket number is valid write it to a new file called: valid_tix.txt
                5) Ignore the invalid ticket numbers

            ~   Additional code must also be commented. But I am more interested in why you are doing
                something, not the details of how.

            Check your work: There are 101 valid ticket numbers among the 1000 provided ticket numbers
                             These people make mistakes!!!

            Submit only Java source code files. Also submit valid_tix.txt   

        */
        
        
        
        try {
                // file variables
                File ticketFile = new File("tickets.txt"); // the tickets to be validated
                Scanner checker = new Scanner(ticketFile); // the Scanner that reads tickets.txt
                FileWriter validTix = new FileWriter("valid_tix.txt"); // valid tickets are piped to this file
                                
                // variables used in preparing outputs and results
                String ticket;            // the original ticket number
                String last;              // the last digit of ticket
                int last_digit = 0;       // the variable 'last' as an integer 
                String reduced_ticket;    // the ticket without the last digit
                int ticket_number = 0;    // the reduced ticket as an integer
                int remainder = 0;        // calculated remainder from ticket_number % 7
                boolean validity = false; // indicates if the last digit equals the remainder
                String  format = "Original Ticket #: %s\nLast Digit: %d\nReduced Ticket #: %d\nRemainder: %d\nValidity: %b\n";
                
                int totalTixCounter = 0; // counts total tickets processed
                int validTixCounter = 0; // counts the valid tickets saved into valid_tix.txt
                
                // process validity of tickets until the all of tickets.txt has been checked
                while (checker.hasNext()) {
                    ticket = checker.nextLine();                               // get the ticket number from the provided tickets
                    last = ticket.substring(ticket.length() - 1);              // get the last number and make...
                    last_digit = Integer.valueOf(last);                        // that into a string and an integer
                    reduced_ticket = ticket.substring(0, ticket.length() - 1); // get everything but the last number in the...
                    ticket_number = Integer.valueOf(reduced_ticket);           // ticket and put it into a string and an integer
                    remainder = ticket_number % 7;                             // calculate the remainder of the ticket
                    validity = (remainder == last_digit);                      // return true if the remainder is equal to the last...
                                                                               // digit of the ticket
                    
                    System.out.printf(format, ticket, last_digit, ticket_number, remainder, validity); // print ticket statuses
                    
                    if(validity == true) { // if remainder = last digit
                        validTix.write(ticket + '\n'); // save it to the text file and go to next line
                        validTixCounter++;             // add to valid ticket counter
                    }
                    
                    totalTixCounter++; // add to total tickets processed
                }
                
                // print out numbers for number of tickets processed and number correct
                System.out.println("Of the " + totalTixCounter + " tickets processed, " + validTixCounter + " are valid.");
                
                // close Scanners and FileWriters
                checker.close();  // close the input file stream
                validTix.close(); // close the output file stream
        }
        
        catch (FileNotFoundException e) { // file not found
            System.out.println("ERROR: File not found.");
        }
        
        catch (IOException e) { // cannot write a file
            System.out.println("ERROR: File writing problem occured.");
        }
    }
}

