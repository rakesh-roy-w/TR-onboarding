package day11.strategyDesignPattern;

public class All implements Strategy{
    @Override
    public void execute() {
        System.out.println("All is running");
    }
}
