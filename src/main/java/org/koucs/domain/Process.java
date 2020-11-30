package org.koucs.domain;

import lombok.Getter;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

@Getter
public class Process {

    private final BlockingQueue<Person> personList;
    private final FloorNumber to;
    private final FloorNumber from;

    public Process(FloorNumber to, FloorNumber from){
        this.personList = new ArrayBlockingQueue<>(10);
        this.to = to;
        this.from = from;
    }
}
