//--== CS400 File Header Information ==--
//Name: Edward Zhao
//Email: edward.zhao@wisc.edu
//Team: GE red
//Role: Data Wrangler
//TA: Surabhi
//Lecturer: Gary
//Notes to Grader: <optional extra notes>

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.StringReader;
import java.util.List;
import java.util.zip.DataFormatException;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * This class tests the implementation of CityInterface and
 * CityDataReaderInterface. 
 * 
 * @author edward
 *
 */
public class DataWranglerTests {

  StringReader cityInfo = new StringReader(
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
  );

  /**
   * Checks the correct implementation of readDataSet() by checking
   * the number of CityInterface objects in the list. 
   */
  @Test
  public void testNumberOfCity() {

    CityDataReaderInterface test = new CityDataReader();

    try {
      List<CityInterface> city = test.readDataSet(cityInfo);

      // check the size of the city list
      assertEquals(10, city.size());

    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    } catch (DataFormatException e) {
      e.printStackTrace();
    }
  }

  /**
   * Checks the correct implementation of readDataSet() by checking the
   * names of city/state/countries of CityInterface objects in the list. 
   */
  @Test
  public void testNameOfCityStateCountry() {

    CityDataReaderInterface test = new CityDataReader();

    try {
      List<CityInterface> city = test.readDataSet(cityInfo);

      // check the name of city/state/country
      assertEquals("Hamilton", city.get(0).getCity());
      assertEquals(null, city.get(0).getState());
      assertEquals("Bermuda", city.get(0).getCountry());
      assertEquals("Bloomington", city.get(6).getCity());
      assertEquals("IN", city.get(6).getState());
      assertEquals("United States", city.get(6).getCountry());

    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    } catch (DataFormatException e) {
      e.printStackTrace();
    }
  }

  /**
   * Checks the correct implementation of readDataSet() by checking the
   * cost of living index of CityInterface objects in the list. 
   */
  @Test
  public void testCostOfLivingIndex() {

    CityDataReaderInterface test = new CityDataReader();

    try {
      List<CityInterface> city = test.readDataSet(cityInfo);

      // check the cost index
      assertEquals("141.25", city.get(1).getCostIndex().toString());
      assertEquals("100.0", city.get(2).getCostIndex().toString());
      assertEquals("91.9", city.get(8).getCostIndex().toString());

    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    } catch (DataFormatException e) {
      e.printStackTrace();
    }
  }

  /**
   * Checks the correct implementation of compareTo() method by 
   * comparing two CityInterface objects in the list. 
   */
  @Test
  public void testCompareTo() {

    CityDataReaderInterface test = new CityDataReader();

    try {
      List<CityInterface> city = test.readDataSet(cityInfo);

      // check the correct implementation of compareTo
      assertEquals(-1, city.get(9).compareTo(city.get(3)));
      assertEquals(1, city.get(3).compareTo(city.get(7)));
      assertEquals(0, city.get(9).compareTo(city.get(9)));

    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    } catch (DataFormatException e) {
      e.printStackTrace();
    }
  }

  /**
   * Checks the correct implementation of toString() method by 
   * comparing the return value and the expected output.
   */
  @Test
  public void testToString() {

    CityDataReaderInterface test = new CityDataReader();

    try {
      List<CityInterface> city = test.readDataSet(cityInfo);

      // check the correct implementation of 
      assertEquals("Hamilton, Bermuda, 145.43", city.get(0).toString());
      assertEquals("New York, NY, United States, 100.0", city.get(2).toString());
      assertEquals("Singapore, Singapore, 91.4", city.get(9).toString());

    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    } catch (DataFormatException e) {
      e.printStackTrace();
    }
  }
}
