// Patrick Temple
// Prof. Whitener
// CSCI165
// 13 March 2020

// Week 6 Lab: Invoice
// Purpose: to make the Invoice class
// improved security

import java.util.ArrayList;

public class Invoice {
	
	// class features/variables
	private String  invoiceNumber;
	private Account account;
	private double  amount;
	private Date    orderDate;
	
	// here's where the products are stored
	// (example of instantiation)
	private ArrayList<Product> products = new ArrayList<Product>();
	
	// constructors
	public Invoice() {  }
	
	// methods
	public Invoice(String inv_num, Account acct, double amount, Date date) {
		
		this.invoiceNumber = inv_num;
		setAccount(new Account(acct));
		this.amount        = amount;
		setDate(new Date(date));
		
	}
	
	// copy constructor
	public Invoice(Invoice otherInvoice) {
		
		this.invoiceNumber = otherInvoice.invoiceNumber;
		this.account       = new Account(otherInvoice.account);
		this.amount        = otherInvoice.amount;
		this.orderDate     = new Date(otherInvoice.orderDate);
		this.products      = new ArrayList<Product>(otherInvoice.products);
		
	}
	
	// getters and setters
	public String getInvoiceNumber() {
		return this.invoiceNumber;
	}
	
	public Account getAccount() {
		return new Account(this.account);
	}
	
	public void setAccount(Account acct) {
		this.account = new Account(acct);
	}
	
	public double getAmount() {
		return this.amount;
	}
	
	public Date getDate() {
		return new Date(this.orderDate);
	}
	
	public void setDate(Date d) {
		this.orderDate = new Date(d);
	}
	
	// add to ArrayList of Products
	public void addProduct(Product p) {
		products.add(new Product(p));
	}
	
	public int compareTo(Invoice otherInvoice) {
		
		// we need to compare amount due
		// for later in the assignment
		if(this.amount > otherInvoice.amount) {
			return 1;
		}
		else if(this.amount < otherInvoice.amount) {
			return -1;
		}
		else {
			return 0;
		}
		
	}
	
	// accept list of products
	public ArrayList<Product> getProducts() {
		return new ArrayList<Product>(this.products);
	}
	
	public boolean equals(Invoice otherInvoice) {
		
		return this.invoiceNumber.equals(otherInvoice.invoiceNumber) &&
			   this.account.equals(otherInvoice.account)             &&
			   this.amount == otherInvoice.amount                    &&
			   this.orderDate.equals(otherInvoice.orderDate);
		
	}
	
	@Override
	public String toString() {
		
		String result = String.format("Invoice Number: %s%nAccount:%n%n%s%n%nAmount Due: $%.2f%n"
						+ "Order Date: %s%nProducts:%n", invoiceNumber, account.toString(),
						amount, orderDate);
		
		for(int i = 0; i < products.size(); i++) {
			result += "\n";
			Product temp = products.get(i);
			result += temp.toString();
		}
		
		return result;
	}
	
	
}
