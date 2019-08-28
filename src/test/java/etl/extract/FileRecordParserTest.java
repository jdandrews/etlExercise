package etl.extract;

import java.math.BigInteger;

import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class FileRecordParserTest {
    private final FileRecordParser parser = new FileRecordParser();

    private static final String[] SAMPLES = {
            "80000001 Kimchi-flavored white rice                                  00000567 00000000 00000000 00000000 00000000 00000000 NNNNNNNNN      18oz",
            "14963801 Generic Soda 12-pack                                        00000000 00000549 00001300 00000000 00000002 00000000 NNNNYNNNN   12x12oz",
            "40123401 Marlboro Cigarettes                                         00001000 00000549 00000000 00000000 00000000 00000000 YNNNNNNNN          ",
            "50133333 Fuji Apples (Organic)                                       00000349 00000000 00000000 00000000 00000000 00000000 NNYNNNNNN        lb",
    };

    @Test
    public void testParse() {
        FileRecord result = parser.parse(SAMPLES[0]);

        assertNotNull(result);
        assertEquals(result.getProductId(), new BigInteger("80000001"));
        assertEquals(result.getProductDescription(), "Kimchi-flavored white rice                                 ");
        assertEquals(result.getRegularSingularPrice(), new Money("5.67"));
        assertEquals(result.getPromotionalSingularPrice(), new Money("0.00"));
        assertEquals(result.getRegularSplitPrice(), new Money("0.00"));
        assertEquals(result.getPromotionalSplitPrice(), new Money("0.00"));
        assertEquals(result.getRegularForX(), BigInteger.ZERO);
        assertEquals(result.getPromotionalForX(), BigInteger.ZERO);
        assertEquals(result.getFlags(), new boolean[9]);
        assertEquals(result.getProductSize(), "     18oz");

        result = parser.parse(SAMPLES[1]);

        assertNotNull(result);
        assertEquals(result.getProductId(), new BigInteger("14963801"));
        assertEquals(result.getProductDescription(), "Generic Soda 12-pack                                       ");
        assertEquals(result.getRegularSingularPrice(), new Money("0.00"));
        assertEquals(result.getPromotionalSingularPrice(), new Money("5.49"));
        assertEquals(result.getRegularSplitPrice(), new Money("13.00"));
        assertEquals(result.getPromotionalSplitPrice(), new Money("0.00"));
        assertEquals(result.getRegularForX(), new BigInteger("2"));
        assertEquals(result.getPromotionalForX(), BigInteger.ZERO);
        boolean[] expected = new boolean[9];
        expected[4] = true;
        assertEquals(result.getFlags(), expected);
        assertEquals(result.getProductSize(), "  12x12oz");

        result = parser.parse(SAMPLES[2]);

        assertNotNull(result);
        assertEquals(result.getProductId(), new BigInteger("40123401"));
        assertEquals(result.getProductDescription(), "Marlboro Cigarettes                                        ");
        assertEquals(result.getRegularSingularPrice(), new Money("10.00"));
        assertEquals(result.getPromotionalSingularPrice(), new Money("5.49"));
        assertEquals(result.getRegularSplitPrice(), new Money("0.00"));
        assertEquals(result.getPromotionalSplitPrice(), new Money("0.00"));
        assertEquals(result.getRegularForX(), BigInteger.ZERO);
        assertEquals(result.getPromotionalForX(), BigInteger.ZERO);
        expected = new boolean[9];
        expected[0] = true;
        assertEquals(result.getFlags(), expected);
        assertEquals(result.getProductSize(), "         ");

        result = parser.parse(SAMPLES[3]);

        assertNotNull(result);
        assertEquals(result.getProductId(), new BigInteger("50133333"));
        assertEquals(result.getProductDescription(), "Fuji Apples (Organic)                                      ");
        assertEquals(result.getRegularSingularPrice(), new Money("3.49"));
        assertEquals(result.getPromotionalSingularPrice(), new Money("0.00"));
        assertEquals(result.getRegularSplitPrice(), new Money("0.00"));
        assertEquals(result.getPromotionalSplitPrice(), new Money("0.00"));
        assertEquals(result.getRegularForX(), BigInteger.ZERO);
        assertEquals(result.getPromotionalForX(), BigInteger.ZERO);
        expected = new boolean[9];
        expected[2] = true;
        assertEquals(result.getFlags(), expected);
        assertEquals(result.getProductSize(), "       lb");

    }
}
