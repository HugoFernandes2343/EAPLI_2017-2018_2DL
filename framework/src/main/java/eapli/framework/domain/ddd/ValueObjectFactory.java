/**
 *
 */
package eapli.framework.domain.ddd;

/**
 * @author Paulo Gandra Sousa
 *
 */
@FunctionalInterface
public interface ValueObjectFactory<T extends ValueObject> {

    /**
     * parses a string in order to create a new value object instance. works in
     * reverse to the toString() method of a Value Object.
     *
     * @param value
     * @return
     */
    T valueOf(String value);
}
