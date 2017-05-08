/**
 *
 */
package eapli.ecafeteria.domain.authz;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * @author pgsou_000
 *
 */
public class PasswordTest {

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

    @Test
    public void ensurePasswordHasAtLeastOneDigitOneCapitalAnd6CharactersLong() {
	new Password("abCdefgh1");
    }

    @Test(expected = IllegalStateException.class)
    public void ensurePasswordsSmallerThan6CharactersAreNotAllowed() {
	new Password("ab1c");
    }

    @Test(expected = IllegalStateException.class)
    public void ensurePasswordsWithoutDigitsAreNotAllowed() {
	new Password("abcdefgh");
    }

    @Test(expected = IllegalStateException.class)
    public void ensurePasswordsWithoutCapitalLetterAreNotAllowed() {
	new Password("abcdefgh1");
    }
}
