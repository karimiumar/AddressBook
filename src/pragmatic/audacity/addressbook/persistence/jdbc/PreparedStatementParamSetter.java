package pragmatic.audacity.addressbook.persistence.jdbc;

import java.sql.PreparedStatement;

/**
 * Created with IntelliJ IDEA.
 * User:
 * Date: 3/19/14
 * Time: 3:48 PM
 * To change this template use File | Settings | File Templates.
 */
public interface PreparedStatementParamSetter {
    void setParams(final PreparedStatement ps, final Object ... params);
}
