package etl.extract;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Currency;

/**
 * Money. Note that JSR-354 might make this obsolete soon... but I'm using Java 9.
 *
 * Also note that almost none of the BigDecimal methods are overridden. Please, when you have the time, add the rest.
 * But be very careful with constructors.
 *
 * This version is limited to US Dollars, but it should be easy to extend (or re-implement--the dependency is hidden).
 */
public class Money extends BigDecimal {
    private static final long serialVersionUID = 1L;

    private Currency currency = Currency.getInstance("USD");

    public Money(String val) {
        super(val);
    }

    private Money(BigDecimal value, Currency currency) {
        super(value.toPlainString());
        this.currency = currency;
    }

    public Currency getCurrency() {
        return currency;
    }

    @Override
    public Money divide(BigDecimal divisor, int scale, RoundingMode roundingMode) {
        return new Money(super.divide(divisor, scale, roundingMode), currency);
    }
}
