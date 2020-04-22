package com.notebook.concurrent;

import java.util.concurrent.locks.LockSupport;

/**
 * Created by luorigong on 2020-04-20.
 */
public class UnSafeSource {

  public static void main(String[] args) {
    LockSupport.park();
  }
}
