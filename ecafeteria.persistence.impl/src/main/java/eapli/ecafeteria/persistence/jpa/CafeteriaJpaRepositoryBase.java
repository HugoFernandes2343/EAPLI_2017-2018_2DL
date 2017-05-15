package eapli.ecafeteria.persistence.jpa;

import eapli.ecafeteria.Application;
import eapli.framework.persistence.repositories.impl.jpa.JpaTransactionalRepository;
import java.io.Serializable;

/**
 * simplifies the use of the transactional base repository by extending the
 * class
 *
 * @author pgsou_000
 * @param <T>
 * @param <K>
 */
class CafeteriaJpaRepositoryBase<T, K extends Serializable> extends JpaTransactionalRepository<T, K> {

    CafeteriaJpaRepositoryBase(String persistenceUnitName) {
        super(persistenceUnitName);
    }

    CafeteriaJpaRepositoryBase() {
        super(Application.settings().getPersistenceUnitName());
    }
}
