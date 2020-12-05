package org.koucs.service;

import lombok.extern.slf4j.Slf4j;
import org.koucs.domain.Building;
import org.koucs.domain.Elevator;
import org.koucs.domain.Floor;

import java.util.Optional;

@Slf4j
public class ControllerThread implements Runnable {

    private boolean isRunning = false;

    private static final Integer MAX_SIZE = 20;

    private static final Integer WORK = 100;

    private final Building building;

    public ControllerThread(Building building) {
        this.building = building;
    }

    @Override
    public void run() {
        try {
            while (true) {

                for (Floor floor : building.upFloors()) {
                    if (floor.getElevatorQueue().size() > MAX_SIZE) {
                        // elevatorlardan çalışmayan bir tane alıyoruz
                        Optional<Elevator> elevator =  building.getElevators()
                                .stream()
                                .filter(e -> !e.isRunning())
                                .findAny();

                        // eğer çalışmayan elevator varsa çalıştırıyor. yoksa şimdilik pass geçiyor
                        if (elevator.isPresent()) {
                            new Thread( elevator.get() );
                        } else {
                            log.info("Tüm asansörler çalışıyor.");
                        }

                    }
                }

                Thread.sleep(WORK);
            }


        }catch (Exception e) {
            log.error(e.getMessage());
        }
    }

    public boolean isRunning() {
        return isRunning;
    }

}
