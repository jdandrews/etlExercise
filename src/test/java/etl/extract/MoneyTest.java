package etl.extract;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Currency;

import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class MoneyTest {
    @Test
    public void testDivide() {
        Money money = new Money("17.25");
        Money result = money.divide(new BigDecimal("2"), 2, RoundingMode.HALF_UP);

        assertEquals(result, new Money("8.63"));
        assertEquals(result.getCurrency(), Currency.getInstance("USD"));
    }
}
