package eapli.framework.persistence.repositories.impl.inmemory;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import eapli.framework.persistence.repositories.IterableRepository;

/**
 * Created by nuno on 20/03/16.
 */
public abstract class InMemoryRepository<T, K extends Serializable> implements IterableRepository<T, K> {

	// Ideally this would be a typed generic Map but since it is a static member
	// it cannot be generic. the solution is to use the old-style untyped Map
	// and cast whenever needed
	@SuppressWarnings("rawtypes")
	private static final Map DATA = new HashMap();
	private final Class<T> entityClass;

	@SuppressWarnings("unchecked")
	public InMemoryRepository() {
		final ParameterizedType genericSuperclass = (ParameterizedType) getClass().getGenericSuperclass();
		this.entityClass = (Class<T>) genericSuperclass.getActualTypeArguments()[0];
	}

	@SuppressWarnings({ "unchecked", "rawtypes", "static-access", "squid:S2209" })
	protected Map<K, T> data() {
		if (!this.DATA.containsKey(this.entityClass)) {
			this.DATA.put(this.entityClass, new HashMap());
		}
		return (Map<K, T>) this.DATA.get(this.entityClass);
	}

	@Override
	public T first() {
		final Iterator<T> it = data().values().iterator();
		return it.hasNext() ? it.next() : null;
	}

	@Override
	public Iterable<T> first(int n) {
		final List<T> ret = new ArrayList<>();
		final Iterator<T> it = data().values().iterator();
		while (n > 0 && it.hasNext()) {
			ret.add(it.next());
		}
		return ret;
	}

	@Override
	public void delete(T entity) {
		//
		// this could be made more efficient if we had a direct way to get the
		// ID of the entity, e.g.,
		// delete(keyOf(entity))
		//
		for (final Entry<K, T> each : data().entrySet()) {
			if (each.getValue().equals(entity)) {
				data().remove(each.getKey());
				break;
			}
		}
	}

	@Override
	public void delete(K entityId) {
		data().remove(entityId);
	}

	@Override
	public Iterator<T> iterator(int pagesize) {
		throw new UnsupportedOperationException();
	}

	@Override
	public T save(T entity) {
		data().put(newKeyFor(entity), entity);
		return entity;
	}

	protected abstract K newKeyFor(T entity);

	@Override
	public Iterable<T> findAll() {
		return data().values();
	}

	/**
	 *
	 * @param id
	 *            K identifier for object
	 * @return T if object identified by K is found, otherwise returns null.
	 */
	@Override
	public Optional<T> findOne(K id) {
		return Optional.ofNullable(data().get(id));
	}

	@Override
	public long count() {
		return data().size();
	}

	@Override
	public Iterator<T> iterator() {
		return data().values().iterator();
	}

	protected Iterable<T> match(Predicate<T> filterFunc) {
		return data().values().stream().filter(filterFunc).collect(Collectors.toList());
	}

	protected Optional<T> matchOne(Predicate<T> filterFunc) {
		T ret;
		try {
			ret = data().values().stream().filter(filterFunc).collect(Collectors.toList()).get(0);
		} catch (final IndexOutOfBoundsException e) {
			ret = null;
		}
		return Optional.of(ret);
	}
}
