import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

public class EmployeeManagementSystem {
    public static void main(String[] args) throws SQLException {
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/employee_management_system", "root", "Sb8407943874")) {

            //connection is initialized in employeeDAO to used by methods present in it
            EmployeeDAO employeeDAO = new EmployeeDAO(connection);

            //Use Scanner to take input from user[System.in] and read it.
            Scanner scanner = new Scanner(System.in);

//Below loop is used to show options to user what action user wants to do.
            while (true) {
                System.out.println("\nEmployee Management System");
                System.out.println("1. Add Employee");
                System.out.println("2. View All Employees");
                System.out.println("3. Update Employee");
                System.out.println("4. Delete Employee");
                System.out.println("5. Exit");
                System.out.print("Enter your choice: ");

                //User will enter number by reading options
                int choice = scanner.nextInt();


                //switch case will choose on the basis of input number by user for ex 1 entered to add employee.
                switch (choice) {
                    case 1:
                        System.out.println("Enter employee name:");
                        String name = scanner.next();
                        System.out.println("Enter employee age:");
                        scanner.nextLine();
                        int age = scanner.nextInt();
                        System.out.println("Enter employee position:");
                        String position = scanner.next();
                        employeeDAO.addEmployee(new Employee(name, age, position));
                        break;
                    case 2:
                        System.out.println("All Employees:");
                        employeeDAO.getAllEmployees().forEach(System.out::println);
                        break;
                    case 3:
                        System.out.println("Enter employee ID to update:");
                        int idToUpdate = scanner.nextInt();
                        System.out.println("Enter new name:");
                        String newName = scanner.next();
                        System.out.println("Enter new age:");
                        //To clear buffer
                        scanner.nextLine();
                        int newAge = scanner.nextInt();
                        System.out.println("Enter new position:");
                        String newPosition = scanner.next();
                        employeeDAO.updateEmployee(idToUpdate, new Employee(newName, newAge, newPosition));
                        break;
                    case 4:
                        System.out.println("Enter employee ID to delete:");
                        int idToDelete = scanner.nextInt();
                        employeeDAO.deleteEmployee(idToDelete);
                        break;
                    case 5:
                        System.out.println("Exiting...");
                        return;
                    default:
                        System.out.println("Invalid choice!");
                }


            }
        }
    }
}
