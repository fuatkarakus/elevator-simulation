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
            while (isRunning) {

                if (direction.equals(Direction.UP)) { // yukarı yöne giderken

                    log.debug("{}. asansör yukarı çıkıyor", name);

                    for (Floor floor : building.upFloors()) { // katları dönüyoruz
                        current = floor;
                        // asansörden insan indir
                        log.debug("{}. asansör {}. katta, {} taşıdığı insan ", name, current.getFloorNumber().num(), people.size());
                        int count = 0;
                        for ( Person person : people) { // asansörün içinde bulunan insanları kata bırakıyoruz.

                            if (person.getDestination() == current.getFloorNumber()) {
                                boolean drop = people.remove(person);

                                //if (current.getFloorNumber() != GROUND) { // eğer 0. kata indiriyorsak bu insan binadan çıkmış demektir. yani tekrardan bi kata koymaya gerek yok
                                    current.getPeople().put(person);
                                //}
                                count ++;
                            }
                        }
                        log.debug("{}. asansörden indirilen insan sayısı {}", name, count);


                        while (!current.getElevatorQueue().isEmpty()) { // asansör kuyruğunda insanlar varsa onları asansöre al
                            if (people.size() == MAX_CAPACITY) {
                                log.debug("{}. asansörün kapasitesi {}, insan almıyor.", name, people.size());
                                break;
                            }
                            people.put(current.getElevatorQueue().take());
                        }

                        Thread.sleep(WORK);

                        if (current.getFloorNumber() == FOURTH) {
                            direction = Direction.DOWN;
                            break;
                        }

                    }
                }

                if (direction.equals(Direction.DOWN)) {
                    log.debug("{}. asansör aşağı iniyor", name);

                    for (Floor floor : building.downFloors()) { // katları aşağı in
                        current = floor;
                        // asansörden insan indir
                        log.debug("{}. asansör {}. katta, {} taşıdığı insan ", name, current.getFloorNumber().num(), people.size());

                        int count = 0;
                        for ( Person person : people) { // asansörün içinde bulunan insanları kata bırakıyoruz.

                            if (person.getDestination() == current.getFloorNumber()) {
                                boolean drop = people.remove(person);
                                //log.info("insanı bıraktı mı : " + drop);

                                // eğer adam 0. kata bırakıldı ise binadan çıkış yapmış demektir. Dolayısıyla tekrar kattaki insanlara eklemeye gerek yok.
                               // if (current.getFloorNumber() != GROUND) {
                                    current.getPeople().put(person);
                                //}

                                count ++;
                            }

                        }

                        log.debug("{}. asansörden indirilen insan sayısı {}", name, count);

                        // asansöre insan alma işlemi
                        while (!current.getElevatorQueue().isEmpty()) { // asansör kuyruğunda insanlar varsa onları asansöre al
                            if (people.size() == MAX_CAPACITY) {
                                log.debug("{}. asansörün kapasitesi {}, insan almıyor.", name, people.size());
                                break;
                            }
                            people.put(current.getElevatorQueue().take());
                        }

                        Thread.sleep(WORK);
                        if (current.getFloorNumber() == GROUND) {
                            direction = Direction.UP;
                            break;
                        }

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

    public void stop() {
        isRunning = false;
    }

    public String getName() {
        return name;
    }
}
