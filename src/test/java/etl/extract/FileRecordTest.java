package etl.extract;

import java.math.BigInteger;

import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class FileRecordTest {
    private final FileRecord record = new FileRecord();

    @Test
    public void testProductId() {
        assertNull(record.getProductId());
        record.setProductId(new BigInteger("1875"));
        assertNotNull(record.getProductId());
        assertEquals(record.getProductId(), new BigInteger("1875"));
    }

    @Test
    public void testProductDescription() {
        assertNull(record.getProductDescription());
        record.setProductDescription("a very nice product");
        assertNotNull(record.getProductDescription());
        assertEquals(record.getProductDescription(), "a very nice product");
    }

    @Test
    public void testRegularSingularPrice() {
        assertNull(record.getRegularSingularPrice());
        record.setRegularSingularPrice(new Money("17.55"));
        assertNotNull(record.getRegularSingularPrice());
        assertEquals(record.getRegularSingularPrice(),new Money("17.55"));
    }

    @Test
    public void testPromotionalSingularPrice() {
        assertNull(record.getPromotionalSingularPrice());
        record.setPromotionalSingularPrice(new Money("13.41"));
        assertNotNull(record.getPromotionalSingularPrice());
        assertEquals(record.getPromotionalSingularPrice(),new Money("13.41"));
    }

    @Test
    public void testRegularSplitPrice() {
        assertNull(record.getRegularSplitPrice());
        record.setRegularSplitPrice(new Money("144.72"));
        assertNotNull(record.getRegularSplitPrice());
        assertEquals(record.getRegularSplitPrice(),new Money("144.72"));
    }

    @Test
    public void testPromotionalSplitPrice() {
        assertNull(record.getPromotionalSplitPrice());
        record.setPromotionalSplitPrice(new Money("-17.21"));
        assertNotNull(record.getPromotionalSplitPrice());
        assertEquals(record.getPromotionalSplitPrice(),new Money("-17.21"));
    }

    @Test
    public void testRegularForX() {
        assertNull(record.getRegularForX());
        record.setRegularForX(new BigInteger("-3"));
        assertNotNull(record.getRegularForX());
        assertEquals(record.getRegularForX(),new BigInteger("-3"));
    }

    @Test
    public void testPromotionalForX() {
        assertNull(record.getPromotionalForX());
        record.setPromotionalForX(new BigInteger("17"));
        assertNotNull(record.getPromotionalForX());
        assertEquals(record.getPromotionalForX(),new BigInteger("17"));
    }

    @Test
    public void testFlags() {
        boolean[] expected = new boolean[8];
        expected[4] = true;

        assertNull(record.getFlags());
        record.setFlags(expected);

        boolean[] actual = record.getFlags();
        assertNotNull(actual);
        assertEquals(actual.length, 8);
        assertTrue(actual[4]);
        assertFalse(actual[3]);
    }

    @Test
    public void testProductSize() {
        assertNull(record.getProductSize());
        record.setProductSize("really really big");
        assertNotNull(record.getProductSize());
        assertEquals(record.getProductSize(), "really really big");
    }

    @Test
    public void testSourceFilename() {
        assertNull(record.getSourceFilename());
        record.setSourceFilename("files are phun");
        assertNotNull(record.getSourceFilename());
        assertEquals(record.getSourceFilename(), "files are phun");
    }

    @Test
    public void testSourceFileLastModified() {
        long now = System.currentTimeMillis();

        assertEquals(record.getSourceFileLastModified(), 0L);
        record.setSourceFileLastModified(now);
        assertEquals(record.getSourceFileLastModified(), now);
    }
}
