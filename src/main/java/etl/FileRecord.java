package etl;

import java.math.BigInteger;

/**
 * A single store-format record to be inserted. A dumb data structure.
 */
public class FileRecord {
    private BigInteger productId;
    private String productDescription;
    private Money regularSingularPrice;
    private Money promotionalSingularPrice;
    private Money regularSplitPrice;
    private Money promotionalSplitPrice;
    private BigInteger regularForX;
    private BigInteger promotionalForX;
    private boolean[] flags;
    private String productSize;
}
