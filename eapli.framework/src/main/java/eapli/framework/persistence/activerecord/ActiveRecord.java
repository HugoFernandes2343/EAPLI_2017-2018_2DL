/*
 * To change this template, choose Tools | Templates and open the template in
 * the editor.
 */
package eapli.framework.persistence.activerecord;

import eapli.framework.domain.Identifiable;

/**
 * An interface to mark a class as an Active Record.
 *
 * Active Records might have static finder methods or use a separated Finder
 * class.
 *
 * @author Paulo Gandra Sousa
 */
public interface ActiveRecord<I> extends Identifiable<I> {

	/*
	 * save the current object to the persistence store either by creating it or
	 * updating it
	 */
	void save();
}
