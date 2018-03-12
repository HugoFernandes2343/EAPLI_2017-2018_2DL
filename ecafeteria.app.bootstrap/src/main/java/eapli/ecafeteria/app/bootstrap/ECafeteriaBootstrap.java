package eapli.ecafeteria.app.bootstrap;

import eapli.ecafeteria.bootstrapers.ECafeteriaBootstrapper;

/**
 * eCafeteria Bootstrapping data app
 *
 */
public class ECafeteriaBootstrap {

    public static void main(String[] args) {
        System.out.println("======================================");
        System.out.println("Bootstrapping eCafeteria data");
        System.out.println("(C) 2016, 2017, 2018");
        System.out.println("======================================");

        //TODO: set the persistence layer in drop-and-create mode
        //Persistence.reset();
        new ECafeteriaBootstrapper().execute();

        System.out.println("======================================");
        System.out.println("Bootstrap data done.");
        System.out.println("======================================");
    }
}
