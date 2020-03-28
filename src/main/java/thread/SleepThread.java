package thread;

/**
 * @author eric
 */
public class SleepThread extends Thread{
    @Override
    public void run() {
        try {
            Thread.sleep(5000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("this is a sleep thread extending Thread");
    }
}
