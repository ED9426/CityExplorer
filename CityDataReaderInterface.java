// --== CS400 File Header Information ==--
// Name: Edward Zhao
// Email: edward.zhao@wisc.edu
// Team: GE red
// Role: Data Wrangler
// TA: Surabhi
// Lecturer: Gary
// Notes to Grader: <optional extra notes>

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Reader;
import java.util.List;
import java.util.zip.DataFormatException;

public interface CityDataReaderInterface {
  
  public List<CityInterface> readDataSet(Reader inputFileReader) throws FileNotFoundException, IOException, DataFormatException;

}
