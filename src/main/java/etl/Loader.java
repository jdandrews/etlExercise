package etl;

import java.util.Queue;

import etl.model.DatabaseRecord;

public class Loader implements Runnable {

    public Loader(Queue<DatabaseRecord> recordsToLoad) {
        // TODO Auto-generated constructor stub
    }

    @Override
    public void run() {
        System.out.println(this.getClass().getName() + " running.");
    }
}
