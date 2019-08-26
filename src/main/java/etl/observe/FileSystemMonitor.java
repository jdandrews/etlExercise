package etl.observe;

import java.io.File;
import java.util.Arrays;
import java.util.Queue;

import etl.Configuration;

/**
 * Watches a directory for inbound files. When it finds them, it enqueues them on the supplied queue to be processed.
 */
public class FileSystemMonitor implements Runnable {

    private final Queue<File> incomingFileQueue;

    private File incomingFiles;

    /**
     * Construct a File System Monitor. The constructed monitor will use the configured directory, or will default
     * to "./etlIncoming" to look for inbound files.
     *
     * @param queueForIncomingFiles the queue to use for listing unprocessed files.
     */
    public FileSystemMonitor(Queue<File> queueForIncomingFiles) {
        this(queueForIncomingFiles,
                new File(Configuration.instance().getProperty(Configuration.INPUT_DIRECTORY_KEY, "./etlIncoming")));
    }

    /**
     * Construct a File System Monitor.
     *
     * @param queueForIncomingFiles the queue to use for listing unprocessed files.
     * @param incomingDirectory the directory to scan for unprocessed files.
     */
    public FileSystemMonitor(Queue<File> queueForIncomingFiles, File incomingDirectory) {
        if ( queueForIncomingFiles == null ) {
            throw new NullPointerException("inbound file queue may not be null");
        }
        incomingFileQueue = queueForIncomingFiles;
        setIncomingDirectory(incomingDirectory);
    }

    /**
     * Sets the incoming file directory. The supplied File must be a directory or must not exist. It may not be null.
     *
     * @param directory the directory to use
     * @throws IllegalArgumentException if the supplied file exists and is not a directory.
     * @throws NullPointerException if the supplied directory is null.
     */
    public void setIncomingDirectory(File directory) {
        if ( directory == null ) {
            throw new NullPointerException("inbound directory may not be null");
        }

        if ( directory.exists() && !directory.isDirectory() ) {
            throw new IllegalArgumentException(directory.getName() + " exists and is not a directory.");
        }

        incomingFiles = directory;
    }

    /**
     * Executes the process once; the inbound directory is scanned for files, and if any are present, they are
     * enqueued for processing.
     */
    @Override
    public void run() {
        if ( !incomingFiles.exists() ) {
            incomingFiles.mkdir();
        }

        File[] files = incomingFiles.listFiles();
        // enqueue files oldest-to-newest
        Arrays.sort(files, (file1, file2) -> file1.lastModified() >= file2.lastModified() ? 1 : -1);

        for (File f : files) {
            if ( f.isFile() ) {
                incomingFileQueue.add(f);
            }
        }
    }

}
