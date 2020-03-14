import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class Driver {

	public static void main(String[] args) {
		
		// get the list of all products
		ArrayList<Product> products = new ArrayList<Product>();
		products = new ArrayList<Product>(getProducts());
		
		// get all addresses
		// this was the first part of my problems:
		// running this function caused an OutOfMemoryError
		// and high CPU usage
		ArrayList<Address> addresses = new ArrayList<Address>();
		addresses = new ArrayList<Address>(getAddresses());
		System.out.println("Finished");
	}
	
	public static ArrayList<Product> getProducts(){
		
		// the ArrayList to return
		ArrayList<Product> list = new ArrayList<Product>();
		
		try {
			// scanner and file objects
			File productList = new File("products.txt");
			Scanner getter = new Scanner(productList);
			
			// assuming everything's OK with the
			// File and Scanner, go through products.txt
			// to turn the data into Products
			for(int i = 0; i < 1000; i++) {
				
				String currentLine = getter.nextLine(); // get the next line
				String[] splitLine = new String[4];     // create array to store values
				                                        // after separated from their tabs
				
				// from the current line, split the
				// data from the tabs
				splitLine = currentLine.split("\t");
				
				// let's load everything
				String pName = splitLine[0];                 // get the name
				String pDesc = splitLine[1];                 // get the product description
				double price = Double.valueOf(splitLine[2]); // convert price from double to String
				                                             // then set the price
				String pSKU  = splitLine[3];                 // set the SKU
				
				// make a temporary object and then
				// add it to the ArrayList
				Product temp = new Product(pName, pDesc, price, pSKU);
				list.add(temp);
			}
			
			getter.close(); // close the reader
		} // end try
		
		
		
		catch (FileNotFoundException e) { // products.txt is MIA
			System.out.println("ERROR: products.txt is missing.");
		}
		return new ArrayList<Product>(list);
		
	}
	
	public static ArrayList<Address> getAddresses(){
		
		ArrayList<Address> list = new ArrayList<Address>();
		
		try {
			
			// we are getting only the addresses from the
			// customers.txt file so we can pass these when
			// we set up the customer
			File customerFile = new File("customers.txt");
			Scanner getter = new Scanner(customerFile);
			
			// now let's get the addresses
			for(int i = 0; i < 1000; i++) {
				
				String currentLine = getter.nextLine();
				String[] splitLine = new String[6];
				splitLine = currentLine.split("\t");
				
				// only get info about addresses
				String roadFragOne = splitLine[3]; // name of road
				                                   // (ie. Keystone, not Keystone AVE.)
				String roadFragTwo = splitLine[4]; // type of road (ex. ave, st, rd, etc.)
				
				String street = roadFragOne + " " + roadFragTwo; // concatenation time
				String zip = splitLine[5]; // get the zip code
				
				// now we can make an object
				Address temp = new Address(street, zip);
				list.add(temp);
				
			}
			getter.close();
		}
		
		catch (FileNotFoundException e) {
			System.out.println("ERROR: customers.txt is missing");
		}
		
		return new ArrayList<Address>(list);
		
	}

}
