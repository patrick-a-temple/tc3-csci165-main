// Patrick Temple
// Prof. Whitener
// CSCI165
// 13 March 2020

// Week 6 Lab: Account
// Purpose: to define the account class.
// Altered for data privacy

public class Account {
	
	// class features/variables
	private int      accountID;
	private Customer customer;
	private double   balance       = 0.00;
	private double   creditLimit   = 0.00;
	private Date     dateCreated;
	private double   discountLevel = 0.00;
	
	// constructors
	public Account() {  } // blank constructor
	
	// customer ID and customer object only
	public Account(int id, Customer customer) {
		
		// set customer and account id
		setAccountID(id);
		setCustomer(new Customer(customer));
		
	}
	
	// full constructor
	public Account(int id, Customer customer,
					double balance, double creditLimit,
					Date date, double discountLevel) {
		
		// set all fields inside the
		// constructor
		setAccountID(id);
		setCustomer(new Customer(customer)); // make a copy of Customer obj
		                                     // to prevent leaks
		setBalance(balance);
		setCreditLimit(creditLimit);
		setDateCreated(new Date(date));      // make a copy of Date obj too
		setDiscountLevel(discountLevel);
		
	}
	
	// copy constructor
	public Account(Account otherAccount) {
		
		this.accountID     = otherAccount.accountID;
		this.customer      = new Customer(otherAccount.customer);
		this.balance       = otherAccount.balance;
		this.creditLimit   = otherAccount.creditLimit;
		this.dateCreated   = new Date(otherAccount.dateCreated);
		this.discountLevel = otherAccount.discountLevel;
		
	}
	
	public void setAccountID(int aid) {
		this.accountID = aid;
	}
	
	public double getAccountID() {
		return this.accountID;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		
		// flip signs if balance is negative
		if(balance < 0.00) {
			balance *= -1;
		}
		
		this.balance = balance;
	}
	
	public void setCustomer(Customer customer) {
		
		this.customer = new Customer(customer); // return a copied version of Customer
		                                        // object to prevent a leak
	}

	public double getCreditLimit() {
		return creditLimit;
	}
	
	public void setCreditLimit(double creditLimit) {
		
		// flip signs if credit limit is negative
		if(creditLimit < 0.00) {
			creditLimit *= -1;
		}
		
		// check if credit limit is over
		// double the balance
		if(creditLimit > (2 * balance)) {
			creditLimit = 2 * balance;
		}
		
		this.creditLimit = creditLimit;
		
	}

	public Customer getCustomer() {
		return new Customer(this.customer);
	}

	public void setDateCreated(Date dateCreated) {
		this.dateCreated = new Date(dateCreated);
	}
	
	public double getDiscountLevel() {
		return this.discountLevel;
	}
	
	public void setDiscountLevel(double discountLevel) {
		
		// ensure the discount rate is not negative
		if(discountLevel < 0.00) {
			discountLevel *= -1;
		}
		
		int    currentYear    = 2020;
		int    yearOfCreation = this.dateCreated.getYear();
		int    yearsActive    = currentYear - yearOfCreation;
		double expectedRate   = (double) yearsActive * 0.02;
		
		// see if the inputted rate is correct,
		// and make sure
		
		if(yearsActive < 0) {
			this.discountLevel = 0.00;
		}
		
		else if(discountLevel == expectedRate) {
			this.discountLevel = discountLevel;
		}
		
		else { // incorrect rate
			
			// set to calculation from
			// above
			this.discountLevel = expectedRate;
			
		}
		
	}
	
	public int compareTo(Account otherAccount) {
		
		// function to compare account id
		if(this.accountID > otherAccount.accountID) {
			return 1;
		}
		else if(this.accountID < otherAccount.accountID) {
			return -1;
		}
		else {
			return 0;
		}
		
		
	}
	
	public boolean equals(Account otherAccount) {
		
		return this.accountID == otherAccount.accountID          &&
			   this.customer.equals(otherAccount.customer)       &&
			   this.balance == otherAccount.balance              &&
			   this.creditLimit == otherAccount.creditLimit      &&
			   this.dateCreated.equals(otherAccount.dateCreated) &&
			   this.discountLevel == otherAccount.discountLevel;
		
	}
	
	@Override
	public String toString() {
		
		String result = String.format("Account ID: %d%nCustomer:%n%n%s%n%nBalance: $%.2f%n"
				+ "Credit Limit: $%.2f%nDate Created: %s%nDiscount Level: %.0f%%",
				this.accountID, this.customer.toString(), this.balance,
				this.creditLimit, this.dateCreated.toString(), ((double) 100.00 * this.discountLevel));
		
		return result; // TODO: Unit Test for this class
		
	}
	
	
}
