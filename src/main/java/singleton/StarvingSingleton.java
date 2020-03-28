package singleton;

/**
 * @author eric
 */
public class StarvingSingleton {

    private static StarvingSingleton INSTANCE = new StarvingSingleton();

    private StarvingSingleton() {

    }

    public static StarvingSingleton getInstance() {
        return INSTANCE;
    }
}
