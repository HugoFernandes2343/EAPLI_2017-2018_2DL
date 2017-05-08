/*
 * To change this template, choose Tools | Templates and open the template in
 * the editor.
 */
package eapli.framework.actions;

/**
 *
 * @author Paulo Gandra Sousa
 */
public class NullAction implements Action {

    private static class LazyHolder {
	private static final NullAction INSTANCE = new NullAction();

	private LazyHolder() {
	}
    }

    private NullAction() {
    }

    public static NullAction instance() {
	return LazyHolder.INSTANCE;
    }

    @Override
    public boolean execute() {
	return false;
    }
}
