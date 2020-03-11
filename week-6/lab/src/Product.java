// Patrick Temple
// Prof. Whitener
// CSCI165
// 13 March 2020

// Week 6 and 7 Lab: Product
// Purpose: to define Product class

public class Product {
	
	private String name;
	private String description;
	private double price;
	private String sku;
	
	// blank constructor
	public Product() {  }
	
	public Product(String loadSKU) {
		setSKU(loadSKU);
	}
	
	public Product(String loadName, String loadDesc,
					double loadPrice, String loadSKU) {
		
		setName(loadName);
		setDescription(loadDesc);
		setPrice(loadPrice);
		setSKU(loadSKU);
		
	}
	
	// copy constructor
	public Product(Product anotherProduct) {
		
		this.name        = anotherProduct.name;
		this.description = anotherProduct.description;
		this.price       = anotherProduct.price;
		this.sku         = anotherProduct.sku;
		
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public void setDescription(String desc) {
		this.description = desc;
	}
	
	public String getDescription() {
		return description;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		
		// is price negative?
		if(isNotNegative(price) == true) {
			this.price = price; // set price normally
		}
		else {
			this.price = 19.99; // set price to a failure
			                    // state price
		}
	}
	
	// helper method: setPrice
	private boolean isNotNegative(double value) {
		
		// if the value transferred is positive,
		// return true; else, return false
		
		if(value >= 0.00) {
			return true;
		}
		else {
			return false;
		}
		
	}
	
	public void setSKU(String sku) {
		
		this.sku = makeSKU(sku);
		
	}
	
	private String makeSKU(String toFormat) {
		
		String firstThree = toFormat.substring(0, 3);
		String processed = toFormat;
		
		// does the SKU start with 001 - 004 or 110?
		if ( !((firstThree.equals("001")) || (firstThree.equals("002")) 
				|| firstThree.equals("003") || (firstThree.equals("004"))
				|| firstThree.equals("110")) ) {
			
			// append 110 to the end of the SKU
			processed = "110" + processed;
			
		}
		
		// make sure it's 10 characters long
		if(processed.length() < 10) {
			
			while(processed.length() != 10) {
				processed += "0";
			}
			
			return processed;
			
		}
		
		else if(processed.length() > 10) {
			
			processed = processed.substring(0, 10);
			return processed;
			
		}
		
		else {
			
			return processed;
			
		}
		
	}
	
	public String getSKU() {
		return this.sku;
	}
	
	public boolean equals(Product differentProduct) {
		
		return this.name.equals(differentProduct.name)                 &&
				this.description.equals(differentProduct.description)  &&
				this.price == differentProduct.price                   &&
				this.sku.equals(differentProduct.sku);
		
	}
	
	@Override
	public String toString() {
		
		String result = String.format("Item: %s%nDescription: %s%nPrice: $%.2f%nSKU: %s",
										this.name, this.description, this.price, this.sku);
		
		return result;
		
	}
	
}
