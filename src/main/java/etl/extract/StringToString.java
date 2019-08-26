package etl.extract;

/**
 * Extracts a substring from the record.
 *
 * The supplied start and end values establish the closed and open (respectively) ends of the substring to convert.
 */
public class StringToString {
    /**
     * Extracts the appropriate substring.
     *
     * @param record the record to parse
     * @param beginIndex the starting index to parse, inclusive
     * @param endIndex the ending index to parse, exclusive
     * @return the substring of the record between the beginning and the end supplied.
     * @throws IndexOutOfBoundsException if beginIndex or endIndex is negative,
     *          or if beginIndex is greater than endIndex, or if beginIndex &gt; record.length()
     */
    public String parse(String record, int beginIndex, int endIndex) {
        ParserUtils.verifyIndices(beginIndex, endIndex);

        return record.substring(beginIndex, endIndex);
    }
}
