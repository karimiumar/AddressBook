package pragmatic.audacity.addressbook.persistence;

import pragmatic.audacity.addressbook.domain.classes.City;
import pragmatic.audacity.addressbook.domain.classes.Country;

import java.util.List;

public interface ICityPersistenceService extends IPersistenceService<Long, City> {

	List<City> getAllCities(Country country);
	List<City> getCityDetails(City city);
	List<City> getAllCities();
}
