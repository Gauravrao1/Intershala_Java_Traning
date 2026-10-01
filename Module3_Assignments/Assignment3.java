abstract class Employee {
    private String name;
    private int employeeID;

    public Employee(String name, int employeeID) {
        this.name = name;
        this.employeeID = employeeID;
    }

    public String getName() {
        return name;
    }

    public int getEmployeeID() {
        return employeeID;
    }

    public abstract double calculateSalary();

    public void displayDetails() {
        System.out.printf("Name: %s%nEmployee ID: %d%nSalary: %.2f%n%n",
                name, employeeID, calculateSalary());
    }
}

class FullTimeEmployee extends Employee {
    private double monthlySalary;

    public FullTimeEmployee(String name, int employeeID, double monthlySalary) {
        super(name, employeeID);
        this.monthlySalary = monthlySalary;
    }

    @Override
    public double calculateSalary() {
        return monthlySalary;
    }
}

class PartTimeEmployee extends Employee {
    private double hourlyRate;
    private int hoursWorked;

    public PartTimeEmployee(String name, int employeeID, double hourlyRate, int hoursWorked) {
        super(name, employeeID);
        this.hourlyRate = hourlyRate;
        this.hoursWorked = hoursWorked;
    }

    @Override
    public double calculateSalary() {
        return hourlyRate * hoursWorked;
    }
}

public class Assignment3 {
    public static void main(String[] args) {
        Employee fullTimeEmployee = new FullTimeEmployee("Riya", 201, 60000.00);
        Employee partTimeEmployee = new PartTimeEmployee("Kabir", 202, 500.00, 80);

        fullTimeEmployee.displayDetails();
        partTimeEmployee.displayDetails();
    }
}
