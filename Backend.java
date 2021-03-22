// --== CS400 File Header Information ==--
// Name: Austin COhen 
// Email: aacohen3@wisc.edu
// Team: GE red
// Role: Backend
// TA: Surabhi
// Lecturer: Florian
// Notes to Grader: <optional extra notes>
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Stack;
import java.util.zip.DataFormatException;
import java.io.IOException;
import java.io.Reader;
/**
 * Backend class which is implemented by frontend class
 * and implements City Data Reader class.
 * @author austincohen
 *
 */
public class Backend implements BackendInterface {
	private RedBlackTree<CityInterface> rbt=new RedBlackTree<CityInterface>();
	private Hashtable<String, CityInterface> ht=new Hashtable<String, CityInterface>(); 
	private int size=0;
	/**
	 * The backend constructor method
	 * @param args: the reader to read in the file to be added
	 * to the BST and the hash table
	 */
	public Backend(Reader args) {
		List<CityInterface> data=new ArrayList<CityInterface>();
		CityDataReaderInterface cdr = new CityDataReader();
		try {
			data= cdr.readDataSet(args);
		} catch (IOException | DataFormatException e) {
			e.printStackTrace();
		}
		for (int i=0; i<data.size();i++) {
			ht.put(data.get(i).getCity(),data.get(i));
			rbt.insert(data.get(i));
			size++;
		}
	}

	/**
	 * @param: key: String the came of the city
	 * @param: index: the float index of the city
	 * @param: state: the string state of the city
	 * @param: country: the string country which the city is in 
	 * @return: boolean true if city is not already added false otherwise
	 * This method adds a new city to both the BST and the hash table
	 * if it is not already been added, then returns false if already 
	 * was added or invalid input and true otherwise
	 */
	public boolean addCity(String key, float index, String state, String country) {
		if (key==null||index<0||index>150||country==null) {
			return false;
		}
		City newCity=new City(key, state, country, index);
		if (ht.containsKey(newCity.getCity()))
			return false;
		else {
			ht.put(newCity.getCity(), newCity);
			rbt.insert(newCity);
			size++;
		}
		return true;
	}

	/**
	 * this method returns the number of cities avaliable for looking up
	 * @return: int the size of the bst
	 */
	public int getSize() {
		return size;
	}
	/**
	 * This method gets a list of all of the cities within the 
	 * identified range 
	 * @param: a: a float minium index that the user would like to search for
	 * @param: b: a float maximum index that the user would like to search for 
	 * @return: a List of CityInterfaces of every city within the given range
	 */
	public List<CityInterface> getOrderedCities(float a, float b){
		List<CityInterface> inRange = new ArrayList<CityInterface>();
		Iterator<CityInterface> treeNodeIterator =rbt.iterator();
		if (treeNodeIterator.hasNext())
			if (treeNodeIterator.next().getCostIndex()>=a&&treeNodeIterator.next().getCostIndex()<=b)
				inRange.add(treeNodeIterator.next());
		while (treeNodeIterator.hasNext()) {
			CityInterface data = treeNodeIterator.next();
			if (data.getCostIndex()>=a&&data.getCostIndex()<=b)
				inRange.add(data);
		}
		return inRange; 
	}
	/**
	 * this method returns a city when the name of the 
	 * city is used to search for it 
	 * @return: this method returns a CityInterface if 
	 * the city was found otherwise it returns null
	 */
	public CityInterface getCity(String name) {
		return ht.get(name);
	}
}