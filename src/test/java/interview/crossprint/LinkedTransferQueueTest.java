package interview.crossprint;

import org.junit.Test;

import java.util.Stack;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.LinkedTransferQueue;
import java.util.concurrent.locks.LockSupport;

public class LinkedTransferQueueTest {

    @Test
    public void testCrossPrint() throws InterruptedException {

        LinkedTransferQueue<Integer> queue = new LinkedTransferQueue<>();

        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < 20; i++) {
            stack.push(20 - i);
        }

        final Thread oddThread = new Thread(() -> {
            System.out.println("odd thread starts");
            while (!stack.isEmpty()) {
                try {
                    System.out.println("odd thread prints " + queue.take());
                    queue.transfer(stack.pop());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "odd");

        final Thread evenThread = new Thread(() -> {
            System.out.println("even thread starts");
            while (!stack.isEmpty()) {
                try {
                    queue.transfer(stack.pop());
                    System.out.println("even thread prints " + queue.take());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "even");

        oddThread.start();
        evenThread.start();

        oddThread.join();
        oddThread.join();
    }
}
