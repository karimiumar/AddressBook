/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pragmatic.audacity.addressbook.domain.test;

import pragmatic.audacity.addressbook.util.NullChecker;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class NullCheckerTest {

    @DataProvider(name = "areNullsOrEmpty")
    private Object[][] areNullsOrEmpty() {
        return new Object[][] {
          new String[] {""},
          new Object [] { null},
          new String[] {null},
        };
    }
    
    @DataProvider(name = "areNonNullsNorEmpty")
    private Object[][] areNonNullsNorEmpty() {
        return new Object[][] {
          new String[] {"non"},
          new String [] {"567"},
          new Object [] {3},
        };
    }
    
    @Test(dataProvider = "areNullsOrEmpty")
    public void areNulls(Object obj) {
        Assert.assertTrue(NullChecker.isNullOrEmpty(obj));
    }
    
    @Test(dataProvider = "areNonNullsNorEmpty")
    public void areNonNulls(Object obj) {
        Assert.assertFalse(NullChecker.isNullOrEmpty(obj));
    }
}
