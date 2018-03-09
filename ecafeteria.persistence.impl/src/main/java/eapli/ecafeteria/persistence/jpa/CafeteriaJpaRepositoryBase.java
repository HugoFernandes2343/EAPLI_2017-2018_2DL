package eapli.ecafeteria.persistence.jpa;

import java.io.Serializable;

import eapli.ecafeteria.Application;
import eapli.framework.persistence.repositories.impl.jpa.JpaTransactionalRepository;

/**
 * a base class for all transactional repositories to use the same persistence
 * unit
 *
 * @author pgsou_000
 * @param <T>
 * @param <K>
 */
class CafeteriaJpaRepositoryBase<T, K extends Serializable>
        extends JpaTransactionalRepository<T, K> {

    CafeteriaJpaRepositoryBase(String persistenceUnitName) {
        super(persistenceUnitName);
    }

    CafeteriaJpaRepositoryBase() {
        super(Application.settings().getPersistenceUnitName());
    }
}
