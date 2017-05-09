/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.framework.persistence.repositories.impl.jpa;

import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;
import eapli.framework.persistence.repositories.DataRepository;
import eapli.framework.persistence.repositories.TransactionalContext;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.Iterator;
import java.util.Optional;

/**
 *
 * @author pgsou_000
 */
public class JpaAutoTxRepository<T, K extends Serializable> implements DataRepository<T, K> {

    protected final JpaBaseRepository<T, K> repo;
    private final TransactionalContext autoTx;

    public JpaAutoTxRepository(String persistenceUnitName, TransactionalContext autoTx) {
        final ParameterizedType genericSuperclass = (ParameterizedType) getClass().getGenericSuperclass();
        @SuppressWarnings("unchecked")
        final Class<T> entityClass = (Class<T>) genericSuperclass.getActualTypeArguments()[0];

        if (autoTx == null) {
            this.repo = new JpaTransactionalRepository<>(persistenceUnitName, entityClass);
        } else {
            this.repo = new JpaNotRunningInContainerRepository<>(autoTx, entityClass);
        }
        this.autoTx = autoTx;
    }

    public TransactionalContext context() {
        return this.autoTx;
    }

    public boolean isInTransaction() {
        return this.autoTx == null;
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
}
