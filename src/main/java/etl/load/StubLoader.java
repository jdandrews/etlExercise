package etl.load;

import java.util.Queue;

import etl.model.DatabaseRecord;

/**
 * This is a stub; it just reports what it finds in the queue.  In an implementation, it would persist the
 * records it finds.
 */
public class StubLoader implements Runnable {
    Queue<DatabaseRecord> inputQueue;

    public StubLoader(Queue<DatabaseRecord> recordsToLoad) {
        inputQueue = recordsToLoad;
    }

    @Override
    public void run() {
        while ( ! inputQueue.isEmpty() ) {
            DatabaseRecord record = inputQueue.poll();
            if (record==null) {
                continue;
            }

            System.out.println(String.format("store %s: product ID %d", record.getStoreId(), record.getProductId()));
        }
    }
}
