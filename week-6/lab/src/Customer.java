// Patrick Temple
// Prof. Whitener
// CSCI165
// 13 March 2020

// Week 6/7 Lab: Customer:
// Purpose: to define the customer class

// imported classes
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class Customer {
	
	// class element variables
	private String  firstName;
	private String  lastName;
	private String  email           = "None on File";
	private Address mailingAddress;
	
	// constructors
	public Customer() {  } // blank constructor
	
	// complete constructor
	public Customer(String fName, String lName,
					String email, Address mAddress) {
		
		setFirstName(fName);
		setLastName(lName);
		setEmail(email);
		setMailingAddress(mAddress);
		
	}
	
	public Customer(String fName, String lName) {
		
		setFirstName(fName);
		setLastName(lName);
		
	}
	
	// getters and setters

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		
		if(emailIsValid(email) == true) {
			this.email = email;
		}
		else {
			this.email = "None on File";
		}
		
	}
	
	// domain validation for setting email
	private boolean emailIsValid(String email) { 
		
		// method used adapted from
		// https://youtu.be/OOdO785p3Qo
		// but was changed to work with 2-3
		// character top-level domain limitation
		
		// add our RegEx and establish a relevant pattern
		final String EMAIL_REGEX = "^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,3}$";
		Pattern emailPattern     = Pattern.compile(EMAIL_REGEX, Pattern.CASE_INSENSITIVE);
		
		Matcher validator        = emailPattern.matcher(email); // run the validation test
		
		// see if it's valid
		if(validator.find() == true) {
			return true;
		}
		else {
			return false;
		}
		
	}

	public Address getMailingAddress() {
		return mailingAddress;
	}

	public void setMailingAddress(Address mailingAddress) {
		this.mailingAddress = mailingAddress;
	}
	
	public boolean equals(Customer anotherCustomer) {
		
		return  this.firstName.equals(anotherCustomer.firstName) &&
				this.lastName.equals(anotherCustomer.lastName)  &&
				this.email.equals(anotherCustomer.email)        &&
				this.mailingAddress.equals(anotherCustomer.mailingAddress);
		
	}
	
	@Override
	public String toString() {
		
		String result = String.format("Name: %s %s%nEmail: %s%nMailing Address: %s",
						this.firstName, this.lastName, this.email,
						this.mailingAddress.toString());
		return result; 
				
	}
	
	

}
