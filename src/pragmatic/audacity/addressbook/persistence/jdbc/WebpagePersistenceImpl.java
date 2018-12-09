package pragmatic.audacity.addressbook.persistence.jdbc;

import pragmatic.audacity.addressbook.domain.classes.Webpage;
import pragmatic.audacity.addressbook.persistence.jdbc.util.JdbcConnectionManager;

import java.util.List;

public class WebpagePersistenceImpl extends JdbcPersistenceServiceImpl<Webpage> {

    public WebpagePersistenceImpl(JdbcConnectionManager connectionManager, String schema) {
        super(connectionManager, schema);
    }

    @Override
    public Webpage find(Long id) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public Webpage find(Webpage webpage) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public List<Webpage> getAll() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
