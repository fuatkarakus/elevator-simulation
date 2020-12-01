package org.koucs.service;

import lombok.extern.slf4j.Slf4j;
import org.koucs.domain.Building;
import org.koucs.domain.Floor;

@Slf4j
public class ControllerThread implements Runnable {

    private boolean isRunning = false;

    private static final Integer max_size = 20;

    private Building building;

    public ControllerThread(Building building) {
        this.building = building;
    }

    @Override
    public void run() {
        try {
            while (true) {

                for (Floor floor : building.floors()) {
                    if (floor.getElevatorQueue().size() > max_size) {
                        // elevator çalıştırıcaz
                    }
                }


                Thread.sleep(100);
            }


        }catch (Exception e) {
            log.error(e.getMessage());
        }
    }

    public boolean isRunning() {
        return isRunning;
    }

}
