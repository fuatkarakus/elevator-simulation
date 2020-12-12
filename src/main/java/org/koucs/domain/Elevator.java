package org.koucs.domain;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import static org.koucs.domain.FloorNumber.FOURTH;
import static org.koucs.domain.FloorNumber.GROUND;

@Slf4j
public class Elevator implements Runnable{

    private static final Integer MAX_CAPACITY = 10;

    private static final Integer WORK = 200;

    private boolean isRunning = false;

    private Direction direction;

    private Floor current;

    private final Building building;

    private final BlockingQueue<Person> people;

    private final String name;

    public Elevator(Building building, String name) {
        this.building = building;
        this.name = name;
        this.people = new ArrayBlockingQueue<>(MAX_CAPACITY);
        this.direction = Direction.UP;
    }

    @Override
    public void run() {
        try {
            isRunning = true;
            log.info("{}. asansör çalışmaya başladı.", name);
            while (true) {

                if (direction.equals(Direction.UP)) {
                    log.info("{}. asansör yukarı çıkıyor", name);
                    for (Floor floor : building.upFloors()) {
                        current = floor;
                        // asansörden insan indir
                        log.info("{}. asansör {}. katta, {} taşıdığı insan ", name, current.getFloorNumber().num(), people.size());
                        for ( Person person : people) {
                            int count = 0;
                            if (person.getDestination() == current.getFloorNumber()) {
                                people.remove(person);
                                current.getPeople().put(person);
                                count ++;
                            }
                            log.info("{}. asansörden indirilen insan sayısı {}", name, count);
                        }

                        // asansöre insan alma işlemi
                        while (!current.getElevatorQueue().isEmpty()) { // asansör kuyruğunda insanlar varsa onları asansöre al
                            if (people.size() == MAX_CAPACITY) {
                                log.info("{}. asansörün kapasitesi {}, insan almıyor.", name, people.size());
                                continue;
                            }
                            people.put(current.getElevatorQueue().take());
                        }

                        if (current.getFloorNumber() == FOURTH) {
                            direction = Direction.DOWN;
                        }
                        Thread.sleep(WORK);
                    }
                }
                if (direction.equals(Direction.DOWN)) {
                    log.info("{}. asansör aşağı iniyor", name);
                    for (Floor floor : building.downFloors()) {
                        current = floor;
                        // asansörden insan indir
                        log.info("{}. asansörde bulunan insan sayısı {}", name, people.size());
                        for ( Person person : people) {
                            int count = 0;
                            if (person.getDestination() == current.getFloorNumber()) {
                                people.remove(person);
                                current.getPeople().put(person);
                                count ++;
                            }
                            log.info("{}. asansörden indirilen insan sayısı {}", name, count);
                        }

                        // asansöre insan alma işlemi
                        while (!current.getElevatorQueue().isEmpty()) { // asansör kuyruğunda insanlar varsa onları asansöre al
                            if (people.size() == MAX_CAPACITY) {
                                log.info("{}. asansörün kapasitesi {}, insan almıyor.", name, people.size());
                                continue;
                            }
                            people.put(current.getElevatorQueue().take());
                        }

                        if (current.getFloorNumber() == GROUND) {
                            direction = Direction.UP;
                        }
                        Thread.sleep(WORK);
                    }
                }
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

    public Direction getDirection() {
        return direction;
    }

    public Floor getCurrent() {
        return current;
    }

    public BlockingQueue<Person> getPeople() {
        return people;
    }

}
