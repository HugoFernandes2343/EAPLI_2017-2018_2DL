package eapli.ecafeteria.app.user.console.complaint;

import eapli.framework.actions.Action;

/**
 * @author <1160777@isep.ipp.pt>Marco Carneiro</1160777@isep.ipp.pt>
 */
public class RegisterComplaintAction implements Action {


    @Override
    public boolean execute() {  return new RegisterComplaintUI().show();  }
}
