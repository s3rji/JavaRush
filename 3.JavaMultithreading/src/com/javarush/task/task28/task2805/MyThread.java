package com.javarush.task.task28.task2805;

import java.util.concurrent.atomic.AtomicInteger;

public class MyThread extends Thread {
    private static volatile AtomicInteger priority = new AtomicInteger();

    public static int getNextPriority() {
        if (priority.get() == Thread.MAX_PRIORITY) {
            priority.set(Thread.MIN_PRIORITY);
            return priority.get();

        } else {
            return priority.incrementAndGet();
        }

    }

    public MyThread() {
        int nextPriority = getNextPriority();
        setPriority(Math.min(nextPriority, getThreadGroup().getMaxPriority()));
    }

    public MyThread(Runnable target) {
        super(target);
        int nextPriority = getNextPriority();
        setPriority(Math.min(nextPriority, getThreadGroup().getMaxPriority()));
    }

    public MyThread(ThreadGroup group, Runnable target) {
        super(group, target);
        int nextPriority = getNextPriority();
        setPriority(Math.min(nextPriority, getThreadGroup().getMaxPriority()));
    }

    public MyThread(String name) {
        super(name);
        int nextPriority = getNextPriority();
        setPriority(Math.min(nextPriority, getThreadGroup().getMaxPriority()));
    }

    public MyThread(ThreadGroup group, String name) {
        super(group, name);
        int nextPriority = getNextPriority();
        setPriority(Math.min(nextPriority, getThreadGroup().getMaxPriority()));
    }

    public MyThread(Runnable target, String name) {
        super(target, name);
        int nextPriority = getNextPriority();
        setPriority(Math.min(nextPriority, getThreadGroup().getMaxPriority()));
    }

    public MyThread(ThreadGroup group, Runnable target, String name) {
        super(group, target, name);
        int nextPriority = getNextPriority();
        setPriority(Math.min(nextPriority, getThreadGroup().getMaxPriority()));
    }

    public MyThread(ThreadGroup group, Runnable target, String name, long stackSize) {
        super(group, target, name, stackSize);
        int nextPriority = getNextPriority();
        setPriority(Math.min(nextPriority, getThreadGroup().getMaxPriority()));
    }
}
