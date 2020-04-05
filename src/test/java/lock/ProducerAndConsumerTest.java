package lock;

import org.junit.Test;

import java.util.concurrent.atomic.AtomicInteger;


public class ProducerAndConsumerTest {

    @Test
    public void testThreadSafeList() {

        AtomicInteger count = new AtomicInteger(1);

        final ThreadSafeList<String> list = new ThreadSafeList<>();

        for (int i = 0; i < 2; i++) {
            new Thread(() -> {
                for (int j = 0; j < 25; j++) {
                    try {
                        list.put(Thread.currentThread().getName() + " produces " + j);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }, "P" + i).start();
        }

        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                for (int j = 0; j < 5; j++) {
                    try {
                        System.out.println(count.getAndIncrement() + "." + Thread.currentThread().getName() + " consumes " + list.get());
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }, "C" + i).start();
        }

    }
}