package eapli.ecafeteria.app.pos.console.presentation;

import eapli.ecafeteria.application.pos.ClosePosController;
import eapli.framework.application.Controller;
import eapli.framework.domain.POSStateViolationException;
import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.util.Console;

public class ClosePosUI extends AbstractUI {

    private final ClosePosController cont = new ClosePosController();

    protected Controller controller() {
        return this.cont;
    }

    @Override
    protected boolean doShow() {
        boolean open = true;
        try {
            open = cont.ListAllActivePOS();
        } catch (POSStateViolationException ex) {
            System.out.println("POS state error");
        }

        if (!open) {
            return false;
        }

        int pos;
        boolean success = false;

        do {
            pos = Console.readInteger("Choose a pos to close:");
            try {
                success = cont.CloseAndSavePOS(pos);
            } catch (POSStateViolationException | DataConcurrencyException | DataIntegrityViolationException ex) {
                System.out.printf("POS State Error");
            }
            if (!success) {
                System.out.println("\n---Not a valid POS!---\n");

                try {
                    cont.ListAllActivePOS();
                } catch (POSStateViolationException ex) {
                    System.out.println("POS state error");
                }
            }
        } while (!success);

        System.out.println("POS closed successfully!");
        return false;
    }

    @Override
    public String headline() {
        return "Close POS shift";
    }

}
