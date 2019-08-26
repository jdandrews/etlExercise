package etl.extract;

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

    private String sourceFilename;
    private long sourceFileLastModified;

    public BigInteger getProductId() {
        return productId;
    }

    void setProductId(BigInteger productId) {
        this.productId = productId;
    }

    public String getProductDescription() {
        return productDescription;
    }

    void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public Money getRegularSingularPrice() {
        return regularSingularPrice;
    }

    void setRegularSingularPrice(Money regularSingularPrice) {
        this.regularSingularPrice = regularSingularPrice;
    }

    public Money getPromotionalSingularPrice() {
        return promotionalSingularPrice;
    }

    void setPromotionalSingularPrice(Money promotionalSingularPrice) {
        this.promotionalSingularPrice = promotionalSingularPrice;
    }

    public Money getRegularSplitPrice() {
        return regularSplitPrice;
    }

    void setRegularSplitPrice(Money regularSplitPrice) {
        this.regularSplitPrice = regularSplitPrice;
    }

    public Money getPromotionalSplitPrice() {
        return promotionalSplitPrice;
    }

    void setPromotionalSplitPrice(Money promotionalSplitPrice) {
        this.promotionalSplitPrice = promotionalSplitPrice;
    }

    public BigInteger getRegularForX() {
        return regularForX;
    }

    void setRegularForX(BigInteger regularForX) {
        this.regularForX = regularForX;
    }

    public BigInteger getPromotionalForX() {
        return promotionalForX;
    }

    void setPromotionalForX(BigInteger promotionalForX) {
        this.promotionalForX = promotionalForX;
    }

    public boolean[] getFlags() {
        return flags;
    }

    void setFlags(boolean[] flags) {
        this.flags = flags;
    }

    public String getProductSize() {
        return productSize;
    }

    void setProductSize(String productSize) {
        this.productSize = productSize;
    }

    public String getSourceFilename() {
        return sourceFilename;
    }

    void setSourceFilename(String filename) {
        sourceFilename = filename;
    }

    public long getSourceFileLastModified() {
        return sourceFileLastModified;
    }

    void setSourceFileLastModified(long timeInMsPastTheEpoch) {
        sourceFileLastModified = timeInMsPastTheEpoch;
    }
}
