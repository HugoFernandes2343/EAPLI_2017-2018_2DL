/*
 * To change this template, choose Tools | Templates and open the template in
 * the editor.
 */
package eapli.framework.presentation.console;

import java.util.EnumSet;

import eapli.framework.visitor.Visitor;

/**
 * A simple widget that lists the items of a enum and allows the user to select
 * an item.
 *
 * @author Paulo Gandra Sousa
 * @param <T>
 *            the type of enum in the collection
 */
public class EnumSelectWidget<T extends Enum<T>> extends SelectWidget<T> {

    public EnumSelectWidget(String header, Class<T> source) {
	super(header, EnumSet.allOf(source));
    }

    public EnumSelectWidget(String header, Class<T> source, Visitor<T> visitor) {
	super(header, EnumSet.allOf(source), visitor);
    }
}
