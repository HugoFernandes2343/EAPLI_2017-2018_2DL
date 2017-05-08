/**
 *
 */
package eapli.framework.presentation.console;

/**
 * @author Paulo Gandra Sousa
 *
 */
public class VerticalMenuRenderer extends MenuRenderer {
    public VerticalMenuRenderer(Menu menu) {
	super(menu);
    }

    @Override
    protected void doShow() {
	for (final MenuItem item : menu.itens()) {
	    item.show();
	    System.out.println();
	}
    }
}
