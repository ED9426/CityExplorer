import java.util.Hashtable;
import java.util.List;
import java.util.zip.DataFormatException;
import java.io.IOException;
import java.io.Reader;

public class Backend implements BackendInterface {
	private RedBlackTree<CityInterface> rbt;
	private Hashtable<String, Float> ht; 

	public Backend(Reader args) {
		List<CityInterface> data;
		CityDataReaderInterface cdr = new CityDataReader();
		try {
			data= cdr.readDataSet(args);
		} catch (IOException | DataFormatException e) {
			e.printStackTrace();
		}
		for (int i=0; i<data.size();i++) {
			ht.put(data.get(i).getName(), data.get(i).getCost());
			rbt.insert(data.get(i));
		}
	}

	public boolean addCity(String key, float index, String state, String country) {
	}
	public int getSize();
	public List<CityInterface> getOrderedCities(float a, float b);
	public CityInterface getCity(String name);
}