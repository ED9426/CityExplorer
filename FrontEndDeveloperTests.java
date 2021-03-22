// --== CS400 File Header Information ==--
// Name: HUI GENG
// Email: hgeng7@wisc.edu
// Team: GE red
// Role: Frontend developer
// TA: Surabhi
// Lecturer: Florian
// Notes to Grader: <optional extra notes>

import org.junit.Test;
import java.io.*;
import static org.junit.Assert.*;

/**
 * Contain five different tests for testing the correctness of the frontend
 * @author Geng Hui
 */
public class FrontEndDeveloperTests {

    public void initEach() throws FileNotFoundException {
        Frontend test = new Frontend();
        test.run(new Backend(new FileReader("./Cost-of-living-2018.csv")));
    }

    /**
     * Test enterXToExit
     */
    @Test
    public void test1() {
        PrintStream standardOut = System.out;
        InputStream standardIn = System.in;

        try {
            ByteArrayInputStream exit = new ByteArrayInputStream("x".getBytes());
            System.setIn(exit);
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            System.setOut(new PrintStream(outputStream));
            Frontend test = new Frontend();
            test.run(new Backend(new FileReader("./Cost-of-living-2018.csv")));
            System.setOut(standardOut);
            System.setIn(standardIn);
            assertTrue(test != null);
        } catch (Exception e) {
            System.setOut(standardOut);
            System.setIn(standardIn);
            e.printStackTrace();
        }
    }

    /**
     * Test Frontend Initialized with some Outputs City
     */
    @Test
    public void test2() {
        PrintStream standardOut = System.out;
        InputStream standardIn = System.in;
        try {
            ByteArrayInputStream exit = new ByteArrayInputStream("x".getBytes());
            System.setIn(exit);
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            System.setOut(new PrintStream(outputStream));
            initEach();
            System.setOut(standardOut);
            System.setIn(standardIn);
            String Output = outputStream.toString();
            assertTrue(Output.contains("Thiruvananthapuram") && Output.contains("Alexandria"));
        } catch (Exception e) {
            System.setOut(standardOut);
            System.setIn(standardIn);
            e.printStackTrace();
        }
    }

    /**
     * Test Frontend City Information Mode
     */
    @Test
    public void test3() {
        PrintStream standardOut = System.out;
        InputStream standardIn = System.in;
        try {
            String input = "c" +  System.lineSeparator() + "York" + System.lineSeparator() + "x" + System.lineSeparator() + "x";
            InputStream inputStreamSimulator = new ByteArrayInputStream(input.getBytes());
            System.setIn(inputStreamSimulator);
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            System.setOut(new PrintStream(outputStream));
            initEach();
            System.setOut(standardOut);
            System.setIn(standardIn);
            String Output = outputStream.toString();
            assertTrue(Output.contains("York") && !Output.contains("Washington") && !Output.contains("Rockville"));
        } catch (Exception e) {
            System.setOut(standardOut);
            System.setIn(standardIn);
            e.printStackTrace();
        }
    }

    /**
     * Test Frontend addCity Mode
     */
    @Test
    public void test4() {
        PrintStream standardOut = System.out;
        InputStream standardIn = System.in;
        try {
            String input = "a" + System.lineSeparator() + "NewNewYork" + System.lineSeparator() +
                    "NY" + System.lineSeparator() + "US" + System.lineSeparator() +
                    "1" + System.lineSeparator() + "Y"  + System.lineSeparator() + "x" + System.lineSeparator() + "x";
            InputStream inputStreamSimulator = new ByteArrayInputStream(input.getBytes());
            System.setIn(inputStreamSimulator);
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            System.setOut(new PrintStream(outputStream));
            initEach();
            System.setOut(standardOut);
            System.setIn(standardIn);
            String Output = outputStream.toString();
            assertTrue(Output.contains("NewNewYork") && !Output.contains("Washington") && !Output.contains("Rockville"));
        } catch (Exception e) {
            System.setOut(standardOut);
            System.setIn(standardIn);
            e.printStackTrace();
        }
    }

    /**
     * Test Frontend initialized return 3 cities.
     */
    @Test
    public void test5() {
        PrintStream standardOut = System.out;
        InputStream standardIn = System.in;
        try {
            String input = "x";
            InputStream inputStreamSimulator = new ByteArrayInputStream(input.getBytes());
            System.setIn(inputStreamSimulator);
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            System.setOut(new PrintStream(outputStream));
            initEach();
            System.setOut(standardOut);
            System.setIn(standardIn);
            String Output = outputStream.toString();
            assertTrue( Output.contains("1")
                    && Output.contains("2")
                    && Output.contains("3"));
        } catch (Exception e) {
            System.setOut(standardOut);
            System.setIn(standardIn);
            e.printStackTrace();
        }
    }
}

