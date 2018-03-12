/**
 *
 */
package eapli.framework.actions;

/**
 * A compound action. it implements the Composite (GoF) pattern. subclasses must
 * override execute() AND call next() in the end
 *
 * @author Paulo Gandra Sousa
 *
 */
public abstract class CompoundAction implements Action {

    private final Action next;

    /**
     *
     */
    public CompoundAction(Action next) {
	this.next = next;
    }

    protected boolean next() {
	return next.execute();
    }
}
