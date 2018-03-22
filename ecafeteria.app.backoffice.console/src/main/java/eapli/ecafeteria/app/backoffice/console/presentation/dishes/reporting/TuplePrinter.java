package eapli.ecafeteria.app.backoffice.console.presentation.dishes.reporting;

import eapli.framework.visitor.Visitor;

public class TuplePrinter implements Visitor<Object[]> {

    @Override
    public void visit(final Object[] visitee) {
	for (final Object e : visitee) {
	    System.out.print(e);
	    System.out.print(' ');
	}
    }
}
