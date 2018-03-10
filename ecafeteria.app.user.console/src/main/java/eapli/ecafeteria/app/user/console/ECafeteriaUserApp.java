package eapli.ecafeteria.app.user.console;

import eapli.ecafeteria.app.user.console.presentation.FrontMenu;

/**
 * eCafeteria User App.
 */
@SuppressWarnings("squid:S106")
public final class ECafeteriaUserApp {

    /**
     * Empty constructor is private to avoid instantiation of this class.
     */
    private ECafeteriaUserApp() {
    }

    public static void main(final String[] args) {
        System.out.println("=====================================");
        System.out.println("eCafeteria User App");
        System.out.println("(C) 2016, 2017, 2018");
        System.out.println("=====================================");

        new FrontMenu().show();
    }
}
