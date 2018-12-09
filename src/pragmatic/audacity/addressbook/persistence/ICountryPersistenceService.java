package pragmatic.audacity.addressbook.persistence;

import pragmatic.audacity.addressbook.domain.classes.Country;

import java.util.List;

public interface ICountryPersistenceService extends	IPersistenceService<Long, Country> {

	List<Country> getCountryByCode(String code);
	List<Country> getCountryByName(String name);
	List<Country> getAllCountries();
}
