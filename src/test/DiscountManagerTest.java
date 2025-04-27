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
            oneOf(mockCalculator).isTheSpecialWeek();
            will(returnValue(true));
        }});

        DiscountManager manager = new DiscountManager(true, mockCalculator);

        // Act
        double result = manager.calculatePriceAfterDiscount(100.0);

        // Assert
        assertEquals(80.0, result, 0.001);
        context.assertIsSatisfied();
    }

    @Test
    public void testCalculatePriceWhenSeasonAndEvenWeek() {
        Mockery context = new Mockery();
        IDiscountCalculator mockCalculator = context.mock(IDiscountCalculator.class);

        context.checking(new Expectations() {{
            oneOf(mockCalculator).isTheSpecialWeek();
            will(returnValue(false));
            oneOf(mockCalculator).getDiscountPercentage();
            will(returnValue(7));
        }});

        DiscountManager manager = new DiscountManager(true, mockCalculator);

        double result = manager.calculatePriceAfterDiscount(100.0);
        assertEquals(93.0, result, 0.001);
        context.assertIsSatisfied();
    }


    @Test
    public void testCalculatePriceWhenSeasonAndOddWeek() {
        Mockery context = new Mockery();
        IDiscountCalculator mockCalculator = context.mock(IDiscountCalculator.class);

        context.checking(new Expectations() {{
            oneOf(mockCalculator).isTheSpecialWeek(); will(returnValue(false));
            oneOf(mockCalculator).getDiscountPercentage(); will(returnValue(5));
        }});

        DiscountManager manager = new DiscountManager(true, mockCalculator);

        double result = manager.calculatePriceAfterDiscount(100.0);
        assertEquals(95.0, result, 0.001);
        context.assertIsSatisfied();
    }

    @Test
    public void testConstructorAssignmentOfFields() throws Exception {
        boolean isDiscountsSeason = true;
        Mockery mockingContext = new Mockery();
        final IDiscountCalculator mockedDependency = (IDiscountCalculator)mockingContext.mock(IDiscountCalculator.class);
        DiscountManager discountManager = new DiscountManager(isDiscountsSeason, mockedDependency);
        mockingContext.checking(new Expectations() {
            {
                this.oneOf(mockedDependency).isTheSpecialWeek();
                this.will(returnValue(false));
                this.oneOf(mockedDependency).getDiscountPercentage();
                this.will(returnValue(5));
            }
        });
        discountManager.calculatePriceAfterDiscount(100.0);
        mockingContext.assertIsSatisfied();
    }




}
