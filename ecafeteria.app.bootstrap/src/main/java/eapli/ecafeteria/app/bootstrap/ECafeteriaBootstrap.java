package eapli.ecafeteria.app.bootstrap;

import eapli.ecafeteria.bootstrapers.ECafeteriaBootstrapper;

/**
 * eCafeteria Bootstrapping data app
 *
 */
public class ECafeteriaBootstrap {

    public static void main(String[] args) {
        System.out.println("======================================");
        System.out.println("Bootstrapping eCafeteria (c) 2018 data");
        System.out.println("======================================");

        new ECafeteriaBootstrapper().execute();

        System.out.println("======================================");
        System.out.println("Bootstrap data done.");
        System.out.println("======================================");
    }
}
