package org.koucs.domain;

import lombok.Getter;

import java.util.concurrent.BlockingQueue;

@Getter
public class Process {

    private BlockingQueue<Person> personList;
    private FloorNumber to;
    private FloorNumber from;

    public Process(BlockingQueue<Person> personList, FloorNumber to, FloorNumber from){
        // todo personlist should be max 10
        this.personList = personList;
        this.to = to;
        this.from = from;
    }
}
