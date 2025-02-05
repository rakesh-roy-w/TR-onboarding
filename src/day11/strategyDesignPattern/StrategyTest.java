package day11.strategyDesignPattern;

public class StrategyTest {
    public static void main(String[] args) {
        User user = new User();
        user.setStrategy(new All());
        user.executeStrategy();
    }
}
