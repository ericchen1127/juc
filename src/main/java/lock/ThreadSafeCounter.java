package lock;

/**
 * @author eric
 */
public class ThreadSafeCounter {

    private Long count = 0L;

    public Long getCount() {
        return count;
    }

    public synchronized void increase() {
        count += 1;
        System.out.println("increase");
    }

    public synchronized void decrease() {
        count -= 1;
        System.out.println("decrease");
    }
}
