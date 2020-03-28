package singleton;

/**
 * @author eric
 */
public class LazySingleton {

    private static LazySingleton INSTANCE;

    private LazySingleton() {

    }

    /**
     * this method is not thread safe
     * @return
     */
    private static LazySingleton getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new LazySingleton();
        }
        return INSTANCE;
    }
}
