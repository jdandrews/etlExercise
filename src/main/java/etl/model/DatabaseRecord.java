package etl.model;

import java.math.BigDecimal;
import java.math.BigInteger;

/**
 * A single internal-format record to be inserted. A dumb data structure, which you can optionally
 * O-R map to a relational database;
 */
public class DatabaseRecord {
    private String storeId;
    private BigInteger productId;
    private String productDescription;
    private String regularDisplayPrice;
    private Money regularCalculatorPrice;
    private String promotionalDisplayPrice;
    private Money promotionalCalculatorPrice;
    private String unitOfMeasure;
    private String productSize;
    private BigDecimal taxRate;

    public String getStoreId() {
        return storeId;
    }
    public void setStoreId(String storeId) {
        this.storeId = storeId;
    }
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
    public String getRegularDisplayPrice() {
        return regularDisplayPrice;
    }
    public void setRegularDisplayPrice(String regularDisplayPrice) {
        this.regularDisplayPrice = regularDisplayPrice;
    }
    public Money getRegularCalculatorPrice() {
        return regularCalculatorPrice;
    }
    public void setRegularCalculatorPrice(Money regularCalculatorPrice) {
        this.regularCalculatorPrice = regularCalculatorPrice;
    }
    public String getPromotionalDisplayPrice() {
        return promotionalDisplayPrice;
    }
    public void setPromotionalDisplayPrice(String promotionalDisplayPrice) {
        this.promotionalDisplayPrice = promotionalDisplayPrice;
    }
    public Money getPromotionalCalculatorPrice() {
        return promotionalCalculatorPrice;
    }
    public void setPromotionalCalculatorPrice(Money promotionalCalculatorPrice) {
        this.promotionalCalculatorPrice = promotionalCalculatorPrice;
    }
    public String getUnitOfMeasure() {
        return unitOfMeasure;
    }
    public void setUnitOfMeasure(String unitOfMeasure) {
        this.unitOfMeasure = unitOfMeasure;
    }
    public String getProductSize() {
        return productSize;
    }
    public void setProductSize(String productSize) {
        this.productSize = productSize;
    }
    public BigDecimal getTaxRate() {
        return taxRate;
    }
    public void setTaxRate(BigDecimal taxRate) {
        this.taxRate = taxRate;
    }
}
