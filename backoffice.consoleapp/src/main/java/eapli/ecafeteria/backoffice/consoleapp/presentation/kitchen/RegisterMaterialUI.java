/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates and open the template
 * in the editor.
 */
package eapli.ecafeteria.backoffice.consoleapp.presentation.kitchen;

import eapli.ecafeteria.application.kitchen.RegisterMaterialController;
import eapli.framework.application.Controller;
import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.util.io.Console;

/**
 *
 * @author mcn
 */
public class RegisterMaterialUI extends AbstractUI {

    private final RegisterMaterialController theController = new RegisterMaterialController();

    protected Controller controller() {
	return this.theController;
    }

    @Override
    protected boolean doShow() {
	final String acronym = Console.readLine("Acronym:");
	final String description = Console.readLine("Description:");

	try {
	    this.theController.registerMaterial(acronym, description);
	} catch (final DataConcurrencyException | DataIntegrityViolationException e) {
	    System.out.println("That acronym is already in use.");
	}
	return false;
    }

    @Override
    public String headline() {
	return "Register Material";
    }
}
