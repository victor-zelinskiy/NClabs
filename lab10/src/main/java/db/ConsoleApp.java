package db;

import db.dao.EmployeeDao;
import db.exception.NoSuchEmployeeException;
import db.model.Employee;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class ConsoleApp {

    private Scanner scanner = new Scanner(System.in);
    private EmployeeDao employeeDao;

    public void setEmployeeDao(EmployeeDao employeeDao) {
        this.employeeDao = employeeDao;
    }


    public static void main(String[] args) {
        try {
            Connection connection = new AutoconfiguratedDataSource().getConnection();
            EmployeeDao employeeDao = new EmployeeDao();
            employeeDao.setConnection(connection);
            ConsoleApp consoleApp = new ConsoleApp();
            consoleApp.setEmployeeDao(employeeDao);
            consoleApp.inputLoop();
        } catch (SQLException e) {
            System.out.println("can't connect to db: " + e.getMessage());
        }
    }

    private void printInfo() {
        System.out.println("Commands:");
        System.out.println("    1) get employee by id");
        System.out.println("    2) add new employee");
        System.out.println("    3) delete employee by id");
        System.out.println("    0) exit");
    }

    private void inputLoop() {

        boolean isEnd = false;

        while (!isEnd) {
            printInfo();
            try {
                System.out.println("Input command:");
                int command = scanner.nextInt();
                switch (command) {
                    case 1:
                        printEmployeeById();
                        break;
                    case 2:
                        addNewEmployee();
                        break;
                    case 3:
                        deleteEmployeeById();
                        break;
                    case 0:
                        isEnd = true;
                }
            } catch (NoSuchEmployeeException e) {
                System.out.println("No such employee in db");
            } catch (SQLException e) {
                System.out.println("Error in db: " + e);
            } catch (InputMismatchException e) {
                System.out.println("Wrong input");
            } finally {
                System.out.println();
                scanner.nextLine();
            }
        }

    }

    private void addNewEmployee() throws SQLException {
        scanner.nextLine();
        System.out.println("Input employee first name:");
        String firstName = scanner.nextLine();
        System.out.println("Input employee last name:");
        String lastName = scanner.nextLine();
        System.out.println("Input employee email:");
        String email = scanner.nextLine();
        System.out.println("Input employee phone:");
        String phone = scanner.nextLine();
        System.out.println("Input employee salary:");
        BigDecimal salary = BigDecimal.valueOf(scanner.nextDouble());
        Employee employee = new Employee(firstName, lastName, email, phone, new Date(System.currentTimeMillis()), salary);
        long newEmployeeId = employeeDao.addEmployee(employee);
        System.out.println("Added new employee with id " + newEmployeeId);
        System.out.println();
    }

    private void printEmployeeById() throws SQLException {
        System.out.println("Input employee id:");
        long emmployeeId = scanner.nextLong();
        Employee employee = employeeDao.getEmployeeById(emmployeeId);
        System.out.println(Employee.ID + ": " + employee.getId());
        System.out.println(Employee.FIRST_NAME + ": " + employee.getFirstName());
        System.out.println(Employee.LAST_NAME + ": " + employee.getLastName());
        System.out.println(Employee.EMAIL + ": " + employee.getEmail());
        System.out.println(Employee.PHONE_NUMBER + ": " + employee.getPhoneNumber());
        System.out.println(Employee.JOB_NAME + ": " + employee.getJobName());
        System.out.println(Employee.SALARY + ": " + employee.getSalary());
        System.out.println(Employee.CITY_NAME + ": " + employee.getCityName());
        System.out.println();
    }

    private void deleteEmployeeById() throws SQLException {
        System.out.println("Input employee id:");
        long emmployeeId = scanner.nextLong();
        employeeDao.deleteEmployeeById(emmployeeId);
        System.out.println("Employee №" + emmployeeId + " deleted");
        System.out.println();
    }



}
