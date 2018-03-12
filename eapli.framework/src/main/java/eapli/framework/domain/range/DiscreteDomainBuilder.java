package eapli.framework.domain.range;

import java.util.HashSet;
import java.util.Set;

public class DiscreteDomainBuilder<T> {
    private final Set<T> from = new HashSet<>();

    public DiscreteDomainBuilder<T> add(T e) {
	from.add(e);
	return this;
    }

    public DiscreteDomainBuilder<T> forget(T e) {
	from.remove(e);
	return this;
    }

    public DiscreteDomain<T> build() {
	return new DiscreteDomain<>(from);
    }
}
