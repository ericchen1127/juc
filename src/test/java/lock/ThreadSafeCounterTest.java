package lock;


import org.junit.Test;

import java.util.Stack;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

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

    @Test
    public void testCrossPrint() throws InterruptedException {

        CountDownLatch latch = new CountDownLatch(1);
        Object lock = new Object();

        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < 20; i++) {
            stack.push(20 - i);
        }

        final Thread oddThread = new Thread(() -> {
            System.out.println("odd thread print starts");
            synchronized (lock) {
                while (!stack.isEmpty()) {
                    System.out.println("odd thread print " + stack.pop());
                    lock.notifyAll();
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }, "odd");

        final Thread evenThread = new Thread(() -> {
            System.out.println("even thread print starts");
            latch.countDown();
            System.out.println("even thread count down latch");
            synchronized (lock) {
                while (!stack.isEmpty()) {
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("even thread print " + stack.pop());
                    lock.notifyAll();
                }
            }
        }, "even");

        evenThread.start();
        latch.await();
        oddThread.start();

        oddThread.join();
        evenThread.join();
    }
}