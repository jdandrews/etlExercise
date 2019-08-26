package etl.extract;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Extracts a substring from the record and converts it to a money object. Assumes the string is unscaled,
 * and offers the option to shift the decimal place, effectively inserting an implied decimal point. For example,
 * the string "1725" might imply 1725 USD, but if the decimal shift is 2, then the implied value is 17.25 USD.
 *
 * The supplied start and end values establish the closed and open (respectively) ends of the substring to convert.
 */
public class CurrencyStringToMoney {
    /**
     * Parse a field contained in the supplied record.
     *
     * Use a decimalShift of 0 if the field already contains a decimal point.
     *
     * @param record the record to parse
     * @param beginIndex the starting index to parse, inclusive
     * @param endIndex the ending index to parse, exclusive
     * @param decimalShift decimal places to the left-shift the input;
     *          use 0 if the decimal point is already in the right place.
     * @throws IndexOutOfBoundsException if beginIndex or endIndex is negative,
     *          or if beginIndex is greater than endIndex, or if beginIndex > record.length()
     */
    public Money parse(String record, int beginIndex, int endIndex, int decimalShift) {
        ParserUtils.verifyIndices(beginIndex, endIndex);

        Money rawValue = new Money(record.substring(beginIndex, endIndex).trim());
        return rawValue.divide(BigDecimal.TEN.pow(decimalShift), 2, RoundingMode.HALF_DOWN);
    }
}
