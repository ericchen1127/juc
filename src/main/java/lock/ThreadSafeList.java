package lock;

import java.util.LinkedList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * ReentrantLock and Condition
 *
 * @author eric
 */
public class ThreadSafeList<E> {

    final private int MAX_COUNT = 10;
    private int count;
    private LinkedList<E> list = new LinkedList<>();

    private Lock lock = new ReentrantLock();
    private Condition putCondition = lock.newCondition();
    private Condition getCondition = lock.newCondition();

    public void put(E e) throws InterruptedException {
        lock.lock();
        try {
            while (count == MAX_COUNT) {
                putCondition.await();
            }
            list.push(e);
            count++;
            getCondition.signalAll();
        } finally {
            lock.unlock();
        }
    }

    public E get() throws InterruptedException {
        E e = null;
        lock.lock();
        try {
            while (count == 0) {
                getCondition.await();
            }
            e = list.removeFirst();
            count--;
            putCondition.signalAll();
        } finally {
            lock.unlock();
        }
        return e;
    }
}
