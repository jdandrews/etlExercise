package etl;

import java.io.File;
import java.util.Queue;

/**
 * Extracts inbound records and enqueues them for transformation.
 */
public class Extractor implements Runnable {

    public Extractor(Queue<File> incomingFiles, Queue<FileRecord> recordsToTransform) {
        // TODO Auto-generated constructor stub
    }

    @Override
    public void run() {
        System.out.println(this.getClass().getName() + " running.");
    }

}
