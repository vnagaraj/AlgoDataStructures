package week4;

import org.testng.annotations.Test;

import java.math.BigInteger;
import java.security.SecureRandom;

import static org.testng.Assert.assertTrue;



/**
 * Created by VGN on 7/3/16.
 */
public class SplayTreeTest {

    @Test
    public void test1(){
        SecureRandom random = new SecureRandom();
        String expectedString = new BigInteger(130, random).toString(32);
        System.out.println("Random String Generated " + expectedString);
        Rope rope = new Rope(expectedString);
        String actualString = rope.getString();
        System.out.println("String Stored in Rope " + actualString);
        assertTrue(actualString.equals(expectedString));
        char expectedChar = expectedString.charAt(10);
        char actualChar = rope.getK(10);
        assertTrue(expectedChar==actualChar);
    }

    @Test
    public void test2() {
        String str = "hlelowrold";
        Rope rope = new Rope(str);
        rope.processFast(1, 1, 2);

    }


}