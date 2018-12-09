package pragmatic.audacity.addressbook.persistence.jdbc;

import pragmatic.audacity.addressbook.domain.classes.Mobile;
import pragmatic.audacity.addressbook.domain.classes.Person;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Tables {
    public static final String MOBILE_INFO = "MOBILE_INFO";
    public static final String PHONE_INFO = "PHONE_INFO";
    public static final String WEBPAGE_INFO = "WEBPAGE_INFO";
    public static final String EMAIL_INFO = "EMAIL_INFO";
    public static final String ADDRESS_INFO = "ADDRESS_INFO";
    public static final String CITY_INFO = "CITY_INFO";
    public static final String COUNTRY_INFO = "COUNTRY_INFO";
    public static final String PERSON_INFO = "PERSON_INFO";


    public static final RowMapper<Mobile> MOBILE_ROW_MAPPER = new RowMapper<Mobile>() {
        @Override
        public Mobile map(ResultSet resultSet) throws SQLException {
            final Mobile mobile = new Mobile();
            mobile.setId(resultSet.getLong("ID"));
            mobile.setCreatedOn(resultSet.getDate("CREATED_ON"));
            mobile.setVersion(resultSet.getInt("VERSION"));
            mobile.setMobileNo(resultSet.getString("MOBILE_NO"));
            mobile.setPreferred(resultSet.getBoolean("PREFERRED"));
            return mobile;
        }
    };

    public static final RowMapper<Person> PERSON_ROW_MAPPER = new RowMapper<Person>() {
        @Override
        public Person map(ResultSet resultSet) throws SQLException {
            final Person person = new Person();
            person.setId(resultSet.getLong("ID"));
            person.setFirstName(resultSet.getString("FIRST_NAME"));
            person.setLastName(resultSet.getString("LAST_NAME"));
            person.setMidName(resultSet.getString("MID_NAME"));
            person.setBirthDate(resultSet.getDate("BIRTH_DATE"));
            person.setCreatedOn(resultSet.getTimestamp("CREATED_ON"));
            person.setVersion(resultSet.getInt("VERSION"));
            return person;
        }
    };
}
