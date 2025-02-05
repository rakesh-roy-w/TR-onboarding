package day11.strategyDesignPattern;

public class Even implements Strategy{
    @Override
    public void execute() {
        System.out.println("Even is running");
    }
}
