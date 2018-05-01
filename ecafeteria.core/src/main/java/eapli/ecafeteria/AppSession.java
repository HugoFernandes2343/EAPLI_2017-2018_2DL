/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria;

import eapli.ecafeteria.domain.authz.ActionRight;
import eapli.ecafeteria.domain.authz.UserSession;
import eapli.ecafeteria.domain.authz.exceptions.UnauthorizedException;
import eapli.ecafeteria.domain.authz.exceptions.UserSessionNotInitiatedException;

/**
 *
 * @author João Vieira
 */
public class AppSession {
    private UserSession theSession = null;

    public void setSession(UserSession session) {
	this.theSession = session;
    }

    public void clearSession() {
	this.theSession = null;
    }

    public UserSession session() {
	return this.theSession;
    }

    public void ensurePermissionOfLoggedInUser(ActionRight... actions) {
	if (session() == null) {
	    throw new UserSessionNotInitiatedException();
	}
	if (!session().authenticatedUser().isAuthorizedTo(actions)) {
	    throw new UnauthorizedException("User is not authorized to perform this action",
		    session().authenticatedUser(), actions);
	}
    }
}
