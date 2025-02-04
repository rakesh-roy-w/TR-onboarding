package day1;

import java.math.BigDecimal;

public class BigDecimalTest {
    public static void main(String[] args) {
        Class object = BigDecimalTest.class;
        double d1 = 0.7d;
        System.out.println(d1); // 0.7
        // converting Double to BigDecimal
        BigDecimal b1 = new BigDecimal(d1);
        System.out.println(b1); // 0.6999999999999999555910790149937383830547332763671875

        // To convert Double to BigDecimal, we should use valueOf()
        BigDecimal b2 = BigDecimal.valueOf(d1);
        System.out.println(b2); // 0.7

        // BigDecimal allow us to set Precision and scale
        BigDecimal b3 = new BigDecimal("12345.56734");
        // set the scale to 2 digit i.e, 2 decimal place
        BigDecimal b3Rounded = b3.setScale(2, BigDecimal.ROUND_HALF_EVEN);
        System.out.println(b3Rounded); // 12345.57

        // compare b3 and b3Rounded
        System.out.println(b3.compareTo(b3Rounded)); // -1 i.e b3 is smaller than b3precision

    }
}
