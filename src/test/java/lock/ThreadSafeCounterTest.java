package lock;


import org.junit.Test;

import java.util.Stack;
import java.util.concurrent.CountDownLatch;

public class ThreadSafeCounterTest {

    @Test
    public void testThreadSafeCounter() throws InterruptedException {

        final ThreadSafeCounter counter = new ThreadSafeCounter();

        final Thread incThread = new Thread(() -> {
            for (int i = 0; i < 10000; i++) {
                counter.increase();
            }
        });
        final Thread decThread = new Thread(() -> {
            for (int i = 0; i < 10000; i++) {
                counter.decrease();
            }
        });

        incThread.start();
        decThread.start();

        incThread.join();
        decThread.join();

        System.out.println("result is " + counter.getCount());
    }
}