package interview.crossprint;

import org.junit.Test;

import java.util.Stack;
import java.util.concurrent.CountDownLatch;

public class SynchronizedTest {

    @Test
    public void testCrossPrint() throws InterruptedException {

        CountDownLatch latch = new CountDownLatch(1);
        Object lock = new Object();

        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < 20; i++) {
            stack.push(20 - i);
        }

        final Thread oddThread = new Thread(() -> {
            System.out.println("odd thread starts");
            synchronized (lock) {
                while (!stack.isEmpty()) {
                    System.out.println("odd thread prints " + stack.pop());
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
            System.out.println("even thread starts");
            latch.countDown();
            System.out.println("even thread count down latch");
            synchronized (lock) {
                while (!stack.isEmpty()) {
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("even thread prints " + stack.pop());
                    lock.notifyAll();
                }
            }
        }, "even");

        evenThread.start();
        // make evenThread run before oddThread
        latch.await();
        oddThread.start();

        oddThread.join();
        evenThread.join();
    }
}
