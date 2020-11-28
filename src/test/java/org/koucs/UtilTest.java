package org.koucs;

import org.junit.Test;
import org.koucs.domain.FloorNumber;
import org.koucs.util.Util;

import static org.junit.Assert.assertTrue;
import static org.koucs.util.Util.randomFloor;

public class UtilTest {

    @Test
    public void test() {

        assertTrue(Util.randomLogin() < 11 );
    }

//    @Test
//    public void test2() {
//        FloorNumber i =  randomFloor();
//        System.out.println(i);
//        assertTrue(randomFloor() < 5 );
//    }

}
