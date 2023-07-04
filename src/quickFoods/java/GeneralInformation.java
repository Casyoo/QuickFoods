package quickFoods.java;
public class GeneralInformation {
	//attributes
	int orderNumber;
	String name;
	String contactNumber;
	String location;
	
	//constructor function for General class
	public GeneralInformation(String name, String contactNumber, String location) {
			this.name = name;
			this.contactNumber = contactNumber;
			this.location=location;
	}
}