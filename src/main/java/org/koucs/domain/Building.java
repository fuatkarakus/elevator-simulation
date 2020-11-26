package org.koucs.domain;

import lombok.Data;

import java.util.HashMap;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import static org.koucs.domain.Floor.*;

@Data
public class Building {

    HashMap<Floor, BlockingQueue<Integer>> upToFloorFirst;
    HashMap<Floor, BlockingQueue<Integer>> upToFloorSecond;
    HashMap<Floor, BlockingQueue<Integer>> upToFloorThird;
    HashMap<Floor, BlockingQueue<Integer>> upToFloorFourth;

    HashMap<Floor, BlockingQueue<Integer>> downToFloorFirst;
    HashMap<Floor, BlockingQueue<Integer>> downToFloorSecond;
    HashMap<Floor, BlockingQueue<Integer>> downToFloorThird;
    HashMap<Floor, BlockingQueue<Integer>> downToFloorFourth;

     public Building() {
         this.upToFloorFirst = new HashMap<>();
         upToFloorFirst.put(FIRST, new LinkedBlockingQueue<>());
         this.upToFloorSecond = new HashMap<>();
         upToFloorSecond.put(SECOND, new LinkedBlockingQueue<>());
         this.upToFloorThird = new HashMap<>();
         upToFloorThird.put(THIRD, new LinkedBlockingQueue<>());
         this.upToFloorFourth = new HashMap<>();
         upToFloorFourth.put(FOURTH, new LinkedBlockingQueue<>());

         this.downToFloorFirst = new HashMap<>();
         downToFloorFirst.put(FIRST, new LinkedBlockingQueue<>());
         this.downToFloorSecond = new HashMap<>();
         downToFloorSecond.put(SECOND, new LinkedBlockingQueue<>());
         this.downToFloorThird = new HashMap<>();
         downToFloorThird.put(THIRD, new LinkedBlockingQueue<>());
         this.downToFloorFourth = new HashMap<>();
         downToFloorFourth.put(FOURTH, new LinkedBlockingQueue<>());
     }

}
