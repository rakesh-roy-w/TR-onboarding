package day5;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class StreamTest {
    public static void main(String[] args) {
        Employee e1 = new Employee(1, "Rishi", "Son", LocalDate.of(1990,6,14), 2000, "HR", LocalDate.of(2023, 12, 1));
        Employee e2 = new Employee(2, "Rahul", "Sen", LocalDate.of(1991,5,15), 3000, "HR", LocalDate.of(2023, 11, 2));
        Employee e3 = new Employee(3, "Rina", "Hamata", LocalDate.of(1992,3,16), 10000, "IT", LocalDate.of(2022, 12, 15));
        Employee e4 = new Employee(4, "Roshi", "Suzuki", LocalDate.of(1994,4,17), 5000, "IT", LocalDate.of(2022, 11, 14));
        Employee e5 = new Employee(5, "Roshni", "Honda", LocalDate.of(1993,2,13), 4000, "IT", LocalDate.of(2022, 10, 15));
        Employee e6 = new Employee(6, "Roshan", "Toyoda", LocalDate.of(1995,1,12), 1000, "CA", LocalDate.of(2023, 1, 1));

        List<Employee> employeeList = new ArrayList<>();
        employeeList.addAll( Arrays.asList( e1, e2, e3, e4, e5, e6));

        // print first name of employees who joined in year 2023
        employeeList.stream().filter( e -> e.getDateOfJoining().getYear() == 2023).forEach( e -> System.out.println(e.getFirstName()));

        // print count, min, max, sum, and the average of salary of all employees in a perticular department
        long count = employeeList.stream().count();
        System.out.println("count Of employees : " + count);
        // min
        Optional<Integer> minSalary = employeeList.stream().map(e -> e.getSalary()).min((a, b) -> a - b);
        System.out.println("min salary : " + minSalary.get());
        // max
        Optional<Integer> maxSalary = employeeList.stream().map(e -> e.getSalary()).max((a, b) -> a - b);
        System.out.println("max salary : " + maxSalary.get());
        // sum
        int sum = employeeList.stream().map(e -> e.getSalary()).reduce(0, (a, b) -> a+b);
        System.out.println("sum : " + sum);
        // average
        double average = employeeList.stream().map(e -> e.getSalary()).mapToDouble(Integer::doubleValue).average().orElse(0.0);
        System.out.println("average salary : " + average);

        // print sorted list of employees by firstName in all departments except HR department
        List<Employee> sortedEmployeeList = employeeList.stream().filter( e -> !e.getDepartment().equals("HR")).sorted((a, b) -> a.getFirstName().compareTo(b.getFirstName())).collect(Collectors.toList());
        System.out.println(sortedEmployeeList);

        // program to increment salary of employees in HR department by 10%.
        List<Employee> incrementedSalaryEmployeeList = employeeList.stream().filter( e -> e.getDepartment().equalsIgnoreCase("HR")).map(e -> {
            int newSal = (int) (e.getSalary() * 1.10);
            e.setSalary(newSal);
            return e;
        }).collect(Collectors.toList());
        System.out.println(incrementedSalaryEmployeeList);

        // print 50 odd numbers after 100
        IntStream.rangeClosed(100, 200).filter(x -> x%2 != 0).limit(50).forEach(x -> System.out.println(x));

        // create comma seperated list of First names of employees order by dateOfBirth
        List<String> firstnameList = employeeList.stream().sorted((a, b) -> a.getDateOfBirth().compareTo(b.getDateOfBirth())).map(x -> x.getFirstName()).collect(Collectors.toList());
        System.out.println(firstnameList);
    }
}

class Employee {
    private int id;
    private String firstName;
    private String lastName;
    private LocalDate dateOfBirth;
    private int salary;
    private String department;
    private LocalDate dateOfJoining;

    Employee(int id, String firstName, String lastName, LocalDate dateOfBirth,
             int salary, String department, LocalDate dateOfJoining){
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.salary = salary;
        this.department = department;
        this.dateOfJoining = dateOfJoining;
    }
    Employee(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public LocalDate getDateOfJoining() {
        return dateOfJoining;
    }

    public void setDateOfJoining(LocalDate dateOfJoining) {
        this.dateOfJoining = dateOfJoining;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", salary=" + salary +
                ", department='" + department + '\'' +
                ", dateOfJoining=" + dateOfJoining +
                '}';
    }
}


