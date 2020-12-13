package org.koucs.service;

import lombok.extern.slf4j.Slf4j;
import org.koucs.domain.Building;
import org.koucs.domain.Elevator;
import org.koucs.domain.Floor;

import java.util.Optional;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Slf4j
public class ControllerThread implements Runnable {

    private boolean isRunning = false;

    private static final Integer MAX_SIZE = 20;

    private static final Integer WORK = 200;

    private final Building building;

    private final ExecutorService service = Executors.newFixedThreadPool(4);

    public ControllerThread(Building building) {
        this.building = building;
    }

    @Override
    public void run() {
        try {
            isRunning = true;
            log.info("Controller Thread Çalışıyor.");
            while (true) {

                int count = 0;
                for (Floor floor : building.upFloors()) {

                    if (floor.getElevatorQueue().size() > MAX_SIZE) {
                        log.info("{}. katta asansör bekleyen sayısı {} ", floor.getFloorNumber().num(), floor.getElevatorQueue().size() );
                        // elevatorlardan çalışmayan bir tane alıyoruz
                        Optional<Elevator> elevator =  building.getElevators()
                                .stream()
                                .filter(e -> !e.isRunning())
                                .findAny();

                        // eğer çalışmayan elevator varsa çalıştırıyor. yoksa şimdilik pass geçiyor
                        if (elevator.isPresent()) {
                            Elevator elevator1 = elevator.get();
                            log.info("Controller Thread {}. asansörü çalıştırıyor.", elevator1.getName());
                            service.submit(elevator1);

                        } else {
                            log.info("Tüm asansörler çalışıyor.");
                        }
                    } else {
                        // eğer katta asansör bekleyen yoksa countu arttır
                        count ++;
                    }
                    Thread.sleep(WORK);
                }

                // Boşa çalışan asansörleri durdur
                // eğer count 5 ise yani tüm binada asansör bekleyenlerin sayısı 20 değil ise ve tüm asansörler çalışıyorsa
                if (count == 5 && building.getElevators().stream().allMatch(Elevator::isRunning)) {
                    building.getElevators().stream().filter(Elevator::isRunning).forEach(Elevator::stop);
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
