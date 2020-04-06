package threadpool;

import org.junit.Test;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class FutureTest {

    @Test
    public void testCallable() throws ExecutionException, InterruptedException {

        ExecutorService service = Executors.newFixedThreadPool(1);

        // callable
        final Callable<Integer> integerCallable = new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                return 1;
            }
        };
        final Future<Integer> integerFuture = service.submit(integerCallable);

        // lambada
        final Future<String> stringFuture = service.submit(() -> {
            return "hello future";
        });

        System.out.println(integerFuture.get());
        System.out.println(stringFuture.get());

        service.shutdown();
    }
}
