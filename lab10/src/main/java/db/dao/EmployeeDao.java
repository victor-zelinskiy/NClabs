package db.dao;

import db.exception.NoSuchEmployeeException;
import db.model.Employee;

import java.sql.*;

public class EmployeeDao {
    private Connection connection;

    public EmployeeDao(Connection connection) {
        this.connection = connection;
    }

    public EmployeeDao() {
    }



    private static final String SQL_DELETE_BY_ID = "DELETE EMPLOYEES\n" +
            "WHERE EMPLOYEE_ID = ?";

    private static final String SQL_GET_BY_ID = "SELECT EMP.EMPLOYEE_ID, EMP.FIRST_NAME, " +
            "EMP.LAST_NAME, EMP.EMAIL, " +
            "EMP.HIRE_DATE,EMP.PHONE_NUMBER, EMP.SALARY, JOB.JOB_TITLE, " +
            "DEP.DEPARTMENT_NAME, LOC.CITY\n" +
            "FROM EMPLOYEES EMP\n" +
            "LEFT JOIN DEPARTMENTS DEP ON (EMP.DEPARTMENT_ID = DEP.DEPARTMENT_ID)\n" +
            "LEFT JOIN LOCATIONS LOC ON (LOC.LOCATION_ID = DEP.LOCATION_ID)\n" +
            "LEFT JOIN JOBS JOB ON (JOB.JOB_ID = EMP.JOB_ID)\n" +
            "WHERE EMP.EMPLOYEE_ID = ?";

    private static final String SQL_ADD = "INSERT INTO EMPLOYEES(" +
            "FIRST_NAME, LAST_NAME, EMAIL, PHONE_NUMBER, " +
            "HIRE_DATE, SALARY, EMPLOYEE_ID)\n" +
            "    VALUES (?, ?, ?, " +
            "?, ?, ?, ?)";

    private static final String SQL_GET_SEQ_ID = "SELECT EMP_SEQ.NEXTVAL\n" +
            "FROM DUAL";

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public Employee getEmployeeById(long id) throws SQLException {

        try (PreparedStatement statement = connection.prepareStatement(SQL_GET_BY_ID)) {
            statement.setLong(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    Employee employee = new Employee();
                    employee.setId(resultSet.getLong(Employee.ID));
                    employee.setFirstName(resultSet.getString(Employee.FIRST_NAME));
                    employee.setLastName(resultSet.getString(Employee.LAST_NAME));
                    employee.setEmail(resultSet.getString(Employee.EMAIL));
                    employee.setHireDate(resultSet.getDate(Employee.HIRE_DATE));
                    employee.setPhoneNumber(resultSet.getString(Employee.PHONE_NUMBER));
                    employee.setSalary(resultSet.getBigDecimal(Employee.SALARY));
                    employee.setJobName(resultSet.getString(Employee.JOB_NAME));
                    employee.setDepartmentName(resultSet.getString(Employee.DEPARTMENT_NAME));
                    employee.setCityName(resultSet.getString(Employee.CITY_NAME));
                    return employee;
                }
                throw new NoSuchEmployeeException();
            }

        }
    }

    private long generateNewId() throws SQLException {
        try (Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(SQL_GET_SEQ_ID)) {
            if (resultSet.next()) {
                return resultSet.getLong(1);
            }
            throw new SQLException();
        }

    }

    public void deleteEmployeeById(long id) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement(SQL_DELETE_BY_ID)) {
            statement.setLong(1, id);
            int deletedRowCount = statement.executeUpdate();
            if (deletedRowCount == 0) {
                throw new NoSuchEmployeeException();
            }
        }
    }

    public long addEmployee(Employee newEmployee) throws SQLException {

        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_ADD)) {
            preparedStatement.setString(1, newEmployee.getFirstName());
            preparedStatement.setString(2, newEmployee.getLastName());
            preparedStatement.setString(3, newEmployee.getEmail());
            preparedStatement.setString(4, newEmployee.getPhoneNumber());
            preparedStatement.setDate(5, newEmployee.getHireDate());
            preparedStatement.setBigDecimal(6, newEmployee.getSalary());
            Long newId = generateNewId();
            preparedStatement.setLong(7, newId);
            preparedStatement.executeUpdate();
            return newId;
        }
    }

}
