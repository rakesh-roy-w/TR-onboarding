package day10;

public class SolidDesignTest {
    public static void main(String[] args) {
        Crow crow = new Crow("crow");
        Pigeon pigeon = new Pigeon("pigeon");
        Bird penguin = new Penguin("crow");

        crow.fly();
        pigeon.fly();
        penguin.eat();
    }
}
class Bird {
    String species;

    Bird(String species) {
        this.species = species;
    }

    void sound() {
        System.out.println(this.species + " make sounds");
    }

    void eat() {
        System.out.println(this.species + " can eat");
    }

    @Override
    public String toString() {
        return "Bird{" +
                "species='" + species + '\'' +
                '}';
    }
}
class Crow extends Bird implements Flyable {

    Crow(String species) {
        super(species);
    }

    @Override
    public void fly() {
        System.out.println("crow can fly");
    }
}
class Pigeon extends Bird implements Flyable {

    Pigeon(String species) {
        super(species);
    }

    @Override
    public void fly() {
        System.out.println("Pigeon can fly");
    }
}

class Penguin extends Bird {

    Penguin(String species) {
        super(species);
    }

}
interface Flyable {
    void fly();
}
