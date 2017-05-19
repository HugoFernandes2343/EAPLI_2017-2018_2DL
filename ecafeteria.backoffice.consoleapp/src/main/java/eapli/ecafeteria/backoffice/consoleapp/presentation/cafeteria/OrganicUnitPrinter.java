/**
 *
 */
package eapli.ecafeteria.backoffice.consoleapp.presentation.cafeteria;

import eapli.ecafeteria.domain.cafeteria.OrganicUnit;
import eapli.framework.visitor.Visitor;

/**
 * TODO move to console.common to allow reuse in both backoffice and utenteapp
 *
 * @author Paulo Gandra Sousa
 *
 */
public class OrganicUnitPrinter implements Visitor<OrganicUnit> {

    @Override
    public void visit(OrganicUnit visitee) {
        System.out.printf("%-10s%-30s%-4s\n", visitee.id(), visitee.description(), String.valueOf(visitee.isActive()));
    }

    @Override
    public void beforeVisiting(OrganicUnit visitee) {
        // nothing to do
    }

    @Override
    public void afterVisiting(OrganicUnit visitee) {
        // nothing to do
    }
}
