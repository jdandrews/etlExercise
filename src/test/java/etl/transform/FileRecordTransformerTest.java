package etl.transform;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.LinkedList;
import java.util.Queue;

import org.testng.annotations.Test;

import static org.testng.Assert.*;

import etl.DatabaseRecord;
import etl.extract.FileRecord;
import etl.extract.Money;

public class FileRecordTransformerTest {
    Queue<FileRecord> inputQueue = new LinkedList<>();
    Queue<DatabaseRecord> outputQueue = new LinkedList<>();
    FileRecordTransformer transformer = new FileRecordTransformer(inputQueue, outputQueue);

    @Test
    public void testFileRecordTransfomer() {
        inputQueue.add(getFileRecordOne());
        inputQueue.add(getFileRecordTwo());

        transformer.run();

        assertTrue(inputQueue.isEmpty());
        assertEquals(outputQueue.size(), 2);

        DatabaseRecord actual = outputQueue.poll();
        assertEquals(actual.getStoreId(), "something with indicating the store name");
        assertEquals(actual.getRegularDisplayPrice(), "7.98");
        assertEquals(actual.getRegularCalculatorPrice(), new Money("7.98"));
        assertEquals(actual.getPromotionalDisplayPrice(), "2.49");
        assertEquals(actual.getPromotionalCalculatorPrice(), new Money("2.49"));
        assertEquals(actual.getUnitOfMeasure(), "Each");
        assertEquals(actual.getProductSize(), "huge");
        assertEquals(actual.getTaxRate(), new BigDecimal("7.75"));

        actual = outputQueue.poll();
        assertEquals(actual.getStoreId(), "something store name");
        assertEquals(actual.getRegularDisplayPrice(), "5.00 / 7");
        assertEquals(actual.getRegularCalculatorPrice(), new Money("0.7143"));
        assertEquals(actual.getPromotionalDisplayPrice(), "9.00 / 7");
        assertEquals(actual.getPromotionalCalculatorPrice(), new Money("1.2857"));
        assertEquals(actual.getUnitOfMeasure(), "Pound");
        assertEquals(actual.getProductSize(), "teensy");
        assertEquals(actual.getTaxRate(), BigDecimal.ZERO);
    }

    private FileRecord getFileRecordOne() {
        FileRecord result = new FileRecord();

        result.setSourceFilename("something with indicating the store name");
        result.setRegularSingularPrice(new Money("7.98"));
        result.setRegularSplitPrice(new Money("0.00"));
        result.setRegularForX(BigInteger.ZERO);
        result.setPromotionalSingularPrice(new Money("2.49"));
        result.setPromotionalSplitPrice(new Money("0.00"));
        result.setPromotionalForX(BigInteger.ZERO);
        boolean[] flags = new boolean[8];
        flags[5] = true;
        result.setFlags(flags);
        result.setProductSize("huge");

        return result;
    }

    private FileRecord getFileRecordTwo() {
        FileRecord result = new FileRecord();

        result.setSourceFilename("something store name");
        result.setRegularSingularPrice(new Money("0.00"));
        result.setRegularSplitPrice(new Money("5.00"));
        result.setRegularForX(new BigInteger("7"));
        result.setPromotionalSingularPrice(new Money("0.00"));
        result.setPromotionalSplitPrice(new Money("9.00"));
        result.setPromotionalForX(new BigInteger("7"));
        boolean[] flags = new boolean[8];
        flags[3] = true;
        result.setFlags(flags);
        result.setProductSize("teensy");

        return result;
    }
}
