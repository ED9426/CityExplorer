// --== CS400 File Header Information ==--
// Name: Austin COhen 
// Email: aacohen3@wisc.edu
// Team: GE red
// Role: Backend
// TA: Surabhi
// Lecturer: Florian
// Notes to Grader: <optional extra notes>
import org.junit.Test;
import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;
import java.io.StringReader;
public class BackEndDeveloperTests {
	/**
	 * this method tests the add city method when 
	 * bad input is inputed to make sure that the 
	 * correct exceptions are thrown and the size
	 * is not changed. If any of these things don't happen
	 * the test fails
	 * @throws FileNotFoundException: if file can not be found
	 */
	@Test
	public void testBadInputAddCity() throws FileNotFoundException {
		Backend b=new Backend(new StringReader("City,State,Country,Cost of Living Index\n" + 
		    "Hamilton,,Bermuda,145.43\n" + 
		    "Zurich,,Switzerland,141.25\n" + 
		    "New York,NY,United States,100\n" + 
		    "Nassau,,Bahamas,99.73\n" + 
		    "San Francisco,CA,United States,97.84\n" + 
		    "Rockville,MD,United States,92.66\n" + 
		    "Bloomington,IN,United States,92.14\n" + 
		    "Washington,DC,United States,91.94\n" + 
		    "Arhus,,Denmark,91.9\n" + 
		    "Singapore,,Singapore,91.4"));
		try {
			boolean t=b.addCity("", -4, "", "");
			if (t)
			fail("should throw illegal argument exception because of negative index");
		}catch (IllegalArgumentException e) {}
		int size=b.getSize();
		b.addCity("Rockville", (float)86.01, "FL", "UnitedStates");
		if (size!=b.getSize())
			fail("duplicate city added");
	}
	/**
	 * this method tests the add city method when 
	 * good input is inputed to make sure that the 
	 * correct cities are added at the correct location and the size
	 * is changed appropriatly. If any of these things don't happen
	 * the test fails
	 */
	@Test
	public void testGoodInputAddCity() {
		Backend b=new Backend(new StringReader("City,State,Country,Cost of Living Index\n" + 
		    "Hamilton,,Bermuda,145.43\n" + 
		    "Zurich,,Switzerland,141.25\n" + 
		    "New York,NY,United States,100\n" + 
		    "Nassau,,Bahamas,99.73\n" + 
		    "San Francisco,CA,United States,97.84\n" + 
		    "Rockville,MD,United States,92.66\n" + 
		    "Bloomington,IN,United States,92.14\n" + 
		    "Washington,DC,United States,91.94\n" + 
		    "Arhus,,Denmark,91.9\n" + 
		    "Singapore,,Singapore,91.4"));
		int size=b.getSize();
		b.addCity("Brookline", (float)92.5, "MA", "United States");
		City rockville=new City("Rockville", "MD", "United States", (float)92.66);
		City brookline= new City("Brookline", "MA", "United States", (float)92.5);
		City bloomington=new City ("Bloomington", "IN", "United States", (float)92.14);
				if(size!=b.getSize()-1)
					fail("city was not added");
		List<CityInterface>arr=b.getOrderedCities((float)0, (float)150);
		for(int i=0; i<arr.size();i++) {
			if (arr.get(i).equals(brookline)) {
				if (!arr.get(i-1).equals(bloomington)||!arr.get(i+1).equals(rockville))
					fail("City was not put in the right location");

			}
		}
	}
	/**
	 * this method tests the get size method and make sure that
	 * the correct number of cities are counted initially and then 
	 * once new ones are added. If size is not what is exspected the 
	 * test fails
	 */
	@Test
	public void testGetSize() {
		Backend b=new Backend(new StringReader("City,State,Country,Cost of Living Index\n" + 
		    "Hamilton,,Bermuda,145.43\n" + 
		    "Zurich,,Switzerland,141.25\n" + 
		    "New York,NY,United States,100\n" + 
		    "Nassau,,Bahamas,99.73\n" + 
		    "San Francisco,CA,United States,97.84\n" + 
		    "Rockville,MD,United States,92.66\n" + 
		    "Bloomington,IN,United States,92.14\n" + 
		    "Washington,DC,United States,91.94\n" + 
		    "Arhus,,Denmark,91.9\n" + 
		    "Singapore,,Singapore,91.4"));
		if (b.getSize()!=10)
			fail("inital size is not correct");
		b.addCity("Brookline", 79, "MA", "United States");
		if (b.getSize()!=11)
			fail("size after adding is not correct");
	}
	/**
	 * this method tests the getOrderedCities method. 
	 * if the cities at location 0, 10, and 514 are not 
	 * what they should be the test fails. 
	 */
	@Test
	public void testGetOrdedCities() {
		Backend b=new Backend(new StringReader("City,State,Country,Cost of Living Index\n" + 
		    "Hamilton,,Bermuda,145.43\n" + 
		    "Zurich,,Switzerland,141.25\n" + 
		    "New York,NY,United States,100\n" + 
		    "Nassau,,Bahamas,99.73\n" + 
		    "San Francisco,CA,United States,97.84\n" + 
		    "Rockville,MD,United States,92.66\n" + 
		    "Bloomington,IN,United States,92.14\n" + 
		    "Washington,DC,United States,91.94\n" + 
		    "Arhus,,Denmark,91.9\n" + 
		    "Singapore,,Singapore,91.4"));
		List<CityInterface>arr=b.getOrderedCities((float)0, (float)150);
		if (!arr.get(0).getCity().equals("Singapore"))
			fail("first city was not in the correct location");
		if (!arr.get(2).getCity().equals("Washington"))
			fail("third city was not in the correct location");
		if (!arr.get(9).getCity().equals("Hamilton"))
			fail("last city was not in the correct location");
	}
	/**
	 * this method tests the getCity method. 
	 * if the method returns the correct city at certain index's
	 * and null when there is no corresponding city the test passes
	 * otherwise it fails. 
	 */
	@Test
	public void testGetCity() {
		Backend b=new Backend(new StringReader("City,State,Country,Cost of Living Index\n" + 
		    "Hamilton,,Bermuda,145.43\n" + 
		    "Zurich,,Switzerland,141.25\n" + 
		    "New York,NY,United States,100\n" + 
		    "Nassau,,Bahamas,99.73\n" + 
		    "San Francisco,CA,United States,97.84\n" + 
		    "Rockville,MD,United States,92.66\n" + 
		    "Bloomington,IN,United States,92.14\n" + 
		    "Washington,DC,United States,91.94\n" + 
		    "Arhus,,Denmark,91.9\n" + 
		    "Singapore,,Singapore,91.4"));
		if (b.getCity("Hamilton").getCostIndex()!=(float)145.43)
			fail("did not return correct city, Hamilton");
		if (b.getCity("Zurich").getCostIndex()!=(float)141.25)
			fail("did not return correct city, Zurich");
		if (b.getCity("New York").getCostIndex()!=(float)100)
				fail("did not return correct city, New York ");
		System.out.println(b.getCity("x"));
		if (b.getCity("x")!=null)
			fail("did not return null when city did not exist");
	}
}
