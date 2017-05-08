/**
 *
 */
package eapli.framework.domain.ddd;

/**
 * A factory of domain objects. When creation of an entire, internally
 * consistent aggregate, or a large value object, becomes complicated or reveals
 * too much of the internal structure, factories provide encapsulation.
 *
 * @author Paulo Gandra Sousa
 * @param <T>
 *
 */
@FunctionalInterface
public interface Factory<T extends DomainEntity<?>> {

    /**
     * Constructs a new instance of the domain entity. most likely this method
     * is the last step in the building process (following the Builder GoF
     * pattern) where a series of steps to provide the necessary information to
     * the object constructor is previously loaded into the builder.
     *
     * @return
     */
    T build();
}
