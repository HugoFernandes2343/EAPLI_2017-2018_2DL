/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates and open the template
 * in the editor.
 */
package eapli.framework.persistence.repositories.impl.jpa;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;
import eapli.framework.persistence.repositories.DataRepository;
import eapli.framework.persistence.repositories.TransactionalContext;

/**
 * A JPA repository implementation for use with applications not running inside
 * a container. this class can either support working within a
 * TransactionalContext or a single immediate transaction mode. if running in
 * single immediate mode a transaction is initiated, committed and the
 * connection closed in the scope of each repository method call.
 *
 * @author pgsou_000
 */
public class JpaAutoTxRepository<T, K extends Serializable> implements DataRepository<T, K> {

    protected final JpaBaseRepository<T, K> repo;
    private final TransactionalContext autoTx;

    @SuppressWarnings("rawtypes")
    public JpaAutoTxRepository(String persistenceUnitName) {
        this(persistenceUnitName, new HashMap());
    }

    @SuppressWarnings("rawtypes")
    public JpaAutoTxRepository(String persistenceUnitName, Map properties) {
        final ParameterizedType genericSuperclass = (ParameterizedType) getClass()
                .getGenericSuperclass();
        @SuppressWarnings("unchecked")
        final Class<T> entityClass = (Class<T>) genericSuperclass.getActualTypeArguments()[0];

        this.repo = new JpaTransactionalRepository<>(persistenceUnitName, properties, entityClass);
        this.autoTx = null;
    }

    public JpaAutoTxRepository(TransactionalContext autoTx) {
        final ParameterizedType genericSuperclass = (ParameterizedType) getClass()
                .getGenericSuperclass();
        if (autoTx == null) {
            throw new IllegalArgumentException();
        }
        @SuppressWarnings("unchecked")
        final Class<T> entityClass = (Class<T>) genericSuperclass.getActualTypeArguments()[0];

        this.repo = new JpaWithTransactionalContextRepository<>(autoTx, entityClass);
        this.autoTx = autoTx;
    }

    public static TransactionalContext buildTransactionalContext(String persistenceUnitName) {
        return new JpaTransactionalContext(persistenceUnitName);
    }

    @SuppressWarnings("rawtypes")
    public static TransactionalContext buildTransactionalContext(String persistenceUnitName,
            Map properties) {
        return new JpaTransactionalContext(persistenceUnitName, properties);
    }

    public TransactionalContext context() {
        return this.autoTx;
    }

    /**
     * returns if the repository is running in single transaction mode or within
     * a TransactionalContext
     *
     * @return true if the repository is running in single transaction mode
     *         false if running within a Transactional Context
     */
    public boolean isInTransaction() {
        return context() == null;
    }

    @Override
    public void delete(T entity) throws DataIntegrityViolationException {
        this.repo.delete(entity);
    }

    @Override
    public void delete(K id) throws DataIntegrityViolationException {
        this.repo.delete(id);
    }

    @Override
    public T save(T entity) throws DataConcurrencyException, DataIntegrityViolationException {
        return this.repo.save(entity);
    }

    @Override
    public Iterable<T> findAll() {
        return this.repo.findAll();
    }

    @Override
    public Optional<T> findOne(K id) {
        return this.repo.findOne(id);
    }

    @Override
    public long count() {
        return this.repo.count();
    }

    public Iterator<T> iterator() {
        return this.repo.iterator();
    }

    protected List<T> match(String where) {
        return repo.match(where);
    }

    protected List<T> match(String whereWithParameters, Map<String, Object> params) {
        return repo.match(whereWithParameters, params);
    }

    protected Optional<T> matchOne(String where) {
        return repo.matchOne(where);
    }

    protected Optional<T> matchOne(String whereWithParameters, Map<String, Object> params) {
        return repo.matchOne(whereWithParameters, params);
    }
}
