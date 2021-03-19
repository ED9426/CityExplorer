// --== CS400 File Header Information ==--
// Name: Edward Zhao
// Email: kzhao65@wisc.edu
// Team: GE red
// Role: Data Wrangler
// TA: Surabhi
// Lecturer: Gary
// Notes to Grader: <optional extra notes>

/**
 * Implements CityInterface and stores, retrieves, and applies city information.
 * @author edward
 *
 */
public class City implements CityInterface {

  String city;
  String state;
  String country;
  Float costIndex;

  /**
   * Constructor method that initializes properties of a City object
   * 
   * @param city the name of the city
   * @param state the name of the state 
   * @param country the name of the country
   * @param costIndex the cost of living index
   */
  public City(String city, String state, String country, Float costIndex) {

    this.city = city;
    this.state = state;
    this.country = country;
    this.costIndex = costIndex;

  }

  /**
   * Returns the city name
   * @return the city name
   */
  @Override
  public String getCity() {
    return city;
  }

  /**
   * Returns the state name
   * @return the state name
   */
  @Override
  public String getState() {
    return state;
  }

  /**
   * Returns the country name
   * @return the country name
   */
  @Override
  public String getCountry() {
    return country;
  }

  /**
   * Returns the cost of living index
   * @return the cost of living index
   */
  @Override
  public Float getCostIndex() {
    return costIndex;
  }

  /**
   * Compares two cities based on their cost of living index. 
   * 
   * @param otherCity the city to be compared
   * @return 1 if the current city's cost of living index is greater than otherCity's,
   * -1 if the current city's cost of living index is lower than otherCity's,
   * 0 if they have the equal cost of living index. 
   */
  @Override
  public int compareTo(CityInterface otherCity) {

    if (getCostIndex() > otherCity.getCostIndex())
      return 1;
    else if (getCostIndex() < otherCity.getCostIndex())
      return -1;
    else
      return 0;

  }

  /**
   * Returns all the information of a city in the format city,( state,) country, costIndex.
   * 
   * @return all the information of a city in the format city,( state,) country, costIndex.
   */
  @Override
  public String toString() {
    if (state != null)
      return city + ", " + state + ", " + country + ", " + costIndex.toString();
    else
      return city + ", " + country + ", " + costIndex.toString();
  }

}
