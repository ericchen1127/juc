package cas;

import java.util.concurrent.atomic.AtomicLong;

/**
 * @author eric
 */
public class AtomicAccumulator {

    public static AtomicLong count = new AtomicLong(0);

    public void increase(){
        count.incrementAndGet();
    }
}
