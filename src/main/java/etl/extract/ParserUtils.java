package etl.extract;

/**
 * Some basic tools used by many parsing classes.
 */
public class ParserUtils {
    /**
     * Verifies string parsing indices as far as reasonable under the circumstances.
     *
     * @param beginIndex the starting index to parse, inclusive
     * @param endIndex the ending index to parse, exclusive
     */
    public static void verifyIndices(int beginIndex, int endIndex) {
        if (beginIndex < 0 || endIndex < 0) {
            throw new IndexOutOfBoundsException( String.format("indices must be non-negative "
                    + "(begin: %d, end: %d).", beginIndex, endIndex));
        }
        if (beginIndex >= endIndex) {
            throw new IndexOutOfBoundsException( String.format("the substring defined starts after it ends "
                    + "(begin: %d, end: %d).", beginIndex, endIndex));
        }
    }

}
