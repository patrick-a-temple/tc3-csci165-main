
public class SalariedEmployee extends Employee{
    private double salary; //annual

    public SalariedEmployee( ){
        super( );
        salary = 0;
    }

    public SalariedEmployee(String theName, Date theDate, double theSalary){
         super(theName, theDate);
         if (theSalary >= 0)
             salary = theSalary;
    }

    public SalariedEmployee(SalariedEmployee originalObject ){
         super(originalObject);
         salary = originalObject.salary;
    }

    public double getSalary( ){
        return salary;
    }

    public double getPay( ){
        return salary/12;
    }

    public void setSalary(double newSalary){
         if (newSalary >= 0)
             salary = newSalary;
    }

    public String toString( ){
        return  super.toString() +
                "\n$" + salary + " per year";
    }

    @Override
public int hashCode() {
	final int prime = 31;
	int result = 1;
	long temp;
	temp = Double.doubleToLongBits(salary);
	result = prime * result + (int) (temp ^ (temp >>> 32));
	return result;
}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (!super.equals(obj))
				return false;
			if (getClass() != obj.getClass())
				return false;
			SalariedEmployee other = (SalariedEmployee) obj;
			if (Double.doubleToLongBits(salary) != Double.doubleToLongBits(other.salary))
				return false;
			return true;
		}
}
