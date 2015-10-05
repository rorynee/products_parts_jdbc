package productParts;
/**
 * This class is used when passing back multiple data to the user.
 * It facilitate ease of access to a Parts object.
 * @author Rory Nee
 *
 */
public class Part {
	
	private int partid;
	private int prodid;
	private String name;
	private String description;
	private double cost;
	
	/**
	 * Default constructor
	 */
	public Part() {
		super();
	}
	/**
	 * Overloaded Constructor
	 * @param partid - Part Id
	 * @param prodid - Product Id
	 * @param name - Part Name
	 * @param description - Part Description
	 * @param cost - Part Cost
	 */
	public Part(int partid, int prodid, String name, String description,
			double cost) {
		super();
		this.partid = partid;
		this.prodid = prodid;
		this.name = name;
		this.description = description;
		this.cost = cost;
	}
	
	/**
	 * Get part id of the current object
	 * @return the partid - part Id of current object
	 */
	public int getPartid() {
		return partid;
	}
	/**
	 * Set the part Id for the current object
	 * @param partid - The part id to set for current object
	 */
	public void setPartid(int partid) {
		this.partid = partid;
	}
	/**
	 * Get product id of the current object
	 * @return the prodid - product Id of current object
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
	 * Get the part name of the current object  
	 * @return the name - part name of current object
	 */
	public String getName() {
		return name;
	}

	/**
	 * Set the part name for the current object
	 * @param name  - the part name to set for current object 
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Get the part description of the current object 
	 * @return the description - part description of current object
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Set the part description for the current object
	 * @param description - the part description to set for the current object
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	/**
	 * Get the part cost of the current object
	 * @return the cost - part cost of current object
	 */
	public double getCost() {
		return cost;
	}
	/**
	 * Set the part cost for the current object
	 * @param cost - the part cost to set for the current object
	 */
	public void setCost(double cost) {
		this.cost = cost;
	}
	/* Return a string version of a Parts object 
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Part [partid=" + partid + ", prodid=" + prodid + ", name="
				+ name + ", description=" + description + ", cost=" + cost
				+ "]";
	}
	
	


}
