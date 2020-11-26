package org.koucs.domain;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class Elevator implements Runnable{

    Integer max_capacity = 10;

    BlockingQueue<Integer> inside = new LinkedBlockingQueue<>(max_capacity);

    Integer work_time = 200;

    @Override
    public void run() {

    }
}
