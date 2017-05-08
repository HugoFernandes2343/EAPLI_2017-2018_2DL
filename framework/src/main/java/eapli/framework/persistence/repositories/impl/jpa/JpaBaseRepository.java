/*
 * To change this template, choose Tools | Templates and open the template in
 * the editor.
 */
package eapli.framework.persistence.repositories.impl.jpa;

import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;
import eapli.framework.persistence.repositories.IterableRepository;
import eapli.util.Strings;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.OptimisticLockException;
import javax.persistence.PersistenceException;
import javax.persistence.PersistenceUnit;
import javax.persistence.TypedQuery;

/**
 * An utility class for implementing JPA repositories. This class' methods don't
 * initiate an explicit transaction relying on an outside Transaction-enabled
 * container. check JpaNocontainerRepositoryBase if you want to have transaction
 * control inside the base class.
 *
 * <p>
 * based on <a href=
 * "http://stackoverflow.com/questions/3888575/single-dao-generic-crud-methods-jpa-hibernate-spring"
 * > stackoverflow</a> and on
 * <a href="https://burtbeckwith.com/blog/?p=40">burtbeckwith</a>.
 * <p>
 * also have a look at
 * <a href="http://blog.xebia.com/tag/jpa-implementation-patterns/">JPA
 * implementation patterns</a>
 *
 * @author Paulo Gandra Sousa
 * @param <T> the entity type that we want to build a repository for
 * @param <K> the key type of the entity
 */
public abstract class JpaBaseRepository<T, K extends Serializable> implements IterableRepository<T, K> {

    private static final String QUERY_MUST_NOT_BE_NULL_OR_EMPTY = "query must not be null or empty";
    private static final int DEFAULT_PAGESIZE = 20;

    protected final Class<T> entityClass;

    // will be injected by Spring
    @PersistenceUnit
    private EntityManagerFactory emFactory;
    private EntityManager entityManager;

    @SuppressWarnings("unchecked")
    public JpaBaseRepository() {
        final ParameterizedType genericSuperclass = (ParameterizedType) getClass().getGenericSuperclass();
        entityClass = (Class<T>) genericSuperclass.getActualTypeArguments()[0];
    }

    JpaBaseRepository(Class<T> classz) {
        entityClass = classz;
    }

    protected EntityManagerFactory entityManagerFactory() {
        return this.emFactory;
    }

    protected EntityManager entityManager() {
        if (this.entityManager == null || !this.entityManager.isOpen()) {
            this.entityManager = entityManagerFactory().createEntityManager();
        }
        return this.entityManager;
    }

    /**
     * adds a new entity to the persistence store
     *
     * @param entity
     * @return the newly created persistent object
     */
    public T create(T entity) throws DataIntegrityViolationException {
        if (entity == null) {
            throw new IllegalArgumentException();
        }
        try {
            this.entityManager().persist(entity);
        } catch (final PersistenceException ex) {
            // TODO need to check and make sure we only throw
            // DataIntegrityViolationException if we get sql state 23505
            throw new DataIntegrityViolationException(ex);
        }
        return entity;
    }

    /**
     * reads an entity given its K
     *
     * @param id
     * @return
     */
    public Optional<T> read(K id) {
        return Optional.ofNullable(this.entityManager().find(this.entityClass, id));
    }

    /**
     * reads an entity given its K
     *
     * @param id
     * @return
     */
    @Override
    public Optional<T> findOne(K id) {
        if (id == null) {
            throw new IllegalArgumentException();
        }

        return read(id);
    }

    public T update(T entity) throws DataConcurrencyException, DataIntegrityViolationException {
        return save(entity);
    }

    /**
     * removes the object from the persistence storage. the object reference is
     * still valid but the persisted entity is/will be deleted
     *
     * @param entity
     * @throws DataIntegrityViolationException
     */
    @Override
    @SuppressWarnings("squid:S1226")
    public void delete(T entity) throws DataIntegrityViolationException {
        if (entity == null) {
            throw new IllegalArgumentException();
        }

        try {
            entity = entityManager().merge(entity);
            entityManager().remove(entity);
        } catch (final PersistenceException ex) {
            // TODO need to check and make sure we only throw
            // DataIntegrityViolationException if we get sql state 23505
            throw new DataIntegrityViolationException(ex);
        }
    }

    /**
     * Removes the entity with the specified ID from the repository.
     *
     * @param entityId
     * @throws DataIntegrityViolationException
     * @throws UnsuportedOperationException if the delete operation makes no
     * sense for this repository
     */
    @Override
    public void delete(K entityId) throws DataIntegrityViolationException {
        if (entityId == null) {
            throw new IllegalArgumentException();
        }

        // TODO this is not efficient...
        final Optional<T> entity = findOne(entityId);
        if (entity.isPresent()) {
            delete(entity.get());
        }
    }

    /**
     * returns the number of entities in the persistence store
     *
     * @return the number of entities in the persistence store
     */
    @Override
    public long count() {
        final TypedQuery<Long> q = entityManager()
                .createQuery("SELECT COUNT(*) FROM " + this.entityClass.getSimpleName(), Long.class);
        return q.getSingleResult();
    }

    /**
     * checks for the existence of an entity with the provided K.
     *
     * @param key
     * @return
     */
    public boolean contains(K key) {
        if (key == null) {
            throw new IllegalArgumentException();
        }

        return findOne(key).isPresent();
    }

    /**
     * Inserts or updates an entity <b>and commits</b>.
     *
     * note that you should reference the return value to use the persisted
     * entity, as the original object passed as argument might be copied to a
     * new object
     *
     * check <a href=
     * "http://blog.xebia.com/2009/03/23/jpa-implementation-patterns-saving-detached-entities/"
     * > JPA implementation patterns</a> for a discussion on saveOrUpdate()
     * behavior and merge()
     *
     * @param entity
     * @return the persisted entity - might be a different object than the
     * parameter
     * @throws eapli.framework.persistence.DataConcurrencyException
     * @throws DataIntegrityViolationException
     */
    @Override
    public T save(T entity) throws DataConcurrencyException, DataIntegrityViolationException {
        try {
            return entityManager().merge(entity);
        } catch (final PersistenceException ex) {
            if (ex.getCause() instanceof OptimisticLockException) {
                throw new DataConcurrencyException(ex);
            }
            throw new DataIntegrityViolationException(ex);
        }
    }

    /**
     * helper method to create a type query
     *
     * @return
     */
    protected TypedQuery<T> queryAll() {
        final String className = this.entityClass.getSimpleName();
        return entityManager().createQuery("SELECT e FROM " + className + " e ", this.entityClass);
    }

    @SuppressWarnings("squid:S3346")
    private TypedQuery<T> query(String where) {
        assert !Strings.isNullOrEmpty(where) : QUERY_MUST_NOT_BE_NULL_OR_EMPTY;

        final String className = this.entityClass.getSimpleName();
        return entityManager().createQuery("SELECT e FROM " + className + " e WHERE " + where, this.entityClass);
    }

    /**
     * helper method to create a typed query with a where clause
     *
     * @param where
     * @param params
     * @return
     */
    @SuppressWarnings("squid:S3346")
    protected TypedQuery<T> query(String where, Map<String, Object> params) {
        assert !Strings.isNullOrEmpty(where) : QUERY_MUST_NOT_BE_NULL_OR_EMPTY;
        assert params != null && params.size() > 0 : "Params must not be null or empty";

        final TypedQuery<T> q = query(where);
        params.entrySet().stream().forEach(e -> q.setParameter(e.getKey(), e.getValue()));
        return q;
    }

    /**
     * returns the first n entities according to its "natural" order
     *
     * @param n
     * @return
     */
    @Override
    public List<T> first(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException();
        }
        final TypedQuery<T> q = queryAll();
        q.setMaxResults(n);
        return q.getResultList();
    }

    /**
     * returns the first entity according to its "natural" order
     *
     * @return
     */
    @Override
    public T first() {
        final List<T> r = first(1);
        return r.isEmpty() ? null : r.get(0);
    }

    public T last() {
        throw new UnsupportedOperationException();
    }

    public List<T> page(int pageNumber, int pageSize) {
        if (pageNumber <= 0 || pageSize <= 0) {
            throw new IllegalArgumentException();
        }
        final TypedQuery<T> q = queryAll();
        q.setMaxResults(pageSize);
        q.setFirstResult((pageNumber - 1) * pageSize);

        return q.getResultList();
    }

    /**
     * returns a paged iterator
     *
     * @return
     */
    @Override
    public Iterator<T> iterator(int pagesize) {
        return new JpaPagedIterator(this, pagesize);
    }

    @Override
    public Iterator<T> iterator() {
        return new JpaPagedIterator(this, DEFAULT_PAGESIZE);
    }

    @Override
    public Iterable<T> findAll() {
        // TODO check performance impact of this 'where' clause
        return match("1=1");
    }

    /**
     * searches for objects that match the given criteria
     *
     * @param where the where clause should use "e" as the query object
     *
     * @return
     */
    @SuppressWarnings("squid:S3346")
    public List<T> match(String where) {
        assert !Strings.isNullOrEmpty(where) : QUERY_MUST_NOT_BE_NULL_OR_EMPTY;

        final TypedQuery<T> q = query(where);
        return q.getResultList();
    }

    @SuppressWarnings("squid:S3346")
    public List<T> match(String whereWithParameters, Map<String, Object> params) {
        assert !Strings.isNullOrEmpty(whereWithParameters) : QUERY_MUST_NOT_BE_NULL_OR_EMPTY;
        assert params != null && params.size() > 0 : "Params must not be null or empty";

        final TypedQuery<T> q = query(whereWithParameters, params);
        return q.getResultList();
    }

    /**
     * searches for one object that matches the given criteria
     *
     *
     *
     * @param where the where clause should use "e" as the query object
     * @return
     */
    public T matchOne(String where) {
        final TypedQuery<T> q = query(where);
        // TODO should we allow to throw NoResultException? it will expose a JPA
        // specific exception to domain layer. most likely we should return null
        return q.getSingleResult();
    }

    public T matchOne(String whereWithParameters, Map<String, Object> params) {
        final TypedQuery<T> q = query(whereWithParameters, params);
        // TODO should we allow to throw NoResultException? it will expose a JPA
        // specific exception to domain layer. most likely we should return null
        return q.getSingleResult();
    }

    public T matchOne(String where, Object... args) {
        final TypedQuery<T> q = query(where);
        boolean handleAsArgName = true;
        String argName = "";
        for (final Object o : args) {
            if (handleAsArgName) {
                argName = (String) o;
            } else {
                q.setParameter(argName, o);
            }
            handleAsArgName = !handleAsArgName;
        }
        // TODO should we allow to throw NoResultException? it will expose a JPA
        // specific exception to domain layer. most likely we should return null
        return q.getSingleResult();
    }

    /**
     * an iterator over JPA
     *
     * @author Paulo Gandra Sousa
     *
     */
    private class JpaPagedIterator implements Iterator<T> {

        private final JpaBaseRepository<T, K> repository;
        private final int pageSize;
        private int currentPageNumber;
        private Iterator<T> currentPage;

        private JpaPagedIterator(JpaBaseRepository<T, K> repository, int pagesize) {
            this.repository = repository;
            this.pageSize = pagesize;
        }

        @Override
        public boolean hasNext() {
            if (needsToLoadPage()) {
                loadNextPage();
            }
            return this.currentPage.hasNext();
        }

        @Override
        public T next() {
            if (needsToLoadPage()) {
                loadNextPage();
            }
            return this.currentPage.next();
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        private void loadNextPage() {
            final List<T> page = this.repository.page(++this.currentPageNumber, this.pageSize);
            this.currentPage = page.iterator();
        }

        private boolean needsToLoadPage() {
            // either we do not have an iterator yet or we have reached the end
            // of the (current) iterator
            return this.currentPage == null || !this.currentPage.hasNext();
        }
    }
}
