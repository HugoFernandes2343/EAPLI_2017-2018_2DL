package eapli.cafeteria.app.common.console.presentation.authz;

import eapli.ecafeteria.application.authz.LoginController;
import eapli.ecafeteria.domain.authz.ActionRight;
import eapli.framework.application.Controller;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.util.Console;

/**
 * UI for user login action. Created by nuno on 21/03/16.
 */
public class LoginUI extends AbstractUI {

	private final LoginController theController = new LoginController();
	private ActionRight onlyWithThis;
	private static final int DEFAULT_MAX_ATTEMPTS = 3;
	private final int maxAttempts;

	protected Controller controller() {
		return theController;
	}

	public LoginUI() {
		maxAttempts = DEFAULT_MAX_ATTEMPTS;
	}

	public LoginUI(final ActionRight onlyWithThis) {
		this.onlyWithThis = onlyWithThis;
		maxAttempts = DEFAULT_MAX_ATTEMPTS;
	}

	public LoginUI(final ActionRight onlyWithThis, final int maxAttempts) {
		this.onlyWithThis = onlyWithThis;
		this.maxAttempts = maxAttempts;
	}

	public LoginUI(final int maxAttempts) {
		this.maxAttempts = maxAttempts;
	}

	@Override
	protected boolean doShow() {
		int attempt = 1;
		while (attempt <= maxAttempts) {
			final String userName = Console.readLine("Username:");
			final String password = Console.readLine("Password:");

			if (theController.login(userName, password, onlyWithThis)) {
				// System.out.println("Authentication Successful.");
				return true;
			} else {
				System.out.printf("Wrong username or password. You have %d attempts left.%n%n»»»»»»»»»%n",
						maxAttempts - attempt);
			}
			attempt++;
		}
		System.out.println("Sorry, we are unable to authenticate you. Please contact your system admnistrator.");
		return false;
	}

	@Override
	public String headline() {
		return "Login";
	}
}
