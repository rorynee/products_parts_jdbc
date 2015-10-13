package productParts;
/**
 * This is the main console class used to interact with the ProductPartsDB class
 * @author Rory Nee
 */
import java.util.ArrayList;
import java.util.Scanner;
public class ConsoleMain  
{
	public static ProductPartsDB dbObject = null;

	/**
	 * Main Method
	 * @param args
	 */
	public static void main(String[] args) 
	{
		
		boolean loop = true;
		Scanner in = new Scanner(System.in); 
		System.out.println("  Welcome to Rorys LapTop Shop");
		System.out.println("--- LapTop Inventory Program ---");

		while(loop) // Loops through the main menu till loop is false.
		{
			System.out.println();
			System.out.println("         Main Menu ");
			System.out.println("------------------------------");
			System.out.println("1. Insert a product or a part");
			System.out.println("2. Update existing products or parts");
			System.out.println("3. Delete a records from product or parts");
			System.out.println("4. Show available products and parts");
			System.out.println("5. Search for Parts for a specified product");
			System.out.println("6. Exit");
			System.out.print("Please Choose an option from the above choices : ");
			// take in relevant data form the user
			if(in.hasNextInt())
			{
				int choice = in.nextInt();

				switch(choice)	// Switch statement to pick the correct method
				{
				case 1: insert(); break;
				case 2: update(); break;
				case 3: delete(); break;
				case 4: show(); break;
				case 5: search(); break;
				case 6: loop = false; break;
				default: loop=true; break;

				}	
			}
			else
			{
				in.nextLine();
				System.out.print("\nInvalid Entry, please choose a number from the menu\n");
				loop = true;
			}
		}
		System.out.println();
		System.out.println("Thank You for using Rory's LapTop Shop Inventory Program"); 
		in.close();
		System.exit(0);
	}
	/**
	 * The following method searches for Parts for a specified product from the database. 
	 */
	public static void search() 
	{
		
		Scanner search_in = new Scanner(System.in); 
		boolean option = true;
		
		System.out.println();
		System.out.println("  Search for Parts for a specified product");
		System.out.println("--------------------------------------------");

		while(option)
		{
			System.out.println();
			System.out.println("To Search for Parts for a specified product we need the following information");
			printProducts();
			System.out.println();
			System.out.print("Please choose a product id number (Prod_ID) listed above to search for : ");
			if(search_in.hasNextInt())
			{
				int prodId = search_in.nextInt();
				
				try {
					// Test the inserted id number
					dbObject = new ProductPartsDB();
					if(dbObject.validId("product",prodId))
					{	// Set up the column headings
						System.out.println();
						System.out.printf("%-10s %-10s %-20s %-50s %-10s\n","Prod_ID","Part_ID","Part Name","Description","Cost");
						System.out.println();
						dbObject = new ProductPartsDB();
						// use the dbObject to access the searchProductById method
						ArrayList<Part> dbParts = dbObject.searchProductById(prodId);
						for(Part part: dbParts){
							System.out.printf("%-10d %-10d %-20s %-50s € %-20.2f\n",part.getPartid(),part.getPartid(),part.getName(),part.getDescription(),part.getCost());
						}
						System.out.println();
						dbObject = new ProductPartsDB();
						// use the dbObject to access the sumPartsByProductId method
						System.out.printf("Total cost of all the parts is € %-10.2f\n",dbObject.sumPartsByProductId(prodId));
						System.out.println();
					}
					else
					{
						System.out.println();
						System.out.println("Invalid Entry, please Try again"); 
					}
				} catch (ExceptionHandler e1) {
					System.out.println("Error: "+e1.getMessage());
				}
			}
			else
			{
				search_in.nextLine(); // delete the end of line pointer/return
				System.out.println("Invalid Entry, please Try again"); 
			}
			System.out.println();
			System.out.println("Would you search for another product?");
			System.out.print("Enter Y for yes and N to return to the main menu : ");
			String repeat = search_in.next();
			//If repeat is not yes - exit the program, If it is Yes -  repeat program
			if(!(repeat.equalsIgnoreCase("Y") || repeat.equalsIgnoreCase("y")))
			{
				option = false;
			}

		}
	}
	/**
	 * The following method shows all Available Products and Parts that are in the database. 
	 */
	public static void show() 
	{
		System.out.println();
		System.out.println("  Available Products and Parts");
		System.out.println("--------------------------------");
		System.out.println();
		// Set up the column headings
		System.out.printf("%-10s %-10s %-50s %-18s %-20s\n","Prod ID","Name","Description","Num of Parts","Total Cost of Parts");
		System.out.println();
		
		try {
			dbObject = new ProductPartsDB();
			// use the dbObject to access the printTotalProducts method
			ArrayList<TotalProducts> dbProdsParts = dbObject.printTotalProducts();
			for(TotalProducts totals: dbProdsParts){
				System.out.printf("%-10d %-10s %-50s %-18d € %-20.2f\n",totals.getProdid(),
																		totals.getName(),
																		totals.getDescription(),
																		totals.getNumOfParts(),
																		totals.getTotalCostOfParts());
			}
			System.out.println();
			
		} catch (ExceptionHandler e1) {
			System.out.println("Error: "+e1.getMessage());
		}
	}
	/**
	 * The following method deletes a product or a part from the database. 
	 */
	public static void delete() 
	{
		
		Scanner in = new Scanner(System.in); 
		boolean option = true;
		int prodId = 0; 
		int partId = 0;
		while(option)
		{
			System.out.println();
			System.out.println("  Delete a records from product or parts");
			System.out.println("------------------------------------------");
			System.out.println("1. Delete a product");
			System.out.println("2. Delete a part");
			System.out.println("3. Back to main menu");
			System.out.print("Please choose from the above choices : ");
			// take in relevant data form the user
			if(in.hasNextInt())
			{
				int insertChoice = in.nextInt();
				if(insertChoice == 1) // user option 1
				{

					System.out.println();
					System.out.println("To delete a product please enter the following information");
					printProducts();
					System.out.print("Please enter a valid product id number (Prod_ID) to be deleted: ");
					// take in relevant data form the user
					if(in.hasNextInt())
					{
						prodId = in.nextInt();
						try {
							// Test the inserted id number
							dbObject = new ProductPartsDB();
							if(dbObject.validId("Product", prodId))
							{
								dbObject = new ProductPartsDB();
								// use the dbObject to access the deleteProductById method
								if(dbObject.deleteProductById(prodId) > 0){
									System.out.println("Product Deleted Successfully");
								}else{
									System.out.println("Product was not deleted. Please try again later.");
								}
								System.out.println();
								// print all products
								printProducts();
								System.out.println();
							}
							else
							{
								System.out.println();
								System.out.println("Product Id was invalid, Please try again\n");
							}
						
						} catch (ExceptionHandler e1) {
							System.out.println("Error: "+e1.getMessage());
						}
					}
					else
					{
						in.nextLine();
						System.out.println("Product Id was invalid, Please try again\n");
					}

				}
				else if(insertChoice == 2) // user option 2
				{

					System.out.println();
					System.out.println("To delete a part please enter the following information");
					printParts();
					System.out.print("Please enter a valid part id number (Part_ID) to be deleted: ");
					// take in relevant data form the user
					if(in.hasNextInt())
					{
						partId = in.nextInt();
						try {
							// Test the inserted id number
							dbObject = new ProductPartsDB();
							if(dbObject.validId("Parts", partId))
							{
								dbObject = new ProductPartsDB();
								// use the dbObject to access the deletePartById method
								if(dbObject.deletePartById(partId) > 0){
									System.out.println("Part Deleted Successfully");
								}else{
									System.out.println("Part was not deleted. Please try again later.");
								}
								System.out.println();
								// print all parts
								printParts();
								System.out.println();
							}
							else
							{
								System.out.println();
								System.out.println("Product Id was invalid, Please try again\n");
							}
						} catch (ExceptionHandler e1) {
							System.out.println("Error: "+e1.getMessage());
						}
					}
					else
					{
						in.nextLine();
						System.out.println("Product Id was invalid, Please try again\n");
					}
				}
				else if(insertChoice == 3) // user option 3
				{
					option = false;
				}
				else // invalid option
				{
					System.out.println("Invalid input please try again");
				}
			}
			else
			{
				in.nextLine();// delete the end of line pointer/return
				System.out.println("Invalid input please try again");
			}
		}
		
	}
	/**
	 * The following method updates a product or a part in the database. 
	 */

	public static void update() 
	{
		Scanner in = new Scanner(System.in); 
		boolean option = true;
		String error = "";
		while(option)
		{
			System.out.println();
			System.out.println("  Update existing products or parts");
			System.out.println("-------------------------------------");
			System.out.println("1. Update a product");
			System.out.println("2. Update a part");
			System.out.println("3. Back to main menu");
			System.out.print("Please choose from the above choices : ");
			// take in relevant data form the user
			if(in.hasNextInt())
			{
				int insertChoice = in.nextInt();
				if(insertChoice == 1)// user option 1
				{
					System.out.println();
					System.out.println("To update a product we need the following information");
					printProducts(); // print all products
					System.out.println();
					System.out.print("Please choose a product id number (Prod_ID)listed above to update : ");
					// take in relevant data form the user
					if(in.hasNextInt())
					{
						int prodId = in.nextInt();
						
						try {
							// Test the inserted id number
							dbObject = new ProductPartsDB();
							if(dbObject.validId("product", prodId))
							{
								// build a Product object
								Product updateProduct = new Product();
								// Add the relevant parts
								updateProduct.setProdid(prodId);
								System.out.println();
								System.out.println("Please choose one of the following: ");
								System.out.println("1. Update the name");
								System.out.println("2. Update the description");
								System.out.println("3. Update both name and description");
								System.out.print("Please enter Choice : ");
								// take in relevant data form the user
								if(in.hasNextInt())
								{
									int updateChoice = in.nextInt();
	
									if(updateChoice == 1) // user option 1
									{
										in.nextLine();
										System.out.println();
										// take in relevant data form the user
										System.out.print("Please enter the updated name : ");
										String tempName = in.nextLine();
										if((tempName.length()-1)<50 && !tempName.isEmpty())
										{
												updateProduct.setName(tempName);
										}
										else
										{
											error += "Invalid 'Name' entered, please try again\n ";
										}
									}
									else if(updateChoice == 2) // user option 2
									{
										in.nextLine();
										System.out.println();
										// take in relevant data form the user
										System.out.print("Please enter the updated description : ");
										String tempdesc = in.nextLine();
										if((tempdesc.length()-1)<50 && !tempdesc.isEmpty())
										{
												updateProduct.setDescription(tempdesc);
										}
										else
										{
											error += "Invalid 'Description' entered, please try again\n ";
										}
	
									}
									else if(updateChoice == 3) // user option 3
									{
										in.nextLine();
										System.out.println();
										// take in relevant data form the user
										System.out.print("Please enter the updated name : ");
										String tempName = in.nextLine();
										if((tempName.length()-1)<50 && !tempName.isEmpty())
										{
											updateProduct.setName(tempName);
										}
										else
										{
											error += "Invalid 'Name' entered, please try again\n ";
										}
										// take in relevant data form the user
										System.out.print("Please enter the updated description : ");
										String tempdesc = in.nextLine();
										if((tempdesc.length()-1)<50 && !tempdesc.isEmpty())
										{
												updateProduct.setDescription(tempdesc);
										}
										else
										{
											error += "Invalid 'Description' entered, please try again\n ";
										}
	
									}
									else
									{
										error += "";
									}
									if(error.equals("") == true)	// If there are no errors do the following
									{
					
										dbObject = new ProductPartsDB();
										// use the dbObject to access the updateProduct method
										if(dbObject.updateProduct(updateProduct) > 0){
											System.out.println("Product Updated Successfully");
										}else{
											System.out.println("Product was not updated. Please try again later.");
										}
										System.out.println();
										printProducts();
									}
									else
									{
										System.out.println(error);
										System.out.println();
									}
								}
								else // invalid choice
								{
									in.nextLine();
									System.out.println("Invalid input please try again");
								}
							}
							else
							{
								System.out.println();
								System.out.println("Invalid Entry, please Try again"); 
							}
						} catch (ExceptionHandler e1) {
							System.out.println("Error: "+e1.getMessage());
						}
					}
					else
					{
						in.nextLine();// delete the end of line pointer/return
						System.out.println("Invalid Entry, please Try again"); 
					}

				}
				else if(insertChoice == 2) // user option 2
				{
		     		System.out.println();
					System.out.println("To update a part we need the following information");
					printParts(); // print all parts
					System.out.println();
					System.out.print("Please choose a part id number (Part_ID) listed above to update : ");
					// take in relevant data form the user
					if(in.hasNextInt())
					{
						int changeId = 0;
						int partId = in.nextInt();
						try {
							// Test the inserted id number
							dbObject = new ProductPartsDB();
							if(dbObject.validId("parts", partId))
							{	// Build the Part object
								Part updatingPart = new Part();
								// Add the relevent parts
								updatingPart.setPartid(partId);
			
								System.out.println();
								System.out.println("Please choose one of the following: ");
								System.out.println("1. Update the name");
								System.out.println("2. Update the description");
								System.out.println("3. Update the cost");
								System.out.println("4. Update the description and cost");
								System.out.println("5. Update the name and cost");
								System.out.println("6. Update the full record");
								System.out.println("7. Change part association");
								System.out.print("Please enter Choice : ");
								// take in relevant data form the user
								if(in.hasNextInt())
								{
									int updateChoice = in.nextInt();
	
									if(updateChoice == 1) // user option 1
									{
										in.nextLine();
										System.out.println();
										// take in relevant data form the user
										System.out.print("Please enter the updated name : ");
										String tempName = in.nextLine();
										if((tempName.length()-1)<50 && !tempName.isEmpty())
										{
											updatingPart.setName(tempName);
										}
										else
										{
											error += "Invalid 'Name' entered, please try again\n ";
										}
									}
									else if(updateChoice == 2) // user option 2
									{
										in.nextLine();
										System.out.println();
										// take in relevant data form the user
										System.out.print("Please enter the updated description : ");
										String tempdesc = in.nextLine();
										if((tempdesc.length()-1)<50 && !tempdesc.isEmpty())
										{
											updatingPart.setDescription(tempdesc);
										}
										else
										{
											error += "Invalid 'Description' entered, please try again\n ";
										}
	
									}
									else if(updateChoice == 3) // user option 3
									{
										in.nextLine();
										System.out.println();
										// take in relevant data form the user
										System.out.print("Please enter the updated cost : € ");
										if(in.hasNextDouble())
										{	
											double tempCost = in.nextDouble();
											if(tempCost > 0)
											{
												updatingPart.setCost(tempCost);
											}
											else
											{
												error += "Invalid 'Cost' entered, please try again\n ";
											}
										}
										else
										{
											in.nextLine();
											error += "Invalid 'Cost' entered, please try again\n ";
										}
									}
									else if(updateChoice == 4) // user option 4
									{
										in.nextLine();
										System.out.println();
										// take in relevant data form the user
										System.out.print("Please enter the updated description : ");
										String tempdesc = in.nextLine();
										if((tempdesc.length()-1)<50  && !tempdesc.isEmpty())
										{
											updatingPart.setDescription(tempdesc);
										}
										else
										{
											error += "Invalid 'Description' entered, please try again\n ";
										}
										System.out.print("Please enter the updated cost : € ");
										// take in relevant data form the user
										if(in.hasNextDouble())
										{
											double tempCost = in.nextDouble();
											if(tempCost > 0)
											{
													updatingPart.setCost(tempCost);
											}
											else
											{
												error += "Invalid 'Cost' entered, please try again\n ";
											}
										}
										else
										{
											in.nextLine();
											error += "Invalid 'Cost' entered, please try again\n ";
										}
									}
									else if(updateChoice == 5) // user option 5
									{
										in.nextLine();
										System.out.println();
										System.out.print("Please enter the updated name : ");
										// take in relevant data form the user
										String tempName = in.nextLine();
										if((tempName.length()-1)<50  && !tempName.isEmpty())
										{
											updatingPart.setName(tempName);
											
										}
										else
										{
											error += "Invalid 'Name' entered, please try again\n ";
										}
										System.out.print("Please enter the updated cost : € ");
										// take in relevant data form the user
										if(in.hasNextDouble())
										{
											double tempCost = in.nextDouble();
											if(tempCost > 0)
											{
												updatingPart.setCost(tempCost);
											}
											else
											{
												error += "Invalid 'Cost' entered, please try again\n ";
											}
										}
										else
										{
											in.nextLine();
											error += "Invalid 'Cost' entered, please try again\n ";
										}
	
	
									}
									else if(updateChoice == 6) // user option 6
									{
										in.nextLine();
										System.out.println();
										System.out.print("Please enter the updated name : ");
										// take in relevant data form the user
										String tempName = in.nextLine();
										if((tempName.length()-1)<50  && !tempName.isEmpty())
										{
												updatingPart.setName(tempName);
										}
										else
										{
											error += "Invalid 'Name' entered, please try again\n ";
										}
										System.out.print("Please enter the updated description : ");
										// take in relevant data form the user
										String tempdesc = in.nextLine();
										if((tempdesc.length()-1)<50  && !tempdesc.isEmpty())
										{
											updatingPart.setDescription(tempdesc);
										}
										else
										{
											error += "Invalid 'Description' entered, please try again\n ";
										}
										System.out.print("Please enter the updated cost : € ");
										// take in relevant data form the user
										if(in.hasNextDouble())
										{
											double tempCost = in.nextDouble();
											if(tempCost > 0)
											{
												updatingPart.setCost(tempCost);
											}
											else
											{
												error += "Invalid 'Cost' entered, please try again\n ";
											}
										}
										else
										{
											in.nextLine();
											error += "Invalid 'Cost' entered, please try again\n ";
										}
	
									}else if(updateChoice == 7){ // user option 7
										in.nextLine();
										System.out.println();
										printProducts();
										System.out.print("Please product id (Prod_ID) to associate to : ");
										// take in relevant data form the user
										if(in.hasNextInt())
										{ 
											dbObject = new ProductPartsDB();
											int prodId = in.nextInt();
											if(dbObject.validId("Product",prodId )){
												changeId = prodId;
											}
											else
											{
												error += "Invalid 'Cost' entered, please try again\n ";
											}
										}
										else
										{
											in.nextLine();
											error += "Invalid 'Cost' entered, please try again\n ";
										}
										
									}
									else
									{
										error += "";
									}
									if(error.equals("") == true)	// If there are no errors do the following
									{
										if(changeId == 0){
											dbObject = new ProductPartsDB();
											// use the dbObject to access the updatePart method
											if(dbObject.updatePart(updatingPart) > 0){
												System.out.println("Part Updating Successfully");
											}else{
												System.out.println("Part was not updated. Please try again later.");
											}
										}else{
											dbObject = new ProductPartsDB();
											// use the dbObject to access the changePartAssociation method
											if(dbObject.changePartAssociation(partId, changeId) > 0){
												System.out.println("Part Changed Successfully");
											}else{
												System.out.println("Part was not changed. Please try again later.");
											}
										}
										printParts(); // print all parts
									}
									else
									{
										System.out.println(error);
										System.out.println();
									}
								}
								else // invalid option
								{
									in.nextLine();
									System.out.println("\nInvalid input please try again");
								}
							}
							else // invalid option
							{
								System.out.println();
								System.out.println("\nInvalid Entry, please Try again"); 
							}
						} catch (ExceptionHandler e1) {
							System.out.println("Error: "+e1.getMessage());
						}
					}
					else // invalid option
					{
						in.nextLine();
						System.out.println("\n Invalid Entry, please Try again"); 
					}
				}
				else if(insertChoice == 3)
				{
					option = false;
				}
				else
				{
					System.out.println("Invalid input please try again");
				}
			}
			else
			{
				in.nextLine(); // delete the end of line pointer/return
				System.out.println("\nInvalid input please try again");
			}
		}
	}
	/**
	 * The following method inserts a product or a part into the database. 
	 */
	public static void insert() 
	{
		Scanner in = new Scanner(System.in);
		boolean option = true;
		String name = "";
		String description = "";
		double cost = 0;
		String error = "";
		int prodId = 0;
		while(option)
		{
			System.out.println();
			System.out.println("Insert a product or a part");
			System.out.println("--------------------------");
			System.out.println("1. Insert a product");
			System.out.println("2. Insert a part");
			System.out.println("3. Back to main menu");
			System.out.print("Please choose from the above choices : ");
			// take in relevant data form the user
			if(in.hasNextInt())
			{
				int insertChoice = in.nextInt();
				if(insertChoice == 1) // user option 1
				{
					in.nextLine();
					System.out.println();
					System.out.println("To insert a product we need the following information");
					System.out.println();
					System.out.print("Please enter the name of the product : ");
					String tempName = in.nextLine();
					// take in relevant data form the user
					if((tempName.length()-1)<50 && !tempName.isEmpty())
					{
						name = tempName;
					}
					else
					{
						error += "Invalid 'Name' entered, please try again\n ";
					}
					System.out.print("Please give a description of the product : ");
					// take in relevant data form the user
					String tempdesc = in.nextLine();
					if((tempdesc.length()-1)<50  && !tempdesc.isEmpty())
					{
						description = tempdesc;
					}
					else
					{
						error += "Invalid 'Description' entered, please try again\n ";
					}

					if(error.equals("") == true)
					{
						try {
							dbObject = new ProductPartsDB();
							// use the dbObject to access the insertProduct method
							if(dbObject.insertProduct(name, description) > 0){
								System.out.println("Product Inserted Successfully");
							}else{
								System.out.println("Product was not inserted. Please try again later.");
							}
							System.out.println();
							printProducts();
							System.out.println();
						} catch (ExceptionHandler e1) {
							System.out.println("Error: "+e1.getMessage());
						}
					}
					else
					{
						System.out.println(error);
						System.out.println();
					}
				}
				else if(insertChoice == 2) // user option 2
				{

					System.out.println();
					System.out.println("To insert a part we need the following information");
					printProducts(); // print all products
					System.out.print("Please enter a valid product id number (Prod_ID) that relates to the part as listed above: ");
					// take in relevant data form the user
					if(in.hasNextInt())
					{
						prodId = in.nextInt();
						try {
							dbObject = new ProductPartsDB();
							// Test the id that it is valid
							if(dbObject.validId("Product", prodId))
							{
								in.nextLine();
								System.out.println();
								System.out.print("Please enter the name of the part : ");
								// take in relevant data form the user
								String tempName = in.nextLine();
								if((tempName.length()-1)<50  && !tempName.isEmpty())
								{
									name = tempName;
								}
								else
								{
									error += "Invalid 'Name' entered, please try again\n ";
								}
	
								System.out.print("Please give a description of the part : ");
								// take in relevant data form the user
								String tempdesc = in.nextLine();
								if((tempdesc.length()-1)<50 && !tempdesc.isEmpty())
								{
									description = tempdesc;
								}
								else
								{
									error += "Invalid 'Description' entered, please try again\n ";
								}
								System.out.print("Please enter the cost of the part : € ");
								// take in relevant data form the user
								if(in.hasNextDouble())
								{
									double costTemp = in.nextDouble();
									if(costTemp>0)
									{
										cost = costTemp;
									}
									else
									{
										error += "Invalid 'Cost' entered, please try again\n ";
									}
								}
								else
								{
									in.nextLine();
									error += "Invalid 'Cost' entered, please try again\n ";
								}
	
							}
							else
							{
								System.out.println();
								error = "Product Id was invalid, Please try again\n";
							}
						} catch (ExceptionHandler e1) {
							System.out.println("Error: "+e1.getMessage());
						}
					}
					else
					{
						in.nextLine();
						error = "Product Id was invalid, Please try again\n";
					}

					if(error.equals("") == true)
					{
						try {
							dbObject = new ProductPartsDB();
							// use the dbObject to access the insertPart method
							if(dbObject.insertPart(prodId, name, description, cost) > 0){
								System.out.println("Part Inserted Successfully");
							}else{
								System.out.println("Part was not inserted. Please try again later.");
							}
							System.out.println();
						} catch (ExceptionHandler e1) {
							System.out.println("Error: "+e1.getMessage());
						}
						printParts(); // print all parts
						System.out.println();
					}
					else
					{
						System.out.print(error);
						System.out.println();
					}
					error="";
				}
				else if(insertChoice == 3) // user option 3
				{
					option = false;
				}
				else // invalis option
				{
					System.out.println("Invalid input please try again");
				}
			}
			else
			{
				in.nextLine(); // delete the end of line pointer/return
				System.out.println("Invalid input please try again");
			}
		}
	}
	/**
	 * This method prints out the products table to screen.
	 */
	public static void printProducts()
	{	// Set up the column headings
		System.out.println();
		System.out.printf("%-10s %-20s %-50s\n","Prod_ID","Name","Description");
		System.out.println();
		
		try {
			dbObject = new ProductPartsDB();
			// use the dbObject to access the printProducts method
			ArrayList<Product> dbProds = dbObject.printProducts();
			for(Product prod: dbProds){
				System.out.printf("%-10d %-20s %-50s\n",prod.getProdid(),
														prod.getName(),
														prod.getDescription());
			}
			System.out.println();
			
		} catch (ExceptionHandler e1) {
			System.out.println("Error: "+e1.getMessage());
		}
	}
	/**
	 * This method prints out the parts table to screen.
	 */
	public static void printParts()
	{
		// Set up the column headings
		System.out.println();
		System.out.printf("%-10s %-10s %-20s %-50s %-20s\n","Part_ID","Prod_ID","Name","Description","Cost");
		System.out.println();
		
		try {
			dbObject = new ProductPartsDB();
			// use the dbObject to access the printParts method
			ArrayList<Part> dbParts = dbObject.printParts();
			for(Part part : dbParts){
				System.out.printf("%-10d %-10d %-20s %-50s € %-20.2f\n",
											part.getPartid(),
											part.getProdid(),
											part.getName(),
											part.getDescription(),
											part.getCost());
			}
			System.out.println();
			
		} catch (ExceptionHandler e1) {
			System.out.println("Error:"+e1.getMessage());
		}
	}
}