// Patrick Temple
// Prof. Whitener
// CSCI165
// 3 February 2020

// LAB 2: GMT.java
// Purpose: to write the time with an apporpriate offset
// for the current time zone

// classes
import java.util.Scanner; // reads input

public class GMT {
	public static void main(String args[]){
        
        Scanner reader = new Scanner(System.in);          // Scanner for input
        long timeInMSeconds = System.currentTimeMillis(); // get current milliseconds
        
        // get offset from user
        System.out.println("Popular Time Zones:\nLos Angeles: -8\nNew York/Tampa, FL: -5");
        System.out.println("Greenwich, England: 0\nTokyo, Japan: 9");
        System.out.print("\nEnter a time offset to GMT: ");
        int timeOffset = reader.nextInt();
        long offsetInMSeconds = (long) timeOffset * (1000 * 60 * 60);
        
        
        // make conversions into hrs, mins, seconds
        // ( adapted from: https://stackoverflow.com/a/625624 )
        int seconds = (int) ((timeInMSeconds / 1000) % 60);
        int minutes = (int) ((timeInMSeconds / (1000 * 60) % 60));
        int hours   = (int) (((timeInMSeconds + offsetInMSeconds) / (1000 * 60 * 60)) % 24);
        
        System.out.printf("Current time: %02d:%02d:%02d%n", hours, minutes, seconds);
        
        
        
	}
}
