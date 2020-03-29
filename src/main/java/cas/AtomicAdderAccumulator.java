package cas;

import java.util.concurrent.atomic.LongAdder;

/**
 * @author eric
 */
public class AtomicAdderAccumulator {

    public static LongAdder count = new LongAdder();

    public void increase() {
        count.increment();
    }
}
