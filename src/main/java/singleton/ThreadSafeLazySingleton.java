package singleton;

/**
 * @author eric
 */
public class ThreadSafeLazySingleton {

    /**
     * volatile here is necessary
     */
    private static volatile ThreadSafeLazySingleton INSTANCE;

    private ThreadSafeLazySingleton() {

    }

    /**
     * how to provide thread safe singleton
     *
     * 1. double check if INSTANCE is null
     * 2. use volatile to define INSTANCE
     * 3. use synchronized to lock the code block
     *
     * @return
     */
    private static ThreadSafeLazySingleton getInstance() {
        if (INSTANCE == null) {
            synchronized (ThreadSafeLazySingleton.class) {
                if (INSTANCE == null) {
                    INSTANCE = new ThreadSafeLazySingleton();
                }
            }
        }
        return INSTANCE;
    }
}
