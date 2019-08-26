package etl.extract;

public class FileRecordParser {
    private static final int DECIMAL_SHIFT = 2;

    /** field parsers */
    private final StringToBigInteger stringToBigInteger = new StringToBigInteger();
    private final StringToString     stringToString = new StringToString();
    private final CurrencyStringToMoney currencyStringToMoney = new CurrencyStringToMoney();
    private final StringToBooleanArray stringToBooleanArray = new StringToBooleanArray();

    /**
     * Parses a String into a FileRecord.
     *
     * Warning: essentially no data validation. This can throw runtime exceptions if the data don't match the spec.
     * TODO: express the input contract and verify on call.
     *
     * @param record a standard file record; see ProductInformationIntegrationSpec#Input Data Format
     * @return a FileRecord: an in-memory representation of the string record
     */
    public FileRecord parse(String record) {
        FileRecord result = new FileRecord();

        result.setProductId(               stringToBigInteger.parse(   record,  0,   8));
        result.setProductDescription(      stringToString.parse(       record,  9,  68));
        result.setRegularSingularPrice(    currencyStringToMoney.parse(record, 69,  77, DECIMAL_SHIFT));
        result.setPromotionalSingularPrice(currencyStringToMoney.parse(record, 78,  86, DECIMAL_SHIFT));
        result.setRegularSplitPrice(       currencyStringToMoney.parse(record, 87,  95, DECIMAL_SHIFT));
        result.setPromotionalSplitPrice(   currencyStringToMoney.parse(record, 96, 104, DECIMAL_SHIFT));
        result.setRegularForX(             stringToBigInteger.parse(   record, 105, 113));
        result.setPromotionalForX(         stringToBigInteger.parse(   record, 114, 122));
        result.setFlags(                   stringToBooleanArray.parse( record, 123, 132));
        result.setProductDescription(      stringToString.parse(       record, 133, 142));

        return result;
    }

}
