import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.io.StringReader;
//import org.junit.Test;
//import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class FrontEndDeveloperTests {

    public void initEach(){
        Frontend test = new Frontend();
        test.run(new Backend((new StringReader(
                "City,State,Country,Cost of Living Index\n" +
                        "Hamilton,,Bermuda,145.43\n" +
                        "Zurich,,Switzerland,141.25\n" +
                        "New York,NY,United States,100\n" +
                        "Nassau,,Bahamas,99.73\n" +
                        "San Francisco,CA,United States,97.84\n" +
                        "Rockville,MD,United States,92.66\n" +
                        "Bloomington,IN,United States,92.14\n" +
                        "Washington,DC,United States,91.94\n" +
                        "Arhus,,Denmark,91.9\n" +
                        "Singapore,,Singapore,91.4"
        ))));
    }

    @Test
    public void test1() {
        // TODO: enterXToExit
        PrintStream standardOut = System.out;
        InputStream standardIn = System.in;

        try {
            ByteArrayInputStream exit = new ByteArrayInputStream("x".getBytes());
            System.setIn(exit);
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream(); // NOT SURE WHAT IT IS DOING
            System.setOut(new PrintStream(outputStream)); // NOT SURE WHAT IT IS DOING
            Frontend test = new Frontend();
            test.run(new Backend((new StringReader(
                    "City,State,Country,Cost of Living Index\n" +
                            "Hamilton,,Bermuda,145.43\n" +
                            "Zurich,,Switzerland,141.25\n" +
                            "New York,NY,United States,100\n" +
                            "Nassau,,Bahamas,99.73\n" +
                            "San Francisco,CA,United States,97.84\n" +
                            "Rockville,MD,United States,92.66\n" +
                            "Bloomington,IN,United States,92.14\n" +
                            "Washington,DC,United States,91.94\n" +
                            "Arhus,,Denmark,91.9\n" +
                            "Singapore,,Singapore,91.4"
            ))));
            System.setOut(standardOut);
            System.setIn(standardIn);
            assertTrue(test != null);
        } catch (Exception e) {
            System.setOut(standardOut);
            System.setIn(standardIn);
            e.printStackTrace();
        }
    }


    @Test
    public void test2() {
        // TODO: testFrontendInitialOutputsSomeCity
        PrintStream standardOut = System.out;
        InputStream standardIn = System.in;
        try {
            ByteArrayInputStream exit = new ByteArrayInputStream("x".getBytes());
            System.setIn(exit);
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream(); // NOT SURE WHAT IT IS DOING
            System.setOut(new PrintStream(outputStream)); // NOT SURE WHAT IT IS DOING
            initEach();
            System.setOut(standardOut);
            System.setIn(standardIn);
            String Output = outputStream.toString();
            assertTrue(Output.contains("New York") && Output.contains("Singapore"));
        } catch (Exception e) {
            System.setOut(standardOut);
            System.setIn(standardIn);
            e.printStackTrace();
        }
    }


    @Test
    public void test3() {
        // TODO: testFrontendCityInformationModel
        PrintStream standardOut = System.out;
        InputStream standardIn = System.in;
        try {
            String input = "c" + System.lineSeparator() + "x" + System.lineSeparator() + "x";
            InputStream inputStreamSimulator = new ByteArrayInputStream(input.getBytes());
            System.setIn(inputStreamSimulator);
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream(); // NOT SURE WHAT IT IS DOING
            System.setOut(new PrintStream(outputStream)); // NOT SURE WHAT IT IS DOING
            initEach();
            System.setOut(standardOut);
            System.setIn(standardIn);
            String Output = outputStream.toString();
            assertTrue(Output.contains("New York") && !Output.contains("Washington") && !Output.contains("Rockville"));
        } catch (Exception e) {
            System.setOut(standardOut);
            System.setIn(standardIn);
            e.printStackTrace();
        }
    }

    @Test
    public void test4() {
        // TODO: testFrontendaddCityModel
        PrintStream standardOut = System.out;
        InputStream standardIn = System.in;
        try {
            String input = "a" + System.lineSeparator() + "New York" + System.lineSeparator() + "x" + System.lineSeparator() + "x";
            InputStream inputStreamSimulator = new ByteArrayInputStream(input.getBytes());
            System.setIn(inputStreamSimulator);
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream(); // NOT SURE WHAT IT IS DOING
            System.setOut(new PrintStream(outputStream)); // NOT SURE WHAT IT IS DOING
            initEach();
            System.setOut(standardOut);
            System.setIn(standardIn);
            String Output = outputStream.toString();
            assertTrue(Output.contains("New York") && !Output.contains("Washington") && !Output.contains("Rockville"));
        } catch (Exception e) {
            System.setOut(standardOut);
            System.setIn(standardIn);
            e.printStackTrace();
        }
    }

    @Test
    public void test5() {
        // TODO: testFrontendCitySize
        PrintStream standardOut = System.out;
        InputStream standardIn = System.in;
        try {
            String input = "x";
            InputStream inputStreamSimulator = new ByteArrayInputStream(input.getBytes());
            System.setIn(inputStreamSimulator);
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream(); // NOT SURE WHAT IT IS DOING
            System.setOut(new PrintStream(outputStream)); // NOT SURE WHAT IT IS DOING
            initEach();
            System.setOut(standardOut);
            System.setIn(standardIn);
            String Output = outputStream.toString();
            assertTrue(Output.contains("0")
                    && Output.contains("1")
                    && Output.contains("2")
                    && Output.contains("3")
                    && Output.contains("4")
                    && Output.contains("5")
                    && Output.contains("6")
                    && Output.contains("7")
                    && Output.contains("8")
                    && Output.contains("9"));
        } catch (Exception e) {
            System.setOut(standardOut);
            System.setIn(standardIn);
            e.printStackTrace();
        }
    }
}

