package etl;

import java.util.Properties;

/**
 * Holds the app's static configuration; a set of key-value pairs.
 * This might be loaded from a file or database (or both) "later".
 */
public class Configuration extends Properties {
    private static final long serialVersionUID = 1L;

    /** where inbound files are located */
    public static final String INPUT_DIRECTORY_KEY = "input_directory";
    /** where files are located while they are being processed */
    public static final String WORK_DIRECTORY_KEY = "work_directory";
    /** where files are left once processing is complete */
    public static final String OUTPUT_DIRECTORY_KEY = "output_directory";

    private Configuration() {
        setProperty(INPUT_DIRECTORY_KEY, "./etlIncoming");
        setProperty(WORK_DIRECTORY_KEY, "./etlWork");
        setProperty(OUTPUT_DIRECTORY_KEY, "./etlComplete");
    }

    /**
     * Retrieve the current configuration.
     *
     * @return the current configuration.
     */
    public static Configuration instance() {
        return new Configuration();
    }
}
