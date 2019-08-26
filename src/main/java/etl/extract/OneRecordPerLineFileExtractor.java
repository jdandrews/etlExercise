package etl.extract;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Queue;

import etl.Configuration;

/**
 * Extracts inbound records from an input file and enqueues them for transformation. Assumes one record per
 * line.
 */
public class OneRecordPerLineFileExtractor implements Runnable {
    private static final String WORKING_DIRECTORY =
            Configuration.instance().getProperty(Configuration.WORK_DIRECTORY_KEY, "./etlWork");
    private static final String OUTPUT_DIRECTORY =
            Configuration.instance().getProperty(Configuration.OUTPUT_DIRECTORY_KEY, "./etlComplete");

    private final Queue<File> filesToParse;
    private final Queue<FileRecord> records;

    public OneRecordPerLineFileExtractor(Queue<File> incomingFiles, Queue<FileRecord> recordsToTransform) {
        if (incomingFiles==null) {
            throw new NullPointerException("an incoming file queue is required.");
        }
        if (recordsToTransform==null) {
            throw new NullPointerException("an outbound queue of extracted records is required.");
        }

        filesToParse = incomingFiles;
        records = recordsToTransform;
    }

    @Override
    public void run() {
        File workingDirectory = new File(WORKING_DIRECTORY);
        File outputDirectory  = new File(OUTPUT_DIRECTORY);

        try {
            create(workingDirectory);
            create(outputDirectory);
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        while ( ! filesToParse.isEmpty() ) {
            File inputFile = filesToParse.poll();
            long fileModified = inputFile.lastModified();

            if (! move(inputFile, workingDirectory)) {
                continue;
            }
            inputFile = new File(workingDirectory, inputFile.getName());

            parseFileContent(inputFile, fileModified);

            move(inputFile, outputDirectory);
        }
    }

    private void create(File directory) throws IOException {
        if ( ! directory.exists() ) {
            if ( ! directory.mkdir()) {
                throw new IOException("Unable to create directory "+directory.getAbsolutePath());
            }
        }
    }

    private void parseFileContent(File inputFile, long fileModified) {
        FileRecordParser parser = new FileRecordParser();

        try (LineNumberReader in = new LineNumberReader(new FileReader(inputFile))) {
            for (String record = in.readLine(); record!=null; record = in.readLine()) {

                FileRecord fileRecord = parser.parse(record);
                fileRecord.setSourceFilename(inputFile.getName());
                fileRecord.setSourceFileLastModified(fileModified);

                records.add(fileRecord);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();

        } catch (IOException e) {
            e.printStackTrace();

        }
    }

    /**
     * Moves a file to a target directory. In a multi-instance environment, this mail fail, because the file
     * may not longer be at the path expected when the move begins. Return success or fail to indicate this
     * behavior.
     *
     * @param inputFile the file to move
     * @param targetDirectory the directory into which the input file is to be moved.
     * @return true of the move succeeds, else false
     */
    private boolean move(File inputFile, File targetDirectory) {
        Path result;
        try {
            result = Files.move(Paths.get(inputFile.getAbsolutePath()),
                    Paths.get(targetDirectory.getAbsolutePath(), inputFile.getName()),
                    StandardCopyOption.ATOMIC_MOVE);

        } catch (IOException e) {
            return false;
        }

        // verify a successful move
        return result.startsWith(targetDirectory.getAbsolutePath());
    }
}
