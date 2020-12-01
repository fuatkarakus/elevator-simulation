package org.koucs.domain;

public class Elevator implements Runnable{

    private final Integer max_capacity = 10;
    private final Integer work = 200;

    private Process process;

    public Elevator(Process process) {
        this.process = process;
    }

    @Override
    public void run() {

    }
}
