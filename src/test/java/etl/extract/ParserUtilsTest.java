package etl.extract;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class ParserUtilsTest {
    @DataProvider(name = "getIndexData")
    public Object[][] getIndexData() {
        return new Object[][] {
            { -1, 10 }, { 10, -1 }, { -1, -1 }, { 3, 3 }, { 4, 3 }, { 0, 0 }, { 1, 0 } };
    }

    @Test(dataProvider = "getIndexData", expectedExceptions = { IndexOutOfBoundsException.class })
    public void testIndexExceptions(int beginIndex, int endIndex) {
        ParserUtils.verifyIndices(beginIndex, endIndex);
    }

    @Test
    public void testVerifyIndiciesSuccess() {
        // no exception is a pass
        ParserUtils.verifyIndices(5, 10);
    }
}
