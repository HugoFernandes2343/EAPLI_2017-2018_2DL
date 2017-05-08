package eapli.framework.presentation.console;

import eapli.util.io.Console;

public abstract class MenuRenderer {

    protected final Menu menu;

    public MenuRenderer(Menu menu) {
	super();
	this.menu = menu;
    }

    public boolean show() {
	doShow();

	final MenuItem item = readOption();
	return item.select();
    }

    protected abstract void doShow();

    /**
     * @return
     */
    protected MenuItem readOption() {
	MenuItem item;
	do {
	    final int option = Console.readInteger("\nPlease choose an option");
	    item = menu.item(option);
	} while (item == null);
	return item;
    }
}