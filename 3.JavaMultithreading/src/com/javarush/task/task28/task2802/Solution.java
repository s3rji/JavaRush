package com.javarush.task.task28.task2802;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/* 
Пишем свою ThreadFactory
*/

public class Solution {

    public static void main(String[] args) {
        class EmulatorThreadFactoryTask implements Runnable {
            @Override
            public void run() {
                emulateThreadFactory();
            }
        }

        ThreadGroup group = new ThreadGroup("firstGroup");
        Thread thread = new Thread(group, new EmulatorThreadFactoryTask());

        ThreadGroup group2 = new ThreadGroup("secondGroup");
        Thread thread2 = new Thread(group2, new EmulatorThreadFactoryTask());

        thread.start();
        thread2.start();
    }

    private static void emulateThreadFactory() {
        AmigoThreadFactory factory = new AmigoThreadFactory();
        Runnable r = new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName());
            }
        };
        factory.newThread(r).start();
        factory.newThread(r).start();
        factory.newThread(r).start();
    }

    public static class AmigoThreadFactory implements ThreadFactory {
        private final static AtomicInteger factoryNumber = new AtomicInteger();
        private final String sufThreadName;
        private final ThreadGroup threadGroup;
        private final AtomicInteger threadNumber = new AtomicInteger();


        public AmigoThreadFactory() {
            threadGroup = Thread.currentThread().getThreadGroup();
            threadGroup.setMaxPriority(Thread.NORM_PRIORITY);

            sufThreadName = threadGroup.getName() + "-pool-" + factoryNumber.incrementAndGet() + "-thread-";
        }

        @Override
        public Thread newThread(Runnable r) {
            Thread thread = new Thread(r, sufThreadName + threadNumber.incrementAndGet());
            if (thread.isDaemon()) thread.setDaemon(false);

            return thread;
        }
    }
}
