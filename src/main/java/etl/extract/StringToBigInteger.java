package etl.extract;

import java.math.BigInteger;

/**
 * Extracts a substring from the record and converts it to a BigInteger.
 *
 * The supplied start and end values establish the closed and open (respectively) ends of the substring to convert.
 */
public class StringToBigInteger {
    /**
     * Extracts a substring from the record and converts it to a BigInteger.
     *
     * The supplied start and end values establish the closed and open (respectively) ends of the substring to convert.
     *
     * @param record the record to parse
     * @param beginIndex the starting index to parse, inclusive
     * @param endIndex the ending index to parse, exclusive
     * @returns the BigInteger value of the included substring.
     * @throws NumberFormatException if the substring does not represent an integer.
     * @throws IndexOutOfBoundsException if beginIndex or endIndex is negative,
     *          or if beginIndex is greater than endIndex, or if beginIndex > record.length()
     */
    public BigInteger parse(String record, int beginIndex, int endIndex) {
        ParserUtils.verifyIndices(beginIndex, endIndex);

        return new BigInteger(record.substring(beginIndex, endIndex).trim());
    }

}
