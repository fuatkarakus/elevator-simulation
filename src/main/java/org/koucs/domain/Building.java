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

    private Elevator secondElevator;
    private Elevator thirdElevator;
    private Elevator fourthElevator;
    private Elevator fifthElevator;

    public Building() {
        ground = new Floor(FloorNumber.GROUND);
        first = new Floor(FloorNumber.FIRST);
        second = new Floor(FloorNumber.SECOND);
        third = new Floor(FloorNumber.THIRD);
        fourth = new Floor(FloorNumber.FOURTH);

        elevators = new ArrayList<>(4);

        consist = new Elevator(this, "1");
        secondElevator =  new Elevator(this, "2");
        thirdElevator =  new Elevator(this, "3");
        fourthElevator =  new Elevator(this, "4");
        fifthElevator =  new Elevator(this, "5");

        elevators.add(thirdElevator);
        elevators.add(secondElevator);
        elevators.add(fourthElevator);
        elevators.add(fifthElevator);

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
            .append("GİRİŞ KAT : \n")
                .append("\t Binadan Toplam Çıkış Yapan Insan Sayısı:  ").append(ground.getPeople().size()).append("\n")
                .append("\t Binaya Yeni Giriş Yapan Insan Sayısı:  ").append(ground.getElevatorQueue().size()).append("\n")

            .append("1. KAT : \n")
                .append("\t Katta Bulunan  Insan Sayısı:  ").append(first.getPeople().size()).append("\n")
                .append("\t Katta Bulunan  Asansör Bekleyen Insan Sayısı:  ").append(first.getElevatorQueue().size()).append("\n")

            .append("2. KAT : \n")
                .append("\t Katta Bulunan  Insan Sayısı:  ").append(second.getPeople().size()).append("\n")
                .append("\t Katta Bulunan  Asansör Bekleyen Insan Sayısı:  ").append(second.getElevatorQueue().size()).append("\n")

            .append("3. KAT : \n")
                .append("\t Katta Bulunan  Insan Sayısı:  ").append(third.getPeople().size()).append("\n")
                .append("\t Katta Bulunan  Asansör Bekleyen Insan Sayısı:  ").append(third.getElevatorQueue().size()).append("\n")

            .append("4. KAT : \n")
                .append("\t Katta Bulunan  Insan Sayısı:  ").append(fourth.getPeople().size()).append("\n")
                .append("\t Katta Bulunan  Asansör Bekleyen Insan Sayısı:  ").append(fourth.getElevatorQueue().size()).append("\n");


        if (consist.isRunning()) {
            sb.append("1. Asansör \n")
                    .append("\t Durumu :").append(consist.isRunning()).append("\n")
                    .append("\t İçindeki İnsan Sayısı: ").append(consist.getPeople().size()).append("\n")
                    .append("\t Yönü: ").append(consist.getDirection().name()).append("\n")
                    .append("\t Bulunduğu Kat: ").append(consist.getCurrent().getFloorNumber().num()).append("\n");
        } else {
            sb.append("1. Asansör \n")
                    .append("\t Durumu :").append(consist.isRunning()).append("\n")
                    .append("\t İçindeki İnsan Sayısı: ").append(consist.getPeople().size()).append("\n");
        }
        if (secondElevator.isRunning()){
            sb.append("2. Asansör \n")
                    .append("\t Durumu :").append(secondElevator.isRunning()).append("\n")
                    .append("\t İçindeki İnsan Sayısı: ").append(secondElevator.getPeople().size()).append("\n")
                    .append("\t Yönü: ").append(secondElevator.getDirection().name()).append("\n")
                    .append("\t Bulunduğu Kat: ").append(secondElevator.getCurrent().getFloorNumber().num()).append("\n");
        } else {
            sb.append("2. Asansör \n")
                    .append("\t Durumu :").append(secondElevator.isRunning()).append("\n")
                    .append("\t İçindeki İnsan Sayısı: ").append(secondElevator.getPeople().size()).append("\n");
        }

        if (thirdElevator.isRunning()) {
            sb.append("3. Asansör \n")
                    .append("\t Durumu :").append(thirdElevator.isRunning()).append("\n")
                    .append("\t İçindeki İnsan Sayısı: ").append(thirdElevator.getPeople().size()).append("\n")
                    .append("\t Yönü: ").append(thirdElevator.getDirection().name()).append("\n")
                    .append("\t Bulunduğu Kat: ").append(thirdElevator.getCurrent().getFloorNumber().num()).append("\n");
        } else {
            sb.append("3. Asansör \n")
                    .append("\t Durumu :").append(thirdElevator.isRunning()).append("\n")
                    .append("\t İçindeki İnsan Sayısı: ").append(thirdElevator.getPeople().size()).append("\n");
        }

        if (fourthElevator.isRunning()) {
            sb.append("4. Asansör \n")
                    .append("\t Durumu :").append(fourthElevator.isRunning()).append("\n")
                    .append("\t İçindeki İnsan Sayısı: ").append(fourthElevator.getPeople().size()).append("\n")
                    .append("\t Yönü: ").append(fourthElevator.getDirection().name()).append("\n")
                    .append("\t Bulunduğu Kat: ").append(fourthElevator.getCurrent().getFloorNumber().num()).append("\n");
        } else {
            sb.append("4. Asansör \n")
                    .append("\t Durumu :").append(fourthElevator.isRunning()).append("\n")
                    .append("\t İçindeki İnsan Sayısı: ").append(fourthElevator.getPeople().size()).append("\n");
        }

        if (fifthElevator.isRunning()) {
            sb.append("5. Asansör \n")
                    .append("\t Durumu :").append(fifthElevator.isRunning()).append("\n")
                    .append("\t İçindeki İnsan Sayısı: ").append(fifthElevator.getPeople().size()).append("\n")
                    .append("\t Yönü: ").append(fifthElevator.getDirection().name()).append("\n")
                    .append("\t Bulunduğu Kat: ").append(fifthElevator.getCurrent().getFloorNumber().num()).append("\n");
        } else {
            sb.append("5. Asansör \n")
                    .append("\t Durumu :").append(fifthElevator.isRunning()).append("\n")
                    .append("\t İçindeki İnsan Sayısı: ").append(fifthElevator.getPeople().size()).append("\n");
        }

        sb.append("---------------------------------------------------------------------------------");
        return sb.toString();
    }
}
