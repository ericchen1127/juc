package thread;
import org.junit.Test;

public class ThreadTest {

    /**
     * how to start a thread
     */
    @Test
    public void testCreateThread(){
        new CustomThread().start();
        new Thread(new CustomRunnableThread()).start();
        new Thread(()-> System.out.println("this is a lambda expression thread")).start();
    }

    /**
     * how to use Thread.join()
     * @throws InterruptedException
     */
    @Test
    public void testThreadJoin() throws InterruptedException {

        final SleepThread sleepThread = new SleepThread();
        sleepThread.start();
        sleepThread.join();

        final CustomThread customThread = new CustomThread();
        customThread.start();
    }
}