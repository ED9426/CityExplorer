import java.io.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class FrontEndDeveloperTests {

    public void initEach() throws FileNotFoundException {
        Frontend test = new Frontend();
        test.run(new Backend(new FileReader("CityExplorer/Cost-of-living-2018.csv")));
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
            test.run(new Backend(new FileReader("CityExplorer/Cost-of-living-2018.csv")));
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
            assertTrue(Output.contains("Thiruvananthapuram") && Output.contains("Alexandria"));
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
            String input = "c" +  System.lineSeparator() + "York" + System.lineSeparator() + "x" + System.lineSeparator() + "x";
            InputStream inputStreamSimulator = new ByteArrayInputStream(input.getBytes());
            System.setIn(inputStreamSimulator);
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream(); // NOT SURE WHAT IT IS DOING
            System.setOut(new PrintStream(outputStream)); // NOT SURE WHAT IT IS DOING
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

    @Test
    public void test4() {
        // TODO: testFrontendaddCityModel
        PrintStream standardOut = System.out;
        InputStream standardIn = System.in;
        try {
            String input = "a" + System.lineSeparator() + "NewNewYork" + System.lineSeparator() + "Y" + System.lineSeparator() +
                    "NY" + System.lineSeparator() + "US" + System.lineSeparator() +
                    "1" + System.lineSeparator() + "x" + System.lineSeparator() + "x";
            InputStream inputStreamSimulator = new ByteArrayInputStream(input.getBytes());
            System.setIn(inputStreamSimulator);
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream(); // NOT SURE WHAT IT IS DOING
            System.setOut(new PrintStream(outputStream)); // NOT SURE WHAT IT IS DOING
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

