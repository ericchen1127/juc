package thread;

/**
 * @author eric
 */
public class CustomThread extends Thread {
    @Override
    public void run() {
        System.out.println("this is a custom thread extending Thread");
    }
}
