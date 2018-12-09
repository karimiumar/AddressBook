package pragmatic.audacity.addressbook.persistence.test.jdbc;

import pragmatic.audacity.addressbook.persistence.jdbc.util.JdbcConnectionManager;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by IntelliJ IDEA.
 * User:
 * Date: 3/17/14
 * Time: 1:33 PM
 * To change this template use File | Settings | File Templates.
 */
public class JDBCConnectivityTest {
    private JdbcConnectionManager dbUtil;

    @BeforeClass
    public void setUp() {
        dbUtil = JdbcConnectionManager.getJdbcUtil();
    }

    @AfterClass
    public void release() throws SQLException {
        dbUtil.close();
        Assert.assertFalse(dbUtil.isActiveConnection());
    }

    @Test
    public void isConnected() {
        Connection con = dbUtil.getConnection();
        Assert.assertNotNull(con);
    }

    @Test
    public void areSameConnections() {
        Connection connection1 = dbUtil.getConnection();
        Connection connection2 = dbUtil.getConnection();
        Assert.assertSame(connection1, connection2);
    }
}
