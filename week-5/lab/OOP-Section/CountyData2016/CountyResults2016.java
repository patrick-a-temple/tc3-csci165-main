// Patrick Temple
// Prof. Whitener
// CSCI165
// 28 February 2020

// Week 5 Lab: CountyResults2016
// Purpose: to define a CountyResults2016
// object, and to include useful ways to
// get information about the object.

public class CountyResults2016 {
	
	// fields
	private double demVotes = 0;           // holds democratic votes
	private double gopVotes = 0;           // holds GOP votes
	private double totalVotes = 0;         // holds total votes
	private double percentDem = 0;         // holds % dem...
	private double percentGOP = 0;         // and % GOP votes
	private double difference = 0;         // holds difference btwn. GOP and Dem
	private double percentDifference = 0;  // holds the difference btwn. GOP and Dem
	private String stateAbbreviation = ""; // holds the state abbreviation
	private String county = "";            // holds the county name
	private int fips = 0; // see https://en.wikipedia.org/wiki/FIPS_county_code
	
	// constructors
	public CountyResults2016() {} // blank constructor
	
	// the complete constructor (sorry for the
	// length)
	public CountyResults2016(double dVotes, double gVotes, double tVotes,
			                 double pDem, double pGOP, double diff, double pDiff,
			                 String sAbbrev, String cty, int fipsNum) {
		
		// assign all variables to the appropriate
		// variable inside of the CR2016 object
		demVotes = dVotes;
		gopVotes = gVotes;
		totalVotes = tVotes;
		percentDem = pDem;
		percentGOP = pGOP;
		difference = diff;
		percentDifference = pDiff;
		stateAbbreviation = sAbbrev;
		county = cty;
		fips = fipsNum;
		
	} // end CountyResults2016 (complete constructor)
	
	// return methods that return values
	public double getTotalVotes() {
		return totalVotes;
	} // end public double
	
	public double getDemVotes() {
		return demVotes;
	} // end public double
	
	public double getGOPVotes() {
		return gopVotes;
	} // end public double
	
	public double getDifference() {
		return difference;
	} // end public double
	
	public double getPercentDifference() {
		return percentDifference;
	} // end public double
	
	public String getState() {
		return stateAbbreviation;
	} // end public String
	
	public String getCounty() {
		return county;
	} // end public String
	
	// toString method
	
	@Override
	public String toString() {
		
		// round the three percentages used
		double pDem = 100 * percentDem;
		double pGOP = 100 * percentGOP;
		double pDiff = percentDifference;
		pDem = Math.round(pDem * 100.0) / 100.0;
		pGOP = Math.round(pGOP * 100.0) / 100.0;
		pDiff = Math.round(pDiff * 100.0) / 100.0;
		
		
		// print our results beautifully
		String result = String.format("%s, %s:%n%f GOP votes (%f%%), %f Democratic votes (%f%%)%n"
				+ "with a total vote count of %f.%n%s party won by %f (%f%% of vote)",
				county, stateAbbreviation, gopVotes, pGOP, demVotes, pDem, totalVotes,
				((gopVotes > demVotes) ? "Republican/GOP" : "Democratic"), difference,
				pDiff);
		
		return result; // return this string
	} // end toString
	
	
}  // end class
