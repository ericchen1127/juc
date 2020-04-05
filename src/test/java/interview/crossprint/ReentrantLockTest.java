package interview.crossprint;

import org.junit.Test;

import java.util.Stack;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockTest {

    @Test
    public void testCrossPrint() throws InterruptedException {

        CountDownLatch latch = new CountDownLatch(1);

        Lock lock = new ReentrantLock();
        final Condition oddCondition = lock.newCondition();
        final Condition evenCondition = lock.newCondition();

        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < 20; i++) {
            stack.push(20 - i);
        }

        final Thread oddThread = new Thread(() -> {
            System.out.println("odd thread starts");
            lock.lock();
            try {
                while (!stack.isEmpty()) {
                    System.out.println("odd thread prints " + stack.pop());
                    evenCondition.signal();
                    oddCondition.await();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }

        }, "odd");

        final Thread evenThread = new Thread(() -> {
            System.out.println("even thread starts");
            latch.countDown();
            lock.lock();
            try {
                while (!stack.isEmpty()) {
                    evenCondition.await();
                    System.out.println("even thread prints " + stack.pop());
                    oddCondition.signal();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }, "even");

        evenThread.start();
        latch.await();
        oddThread.start();

        oddThread.join();
        evenThread.join();
    }
}
