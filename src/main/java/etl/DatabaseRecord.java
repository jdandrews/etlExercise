package etl;

import java.math.BigDecimal;

/**
 * A single internal-format record to be inserted. A dumb data structure, which you can optionally
 * O-R map to a relational database;
 */
public class DatabaseRecord {
    private String storeId;
    private String regularDisplayPrice;
    private Money regularCalculatorPrice;
    private String promotionalDisplayPrice;
    private Money promotionalCalculatorPrice;
    private String unitOfMeasure;
    private String productSize;
    private BigDecimal taxRate;
}
