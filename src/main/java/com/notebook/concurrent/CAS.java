package com.notebook.concurrent;

import java.util.concurrent.atomic.AtomicReference;

/**
 * Created by luorigong on 2020-04-21.
 */
public class CAS {

  private AtomicReference<Thread> cas = new AtomicReference<Thread>();

  public void lock() {
    Thread current = Thread.currentThread();
    // 利用CAS
    while (!cas.compareAndSet(null, current)) {
      // DO nothing
    }
  }

  public void unlock() {
    Thread current = Thread.currentThread();
    cas.compareAndSet(current, null);
  }
}
