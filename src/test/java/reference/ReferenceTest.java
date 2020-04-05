package reference;

import org.junit.Test;

import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;
import java.util.concurrent.TimeUnit;

public class ReferenceTest {

    @Test
    public void testReference() {

        ReferenceModel model = new ReferenceModel();
        model = null;

        System.gc();
    }

    @Test
    public void testSoftReference() throws InterruptedException {

        // create a byte array which size is 10M
        final SoftReference<Byte[]> softReference = new SoftReference<>(new Byte[1024 * 1024 * 10]);
        System.out.println(softReference.get());

        System.gc();
        TimeUnit.SECONDS.sleep(2);

        // byte array is NOT finalized by GC
        System.out.println(softReference.get());

        // create a new 15M byte array, the old one will be finalized if the heap size is 20M
        final Byte[] bytes = new Byte[1024 * 10 * 20];
        System.out.println(softReference.get());
    }

    @Test
    public void testWeakReference() throws InterruptedException {

        // test 1: the instance in heap will be finalized when it is no longer referenced
        WeakReference<ReferenceModel> weakReference = new WeakReference<>(new ReferenceModel());
        System.out.println(weakReference.get());

        System.gc();
        TimeUnit.SECONDS.sleep(2);

        System.out.println(weakReference.get());

        // test 2: thread local weak reference
        ThreadLocal<ReferenceModel> threadLocal = new ThreadLocal<>();

        threadLocal.set(new ReferenceModel());
        threadLocal.remove();
        TimeUnit.SECONDS.sleep(5);
    }
}
