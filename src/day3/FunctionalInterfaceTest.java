package day3;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.BiPredicate;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

public class FunctionalInterfaceTest {
    public static void main(String[] args) {
        Employee e1 = new Employee(1, "Rishi", "Son", LocalDate.of(1990,6,14), 2000);
        Employee e2 = new Employee(2, "Rahul", "Sen", LocalDate.of(1991,5,15), 3000);
        Employee e3 = new Employee(3, "Rina", "Hamata", LocalDate.of(1992,3,16), 10000);
        Employee e4 = new Employee(4, "Roshi", "Suzuki", LocalDate.of(1993,4,17), 5000);
        Employee e5 = new Employee(5, "Roshni", "Honda", LocalDate.of(1994,2,13), 4000);
        Employee e6 = new Employee(6, "Roshan", "Toyoda", LocalDate.of(1995,1,12), 1000);



        List<Employee> employeeList = new ArrayList<>();
        employeeList.addAll(Arrays.asList(e1, e2, e3, e4, e5, e6));

        // consumer
        Consumer<Employee> employeeConsumer = (emp) -> {

            System.out.println("empId : " + emp.id+ ", name : "+emp.firstName+" "+emp.lastName+
                    ", salary : "+emp.salary);

        };

        // predicate
        BiPredicate<Employee, Integer> salaryPredicate = (x, y) -> x.salary > y;

        // supplier
        Supplier<String> passwordSupplier = () ->
                getAlphaNumericString();
        ;

        // function
        Function<Employee, User> userFunction = (emp) -> {
            User user = new User();
            user.setId(emp.id);
            String pass = passwordSupplier.get();
            user.setPassword(pass);
            user.setUsername(user.generate(emp.firstName, emp.lastName, emp.dateOfBirth, emp.id));
            return user;
        };

        List<User> userList = new ArrayList<>();

        for (Employee emp : employeeList) {
            userList.add(userFunction.apply(emp));
        }

        // sort list of employees by month in date of birth
        employeeList.sort((a, b) -> a.dateOfBirth.getMonthValue() - b.dateOfBirth.getMonthValue());
        System.out.println(employeeList);

        // create 2 thread..one thread print list of employees..Another thread print list of User
        Thread thread1 = new Thread(() -> {
            for(Employee emp : employeeList) {
                employeeConsumer.accept(emp);
            }
        });
        Thread thread2 = new Thread(() -> {
            for(User user : userList) {
                System.out.println(user);
            }
        });

        thread1.start();
        thread2.start();

    }
    static String getAlphaNumericString() {
        int n = 16;
        // choose a Character random from this String
        String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
                + "0123456789"
                + "abcdefghijklmnopqrstuvxyz";

        // create StringBuffer size of AlphaNumericString
        StringBuilder sb = new StringBuilder(n);

        for (int i = 0; i < n; i++) {

            // generate a random number between
            // 0 to AlphaNumericString variable length
            int index
                    = (int) (AlphaNumericString.length()
                    * Math.random());

            // add Character one by one in end of sb
            sb.append(AlphaNumericString
                    .charAt(index));

        }
        return sb.toString();
    }
}

class Employee {
    int id;
    String firstName;
    String lastName;
    LocalDate dateOfBirth;
    int salary;

    Employee(int id, String firstName, String lastName, LocalDate dateOfBirth, int salary){
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.salary = salary;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }
}


class User implements UserNameGenerator{
    int id;
    String username;
    String password;

    User(int id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }
    User() {}

    @Override
    public String generate(String firstname, String lastName, LocalDate dob, int id) {
        int yob = dob.getYear();
        String name = firstname+lastName+yob+id;
        return name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

@FunctionalInterface
interface UserNameGenerator {
    abstract String generate(String firstname, String lastName, LocalDate dob, int id);
}

