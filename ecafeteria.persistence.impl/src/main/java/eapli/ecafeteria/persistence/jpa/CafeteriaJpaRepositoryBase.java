package eapli.ecafeteria.persistence.jpa;

import eapli.ecafeteria.Application;
import eapli.framework.persistence.repositories.impl.jpa.JpaTransactionalRepository;
import java.io.Serializable;

/**
 * a base class for all transactional repositories to use the same persistence
 * unit
 *
 * @author pgsou_000
 * @param <T>
 * @param <K>
 */
/* package */ class CafeteriaJpaRepositoryBase<T, K extends Serializable>
        extends JpaTransactionalRepository<T, K> {

    CafeteriaJpaRepositoryBase(String persistenceUnitName) {
        super(persistenceUnitName, Application.settings().getExtendedPersistenceProperties());
    }

    CafeteriaJpaRepositoryBase() {
        super(Application.settings().getPersistenceUnitName(), Application.settings().getExtendedPersistenceProperties());
    }
}
