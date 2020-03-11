
public class Driver {

	public static void main(String[] args) {
		
		Address college = new Address("170 North Street", "13053");
		System.out.println(college);
		college.setStreet("THIS SHOULD NOT BE HAPPENING!!");
		System.out.println(college);
		
		Address school = new Address("Elementary School", "14889");
		System.out.println(school);

	}

}
