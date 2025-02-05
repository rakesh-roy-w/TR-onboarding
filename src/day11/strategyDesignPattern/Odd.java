package day11.strategyDesignPattern;

public class Odd implements Strategy{
    @Override
    public void execute() {
        System.out.println("Odd is running");
    }
}
