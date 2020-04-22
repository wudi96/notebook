package com.notebook.concurrent;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @author luorigong
 */
public class ReentrantLockSource {
    public static void main(String[] args) {
        ReentrantLock reentrantLock = new ReentrantLock();
        reentrantLock.lock();
    }
}
