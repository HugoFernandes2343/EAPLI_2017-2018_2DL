/**
 *
 */
package eapli.framework.domain.range;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import eapli.framework.domain.ddd.ValueObject;

/**
 * a discrete domain, mathematically a Set.
 *
 * allows for empty domains
 *
 * @author Paulo Gandra Sousa
 *
 */
public class DiscreteDomain<T> implements Iterable<T>, ValueObject {

    private static final long serialVersionUID = 1626025693468901924L;
    private final Set<T> elements = new HashSet<>();

    public static <T> DiscreteDomain<T> empty() {
        return new DiscreteDomainBuilder<T>().build();
    }

    protected DiscreteDomain(Iterable<T> from) {
        if (from == null) {
            throw new IllegalArgumentException();
        }
        from.forEach(elements::add);
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || !(o instanceof DiscreteDomain<?>)) {
            return false;
        }

        @SuppressWarnings("unchecked")
        final DiscreteDomain<T> other = (DiscreteDomain<T>) o;
        return elements.equals(other.elements);
    }

    @Override
    public int hashCode() {
        return elements.hashCode();
    }

    public boolean contains(T e) {
        return elements.contains(e);
    }

    public boolean isEmpty() {
        return elements.isEmpty();
    }

    public boolean intersects(DiscreteDomain<T> other) {
        return !this.intersection(other).isEmpty();
    }

    public DiscreteDomain<T> intersection(DiscreteDomain<T> other) {
        final DiscreteDomainBuilder<T> builder = new DiscreteDomainBuilder<>();
        for (final T each : elements) {
            if (other.contains(each)) {
                builder.add(each);
            }
        }
        return builder.build();
    }

    public DiscreteDomain<T> union(DiscreteDomain<T> other) {
        final DiscreteDomainBuilder<T> builder = new DiscreteDomainBuilder<>();
        elements.forEach(builder::add);
        other.forEach(builder::add);
        return builder.build();
    }

    @Override
    public Iterator<T> iterator() {
        return elements.iterator();
    }
}
