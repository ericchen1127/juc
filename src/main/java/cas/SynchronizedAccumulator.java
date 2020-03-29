package cas;

/**
 * @author eric
 */
public class SynchronizedAccumulator {

    public static Long count = 0L;

    public synchronized void increase() {
        count++;
    }
}
