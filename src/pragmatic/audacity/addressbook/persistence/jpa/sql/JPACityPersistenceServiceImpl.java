package pragmatic.audacity.addressbook.persistence.jpa.sql;

import pragmatic.audacity.addressbook.domain.classes.City;
import pragmatic.audacity.addressbook.domain.classes.Country;
import pragmatic.audacity.addressbook.persistence.ICityPersistenceService;

import javax.persistence.Query;
import java.util.List;

public class JPACityPersistenceServiceImpl extends JPAPersistenceImpl<Long, City> implements ICityPersistenceService {
	@Override
	public List<City> getAllCities(Country country) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<City> getCityDetails(City city) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<City> getAllCities() {
		Query query = entityManager.createQuery("from City c");
		return query.getResultList();
	}

}
