// Patrick Temple
// Prof. Whitener
// CSCI165
// 23 January 2019

// LAB 1: String arguments and for loops
// Purpose: to learn command line arguments and loops

public class HelloWorld2 {
    
    public static void main(String[] args) {
    
        System.out.println("Here are the strings inputted into the command line:\n");
        // added another line break for better space
        
        for (String s : args) //print each string argument inputted in terminal
            System.out.println("String argument: " + s);
        
        System.out.println("\nLooks like I am out of strings.");
        
    }
    
}
