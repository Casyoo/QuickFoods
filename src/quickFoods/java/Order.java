package quickFoods.java;
//import classes and packages
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

//Class for all order related constructors and methods
public class Order {
	//attributes to be used in constructor
	String mealName;
	int mealAmount;
	double mealPrice;
	String mealSpecialInstructions;
	
	//constructor function to create Order object
	public Order(String mealName, int mealAmount, Double mealPrice, String mealSpecialInstructions) {
		this.mealName = mealName;
		this.mealAmount=mealAmount;
		this.mealPrice=mealPrice;
		this.mealSpecialInstructions = mealSpecialInstructions;
	}; 
	
	//method to be used to create order information to write to invoice.txt statement
	static String completeOrderDataToString(ArrayList<Order> allMealsOrdered) {
		double invoiceTotal = 0.00;
		String orderDetailString="";
			for(Order meal : allMealsOrdered) { 
				//for loop to assign order details to single string and calculate order total
				orderDetailString = orderDetailString +  meal.mealAmount + " x " + meal.mealName + " @ R " + String.format("%.2f",meal.mealPrice) + "  \n";
				invoiceTotal = invoiceTotal + (meal.mealAmount * meal.mealPrice);
				orderDetailString=orderDetailString + "\n" + "Special instructions received:\n" + meal.mealSpecialInstructions;
				orderDetailString=orderDetailString +"\n\n"+"Total: R" + String.format("%.2f", invoiceTotal) + "\n"; 
			}	
	return orderDetailString;
	};
	
	//method to process new order
	static void processNewOrder() {
		//constructor functions
		Customer newCustomer;
		Restaurant newRestaurant;
		//declare and initiate variables
		String orderNumber;
		String assignedDriver = "";
		//increase order number to be used on invoice and in invoice filename
		orderNumber=increaseOrderCounter();
		//obtain driver information to create Drivers object from .txt file.
		Drivers.driversDetails();
		//obtain customer information to create Customer object from user input
		newCustomer = Customer.addNewCustomer();
		//obtain restaurant information to create REstaurant object from user input
		newRestaurant = Restaurant.addNewRestaurant();
		//add all meals (single meal/multiple meals) to ArrayList
		ArrayList<Order> allMealsOrdered = addNewOrder(); 
		//run method from Order class in order to convert all order data from ArrayList to formatted String element 
		String orderDetailString = Order.completeOrderDataToString(allMealsOrdered);
		//add Driver object information to ArrayLIst
		ArrayList<Drivers> infoDrivers = Drivers.addDrivers();
		assignedDriver = Customer.compareCustomerDriversLocation(newCustomer, infoDrivers);
		//write all assigned information to invoice file
		writeInvoice(orderNumber,newCustomer,newRestaurant,orderDetailString,assignedDriver );
	}
	
	//method to add new order
	static ArrayList<Order> addNewOrder() {
		//Declare and initiate variables and objects;
		ArrayList<Order> allMealsOrdered = new ArrayList<Order>();
		String anotherMeal = "y";
		String mealName = "";
		String mealSpecialInstructions = "";
		int mealAmount = 0;
		Double mealPrice = 0.0;
			//while loop to ask user if the want to add another meal to the customer order
			while(anotherMeal.equals("y")) {
					Scanner scanner = new Scanner(System.in);
					System.out.println("Please enter the meal to order");
					mealName = scanner.nextLine(); 
					System.out.println("Please enter the amount of the " + mealName + " to order. (Number(s) only)");
					mealAmount = Integer.parseInt(scanner.nextLine());
					System.out.println("Please enter the price of the " + mealName + "(No currency (R) symbol please)");
					mealPrice = Double.parseDouble(scanner.nextLine());
					System.out.println("Please enter any special instructions regarding your meal: " + mealName);
					mealSpecialInstructions = scanner.nextLine();
					//create new Order object
					Order mealOrdered = new Order(mealName, mealAmount, mealPrice, mealSpecialInstructions); 
					//add order object to ArrayList	
					allMealsOrdered.add(mealOrdered);
					anotherMeal = addMoreMealsToOrder(scanner);
			}  				
		System.out.println("Order Complete, thank you!");
		return allMealsOrdered;
	}
	
	//method to ask user if they want to add more meals to the order
	private static String addMoreMealsToOrder(Scanner scanner) {
		String anotherMeal;
		System.out.println("Would you like to order another meal?(Y for yes, N for no)");
		anotherMeal = scanner.nextLine().toLowerCase();
		return anotherMeal;
	};
	
	//method to write information from customer, restaurant, driver and order to invoice.txt file
	static String writeInvoice(String orderNumber, Customer newCustomer, Restaurant newRestaurant, String orderDetailString,String assignedDriver){
		File invoice = new File ("invoice" + orderNumber +".txt");
		try {
			FileWriter writer = new FileWriter(invoice, true);
			writer.write(
						"Order number	:" + orderNumber + "\n" +
						"Customer		:" + newCustomer.name + "\n" +
						"Email			:" + newCustomer.customerEmail + "\n" +
						"Phone number	:" + newCustomer.contactNumber + "\n" +
						"Location		:" + newCustomer.location + "\n" +
						"\nYou have ordered the following from " + newRestaurant.name + " in "+ newRestaurant.location + ":\n" +
						"\n"+ 
						orderDetailString + "\n" +
						assignedDriver + "\n" + 
						newCustomer.customerAddress + "\n" + 
						"\nIf you need to contact the restaurant, their number is: " + newRestaurant.contactNumber 
						);
			writer.close();		
		} 
		catch (IOException e) {
				System.out.println("Error writing data to invoice.");
		}
		return orderNumber;
	}
	
	//method to increase order counter 
	static String increaseOrderCounter() {
		//declare local variables
		String orderNumber = "";
		int orderNumberInt = 0;
		//try catch to increase counter value in relevant file. 
		try {
			File file = new File ("orderCounter.txt");
			Scanner scanner = new Scanner (file);
				while (scanner.hasNextLine()){			
					orderNumber = scanner.nextLine(); 
					orderNumberInt = Integer.parseInt(orderNumber);
					orderNumberInt ++;
				}			
				//overwrite existing counter value with incremented value
				FileWriter writer = new FileWriter(file);
				String orderNumberToString = Integer.toString(orderNumberInt);
				writer.write(orderNumberToString);
				writer.close();		
			} 
			catch (Exception e) {
				System.out.println("Counter incrementer has encountered a problem.");
			}
			return orderNumber;	
		}	
}