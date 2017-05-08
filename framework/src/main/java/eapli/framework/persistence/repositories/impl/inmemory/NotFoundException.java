package eapli.framework.persistence.repositories.impl.inmemory;

/**
 * Created by nuno on 22/03/16.
 */
public class NotFoundException extends Exception {
    /**
     * 
     */
    private static final long serialVersionUID = -9166841469754026743L;

    public NotFoundException(String message) {
	super(message);
    }
}
