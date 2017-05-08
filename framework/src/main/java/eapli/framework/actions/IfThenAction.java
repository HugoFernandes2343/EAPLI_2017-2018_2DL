/**
 *
 */
package eapli.framework.actions;

/**
 * @author Paulo Gandra Sousa
 *
 */
public class IfThenAction extends CompoundAction {

    private final Action condition;

    /**
     * @param next
     */
    public IfThenAction(Action condition, Action then) {
	super(then);
	this.condition = condition;
    }

    @Override
    public boolean execute() {
	if (condition.execute()) {
	    return next();
	}
	return false;
    }

}
