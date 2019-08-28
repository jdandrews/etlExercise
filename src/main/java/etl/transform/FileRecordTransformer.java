package etl.transform;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.util.Queue;

import etl.model.DatabaseRecord;
import etl.model.FileRecord;
import etl.model.Money;

public class FileRecordTransformer implements Runnable {
    Queue<FileRecord> inputQueue;
    Queue<DatabaseRecord> outputQueue;

    public FileRecordTransformer(Queue<FileRecord> recordsToTransform, Queue<DatabaseRecord> recordsToLoad) {
        inputQueue = recordsToTransform;
        outputQueue = recordsToLoad;
    }

    @Override
    public void run() {
        while ( ! inputQueue.isEmpty()) {
            outputQueue.add(transform(inputQueue.poll()));
        }
    }

    /**
     * Transforms an input record to an output record.
     * @param in the FileRecord to transform
     * @return the resulting DatabaseRecord
     */
    private DatabaseRecord transform(FileRecord in) {
        DatabaseRecord result = new DatabaseRecord();

        result.setStoreId(in.getSourceFilename());

        result.setProductId(in.getProductId());

        result.setProductDescription(in.getProductDescription());

        result.setRegularDisplayPrice(getDisplayPrice(
                in.getRegularSingularPrice(), in.getRegularSplitPrice(), in.getRegularForX()));

        result.setRegularCalculatorPrice(getCalculatorPrice(
                in.getRegularSingularPrice(), in.getRegularSplitPrice(), in.getRegularForX()));

        result.setPromotionalDisplayPrice(getDisplayPrice(
                in.getPromotionalSingularPrice(), in.getPromotionalSplitPrice(), in.getPromotionalForX()));

        result.setPromotionalCalculatorPrice(getCalculatorPrice(
                in.getPromotionalSingularPrice(), in.getPromotionalSplitPrice(), in.getPromotionalForX()));

        result.setUnitOfMeasure(in.getFlags()[3] ? "Pound" : "Each");

        result.setProductSize(in.getProductSize());

        result.setTaxRate(in.getFlags()[5] ? new BigDecimal("7.75") : BigDecimal.ZERO);

        return result;
    }

    private String getDisplayPrice(Money singularPrice, Money splitPrice, BigInteger forX) {
        if (singularPrice.compareTo(BigDecimal.ZERO) > 0) {
            return singularPrice.toPlainString();
        }
        if (splitPrice.compareTo(BigDecimal.ZERO) > 0 ) {
            return splitPrice.toPlainString() + " / " + forX.toString();
        }
        return singularPrice.toPlainString();   // basically: "free"
    }

    private Money getCalculatorPrice(Money singularPrice, Money splitPrice, BigInteger forX) {
        if (singularPrice.compareTo(BigDecimal.ZERO) > 0) {
            return singularPrice;
        }
        if (splitPrice.compareTo(BigDecimal.ZERO) > 0 ) {
            return splitPrice.divide(new BigDecimal(forX), 4, RoundingMode.HALF_DOWN);
        }
        return singularPrice;   // basically: "free"
    }

}
