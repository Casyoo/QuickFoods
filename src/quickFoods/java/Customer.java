package quickFoods.java;
//import required classes
import java.util.*;
//sub class of General Information containing methods and functions of a general (in relation to other classes) nature
public class Customer extends GeneralInformation {
	//attributes
	String customerAddress;
	String customerEmail;
	
	//constructor function to create Customer object
	public Customer(String name, String contactNumber, String location, String customerAddress, String customerEmail) {
		super(name, contactNumber, location);
		this.customerAddress = customerAddress;
		this.customerEmail = customerEmail;
	}
	
	//method to create new Customer object
	static Customer addNewCustomer(){
		//Scanner to obtain customer information required for constructor 
		Scanner userInput = new Scanner(System.in);
		System.out.println("Please enter customer name");
		String name = userInput.nextLine();
		System.out.println("Please enter your customer contact number.");
		String contactNumber = userInput.nextLine();
		System.out.println("Please enter customer location.");
		String location = userInput.nextLine();
		System.out.println("Please enter customer address.");
		String address = userInput.nextLine();
		System.out.println("Please enter customer email address.");
		String email = userInput.nextLine();
		//Constructor to create new customer object
		Customer newCustomer = new Customer(name,contactNumber,location,address, email);					
	return newCustomer;
	}
	
	//method to compare customer information with restaurant information
	static String compareCustomerDriversLocation(Customer newCustomer, ArrayList<Drivers> driverInfo){
		//initiate variables
		int a=0;
		int b=0;
		int d=0;	
		String assignedDriver = "";
		String customerLocation = newCustomer.location;
		String driverLocation;
		//ArrayLists to be used to compare drivers
		ArrayList<Drivers> driversMatchingLocation = new ArrayList<Drivers>();
		ArrayList<Drivers> driversMatchingLocationCopy = new ArrayList<Drivers>();
			//for loop and nested if statement to compare customer location and driver location
			//adds driver information to ArrayList on positive match
			for(a=0;a<driverInfo.size();a++) {
				driverLocation = (driverInfo.get(a).location);
				if(driverLocation.equals(newCustomer.location)){	/***/
					driversMatchingLocation.add(driverInfo.get(a));
					driversMatchingLocationCopy.add(driverInfo.get(a));
				}
			}
			
			//if statement to determine assigned driver variable on negative location match
			if (driversMatchingLocation.size()==0) {
				assignedDriver = "Sorry, no delivery drivers are avaiable in your area ("+customerLocation+"):";
			}
			
			else {
				assignedDriver = (driversMatchingLocation.get(0).name).toString() + " is nearest to the restaurant "
	             	+ "and will be delivering your order to you at: \n";
				d++;
				
			}
			//else if for loop and nested if statements to determine assigned driver variable on positive location match
				
			
			
			return  assignedDriver;
			};
}