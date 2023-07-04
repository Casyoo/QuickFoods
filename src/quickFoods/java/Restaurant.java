package quickFoods.java;
//import required class
import java.util.Scanner;

//sub-class of GeneralInformation super class
public class Restaurant extends GeneralInformation {
	//constructor function to create Drivers object
	public Restaurant(String name, String contactNumber, String location) {
		super(name, contactNumber, location);
	}
	
	//method to create new Restaurant object
	static Restaurant addNewRestaurant(){
		//Scanner to obtain customer information required for constructor 
		Scanner userInput = new Scanner(System.in);
		System.out.println("Please enter restaurant name.");
		String name = userInput.nextLine();
		System.out.println("Please enter restaurant contact number.");
		String contactNumber = userInput.nextLine();
		System.out.println("Please enter the location of the restaurant?(Capitalized first letter please)");
		String location = userInput.nextLine();
		//Constructor to create new restaurant object
		Restaurant newRestaurant = new Restaurant(name,contactNumber,location);
	return newRestaurant;
	}
}