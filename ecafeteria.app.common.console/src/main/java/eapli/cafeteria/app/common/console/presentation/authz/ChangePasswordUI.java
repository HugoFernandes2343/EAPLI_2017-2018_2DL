package eapli.cafeteria.app.common.console.presentation.authz;

import eapli.ecafeteria.application.authz.ChangePasswordController;
import eapli.framework.application.Controller;
import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.util.Console;

/**
 * UI for user login action. Created by nuno on 21/03/16.
 */
public class ChangePasswordUI extends AbstractUI {

	private final ChangePasswordController theController = new ChangePasswordController();

	protected Controller controller() {
		return this.theController;
	}

	public ChangePasswordUI() {
	}

	@Override
	protected boolean doShow() {
		final String oldPassword = Console.readLine("Old Password:");
		final String newPassword = Console.readLine("New Password:");

		try {
			if (this.theController.changePassword(oldPassword, newPassword)) {
				System.out.println("Password Successfuly changed");
				return true;
			} else {
				System.out.println("Invalid authentication");
				return false;
			}
		} catch (DataConcurrencyException | DataIntegrityViolationException e) {
			System.out.println("An error has occurred> " + e.getLocalizedMessage());
			return false;
		}
	}

	@Override
	public String headline() {
		return "Change Password";
	}
}
