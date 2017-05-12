/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates and open the template
 * in the editor.
 */
package eapli.ecafeteria.backoffice.consoleapp.presentation.cafeteria;

import eapli.ecafeteria.application.cafeteria.AddOrganicUnitController;
import eapli.framework.application.Controller;
import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.util.io.Console;

/**
 *
 * @author arocha
 */
public class AddOrganicUnitUI extends AbstractUI {

    private final AddOrganicUnitController theController = new AddOrganicUnitController();

    protected Controller controller() {
	return this.theController;
    }

    @Override
    protected boolean doShow() {
	final String acronym = Console.readLine("Acronym");
	final String name = Console.readLine("Name");
	final String description = Console.readLine("Description");

	try {
	    this.theController.addOrganicUnit(acronym, name, description);
	} catch (final DataIntegrityViolationException | DataConcurrencyException e) {
	    System.out.println("That acronym is already in use.");
	}
	return false;
    }

    @Override
    public String headline() {
	return "Add Organic Unit";
    }

}
