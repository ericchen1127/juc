package lock;

import org.junit.Test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class CountDownLatchTest {

    @Test
    public void testCountDownLatch() throws InterruptedException {

        final CountDownLatch latch = new CountDownLatch(2);

        new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("request agreement service");
            latch.countDown();
        }).start();
        new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("request member service");
            latch.countDown();
        }).start();

        latch.await();
        System.out.println("merge response");
    }
}
