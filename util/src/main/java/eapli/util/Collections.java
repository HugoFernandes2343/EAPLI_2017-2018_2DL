/**
 *
 */
package eapli.util;

/**
 * @author pgsou_000
 *
 */
public final class Collections {

    private Collections() {
	// to make sure this is an utility class
    }

    public static int size(Iterable<?> col) {
	int i = 0;
	for (@SuppressWarnings({ "unused", "squid:S1481" })
	final Object o : col) {
	    i++;
	}
	return i;
    }
}
