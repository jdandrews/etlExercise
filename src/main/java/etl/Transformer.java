package etl;

import java.util.Queue;

public class Transformer implements Runnable {

    public Transformer(Queue<FileRecord> recordsToTransform, Queue<DatabaseRecord> recordsToLoad) {
        // TODO Auto-generated constructor stub
    }

    @Override
    public void run() {
        System.out.println(this.getClass().getName() + " running.");
    }

}
