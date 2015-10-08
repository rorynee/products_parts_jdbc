package productParts;
/**
 * This class is used when passing back the total amount for products to the user.
 * It facilitate ease of access to a TotalProducts object.
 * @author Rory Nee
 *
 */

public class TotalProducts extends Product{
	
	private int numOfParts;
	private double totalCostOfParts;
	
	
	/**
	 * Overloaded Constructor
	 * @param prodid - Product Id - Passed to the super class
	 * @param name - Product Name - Passed to the super class
	 * @param description - Product Description - Passed to the super class
	 * @param numOfParts - Number of parts available
	 * @param totalCostOfParts - Total cost of parts available
	 */
	public TotalProducts(int prodid, String name, String description,int numOfParts, double totalCostOfParts) {
		// Pass the variable to the super class
		super(prodid,name,description);
		this.numOfParts = numOfParts;
		this.totalCostOfParts = totalCostOfParts;
	}

	/**
	 * Get the number of parts associated with the current object
	 * @return the numOfParts - count of associated parts 
	 */
	public int getNumOfParts() {
		return numOfParts;
	}

	/**
	 * Get the total cost of parts associated with the current object
	 * @return the totalCostOfParts - sum of total cost of parts 
	 */
	public double getTotalCostOfParts() {
		return totalCostOfParts;
	}
	
	/* 
	 * Return a string version of a TotalProducts object 
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "TotalProducts [toString()="
				+ super.toString() + ", numOfParts=" + numOfParts
				+ ", totalCostOfParts=" + totalCostOfParts + "]";
	}

}
