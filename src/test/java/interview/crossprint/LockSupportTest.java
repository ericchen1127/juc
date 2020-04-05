package interview.crossprint;

import org.junit.Test;

import java.util.Stack;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.LockSupport;

public class LockSupportTest {

    private Thread oddThread = null;
    private Thread evenThread = null;

    @Test
    public void testCrossPrint() throws InterruptedException {

        CountDownLatch latch = new CountDownLatch(1);

        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < 20; i++) {
            stack.push(20 - i);
        }

        oddThread = new Thread(() -> {
            System.out.println("odd thread starts");
            while (!stack.isEmpty()) {
                System.out.println("odd thread prints " + stack.pop());
                LockSupport.unpark(evenThread);
                LockSupport.park();
            }
        }, "odd");

        evenThread = new Thread(() -> {
            System.out.println("even thread starts");
            latch.countDown();
            while (!stack.isEmpty()) {
                LockSupport.park();
                System.out.println("even thread prints " + stack.pop());
                LockSupport.unpark(oddThread);
            }
        }, "even");

        evenThread.start();
        latch.await();
        oddThread.start();

        oddThread.join();
        evenThread.join();
    }
}
