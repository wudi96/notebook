package com.notebook.async;

import java.util.concurrent.locks.ReentrantLock;

/**
 * 顺序打印AB 10次
 *
 * @author luorigong
 */
public class ConditionSortAB implements Runnable {
    private static final int PRINT_COUNT = 10;
    private ReentrantLock reentrantLock;
    private Character print;

    private ConditionSortAB(ReentrantLock reentrantLock, Character print) {
        this.reentrantLock = reentrantLock;
        this.print = print;
    }

    @Override
    public void run() {
        reentrantLock.lock();
        try {
            for (int i = 0; i < PRINT_COUNT; i++) {
                System.out.print(print);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            reentrantLock.unlock();
        }
    }


    public static void main(String[] args) {
        ReentrantLock lock = new ReentrantLock();

        Thread printerA = new Thread(new ConditionSortAB(lock, 'A'));
        Thread printerB = new Thread(new ConditionSortAB(lock, 'B'));

        printerA.start();
        printerB.start();
    }
}
