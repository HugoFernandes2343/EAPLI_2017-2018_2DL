/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates and open the template
 * in the editor.
 */
package eapli.framework.persistence;

/**
 * An exception that occurs when data cannot be updated because it has changed
 * or been deleted since it was last read
 *
 * @author arocha
 */
public class DataConcurrencyException extends Exception {

    /**
     * 
     */
    private static final long serialVersionUID = -6364660249001279449L;

    public DataConcurrencyException(Throwable arg0) {
	super(arg0);
    }
}
