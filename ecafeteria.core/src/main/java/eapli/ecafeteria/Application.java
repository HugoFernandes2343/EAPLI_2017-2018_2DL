/**
 *
 */
package eapli.ecafeteria;

import eapli.ecafeteria.domain.authz.ActionRight;

/**
 * A "global" static class with the application registry of well known objects
 *
 * @author Paulo Gandra Sousa
 *
 */
public class Application {

    private static final AppSettings SETTINGS = new AppSettings();
    private static final AppSession SESSION = new AppSession();

    public static AppSession session() {
	return SESSION;
    }

    public static AppSettings settings() {
	return SETTINGS;
    }

    /*
     * @param actions
     */
    public static void ensurePermissionOfLoggedInUser(ActionRight... actions) {
	session().ensurePermissionOfLoggedInUser(actions);
    }

    private Application() {
	// private visibility to ensure singleton & utility
    }

}
