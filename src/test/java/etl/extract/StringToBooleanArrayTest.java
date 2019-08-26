package etl.extract;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class StringToBooleanArrayTest {
    private final StringToBooleanArray parser = new StringToBooleanArray();

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
        String testData = "   0xTtFfYyNnblargh  ";
        boolean[] expected = new boolean[17];
        expected[3] = expected[4] = expected[7] = expected[8] = true;

        boolean[] actual = parser.parse(testData, 2, 19);
        assertNotNull(actual);
        assertEquals(actual.length, expected.length);
        for (int i=0; i<actual.length; ++i) {
            assertEquals(actual[i], expected[i], "error at index "+i);
        }
    }

}
