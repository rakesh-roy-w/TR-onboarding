package day2;

import java.util.Optional;

public class OptionalTest {
    public static void main(String[] args) {
        Optional<String> optionalName = getNullName(1);
        String name = optionalName.isPresent()? optionalName.get() : "NA"; // don't use
//        System.out.println("name: " + name);

        optionalName = getName(1);
        name = optionalName.orElse("NA"); // use this instead
//        System.out.println("name: " + name);

        Optional<String> presentOptional = Optional.of("apple");
        Optional<String> emptyOptional = Optional.empty();

        presentOptional.ifPresent((x) -> System.out.println(x)); // 1 way
//        presentOptional.ifPresentOrElse((x) -> System.out.println(x)); // 2 way


        presentOptional.map(x -> x.toUpperCase()).orElse("student is not present");
        // flatmap

//        System.out.println(presentOptional.orElseGet(() -> "Default String"));
//        System.out.println(emptyOptional.orElseGet(() -> "Default String"));
        System.out.println(presentOptional.orElse(getSchool("Hello"))); // eager
        System.out.println("break");
        System.out.println(presentOptional.orElseGet(() -> getDefaultSchool())); // lazy
        System.out.println(emptyOptional.orElseThrow(() -> new RuntimeException("Default RuntimeException")));
    }
    private static Optional<String> getNullName(int id) {
        String name = null;
        return Optional.ofNullable(name);
    }
    private static Optional<String> getName(int id) {
        String name = "Haru";
        return Optional.of(name);
        // overhead of using wrapper
    }

    private static String getDefaultSchool() {
        System.out.println("get default school method");
        return "St. Xaviers";
    }

    private static String getSchool(String name) {
        System.out.println("get school method");
        return name;
    }
}
