/*
 * To change this template, choose Tools | Templates and open the template in
 * the editor.
 */
package eapli.framework.presentation.console;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Paulo Gandra Sousa
 */
public class Menu {

    private final String title;
    private final List<MenuItem> itens = new ArrayList<>();
    private final Map<Integer, MenuItem> itemByOption = new HashMap<>();

    public Menu() {
	this.title = "";
    }

    public Menu(String title) {
	this.title = title;
    }

    public void add(MenuItem item) {
	if (item == null) {
	    throw new IllegalArgumentException();
	}
	this.itens.add(item);
	this.itemByOption.put(item.option(), item);
    }

    public String title() {
	return this.title;
    }

    public Iterable<MenuItem> itens() {
	return this.itens;
    }

    public MenuItem item(int option) {
	return this.itemByOption.get(option);
    }
}
