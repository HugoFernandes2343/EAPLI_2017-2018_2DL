/*
 * To change this template, choose Tools | Templates and open the template in
 * the editor.
 */
package eapli.framework.presentation.console;

import java.util.Collection;
import java.util.Iterator;

import eapli.framework.util.Console;
import eapli.framework.visitor.Visitor;

/**
 * A simple widget that lists the items of a collection and allows the user to
 * select an item.
 *
 * @author Paulo Gandra Sousa
 * @param <T>
 *            the type of element in the collection
 */
public class SelectWidget<T> extends ListWidget<T> {

    private int option = -1;

    public SelectWidget(final String header, final Collection<T> source) {
	super(header, source);
    }

    public SelectWidget(final String header, final Iterable<T> source) {
	super(header, source);
    }

    public SelectWidget(final String header, final Collection<T> source, final Visitor<T> visitor) {
	super(header, source, visitor);
    }

    public SelectWidget(final String header, final Iterable<T> source, final Visitor<T> visitor) {
	super(header, source, visitor);
    }

    @Override
    public void show() {
	super.show();
	System.out.println("0. Exit");
	this.option = Console.readOption(1, size(), 0);
    }

    /**
     *
     * @return -1 if the user has not yet made a selection 0 if the user
     *         selected "exit" a positive number corresponding to the list index
     *         of source if the user selected an item
     */
    public int selectedOption() {
	return this.option;
    }

    public T selectedElement() {
	switch (this.option) {
	case -1:
	case 0:
	    return null;
	default:
	    return fromIndex();
	}
    }

    private T fromIndex() {
	int idx = 0;
	T elem = null;
	final Iterator<T> it = source.iterator();
	while (idx < this.option) {
	    elem = it.next();
	    idx++;
	}
	return elem;
    }
}
