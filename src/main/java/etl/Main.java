/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package etl;

import java.io.File;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * System entry point. Holds the data queues, and launches the threads which monitor the queues and processes them.
 */
public class Main {
    private final Queue<File> incomingFiles = new ConcurrentLinkedQueue<>();
    /** queue of records extracted from incoming files */
    private final Queue<FileRecord> recordsToTransform = new ConcurrentLinkedQueue<>();
    /** queue of transformed records generated from incoming records */
    private final Queue<DatabaseRecord> recordsToLoad = new ConcurrentLinkedQueue<>();

    /** monitors the file system looking for inbound files. */
    private final ScheduledThreadPoolExecutor fileSystemMonitor = new ScheduledThreadPoolExecutor(1);
    /** extracts records from inbound files */
    private final ScheduledThreadPoolExecutor extractor = new ScheduledThreadPoolExecutor(1);
    /** transforms inbound records into records in internal database format */
    private final ScheduledThreadPoolExecutor transformer = new ScheduledThreadPoolExecutor(1);
    /** loads internal database format records into the data store */
    private final ScheduledThreadPoolExecutor loader = new ScheduledThreadPoolExecutor(1);

    public void run() {
        // build the execution pipeline and run
        fileSystemMonitor.scheduleAtFixedRate(new FileSystemMonitor(incomingFiles), 0, 5, TimeUnit.SECONDS);
        extractor.scheduleAtFixedRate(new Extractor(incomingFiles, recordsToTransform), 0, 5, TimeUnit.SECONDS);
        transformer.scheduleAtFixedRate(new Transformer(recordsToTransform, recordsToLoad), 0, 5, TimeUnit.SECONDS);
        loader.scheduleAtFixedRate(new Loader(recordsToLoad), 0, 5, TimeUnit.SECONDS);
    }

    public static void main(String[] args) {
        new Main().run();
    }
}
