package etl.extract;

import java.math.BigInteger;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class StringToBigIntegerTest {
    private final StringToBigInteger parser = new StringToBigInteger();

    @DataProvider(name = "getBadIndexTestData")
    public Object[][] getBadIndexTestData() {
        return new Object[][] {
            { "dummy record", -1, 10 },
            { "dummy record", 10, -1 },
            { "dummy record", -1, -1 },
            { "dummy record", 3, 3 },
            { "dummy record", 4, 3 },
            { "dummy record", 0, 0 },
            { "dummy record", 1, 0 },
            // 0123456789-12
            { "dummy record", 12, 13},
        };
    }

    @Test(dataProvider = "getBadIndexTestData", expectedExceptions = { IndexOutOfBoundsException.class })
    public void testParseBadIndexExceptions(String record, int beginIndex, int endIndex) {
        parser.parse(record, beginIndex, endIndex);
    }

    @Test
    public void testParse() {
        //                 0123456789-123456789-
        String testData = "   0xTt 1757  rgh  ";

        assertEquals(parser.parse(testData, 0, 4), BigInteger.ZERO);
        assertEquals(parser.parse(testData, 8, 11), new BigInteger("175"));
    }

}
