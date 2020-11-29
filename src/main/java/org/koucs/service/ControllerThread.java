package org.koucs.service;

import org.koucs.domain.Building;

public class ControllerThread implements Runnable {

    private boolean isRunning = false;

    private Building building;

    public ControllerThread(Building building) {
        this.building = building;
    }

    @Override
    public void run() {

    }

    public boolean isRunning() {
        return isRunning;
    }

}
