package db.model;

import com.google.common.base.MoreObjects;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.Objects;

public class Employee {
    public static final String ID = "EMPLOYEE_ID";
    public static final String FIRST_NAME = "FIRST_NAME";
    public static final String LAST_NAME = "LAST_NAME";
    public static final String EMAIL = "EMAIL";
    public static final String HIRE_DATE = "HIRE_DATE";
    public static final String PHONE_NUMBER = "PHONE_NUMBER";
    public static final String SALARY = "SALARY";
    public static final String JOB_NAME = "JOB_TITLE";
    public static final String DEPARTMENT_NAME = "DEPARTMENT_NAME";
    public static final String CITY_NAME = "CITY";
    protected Long id;
    protected String firstName;
    protected String lastName;
    protected String email;
    protected String phoneNumber;
    protected Date hireDate;
    protected BigDecimal salary;
    protected String departmentName;
    protected String cityName;
    protected String jobName;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Date getHireDate() {
        return hireDate;
    }

    public void setHireDate(Date hireDate) {
        this.hireDate = hireDate;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }


    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public Employee() {
    }

    public Employee(String firstName, String lastName, String email, String phoneNumber, Date hireDate, BigDecimal salary) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.hireDate = hireDate;
        this.salary = salary;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Employee)) return false;
        Employee that = (Employee) o;
        return Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("id", id)
                .add("firstName", firstName)
                .add("lastName", lastName)
                .add("email", email)
                .add("phoneNumber", phoneNumber)
                .add("hireDate", hireDate)
                .add("salary", salary)
                .add("departmentName", departmentName)
                .add("cityName", cityName)
                .add("jobName", jobName)
                .toString();
    }
}
