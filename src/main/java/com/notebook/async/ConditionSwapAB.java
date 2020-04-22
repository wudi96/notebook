package com.notebook.async;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 线程交替打印AB
 *
 * @author luorigong
 */
public class ConditionSwapAB implements Runnable {
    private static final int PRINT_COUNT = 10;
    private ReentrantLock reentrantLock;
    private final Condition thisCondition;
    private final Condition nextCondition;
    private final char printChar;

    private ConditionSwapAB(ReentrantLock reentrantLock, Condition thisCondition, Condition nextCondition, char printChar) {
        this.reentrantLock = reentrantLock;
        this.thisCondition = thisCondition;
        this.nextCondition = nextCondition;
        this.printChar = printChar;
    }

    @Override
    public void run() {
        // 获取打印锁 进入临界区
        reentrantLock.lock();
        try {
            // 连续打印PRINT_COUNT次
            for (int i = 0; i < PRINT_COUNT; i++) {
                //打印字符
                System.out.print(printChar);
                // 使用nextCondition唤醒下一个线程
                // 因为只有一个线程在等待，所以signal或者signalAll都可以
                nextCondition.signal();
                // 不是最后一次则通过thisCondtion等待被唤醒
                // 必须要加判断，不然虽然能够打印10次，但10次后就会直接死锁
                if (i < PRINT_COUNT - 1) {
                    try {
                        // 本线程让出锁并等待唤醒
                        thisCondition.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

            }
        } finally {
            // 释放打印锁
            reentrantLock.unlock();
        }

    }

    public static void main(String[] args) throws InterruptedException {
        ReentrantLock lock = new ReentrantLock();
        Condition conditionA = lock.newCondition();
        Condition conditionB = lock.newCondition();

        Thread printerA = new Thread(new ConditionSwapAB(lock, conditionA, conditionB, 'A'));
        Thread printerB = new Thread(new ConditionSwapAB(lock, conditionB, conditionA, 'B'));

        printerA.start();
        printerB.start();
    }
}
