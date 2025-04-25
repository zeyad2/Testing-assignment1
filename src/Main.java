//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.

import JFree.DiscountCalculator;
import JFree.DiscountManager;
import org.jfree.data.time.Week;
import org.jfree.data.time.Year;

public class Main {
    public static void main(String[] args) {
        //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
        // to see how IntelliJ IDEA suggests fixing it.
        System.out.printf("Hello and welcome!");

        Week week= new Week(52,2024);
        DiscountCalculator discountCalculator= new DiscountCalculator(week);
        DiscountManager discountManager= new DiscountManager(true,discountCalculator);



    }
}