package test;

import JFree.DiscountManager;
import JFree.IDiscountCalculator;
import org.jmock.Expectations;
import org.jmock.Mockery;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class DiscountManagerTest {

    @Test
    public void testCalculatePriceWhenDiscountsSeasonIsFalse() {
        // Arrange
        boolean isDiscountsSeason = false;
        double originalPrice = 100.0;
        double expectedPrice = 100.0;

        Mockery context = new Mockery();
        IDiscountCalculator mockCalculator = context.mock(IDiscountCalculator.class);

        context.checking(new Expectations() {{
            // No method calls expected
        }});

        DiscountManager discountManager = new DiscountManager(isDiscountsSeason, mockCalculator);

        // Act
        double result = discountManager.calculatePriceAfterDiscount(originalPrice);

        // Assert
        assertEquals(expectedPrice, result, 0.001);
        context.assertIsSatisfied();
    }

    @Test
    public void testCalculatePriceWhenSeasonAndSpecialWeek() {
        // Arrange
        Mockery context = new Mockery();
        IDiscountCalculator mockCalculator = context.mock(IDiscountCalculator.class);

        context.checking(new Expectations() {{
            oneOf(mockCalculator).isTheSpecialWeek(); will(returnValue(true));
        }});

        DiscountManager manager = new DiscountManager(true, mockCalculator);

        // Act
        double result = manager.calculatePriceAfterDiscount(100.0);

        // Assert
        assertEquals(80.0, result, 0.001);
        context.assertIsSatisfied();
    }

    @Test
    public void testCalculatePriceWhenSeasonAndRegularWeek() {
        // Arrange
        boolean isDiscountsSeason = true;
        double originalPrice = 100.0;
        int fakeDiscountMultiplier = 5;
        double expectedPrice = 100.0 * fakeDiscountMultiplier;

        Mockery mockingContext = new Mockery();
        IDiscountCalculator mockedDependency = mockingContext.mock(IDiscountCalculator.class);

        mockingContext.checking(new Expectations() {{
            oneOf(mockedDependency).isTheSpecialWeek();
            will(returnValue(false));
            oneOf(mockedDependency).getDiscountPercentage();
            will(returnValue(fakeDiscountMultiplier));
        }});

        DiscountManager discountManager = new DiscountManager(isDiscountsSeason, mockedDependency);

        // Act
        double actualPrice = discountManager.calculatePriceAfterDiscount(originalPrice);

        // Assert
        assertEquals(expectedPrice, actualPrice, 0.001);
        mockingContext.assertIsSatisfied();
    }

}
