package eapli.ecafeteria.domain.cafeteriauser;

import eapli.ecafeteria.domain.cafeteriauser.CafeteriaUser;
import eapli.ecafeteria.domain.cafeteriauser.CafeteriaUserBuilder;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

import eapli.ecafeteria.domain.authz.RoleType;
import eapli.ecafeteria.domain.authz.SystemUser;

/**
 * Created by Nuno Bettencourt [NMB] on 03/04/16.
 */
public class CafeteriaUserTest {

	private final String anEmail = "a@a.en";
	private final String aMecanographicNumber = "abc";
	private final String anotherMecanographicNumber = "xyz";

	@Test
	public void ensureCafeteriaUserEqualsPassesForTheSameMecanographicNumber() throws Exception {
		final Set<RoleType> roles = new HashSet<>();
		roles.add(RoleType.ADMIN);

		final CafeteriaUser aCafeteriaUser = new CafeteriaUserBuilder()
				.withMecanographicNumber("DUMMY")
				.withSystemUser(new SystemUser("dummy", "duMMy1", "dummy", "dummy", "a@b.ro", roles)).build();

		final CafeteriaUser anotherCafeteriaUser = new CafeteriaUserBuilder()
				.withMecanographicNumber("DUMMY")
				.withSystemUser(new SystemUser("dummy", "duMMy1", "dummy", "dummy", "a@b.ro", roles)).build();

		final boolean expected = aCafeteriaUser.equals(anotherCafeteriaUser);

		assertTrue(expected);
	}

	@Test
	public void ensureCafeteriaUserEqualsFailsForDifferenteMecanographicNumber() throws Exception {
		final Set<RoleType> roles = new HashSet<>();
		roles.add(RoleType.ADMIN);

		final CafeteriaUser aCafeteriaUser = new CafeteriaUserBuilder()
				.withMecanographicNumber(aMecanographicNumber)
				.withSystemUser(new SystemUser("dummy", "duMMy1", "dummy", "dummy", "a@b.ro", roles)).build();

		final CafeteriaUser anotherCafeteriaUser = new CafeteriaUserBuilder()
				.withMecanographicNumber(anotherMecanographicNumber)
				.withSystemUser(new SystemUser("dummy", "duMMy1", "dummy", "dummy", "a@b.ro", roles)).build();

		final boolean expected = aCafeteriaUser.equals(anotherCafeteriaUser);

		assertFalse(expected);
	}

	@Test
	public void ensureCafeteriaUserEqualsAreTheSameForTheSameInstance() throws Exception {
		final CafeteriaUser aCafeteriaUser = new CafeteriaUser();

		final boolean expected = aCafeteriaUser.equals(aCafeteriaUser);

		assertTrue(expected);
	}

	@Test
	public void ensureCafeteriaUserEqualsFailsForDifferenteObjectTypes() throws Exception {
		final Set<RoleType> roles = new HashSet<>();
		roles.add(RoleType.ADMIN);

		final CafeteriaUser aCafeteriaUser = new CafeteriaUserBuilder()
				.withMecanographicNumber("DUMMY")
				.withSystemUser(new SystemUser("dummy", "duMMy1", "dummy", "dummy", "a@b.ro", roles)).build();

		final Set<RoleType> systemUserRoles = new HashSet<>();
		systemUserRoles.add(RoleType.ADMIN);
		final SystemUser systemUser = new SystemUser("userName", "passwordB1", "firsName", "lastName", anEmail,
				systemUserRoles);

		final boolean expected = aCafeteriaUser.equals(systemUser);

		assertFalse(expected);
	}

	@Test
	public void ensureCafeteriaUserIsTheSameAsItsInstance() throws Exception {
		final Set<RoleType> roles = new HashSet<>();
		roles.add(RoleType.ADMIN);
		final CafeteriaUser aCafeteriaUser = new CafeteriaUserBuilder()
				.withMecanographicNumber("DUMMY")
				.withSystemUser(new SystemUser("dummy", "duMMy1", "dummy", "dummy", "a@b.ro", roles)).build();

		final boolean expected = aCafeteriaUser.sameAs(aCafeteriaUser);

		assertTrue(expected);
	}

	@Test
	public void ensureTwoCafeteriaUserWithDifferentMecanographicNumbersAreNotTheSame() throws Exception {
		final Set<RoleType> roles = new HashSet<>();
		roles.add(RoleType.ADMIN);
		final CafeteriaUser aCafeteriaUser = new CafeteriaUserBuilder()
				.withMecanographicNumber(aMecanographicNumber)
				.withSystemUser(new SystemUser("dummy", "duMMy1", "dummy", "dummy", "a@b.ro", roles)).build();

		final CafeteriaUser anotherCafeteriaUser = new CafeteriaUserBuilder()
				.withMecanographicNumber(anotherMecanographicNumber)
				.withSystemUser(new SystemUser("dummy", "duMMy1", "dummy", "dummy", "a@b.ro", roles)).build();

		final boolean expected = aCafeteriaUser.sameAs(anotherCafeteriaUser);

		assertFalse(expected);
	}

	@Test
	public void ensureTwoCafeteriaUsersWithDifferentSystemUsersAreNotTheSame() throws Exception {
		final Set<RoleType> roles = new HashSet<>();
		roles.add(RoleType.ADMIN);
		final CafeteriaUser aCafeteriaUser = new CafeteriaUserBuilder()
				.withMecanographicNumber("DUMMY")
				.withSystemUser(new SystemUser("one-dummy", "duMMy1", "dummy", "dummy", "a@b.ro", roles)).build();

		final CafeteriaUser anotherCafeteriaUser = new CafeteriaUserBuilder()
				.withMecanographicNumber("DUMMY")
				.withSystemUser(new SystemUser("two-dummy", "duMMy1", "dummy", "dummy", "a@b.ro", roles)).build();

		final boolean expected = aCafeteriaUser.sameAs(anotherCafeteriaUser);

		assertFalse(expected);
	}

	@Test
	public void ensureTwoCafeteriaUsersWithDifferentOrganicUnitsAreNotTheSame() throws Exception {
		boolean expected = false;

		final Set<RoleType> roles = new HashSet<>();
		roles.add(RoleType.ADMIN);

		final CafeteriaUser aCafeteriaUser = new CafeteriaUserBuilder()
				.withMecanographicNumber("DUMMY")
				.withSystemUser(new SystemUser("dummy", "duMMy1", "dummy", "dummy", "a@b.ro", roles)).build();

		final CafeteriaUser anotherCafeteriaUser = new CafeteriaUserBuilder()
				.withMecanographicNumber("DUMMY")
				.withSystemUser(new SystemUser("dummy", "duMMy1", "dummy", "dummy", "a@b.ro", roles)).build();

		expected = aCafeteriaUser.sameAs(anotherCafeteriaUser);

		assertFalse(expected);
	}
}
