package org.koucs;

import lombok.extern.slf4j.Slf4j;
import org.koucs.domain.Building;
import org.koucs.service.ControllerThread;
import org.koucs.service.ExitThread;
import org.koucs.service.LoginThread;

import java.util.concurrent.*;

import static java.util.concurrent.TimeUnit.SECONDS;

@Slf4j
public class App {

    public static void main( String[] args ) {
        Building building = new Building();

        new Thread(new LoginThread(building)).start();
        new Thread(new ExitThread(building)).start();
        new Thread(new ControllerThread(building)).start();
        new Thread(building.getConsist()).start();

        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(1);
        executorService.scheduleAtFixedRate(() -> {
            try {
                log.info(building.toString());
            }catch (Exception e ) {
                log.error("", e);
            }

        }, 10, 5, SECONDS);
    }
}
