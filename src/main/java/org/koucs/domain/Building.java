package org.koucs.domain;

import lombok.Data;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Data
public class Building {

    private Floor ground;
    private Floor first;
    private Floor second;
    private Floor third;
    private Floor fourth;

    private Elevator consist;
    private List<Elevator> elevators;
    Elevator sec ;
    Elevator sec1;
    Elevator sec2;
    Elevator sec3;

    public Building() {
        ground = new Floor(FloorNumber.GROUND);
        first = new Floor(FloorNumber.FIRST);
        second = new Floor(FloorNumber.SECOND);
        third = new Floor(FloorNumber.THIRD);
        fourth = new Floor(FloorNumber.FOURTH);

        elevators = new ArrayList<>(4);

        consist = new Elevator(this, "1");
        sec =  new Elevator(this, "2");
        sec1 =  new Elevator(this, "3");
        sec2=  new Elevator(this, "4");
        sec3 =  new Elevator(this, "5");

        elevators.add(sec1);
        elevators.add(sec);
        elevators.add(sec2);
        elevators.add(sec3);
    }

    public Floor getFLoor( FloorNumber floorNumber ) {
        Floor floor;
        switch (floorNumber) {
            case GROUND:
                floor = getGround();
                break;
            case FIRST:
                floor = getFirst();
                break;
            case SECOND:
                floor = getSecond();
                break;
            case THIRD:
                floor = getThird();
                break;
            case FOURTH:
                floor = getFourth();
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + floorNumber);
        }
        return floor;
    }

    // asansörün sıralı çalışması yukarı
    public List<Floor> upFloors() {
        List<Floor> floors = Arrays.asList(ground, first, second, third, fourth);
        Collections.sort(floors);
        return floors;
    }

    // asansörün sıları çalışması aşağı
    public List<Floor> downFloors() {
        List<Floor> floors = Arrays.asList(ground, first, second, third, fourth);
        Collections.reverse(floors);
        return floors;
    }

    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder();
        sb
        .append("Binanın Durumu : \n")
            .append("1. KAT : \n")
                .append("\t Katta Bulunan  Insan Sayısı:  ").append(ground.getPeople().size()).append("\n")
                .append("\t Katta Bulunan  Asansör Bekleyen Insan Sayısı:  ").append(ground.getElevatorQueue().size()).append("\n")

            .append("2. KAT : \n")
                .append("\t Katta Bulunan  Insan Sayısı:  ").append(first.getPeople().size()).append("\n")
                .append("\t Katta Bulunan  Asansör Bekleyen Insan Sayısı:  ").append(first.getElevatorQueue().size()).append("\n")

            .append("3. KAT : \n")
                .append("\t Katta Bulunan  Insan Sayısı:  ").append(second.getPeople().size()).append("\n")
                .append("\t Katta Bulunan  Asansör Bekleyen Insan Sayısı:  ").append(second.getElevatorQueue().size()).append("\n")

            .append("4. KAT : \n")
                .append("\t Katta Bulunan  Insan Sayısı:  ").append(third.getPeople().size()).append("\n")
                .append("\t Katta Bulunan  Asansör Bekleyen Insan Sayısı:  ").append(third.getElevatorQueue().size()).append("\n")

            .append("5. KAT : \n")
                .append("\t Katta Bulunan  Insan Sayısı:  ").append(fourth.getPeople().size()).append("\n")
                .append("\t Katta Bulunan  Asansör Bekleyen Insan Sayısı:  ").append(fourth.getElevatorQueue().size()).append("\n")

            .append("1. Asansör \n")
                .append("\t Durumu :").append(consist.isRunning()).append("\n")
                .append("\t İçindeki İnsan Sayısı: ").append(consist.getPeople().size()).append("\n")
                .append("\t Yönü: ").append(consist.getDirection()).append("\n")
                .append("\t Bulunduğu Kat: ").append(consist.getCurrent().getFloorNumber().num()).append("\n")

            .append("2. Asansör \n")
                .append("\t Durumu :").append(sec.isRunning()).append("\n")
                .append("\t İçindeki İnsan Sayısı: ").append(sec.getPeople().size()).append("\n")
                .append("\t Yönü: ").append(sec.getDirection()).append("\n")
                .append("\t Bulunduğu Kat: ").append(sec.getCurrent().getFloorNumber().num()).append("\n")

            .append("3. Asansör \n")
                .append("\t Durumu :").append(sec1.isRunning()).append("\n")
                .append("\t İçindeki İnsan Sayısı: ").append(sec1.getPeople().size()).append("\n")
                .append("\t Yönü: ").append(sec1.getDirection()).append("\n")
                .append("\t Bulunduğu Kat: ").append(sec1.getCurrent().getFloorNumber().num()).append("\n")

            .append("4. Asansör \n")
                .append("\t Durumu :").append(sec2.isRunning()).append("\n")
                .append("\t İçindeki İnsan Sayısı: ").append(sec2.getPeople().size()).append("\n")
                .append("\t Yönü: ").append(sec2.getDirection()).append("\n")
                .append("\t Bulunduğu Kat: ").append(sec2.getCurrent().getFloorNumber().num()).append("\n")

            .append("5. Asansör \n")
                .append("\t Durumu :").append(sec3.isRunning()).append("\n")
                .append("\t İçindeki İnsan Sayısı: ").append(sec3.getPeople().size()).append("\n")
                .append("\t Yönü: ").append(sec3.getDirection()).append("\n")
                .append("\t Bulunduğu Kat: ").append(sec3.getCurrent().getFloorNumber().num()).append("\n");

        return sb.toString();
    }
}
