public class EmployeeManagementSystem {
    private Employee[] employees;
    private int size;

    public EmployeeManagementSystem(int capacity) {
        employees = new Employee[capacity];
        size = 0;
    }

    public void addEmployee(Employee employee) {
        if (size >= employees.length) {
            System.out.println("Array is full. Cannot add more employees.");
            return;
        }
        employees[size++] = employee;
        System.out.println("Added: " + employee);
    }

    public Employee searchEmployee(int employeeId) {
        for (int i = 0; i < size; i++) {
            if (employees[i].getEmployeeId() == employeeId) {
                return employees[i];
            }
        }
        return null;
    }

    public void traverseEmployees() {
        for (int i = 0; i < size; i++) {
            System.out.println(employees[i]);
        }
    }

    public void deleteEmployee(int employeeId) {
        for (int i = 0; i < size; i++) {
            if (employees[i].getEmployeeId() == employeeId) {
                // Shift elements to the left
                for (int j = i; j < size - 1; j++) {
                    employees[j] = employees[j + 1];
                }
                employees[--size] = null; // Nullify the last element
                System.out.println("Deleted employee with id " + employeeId);
                return;
            }
        }
        System.out.println("Employee not found.");
    }

    public static void main(String[] args) {
        EmployeeManagementSystem ems = new EmployeeManagementSystem(10);
        Employee emp1 = new Employee(1, "Alice", "Manager", 75000.0);
        Employee emp2 = new Employee(2, "Bob", "Developer", 50000.0);
        Employee emp3 = new Employee(3, "Charlie", "Designer", 60000.0);

        ems.addEmployee(emp1);
        ems.addEmployee(emp2);
        ems.addEmployee(emp3);

        System.out.println("Search Employee with ID 2:");
        System.out.println(ems.searchEmployee(2));

        System.out.println("All Employees:");
        ems.traverseEmployees();

        ems.deleteEmployee(2);
        System.out.println("All Employees after deletion:");
        ems.traverseEmployees();
    }
}
