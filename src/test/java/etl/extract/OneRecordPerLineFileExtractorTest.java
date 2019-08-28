package etl.extract;

import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.Queue;

import org.testng.annotations.Test;

import static org.testng.Assert.*;

import etl.Configuration;
import etl.model.FileRecord;

public class OneRecordPerLineFileExtractorTest {
    private final Queue<File> filesToParse = new LinkedList<>();
    private final Queue<FileRecord> records = new LinkedList<>();
    private final OneRecordPerLineFileExtractor extractor = new OneRecordPerLineFileExtractor(filesToParse, records);

    // TODO: this test is fragile and may leave files; fix that.
    @Test
    public void testCreateDirectory() throws IOException {
        File dir = new File("testDirectory");

        dir = findUnusedName(dir);

        assertFalse(dir.exists());
        extractor.create(dir);
        assertTrue(dir.exists());

        dir.delete();
    }

    // TODO: this test is fragile and may leave files; fix that.
    @Test
    public void testMove() throws IOException {
        File inDir = new File("startDirectory");
        inDir = findUnusedName(inDir);

        File workDir = new File("workDirectory");
        workDir = findUnusedName(workDir);

        inDir.mkdir();
        workDir.mkdir();

        File temp = File.createTempFile("temp", ".tmp", inDir);

        assertTrue(extractor.move(temp, workDir));
        assertFalse(temp.exists());
        temp = new File(workDir, temp.getName());
        assertTrue(temp.exists());

        inDir.delete();
        temp.delete();
        workDir.delete();
    }

    private File findUnusedName(File f) {
        File result = f;
        int n=0;
        while (result.exists()) {
            result = new File(result.getName() + ++n);
        }
        return result;
    }

    // TODO: this test is fragile and may leave files; fix that.
    @Test
    public void testRun() throws IOException {
        // create the input directory and copy the sample data to it.
        Configuration config = Configuration.instance();

        File inDir = new File(config.getProperty(Configuration.INPUT_DIRECTORY_KEY));
        inDir = findUnusedName(inDir);
        inDir.mkdir();

        Files.copy(Paths.get("src/test/resources/input-sample.txt"),
                Paths.get(inDir.getAbsolutePath()+"/input-sample.txt"));

        // insert the inbound file reference into the processing queue
        File inbound = new File(inDir, "input-sample.txt");
        filesToParse.add(inbound);

        // execute that which we are testing
        extractor.run();

        // verify that the working and output directories were created
        File workDir = new File(config.getProperty(Configuration.WORK_DIRECTORY_KEY));
        assertTrue(workDir.exists());
        File outDir = new File(config.getProperty(Configuration.OUTPUT_DIRECTORY_KEY));
        assertTrue(outDir.exists());
        inbound = new File(outDir, "input-sample.txt");
        assertTrue(inbound.exists());

        inbound.delete();
        inDir.delete();
        workDir.delete();
        outDir.delete();

        // verify that the expected output records were created in the expected order
        FileRecord record = records.poll();
        assertEquals(record.getProductId(), new BigInteger("80000001"));
        assertEquals(record.getSourceFilename(), "input-sample.txt");

        record = records.poll();
        assertEquals(record.getProductId(), new BigInteger("14963801"));
        assertEquals(record.getSourceFilename(), "input-sample.txt");

        record = records.poll();
        assertEquals(record.getProductId(), new BigInteger("40123401"));
        assertEquals(record.getSourceFilename(), "input-sample.txt");

        record = records.poll();
        assertEquals(record.getProductId(), new BigInteger("50133333"));
        assertEquals(record.getSourceFilename(), "input-sample.txt");
    }
}
