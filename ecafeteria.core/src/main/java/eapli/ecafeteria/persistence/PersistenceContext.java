/**
 *
 */
package eapli.ecafeteria.persistence;

import java.util.logging.Level;
import java.util.logging.Logger;

import eapli.ecafeteria.Application;

/**
 * provides easy access to the persistence layer. works as a factory of
 * factories
 *
 * @author Paulo Gandra Sousa
 */
public class PersistenceContext {

    private static RepositoryFactory theFactory;

    private PersistenceContext() {
    }

    public static RepositoryFactory repositories() {
	if (theFactory == null) {
	    final String factoryClassName = Application.settings().getRepositoryFactory();
	    try {
		theFactory = (RepositoryFactory) Class.forName(factoryClassName).newInstance();
	    } catch (ClassNotFoundException | IllegalAccessException | InstantiationException ex) {
		// FIXME handle exception properly
		Logger.getLogger(PersistenceContext.class.getName()).log(Level.SEVERE, null, ex);
		return null;
	    }
	}
	return theFactory;
    }
}
