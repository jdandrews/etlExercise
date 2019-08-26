package etl.extract;

/**
 * Extracts a substring of the supplied record and treats it as an array of flags. Each character is converted
 * to upper case and checked against "Y" or "T" (true) and anything else (false). The resulting boolean array
 * will have the same length as the identified substring.
 *
 */
public class StringToBooleanArray {

    /**
     * Extract a substring from the record and parse it as a boolean array.
     *
     * @param record the record to parse
     * @param beginIndex the start index from record to extract, inclusive
     * @param endIndex the end index from record to extract, exclusive
     * @return a boolean array as described in the class description.
     */
    public boolean[] parse(String record, int beginIndex, int endIndex) {
        ParserUtils.verifyIndices(beginIndex, endIndex);

        boolean[] result = new boolean[endIndex - beginIndex];

        for (int n = beginIndex; n < endIndex; ++n) {
            char ch = Character.toUpperCase(record.charAt(n));
            if (ch == 'T' || ch == 'Y') {
                result[n-beginIndex] = true;
            }
        }

        return result;
    }

}
