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

/**
 * Interact the city information system with the user by command line.
 * @author Hui
 *
 */
public class Frontend implements FrontendInterface {
    private Scanner sc;
    private Backend backend;
    private static int printlen = 3;
    public Frontend() {
        this.sc = new Scanner(System.in);
    }

    public static void main(String[] args) throws FileNotFoundException {
        Backend backend;
        backend = new Backend(new FileReader("C:\\Users\\sudhi\\eclipse-workspace\\CityExplorer\\src\\Cost-of-living-2018.csv"));
        Frontend Ft = new Frontend();
        Ft.run(backend);
    }

    /**
     * This function is used to start the frontend
     * @param backend the backend used for storing and manipulating the city data.
     */
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

    /**
     *The BaseMode will show all the cities with a descending order by cost index.
     *Realized by using the getCity by all the ranges.
     *User can scroll the list to see the different cities.
     *type 1- list.size() to scroll the list,
     *type c to enter the city information mode.
     *type a to enter the adding city mode.
     *type # to enter the cost index mode.
     *type x to exit.
     */
    public void runBaseMode() {
        String userInput = "";
        System.out.println("Welcome to the City Information System (CIS). You could type the number to navigate through the"
                + " given cities ranked by the descending order of city living cost index.");
        List<CityInterface> cities = new ArrayList<>();
        cities = this.backend.getOrderedCities(0, 150);
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
           lo = 0;
            userInput = sc.next();
            if (userInput.equals("a") || userInput.equals("c") || userInput.equals("#") || userInput.equals("x")) {
                if (userInput.equals("a")) {
                    runAddCityMode();
                    cities = this.backend.getOrderedCities(0,150);
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

    /**
     * @param userInput the string used to convert to an integer
     * @param size the maximum of the integer that is accepted.
     * @return If the string can be convert into an integer within 0 and size; it will return to an integer, otherwise return 0
     */
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

    /**
     * AddCityMode will let the user to add more city into the system.
     */
    public void runAddCityMode() {
        String userInput = "";
        System.out.println("Welcome to the adding city mode.");
        System.out.println("Please type the city that you want to add into our city information system. Enter \"x\" to " +
                "return the base mode.");
        userInput = sc.next();
        while (!userInput.equals("x")) {
            String city = userInput;
            userInput = sc.nextLine();
            if (userInput != "") {
                city += userInput;
            }
            if (this.backend.getCity(city) == null) {
                System.out.println(city + " is not in the system");
                System.out.println("Please type the state of " + city);
                String state = sc.nextLine();
                System.out.println("Please type the country of " + city);
                String country = sc.nextLine();
                System.out.println("Please type the cost index of " + city + " (0 - 150)");
                float Cindex = 0;
                while (Cindex == 0) {
                    Cindex = Float.parseFloat(sc.next());
                    try {
                        while (Cindex > 150.0 || Cindex < 0.0) {
                            System.out.println("Invalid Input");
                            System.out.println("Please type the cost index of " + city + " (0 - 150)");
                            Cindex = 0;

                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid Input");
                        System.out.println("Please type the cost index of " + city + " (0 - 150)");
                    }
                }
                System.out.println("Do you want to add " + city + " into the system? [Y]/yes [N]/no");
                System.out.println(new City(city, state, country, Cindex).toString());
                userInput = sc.next();
                while (!userInput.equals("N") && !userInput.equals("Y")) {
                    System.out.println("Invalid Input");
                    System.out.println("Do you want to add " + city + " into the system? [Y]/yes [N]/no");
                    System.out.println(new City(city, state, country, Cindex).toString());
                    userInput = sc.next();
                }
                if (userInput.equals("N")) {
                    System.out.println("Do not add city " + city + " into the system");
                }
                if (userInput.equals("Y")) {
                    this.backend.addCity(city, Cindex, state, country);
                    System.out.println("Successfully add the " + city + " into the city information system");
                    System.out.println(this.backend.getCity(city).toString());
                }
                System.out.println("Please type the city that you want to add into our city information system. Enter \"x\" to " +
                        "return the base mode.");
                userInput = sc.next();
            } else {
                System.out.println(city + " is already in the system");
                System.out.println("Please type the city that you want to add into our city information system. Enter \"x\" to " +
                        "return the base mode.");
                userInput = sc.next();
            }
        }
    }

    /**
     * CostIndexMode will help the user the see the city list under a certain cost index range
     * @param cities the current city list shown in the frontend
     * @return the new city list shown in the frontend
     */
    public List<CityInterface> runCostIndexMode(List<CityInterface> cities) {
        String userInput = "";
        System.out.println("Welcome to the cost index mode.");
        System.out.println("Please type the cost index range between 0 - 150. The first number is the lower bound and" +
                " the second number is the upper bound." );
        System.out.println("Please separate the index by whitespace \" \"." +
                "Enter \"x\" to return the base mode." );

        while (!userInput.equals("x")) {
            userInput = sc.next();
            if (userInput.equals("x")) {
                break;
            }

            try {
                float lo = Float.parseFloat(userInput);
                userInput = sc.nextLine();
                float hi = Float.parseFloat(userInput);
                if (lo >= 0 && hi <= 150) {
                    cities = this.backend.getOrderedCities(lo, hi);
                    System.out.println(cities.size() + " cities found! Return the base mode to see the city list!");
                } else {
                    System.out.println("Invalid Input");
                    userInput = sc.next();
                }
            } catch (NullPointerException | NumberFormatException e) {
                System.out.println("Invalid Input");
            }

            System.out.println("Please type the cost index range between 0 - 150. The first number is the lower bound and" +
                " the second number is the upper bound." );
            System.out.println("Please separate the index by whitespace \" \"." +
                "Enter \"x\" to return the base mode." );
        }
        return cities;

    }

    /**
     * User can search and compare the city information by typing the city name.
     */
    public void runCityInfoMode() {
        String userInput = "";
        System.out.println("Welcome to the city information mode.");
        System.out.println("Type the city name that you want to search or compare. Enter \"x\" to " +
                "return the base mode.");
        List<CityInterface> cities = new ArrayList<>();
        userInput = sc.next();
        while (!userInput.equals("x")) {
            String city = userInput;
            userInput = sc.nextLine();
            if (userInput != "") {
                city += userInput;
            }
            if (cities.contains(this.backend.getCity(city))) {
                System.out.println(city + " is already shown");
            }
            else {
                if (this.backend.getCity(city) != null) {
                    cities.add(this.backend.getCity(city));
                } else {
                    System.out.println(city + " is not in the system");
                }
            }
            for (int i = 0; i < cities.size(); i++) {
                System.out.println((i + 1) + ". " + cities.get(i).toString());
            }
            System.out.println("Type the city name that you want to search or compare. Enter \"x\" to " +
                    "return the base mode.");
            userInput = sc.next();
        }

    }


}