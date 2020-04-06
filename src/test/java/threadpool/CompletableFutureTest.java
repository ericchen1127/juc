package threadpool;

import org.junit.Test;

import java.util.concurrent.CompletableFuture;

public class CompletableFutureTest {

    @Test
    public void testCompletableFuture() {

        CompletableFuture<Void> agreementFuture = CompletableFuture.runAsync(() -> {
            System.out.println("request agreement service");
        });

        CompletableFuture<Void> memberFuture = CompletableFuture.runAsync(() -> {
            System.out.println("request member service");
        });

        CompletableFuture.allOf(agreementFuture, memberFuture);
    }
}
