package threadpool;

import org.junit.Test;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

public class FutureTaskTest {

    @Test
    public void testFutureTask() throws ExecutionException, InterruptedException {

        ExecutorService service = Executors.newFixedThreadPool(1);

        FutureTask<String> task = new FutureTask<>(() -> {
            return "hello future task";
        });
        service.submit(task);

        System.out.println(task.get());

        service.shutdown();
    }
}
