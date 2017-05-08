/**
 *
 */
package eapli.framework.domain;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * @author pgsou_000
 *
 */
public class EmailAddressTest {

    /**
     * @throws java.lang.Exception
     */
    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
    }

    /**
     * @throws java.lang.Exception
     */
    @AfterClass
    public static void tearDownAfterClass() throws Exception {
    }

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
    }

    /**
     * @throws java.lang.Exception
     */
    @After
    public void tearDown() throws Exception {
    }

    /**
     * Test method for
     * {@link eapli.framework.domain.EmailAddress#EmailAddress(java.lang.String)}.
     */
    @Test
    public void testEmailAddress() {
	new EmailAddress("geral@acme.com");
    }

    @Test(expected = IllegalStateException.class)
    public void ensureEmailAddressHasAt() {
	new EmailAddress("geralATacme.com");
    }

    @Test(expected = IllegalStateException.class)
    public void ensureEmailAddressHasHostAndDomain() {
	new EmailAddress("geral@acme");
    }

    @Test(expected = IllegalStateException.class)
    public void ensureEmailAddressHasName() {
	new EmailAddress("@acme.com");
    }

    @Test(expected = IllegalStateException.class)
    public void ensureEmailAddressIsNotNull() {
	new EmailAddress(null);
    }

    @Test(expected = IllegalStateException.class)
    public void ensureEmailAddressIsNotEmpty() {
	new EmailAddress("");
    }
}
