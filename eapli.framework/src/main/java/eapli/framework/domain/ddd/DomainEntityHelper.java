/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.framework.domain.ddd;

/**
 *
 * @author pgsou_000
 */
public final class DomainEntityHelper {

    private DomainEntityHelper() {
	// ensure utility
    }

    public static <T extends DomainEntity<T>> boolean areEqual(T one, Object other) {
	if (one == other) {
	    return true;
	}
	if (!(one.getClass().isInstance(other))) {
	    return false;
	}

	@SuppressWarnings("unchecked")
	final T that = (T) other;
	return one.id().equals(that.id());
    }
}
