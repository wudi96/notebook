package com.notebook.async;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * CompletableFuture
 *
 * @author luorigong
 */
public class CompletableFutureTest {
    public static void main(String[] args) throws ExecutionException, InterruptedException {

        CompletableFuture<Object> futureInteract = new CompletableFuture<>();

        futureInteract.thenAccept(response -> {
            System.out.println(response);
            System.out.println("111");
        });
        futureInteract.complete("Future's Result");

    }
}
