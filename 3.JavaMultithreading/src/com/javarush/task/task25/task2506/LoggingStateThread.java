package com.javarush.task.task25.task2506;

public class LoggingStateThread extends Thread {
    Thread thread;

    public LoggingStateThread(Thread thread) {
        this.thread = thread;
    }

    @Override
    public void run() {
        State current = thread.getState();
        System.out.println(current);

        State next;
        do {
            next = thread.getState();
            if (next != current) {
                current = next;
                System.out.println(current);
            }
        }
        while (current != State.TERMINATED);

    }
}
