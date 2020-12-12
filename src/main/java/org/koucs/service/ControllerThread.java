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

    private static final Integer WORK = 200;

    private final Building building;

    public ControllerThread(Building building) {
        this.building = building;
    }

    @Override
    public void run() {
        try {
            isRunning = true;
            log.info("Controller Thread Çalışıyor.");
            while (true) {

                for (Floor floor : building.upFloors()) {
//                    log.info("Controller thread {}. katta ", floor.getFloorNumber().num());
                    if (floor.getElevatorQueue().size() > MAX_SIZE) {
                        log.info("{}. katta asansör kuyruğu bekleyenlerin sayısı {}", floor.getFloorNumber().num(), floor.getElevatorQueue().size());
                        // elevatorlardan çalışmayan bir tane alıyoruz
                        Optional<Elevator> elevator =  building.getElevators()
                                .stream()
                                .filter(e -> !e.isRunning())
                                .findAny();

                        // eğer çalışmayan elevator varsa çalıştırıyor. yoksa şimdilik pass geçiyor
                        if (elevator.isPresent()) {
                            log.info("Controller Thread bir asansör çalıştırıyor.");
                            new Thread( elevator.get() ).start();
                        } else {
                            log.info("Tüm asansörler çalışıyor.");
                        }
                    }
                }

                Thread.sleep(WORK);
            }

        } catch (Exception e) {
            isRunning = false;
            e.printStackTrace();
            log.error("", e);
        }
    }

    public boolean isRunning() {
        return isRunning;
    }

}
