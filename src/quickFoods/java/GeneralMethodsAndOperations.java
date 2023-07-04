package quickFoods.java;
//import required classes
import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;

//class for methods and operations in general
public class GeneralMethodsAndOperations {

	//method to loop through menu until numeral corresponding to quit is entered
	static void menuLoop() {
		//initiate variable
		int userChoice;
			do {
				userChoice = mainMenu();
			}
			while(userChoice !=4);
		}
		
	//method to display menu and read user choice
	private static int mainMenu(){
		//initiate variables
		int userChoice = 0;
		//while loop to display menu 
		while((userChoice < 1) || (userChoice >4)){
			System.out.println(""
					+ "\nMAIN MENU:" +"\n"
					+ "1.	Instructions." +"\n"
					+ "2.	First time setup." +"\n"
					+ "3.	Process new order." +"\n"
					+ "4.	Quit" +"\n"
					+ "\nPlease select a menu item. Enter the corresponding number and press enter to continue.\n");
				//determine user choice through userMenuChoice method
				userChoice = userMenuChoice();
				//runs respective operations based on userChoice
				operation(userChoice);
			}
			return userChoice;
		}
		
	//method to read user choice for menu operation
	private static int userMenuChoice() {
		//initialize new scanner variable
		Scanner scanner = new Scanner(System.in);
		//int variable to use for userChoice
		int userChoice;
		//get variable value from user as int
		userChoice = scanner.nextInt();
		return userChoice;
	}
		
	//method to perform selected operation from main menu
	private static void operation(int userChoice){
		//switch to run required methods as determined by user choice
		switch(userChoice){
			//method to display instructions
			case 1:
				displayInstructions();
				break;
			//method to create necessary .txt files
			case 2:
				createCounterFiles();	
				break;
			//method to process a new order	
			case 3:
				System.out.println("Let\s begin:");
				Order.processNewOrder();
				break;
			case 4:
				//quit program
				System.out.println("Good Bye!");
				break;
			default:
				break;
		} 
	}
	
	//method to display instructions
	private static void displayInstructions() {
		System.out.println("Instructions:\n"
						   + "If this is the first time you run the program, please run 'First Time Setup'\n"
						   + "To process an order, select option 3\n"
						   + "To quit the program, please select option 4.");
	}
	
	//method to create counter file
	private static void createCounterFiles() {
		//try catch block to create counter file
		try {
			File fileName = new File("orderCounter.txt");
				//create file if file does not exist
				if(fileName.createNewFile()) {
					System.out.println("The Order Counter File has been created.");
					//try catch block to write 0 to orderCOunter.txt to start invoice/order numbers at 0
					try {
						File filename = new File("orderCounter.txt");
						FileWriter writer = new FileWriter(filename);
						writer.write("0");
						writer.close();
						System.out.println("Order Counter set to 0;");
					}
					catch (Exception e) {
						System.out.println("Error writing to counter file");
					}
				}
				//displays message if file was already created
				else{
					System.out.println("The Order Counter File has already been created.");
					return;
				}
		} 
		catch (Exception e) {
			System.out.println("Error creating Counter File.");
		}
	}	
}
	