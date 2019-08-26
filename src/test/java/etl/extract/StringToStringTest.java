package etl.extract;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class StringToStringTest {
    private final StringToString parser = new StringToString();

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

        String actual = parser.parse(testData, 2, 17);
        assertNotNull(actual);
        assertEquals(actual, " 0xTtFfYyNnblar");
    }

}
