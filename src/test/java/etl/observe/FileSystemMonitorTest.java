package etl.observe;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;

import org.testng.annotations.Test;

import static org.testng.Assert.*;

import etl.observe.FileSystemMonitor;

public class FileSystemMonitorTest {
    @Test(expectedExceptions = { NullPointerException.class })
    public void testConstructorNull() {
        new FileSystemMonitor(null);
    }

    @Test(expectedExceptions = { NullPointerException.class })
    public void testConstructorValidQueueNullDirectory() {
        new FileSystemMonitor(new LinkedList<File>(), null);
    }

    @Test(expectedExceptions = { IllegalArgumentException.class })
    public void testConstructorFile() throws IOException {
        File temp = File.createTempFile("temp", null);
        temp.deleteOnExit();

        new FileSystemMonitor(new LinkedList<File>(), temp);
    }

    @Test
    public void testRun() throws IOException {
        Queue<File> queue = new LinkedList<>();
        File dir = new File("./testInboundDirectory");
        dir.mkdir();
        File f1 = File.createTempFile("grocery", ".txt", dir);
        sleepOneSecond();
        File f2 = File.createTempFile("grocery", ".txt", dir);

        FileSystemMonitor monitor = new FileSystemMonitor(queue, dir);
        monitor.run();

        assertEquals(queue.size(), 2);
        assertEquals(queue.poll(), f1);
        assertEquals(queue.poll(), f2);

        f1.delete();
        f2.delete();
        dir.delete();
    }

    private void sleepOneSecond() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
