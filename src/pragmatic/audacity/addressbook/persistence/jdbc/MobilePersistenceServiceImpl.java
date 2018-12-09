package pragmatic.audacity.addressbook.persistence.jdbc;

import pragmatic.audacity.addressbook.domain.classes.Mobile;
import pragmatic.audacity.addressbook.domain.classes.Person;
import pragmatic.audacity.addressbook.persistence.jdbc.util.JdbcConnectionManager;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MobilePersistenceServiceImpl extends JdbcPersistenceServiceImpl<Mobile> {

    public MobilePersistenceServiceImpl(JdbcConnectionManager connectionManager, String schema) {
        super(connectionManager, schema);
    }

    public Map<Person, List<Mobile>> getAllMobileNosOf(Person person) {
        String query = "SELECT * FROM " + Tables.MOBILE_INFO + " m INNER JOIN " + Tables.PERSON_INFO
                + " p ON m.PERSON_ID = p.ID AND p.ID=?";
        PreparedStatement ps = prepareStatement(query, person.getId());
        ResultSet rs = getResults(ps);
        List<Mobile> mobiles = convertResultSet(ps, rs, Tables.MOBILE_ROW_MAPPER);
        Map<Person, List<Mobile>> personMobilesMap = new HashMap<>();
        personMobilesMap.putIfAbsent(person, mobiles) ;
        return personMobilesMap;
    }

    public List<Mobile> getPreferredMobileNoOf(Person person) {
        String query = "SELECT * FROM " + Tables.MOBILE_INFO + " m INNER JOIN " + Tables.PERSON_INFO
                + " p ON m.PERSON_ID = p.ID AND p.ID=? AND m.PREFERRED=1";
        PreparedStatement ps = prepareStatement(query, person.getId());
        ResultSet rs = getResults(ps);
        List<Mobile> mobiles = convertResultSet(ps, rs, Tables.MOBILE_ROW_MAPPER);
        return mobiles;
    }

    public List<Mobile> getAll() {
        final List<Mobile> result = select("SELECT * FROM " + Tables.MOBILE_INFO, Tables.MOBILE_ROW_MAPPER);
        return result;
    }

    @Override
    public Mobile find(Long id) {
        final List<Mobile> result = select("SELECT * FROM " + Tables.MOBILE_INFO + " WHERE ID=?", Tables.MOBILE_ROW_MAPPER, id);
        return result.isEmpty() ? null : result.get(0);
    }

    public Mobile find(Mobile mobile) {
        final List<Mobile> result = select("SELECT * FROM " + Tables.MOBILE_INFO + " WHERE MOBILE_NO=?", Tables.MOBILE_ROW_MAPPER, mobile.getMobileNo());
        return result.isEmpty() ? null : result.get(0);
    }
}
