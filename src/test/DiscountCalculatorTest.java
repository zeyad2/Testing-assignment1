package test;

import JFree.DiscountCalculator;
import org.jfree.data.time.Week;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Calendar;
import java.util.Date;

import static org.junit.Assert.*;

public class DiscountCalculatorTest {

    private DiscountCalculator discountCalculator;
    private Week week;


    @Test
    public void testIsTheSpecialWeekWhenFalse() {
        // Create a real Week object for week 25
        week = new Week(25, 2025); // Week 25 of the year 2025

        // Initialize DiscountCalculator with real Week object
        discountCalculator = new DiscountCalculator(week);

        // Test that it's not the special week (Week 26 is the special week)
        boolean result = discountCalculator.isTheSpecialWeek();

        // Assert the expected result
        assertFalse("Expected week to not be special", result);
    }

    @Test
    public void testIsTheSpecialWeekWhenTrue() {
        // Create a real Week object for week 26 (special week)
        week = new Week(26, 2025); // Week 26 of the year 2025

        // Initialize DiscountCalculator with real Week object
        discountCalculator = new DiscountCalculator(week);

        // Test that it is the special week (Week 26 is the special week)
        boolean result = discountCalculator.isTheSpecialWeek();

        // Assert the expected result
        assertTrue("Expected week to be special", result);
    }



    @Test
    public void testGetDiscountPercentageEvenWeek() {
        // Create a real Week object for an even week (week 26)
        week = new Week(26, 2025); // Week 26 is an even week

        // Initialize DiscountCalculator with real Week object
        discountCalculator = new DiscountCalculator(week);

        // Act: Get the discount for even weeks
        int discount = discountCalculator.getDiscountPercentage();

        // Assert: Even weeks should have 7% discount
        assertEquals("Discount should be 7% for even weeks", 7, discount);
    }

    @Test
    public void testGetDiscountPercentageOddWeek() {
        // Create a real Week object for an odd week (week 25)
        week = new Week(25, 2025); // Week 25 is an odd week

        // Initialize DiscountCalculator with real Week object
        discountCalculator = new DiscountCalculator(week);

        // Act: Get the discount for odd weeks
        int discount = discountCalculator.getDiscountPercentage();

        // Assert: Odd weeks should have 5% discount
        assertEquals("Discount should be 5% for odd weeks", 5, discount);
    }

    @Test
    public void testLeapYear(){
       Week week=  new Week(53,2025);
       discountCalculator= new DiscountCalculator(week);
       assertFalse(discountCalculator.isTheSpecialWeek());
       assertEquals(discountCalculator.getDiscountPercentage(),5);
    }

    @Test
    public void testConstructorAssignsWeekCorrectly() throws Exception {
        Calendar calendar = Calendar.getInstance();
        calendar.set(2025, 3, 15);
        Date date = calendar.getTime();
        Week week = new Week(date);
        DiscountCalculator discountCalculator = new DiscountCalculator(week);
        int weekNum = week.getWeek();
        int expectedDiscount = weekNum % 2 == 0 ? 7 : 5;
        Assert.assertEquals((long)expectedDiscount, (long)discountCalculator.getDiscountPercentage());
    }




}
