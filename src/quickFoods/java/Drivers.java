package quickFoods.java;
//import required classes
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

//Class for all driver related methods and functions
public class Drivers{
	//attributes to be used in constructor function
	String name;
	String location;
	String load; 
	
	//constructor function to create Drivers object
	public Drivers(String name, String location, String load) {
		this.name = name;
		this.location = location;
		this.load = load;
	}
	//method to read from drivers file
	private static Scanner fileReaderDrivers(File driversFile) throws FileNotFoundException {
		Scanner fileReader = new Scanner(driversFile);
		return fileReader;
	}
	
	//method to read driver information from .txt file.
	static ArrayList<String> driversDetails(){
		ArrayList<String> driversArrayList = new ArrayList<String>();
		//try catch block to read driver information
		try {
			File driversFile = new File ("drivers.txt");
			Scanner fileReader = fileReaderDrivers(driversFile);
				//while loop to read all lines of files
				//when there are no more lines, data is added to arrayList variable
				while (fileReader.hasNextLine()){
					String data = fileReader.nextLine();
					driversArrayList.add(data);
			}
		} 
		catch (Exception e) {
			System.out.println("Driver data error! Cannot read from source file.");
		}
	return driversArrayList;
	}
	
	//method to add driver objects to ArrayList for use in comparison
	static ArrayList<Drivers> addDrivers(){
		ArrayList<String[]> driversArrayList = new ArrayList<String[]>();
		ArrayList<Drivers> driverInfo = new ArrayList<Drivers>();
		//try catch block to obtain driver information
		try {
			File file = new File("drivers.txt");
			Scanner scanner = new Scanner(file);
			String driver="";
			//while loop to split driver information and add driver information to array.
			while(scanner.hasNextLine()==true){
				driver = scanner.nextLine();
				String[] driverInfoArray = driver.split(", ");
				driversArrayList.add(driverInfoArray);
				String name = driverInfoArray[0];
				String location = driverInfoArray[1];
				
			    String load = driverInfoArray[2];
				//constructor to create new Driver object
				Drivers newDriver = new Drivers(name, location, load);
				driverInfo.add(newDriver);
				}				
			}
		 catch (Exception e) {
			System.out.println("Error creating drivers array.");
		}
		return driverInfo;
	}
}