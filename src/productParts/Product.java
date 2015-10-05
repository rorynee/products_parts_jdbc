package productParts;
/**
 * This class is used when passing back multiple data to the user.
 * It facilitate ease of access to a Product object.
 * @author Rory Nee
 *
 */
public class Product {
	
	private int prodid;
	private String name;
	private String description;
	private int numOfParts;
	private double totalCostOfParts;
	
	/**
	 * Default constructor
	 */
	public Product() {
		super();
	}
	
	/**
	 * Overloaded Constructor
	 * @param prodid - Product Id
	 * @param name - Product Name
	 * @param description - Product Description
	 */
	public Product(int prodid, String name, String description) {
		super();
		this.prodid = prodid;
		this.name = name;
		this.description = description;
		this.numOfParts = 0;
		this.totalCostOfParts = 0;
	}

	/**
	 * Get product id of the current object
	 * @return the prodid - Id of current object
	 */
	public int getProdid() {
		return prodid;
	}

	/**
	 * Set the product Id for the current object
	 * @param prodid - The product id to set for current object
	 */
	public void setProdid(int prodid) {
		this.prodid = prodid;
	}

	/**
	 * Get the product name of the current object  
	 * @return the name - product name of current object
	 */
	public String getName() {
		return name;
	}

	/**
	 * Set the product name for the current object
	 * @param name  - the product name to set for current object 
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Get the product description of the current object 
	 * @return the description - product description of current object
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Set the product description for the current object
	 * @param description - the product description to set for the current object
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/* 
	 * Return a string version of a Product object 
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return "Product [prodid=" + prodid + ", name=" + name
				+ ", description=" + description + "]";
	}

	/**
	 * Get the number of parts associated with the current object
	 * @return the numOfParts - count of associated parts 
	 */
	public int getNumOfParts() {
		return numOfParts;
	}

	/**
	 * Set the number of parts associated with the current object
	 * @param numOfParts - the number of parts to set for current object
	 */
	public void setNumOfParts(int numOfParts) {
		this.numOfParts = numOfParts;
	}

	/**
	 * Get the total cost of parts associated with the current object
	 * @return the totalCostOfParts - sum of total cost of parts 
	 */
	public double getTotalCostOfParts() {
		return totalCostOfParts;
	}

	/**
	 * Set the total cost of parts associated with the current object
	 * @param totalCostOfParts - sum of total cost of parts to set for current object
	 */
	public void setTotalCostOfParts(double totalCostOfParts) {
		this.totalCostOfParts = totalCostOfParts;
	}
	/**
	 * This method show a string representation of a product including the number of parts
	 * and the total cost of the parts.
	 * @return String - Total Products 
	 */
	public String totalProductToString() {
		return "Product [prodid=" + prodid + ", name=" + name
				+ ", description=" + description + ", numOfParts="+ numOfParts +"" +
						", totalCostOfParts="+ totalCostOfParts +"]";
	}
	
	
	
}
