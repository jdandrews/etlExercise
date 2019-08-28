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

    public void setProductId(BigInteger productId) {
        this.productId = productId;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public Money getRegularSingularPrice() {
        return regularSingularPrice;
    }

    public void setRegularSingularPrice(Money regularSingularPrice) {
        this.regularSingularPrice = regularSingularPrice;
    }

    public Money getPromotionalSingularPrice() {
        return promotionalSingularPrice;
    }

    public void setPromotionalSingularPrice(Money promotionalSingularPrice) {
        this.promotionalSingularPrice = promotionalSingularPrice;
    }

    public Money getRegularSplitPrice() {
        return regularSplitPrice;
    }

    public void setRegularSplitPrice(Money regularSplitPrice) {
        this.regularSplitPrice = regularSplitPrice;
    }

    public Money getPromotionalSplitPrice() {
        return promotionalSplitPrice;
    }

    public void setPromotionalSplitPrice(Money promotionalSplitPrice) {
        this.promotionalSplitPrice = promotionalSplitPrice;
    }

    public BigInteger getRegularForX() {
        return regularForX;
    }

    public void setRegularForX(BigInteger regularForX) {
        this.regularForX = regularForX;
    }

    public BigInteger getPromotionalForX() {
        return promotionalForX;
    }

    public void setPromotionalForX(BigInteger promotionalForX) {
        this.promotionalForX = promotionalForX;
    }

    public boolean[] getFlags() {
        return flags;
    }

    public void setFlags(boolean[] flags) {
        this.flags = flags;
    }

    public String getProductSize() {
        return productSize;
    }

    public void setProductSize(String productSize) {
        this.productSize = productSize;
    }

    public String getSourceFilename() {
        return sourceFilename;
    }

    public void setSourceFilename(String filename) {
        sourceFilename = filename;
    }

    public long getSourceFileLastModified() {
        return sourceFileLastModified;
    }

    public void setSourceFileLastModified(long timeInMsPastTheEpoch) {
        sourceFileLastModified = timeInMsPastTheEpoch;
    }
}
