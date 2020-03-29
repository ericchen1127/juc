package cas;

import org.junit.Test;

public class AccumulatorTest {

    @Test
    public void testSynchronizedAccumulator() throws InterruptedException {

        final SynchronizedAccumulator accumulator = new SynchronizedAccumulator();

        final Thread[] threads = new Thread[10];

        for (int i = 0; i < 10; i++) {
            threads[i] = new Thread(() -> {
                for (int j = 0; j < 1000000; j++) {
                    accumulator.increase();
                }
            });
        }

        long start = System.currentTimeMillis();
        for (Thread thread : threads) {
            thread.start();
        }
        for (Thread thread : threads) {
            thread.join();
        }
        long end = System.currentTimeMillis();

        System.out.println("SynchronizedAccumulator.count:" + SynchronizedAccumulator.count);
        System.out.println("duration of SynchronizedAccumulator:" + (end - start));
    }

    @Test
    public void testAtomicAccumulator() throws InterruptedException {

        final AtomicAccumulator accumulator = new AtomicAccumulator();

        final Thread[] threads = new Thread[10];

        for (int i = 0; i < 10; i++) {
            threads[i] = new Thread(() -> {
                for (int j = 0; j < 1000000; j++) {
                    accumulator.increase();
                }
            });
        }

        long start = System.currentTimeMillis();
        for (Thread thread : threads) {
            thread.start();
        }
        for (Thread thread : threads) {
            thread.join();
        }
        long end = System.currentTimeMillis();

        System.out.println("AtomicAccumulator.count:" + AtomicAccumulator.count);
        System.out.println("duration of AtomicAccumulator:" + (end - start));
    }

    @Test
    public void testAtomicAdderAccumulator() throws InterruptedException {

        final AtomicAdderAccumulator accumulator = new AtomicAdderAccumulator();

        final Thread[] threads = new Thread[10];

        for (int i = 0; i < 10; i++) {
            threads[i] = new Thread(() -> {
                for (int j = 0; j < 1000000; j++) {
                    accumulator.increase();
                }
            });
        }

        long start = System.currentTimeMillis();
        for (Thread thread : threads) {
            thread.start();
        }
        for (Thread thread : threads) {
            thread.join();
        }
        long end = System.currentTimeMillis();

        System.out.println("AtomicAdderAccumulator.count:" + AtomicAccumulator.count);
        System.out.println("duration of AtomicAdderAccumulator:" + (end - start));
    }
}