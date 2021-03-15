import java.util.List;
public interface BackendInterface {
	public boolean addCity(String key, float index, String state, String country);
	public int getSize();
	public List<CityInterface> getOrderedCities(float a, float b);
	public CityInterface getCity(String name);
}
