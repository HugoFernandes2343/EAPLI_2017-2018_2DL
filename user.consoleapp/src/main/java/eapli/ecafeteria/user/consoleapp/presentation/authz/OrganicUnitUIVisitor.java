package eapli.ecafeteria.user.consoleapp.presentation.authz;

import eapli.ecafeteria.domain.cafeteria.OrganicUnit;
import eapli.framework.visitor.Visitor;

/**
 * FIXME this class duplicates behaviour already in
 * backoffice.consoleapp/presentation/ui/OrganicUnitPrinter
 *
 * @author Jorge Santos ajs@isep.ipp.pt
 */
public class OrganicUnitUIVisitor implements Visitor<OrganicUnit> {

    @Override
    public void visit(OrganicUnit visited) {
        System.out.println(visited.id() + " " + visited.description() + " " + visited.isActive());
    }

    @Override
    public void beforeVisiting(OrganicUnit visited) {
        System.out.println("ID Acronimo Description Active");
    }

    @Override
    public void afterVisiting(OrganicUnit visited) {
        // nothing to do
    }
}
