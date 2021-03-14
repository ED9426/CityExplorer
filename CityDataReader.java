// --== CS400 File Header Information ==--
// Name: Edward Zhao
// Email: edward.zhao@wisc.edu
// Team: GE red
// Role: Data Wrangler
// TA: Surabhi
// Lecturer: Gary
// Notes to Grader: <optional extra notes>

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.DataFormatException;

/**
 * Reads in city data and parses them into a CityInterface list
 * @author edward
 *
 */
public class CityDataReader implements CityDataReaderInterface {

  /**
   * Reads in either raw data or data file path, parses data into expected format, and 
   * assign data to CityInterface objects. NOTE that if the inputFileReader is a data 
   * file path, the input should be `new FileReader(filePath)`; if the inputFileReader 
   * is raw data, the input should be `new StringReader(rawDataString)`.
   * 
   * @return a list of CityInterface objects with city information
   */
  @Override
  public List<CityInterface> readDataSet(Reader inputFileReader) 
      throws FileNotFoundException, IOException, DataFormatException {

    // create a list that stores information of cities
    List<CityInterface> city = new ArrayList<CityInterface>();

    // read in data
    try (BufferedReader br = new BufferedReader(inputFileReader)) {
      
      String line; // each line in the data set
      br.readLine(); // skip the first line (titles) of the data set
      
      while ((line = br.readLine()) != null) { // traverse each line
        String[] values = line.split(","); // split data by comma
        if (values[1].equals("")) { // if state is empty then set null
          values[1] = null;
        }
        // add to the city list
        city.add(new City(values[0], values[1], values[2], Float.parseFloat(values[3])));
      }
    }
    return city;
  }

}