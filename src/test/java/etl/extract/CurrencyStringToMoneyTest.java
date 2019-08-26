package etl.extract;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class CurrencyStringToMoneyTest {
    private final CurrencyStringToMoney parser = new CurrencyStringToMoney();

    @DataProvider(name = "getBadIndexTestData")
    public Object[][] getBadIndexTestData() {
        return new Object[][] {
            { "dummy record", -1, 10, 0 },
            { "dummy record", 10, -1, 0 },
            { "dummy record", -1, -1, 0 },
            { "dummy record", 3, 3, 0 },
            { "dummy record", 4, 3, 0 },
            { "dummy record", 0, 0, 0 },
            { "dummy record", 1, 0, 0 },
            // 0123456789-12
            { "dummy record", 12, 13, 0},
        };
    }

    @Test(dataProvider = "getBadIndexTestData", expectedExceptions = { IndexOutOfBoundsException.class })
    public void testParseBadIndexExceptions(String record, int beginIndex, int endIndex, int decimalShift) {
        parser.parse(record, beginIndex, endIndex, decimalShift);
    }

    @DataProvider(name = "getParsingData")
    public Object[][] getParsingData() {
        return new Object[][] {
            // 0123456789-
            { "  001010 ", 2, 8, 2, new Money("10.10") },
            { "  -02341 ", 2, 8, 2, new Money("-23.41") },
            { "    7525 ", 2, 8, 3, new Money("7.52") },
            { "    7526 ", 2, 8, 3, new Money("7.53") },
        };
    }

    @Test(dataProvider = "getParsingData")
    public void testApply(String record, int begin, int end, int divisor, Money expected) {
        assertEquals(parser.parse(record, begin, end, divisor), expected);
    }
}
