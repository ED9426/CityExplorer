////import org.junit.Test;
////import static org.junit.Assert.*;
//import org.junit.jupiter.api.Test;
//
//import java.io.StringReader;
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.fail;
//
//public class BackEndDeveloperTests {
//	/**
//	 * this method tests the add city method when
//	 * bad input is inputed to make sure that the
//	 * correct exceptions are thrown and the size
//	 * is not changed. If any of these things don't happen
//	 * the test fails
//	 */
//	@Test
//	public void testBadInputAddCity() {
//		Backend b=new Backend(new StringReader("Cost-of-living-2018.csv"));
//		try {
//			b.addCity("", -4, "", "");
//			fail("should throw illegal argument exception because of negative index");
//		}catch (IllegalArgumentException e) {}
//		try {
//			b.addCity("ajsd;fl", 5, "adkfj;a", null);
//			fail("should throw illegal argument exception because of null");
//		}catch (IllegalArgumentException e) {}
//		int size=b.getSize();
//		b.addCity("Miami", (float)86.01, "FL", "UnitedStates");
//		if (size!=b.getSize())
//			fail("duplicate city added");
//	}
//	/**
//	 * this method tests the add city method when
//	 * good input is inputed to make sure that the
//	 * correct cities are added at the correct location and the size
//	 * is changed appropriatly. If any of these things don't happen
//	 * the test fails
//	 */
//	@Test
//	public void testGoodInputAddCity() {
//		Backend b=new Backend(new StringReader("Cost-of-living-2018.csv"));
//		int size=b.getSize();
//		b.addCity("Boston", 79, "MA", "United States");
//		City boston=new City("Boston", (float)79, "MA", "United States");
//		City bologna= new City("Bologna", (float)79.04, null, "Italy");
//		City eugene=new City ("Eugene", (float)78.94, "Oregon", "United States");
//				if(size!=b.getSize()-1)
//					fail("city was not added");
//		List<CityInterface>arr=b.getOrderedCities();
//		for(int i=0; i<arr.size();i++) {
//			if (arr.get(i).equals(boston)) {
//				if (!arr.get(i-1).equals(bologna)||!arr.get(i+1).equals(eugene))
//					fail("City was not put in the right location");
//
//			}
//		}
//	}
//	/**
//	 * this method tests the get size method and make sure that
//	 * the correct number of cities are counted initially and then
//	 * once new ones are added. If size is not what is exspected the
//	 * test fails
//	 */
//	@Test
//	public void testGetSize() {
//		Backend b=new Backend(new StringReader("Cost-of-living-2018.csv"));
//		if (b.getSize()!=514)
//			fail("inital size is not correct");
//		b.addCity("Boston", 79, "MA", "United States");
//		if (b.getSize()!=515)
//			fail("size after adding is not correct");
//	}
//	/**
//	 * this method tests the getOrderedCities method.
//	 * if the cities at location 0, 10, and 514 are not
//	 * what they should be the test fails.
//	 */
//	@Test
//	public void testGetOrdedCities() {
//		Backend b=new Backend();
//		List<CityInterface>arr=b.getOrderedCities();
//		if (!arr.get(0).getName().equals("Thiruvananthapuram"))
//			fail("first city was not in the correct location");
//		if (!arr.get(10).getName().equals("Mangalore"))
//			fail("tenth city was not in the correct location");
//		if (!arr.get(514).getName().equals("Hamilton"))
//			fail("last city was not in the correct location");
//	}
//	/**
//	 * this method tests the getCity method.
//	 * if the method returns the correct city at certain index's
//	 * and null when there is no corresponding city the test passes
//	 * otherwise it fails.
//	 */
//	@Test
//	public void testGetCity() {
//		Backend b=new Backend();
//		if (!b.getCity((float)78.89).getName().equals("Burlington"))
//			fail("did not return correct city, Burlington");
//		if (!b.getCity((float)80.16).getName().equals("Chicago"))
//			fail("did not return correct city, Chicago");
//		if (!b.getCity((float)86.24).getName().equals("Perth"))
//				fail("did not return correct city, Perth");
//		if (b.getCity((float)0)!=null)
//			fail("did not return null when city did not exist");
//	}
//}
