package pragmatic.audacity.addressbook.persistence.jpa.sql;

import pragmatic.audacity.addressbook.domain.classes.Country;
import pragmatic.audacity.addressbook.persistence.ICountryPersistenceService;

import javax.persistence.Query;
import java.util.List;

public class JPACountryPersistenceServiceImpl extends JPAPersistenceImpl<Long, Country>
	implements ICountryPersistenceService {

	@Override
	public List<Country> getCountryByCode(String code) {
		Query query = entityManager.createQuery("from Country c where c.code=:code");
		query.setParameter("code", code);
		return query.getResultList();
	}

	@Override
	public List<Country> getCountryByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Country> getAllCountries() {
		Query query = entityManager.createQuery("from Country c");
		return query.getResultList();
	}

}
