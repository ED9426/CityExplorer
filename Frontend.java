// --== CS400 File Header Information ==--
// Name: HUI GENG
// Email: hgeng7@wisc.edu
// Team: GE red
// Role: Frontend developer
// TA: Surabhi
// Lecturer: Florian
// Notes to Grader: <optional extra notes>

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Frontend implements FrontendInterface {
    private Scanner sc;
    private Backend backend;
    private static int printlen = 3;
    public Frontend() {
        this.sc = new Scanner(System.in);
    }

    public static void main(String[] args) throws FileNotFoundException {
        Backend backend;
        backend = new Backend(new FileReader("./Cost-of-living-2018.csv"));
        Frontend Ft = new Frontend();
        Ft.run(backend);
    }

    @Override
    public void run(Backend backend) {
        if (backend != null) {
            this.backend = backend;
            runBaseMode();
            System.out.println("Thanks for using the city information system!");
        } else {
            System.out.println("No Backend found!");
        }

    }

    // The BaseMode will show all the cities with a descending order by cost index.
    // Realized by using the getCity by all the ranges.
    // User can scroll the list to see the different cities.
    // type 1- list.size() to scroll the list,
    // type c to enter the city information mode.
    // type a to enter the adding city mode.
    // type # to enter the cost index mode.
    // type x to exit.
    public void runBaseMode() {
        String userInput = "";
        System.out.println("Welcome to the City Information System (CIS). You could type the number to navigate through the"
                + " given cities ranked by the descending order of city living cost index.");
        List<CityInterface> cities = new ArrayList<>();
        cities = this.backend.getOrderedCities(0, 100);
        int lo = 0;
        for (int i = lo; i < cities.size() && i - lo < printlen; i++) {
            System.out.println((i + 1) + ". " + cities.get(i).toString());
        }

        System.out.println("Enter a number between 1 and " + (cities.size()) + " to scroll through cities information.");
        System.out.println("Enter \"c\" to enter searching mode where you could search the city and compare.");
        System.out.println("Enter \"a\" to enter adding city mode where you could add new city information.");
        System.out.println("Enter \"#\" to enter cost index mode where you could find cities within a certain " +
                "range of living cost index.");
        System.out.println("Enter \"x\" to quit.");
        while (!userInput.equals("x")) {
            userInput = sc.next();
            if (userInput.equals("a") || userInput.equals("c") || userInput.equals("#") || userInput.equals("x")) {
                if (userInput.equals("a")) {
                    runAddCityMode();
                    cities = this.backend.getOrderedCities(0,100);
                } else if (userInput.equals("c")) {
                    runCityInfoMode();
                } else if (userInput.equals("#")) {
                    cities = runCostIndexMode(cities);
                } else if (userInput.equals("x")) {
                    break;
                }
                for (int i = lo; i < cities.size() && i - lo < printlen; i++) {
                    System.out.println((i + 1) + ". " + cities.get(i).toString());
                }
            }
            else {
                lo = StringtoInt(userInput, cities.size());
                if (lo == -1) {
                    System.out.println("Invalid Input");
                } else {
                    for (int i = lo; i < cities.size() && i - lo < printlen; i++) {
                        System.out.println((i + 1) + ". " + cities.get(i).toString());
                    }
                }
            }

            System.out.println("Enter a number between 1 and " + (cities.size()) + " to scroll through cities information.");
            System.out.println("Enter \"c\" to enter searching mode. Enter \"a\" to enter adding city mode. Enter \"#\" to enter cost index mode.");
            System.out.println("Enter \"x\" to quit.");
        }
    }

    // if userInput in 1 to size, return userInput;
    // else return 0;
    private int StringtoInt(String userInput, int size) {
        int num = 0;
        try {
            num = Integer.parseInt(userInput);
            if (num > size || num < 0) {
                num = 0;
            }
        } catch (NumberFormatException e) {
            num = 0;
        }
        return num - 1;
    }

    public void runAddCityMode() {
        String userInput = "";
        System.out.println("Welcome to the adding city mode.");
        System.out.println("Please type the city that you want to add into our city information system. Enter \"x\" to " +
                "return the base mode.");
        while (!userInput.equals("x")) {
            userInput = sc.nextLine();
            if (userInput.equals("x")) {
                break;
            }
            if (this.backend.getCity(userInput) == null) {
                System.out.println(userInput + " is not in the system");
                System.out.println("Do you want to add into the system? [Y]/yes [N]/no");
                String city = userInput;
                userInput = sc.next();
                while(!userInput.equals("Y")) {
                    if (userInput.equals("N")) {
                        System.out.println("Please type the city that you want to add into our city information system. Enter \"x\" to " +
                                "return the base mode.");
                    } else {
                        System.out.println("Invalid Input");
                        System.out.println("Do you want to add into the system? [Y]/yes [N]/no");
                        userInput = sc.nextLine();
                    }
                }
                System.out.println("Please type the state of " + city);
                String state = sc.nextLine();
                System.out.println("Please type the country of " + city);
                String country = sc.nextLine();
                System.out.println("Please type the cost index of " + city + " (0 - 150)");
                try {
                    float Cindex = Float.parseFloat(sc.next());
                    while (Cindex > 150.0 || Cindex < 0.0) {
                        System.out.println("Invalid Input");
                        System.out.println("Please type the cost index of " + city + " (0 - 150)");
                        Cindex = Float.parseFloat(sc.next());
                    }
                    this.backend.addCity(city, Cindex, state, country);
                    System.out.println("Successfully add the " + city + " into the city information system");
                    System.out.println(this.backend.getCity(city).toString());
                } catch (NumberFormatException e) {
                    System.out.println("Invalid Input");
                }
                System.out.println("Please type the city that you want to add into our city information system. Enter \"x\" to " +
                        "return the base mode.");
            }
        }
    }

    public List<CityInterface> runCostIndexMode(List<CityInterface> cities) {
        String userInput = "";
        System.out.println("Welcome to the cost index mode.");
        System.out.println("Please type the cost index range between 0 - 150. The first number is the lower bound and" +
                " the second number is the upper bound. Please separate the index by whitespace \" \"." +
                "Enter \"x\" to return the base mode.");


        while (!userInput.equals("x")) {
            userInput = sc.next();
            if (userInput.equals("x")) {
                break;
            } else {
                try {
                    float lo = Float.parseFloat(userInput);
                    userInput = sc.next();
                    float hi = Float.parseFloat(userInput);
                    cities = this.backend.getOrderedCities(lo, hi);
                    System.out.println(cities.size() + " cities found!");
                } catch (NullPointerException | NumberFormatException e) {
                    System.out.println("Invalid Input");
                }
            }
            System.out.println("Please type the cost index range between 0 - 150. The first number is the lower bound and" +
                    "the second number is the upper bound. Please separate the index by comma \",\"." +
                    "Enter \"x\" to return the base mode.");
        }
        return cities;

    }

    public void runCityInfoMode() {
        String userInput = "";
        System.out.println("Welcome to the city information mode.");
        System.out.println("Type the city name that you want to search or compare. Enter \"x\" to " +
                "return the base mode.");
        List<CityInterface> cities = new ArrayList<>();

        while (!userInput.equals("x")) {
            userInput = sc.nextLine();
            if (userInput.equals("x")) {
                break;
            } else {
                if (this.backend.getCity(userInput) != null) {
                    cities.add(this.backend.getCity(userInput));
                } else {
                    System.out.println(userInput + " is not in the system");
                }
                for (int i = 0; i < cities.size(); i++) {
                    System.out.println((i + 1) + ". " + cities.get(i).toString());
                }
                System.out.println("Type the city name that you want to search or compare. Enter \"x\" to " +
                        "return the base mode.");
            }
        }

    }


}