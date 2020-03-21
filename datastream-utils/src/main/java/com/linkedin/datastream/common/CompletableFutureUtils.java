package com.linkedin.datastream.common;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import java.time.Duration;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.function.Function;
import org.apache.commons.lang.NullArgumentException;

public class CompletableFutureUtils {
  private static final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1,
      new ThreadFactoryBuilder().setDaemon(true).setNameFormat("failAfter-%d").build());

  public static <T> CompletableFuture<T> failAfter(Duration duration) {
    final CompletableFuture<T> promise = new CompletableFuture<>();
    scheduler.schedule(() -> {
      TimeoutException ex = new TimeoutException(String.format("Timeout after {}ms", duration));
      return promise.completeExceptionally(ex);
    }, duration.toMillis(), TimeUnit.MILLISECONDS);
    return promise;
  }

  public static <T> CompletableFuture<T> within(CompletableFuture<T> future, Duration duration) throws
                                                                                                NullArgumentException {
    if (future == null) throw new NullArgumentException("future");

    CompletableFuture<T> timeout = failAfter(duration);
    return future.applyToEither(timeout, Function.identity());
  }

}
