package eapli.ecafeteria.user.consoleapp;

import eapli.ecafeteria.user.consoleapp.presentation.FrontMenu;

/**
 * eCafeteria User App.
 */
public final class ECafeteriaUserApp {

    /**
     * Empty constructor is private to avoid instantiation of this class.
     */
    private ECafeteriaUserApp() {
    }

    public static void main(final String[] args) {

	// to ensure some default test data is available, specially when using
	// in memory persistence
	// new ECafeteriaBootstrap().execute();
	new FrontMenu().show();
    }
}
