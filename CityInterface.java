// --== CS400 File Header Information ==--
// Name: Edward Zhao
// Email: kzhao65@wisc.edu
// Team: GE red
// Role: Data Wrangler
// TA: Surabhi
// Lecturer: Gary
// Notes to Grader: <optional extra notes>

public interface CityInterface extends Comparable<CityInterface> {

  public String getCity();
  public String getState();
  public String getCountry();
  public Float getCostIndex();

  // from super interface Comparable
  public int compareTo(CityInterface otherCity);

}
