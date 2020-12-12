package org.koucs;

import lombok.extern.slf4j.Slf4j;
import org.koucs.domain.Building;
import org.koucs.service.ControllerThread;
import org.koucs.service.ExitThread;
import org.koucs.service.LoginThread;

@Slf4j
public class App {

    public static void main( String[] args ) {
        Building building = new Building();

        new Thread(new LoginThread(building)).start();
        new Thread(new ExitThread(building)).start();
        new Thread(new ControllerThread(building)).start();
        new Thread(building.getConsist()).start();

    }
}
