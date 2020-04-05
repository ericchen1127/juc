package interview.crossprint;

import org.junit.Test;

import java.util.Stack;

public class CasTest {

    private volatile int RUNNING_THREAD_NO = 1;

    @Test
    public void testCrossPrint() throws InterruptedException {

        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < 20; i++) {
            stack.push(20 - i);
        }

        final Thread oddThread = new Thread(() -> {
            System.out.println("odd thread starts");
            while (!stack.isEmpty()) {
                while (true) {
                    if (RUNNING_THREAD_NO == 1) {
                        System.out.println("odd thread prints " + stack.pop());
                        if (!stack.isEmpty()) {
                            RUNNING_THREAD_NO = 2;
                        }
                        break;
                    }
                }
            }
        }, "odd");

        final Thread evenThread = new Thread(() -> {
            System.out.println("even thread starts");
            while (!stack.isEmpty()) {
                while (true) {
                    if (RUNNING_THREAD_NO == 2) {
                        System.out.println("even thread prints " + stack.pop());
                        if (!stack.isEmpty()) {
                            RUNNING_THREAD_NO = 1;
                        }
                        break;
                    }
                }
            }
        }, "even");

        oddThread.start();
        evenThread.start();

        oddThread.join();
        evenThread.join();
    }
}
