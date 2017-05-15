/**
 *
 */
package eapli.ecafeteria.persistence;

import eapli.framework.persistence.repositories.TransactionalContext;

/**
 * @author Paulo Gandra Sousa
 *
 */
public interface RepositoryFactory {

    /**
     * factory method to create a transactional context to use in the
     * repositories
     *
     * @return
     */
    TransactionalContext buildTransactionalContext();

    /**
     *
     * @param autoTx declares if the repository should be created in auto
     * transaction mode or if the caller will take care of transactions
     * @return
     */
    UserRepository users(TransactionalContext autoTx);

    DishTypeRepository dishTypes();

    OrganicUnitRepository organicUnits();

    /**
     *
     * @param autoTx declares if the repository should be created in auto
     * transaction mode or if the caller will take care of transactions
     * @return
     */
    CafeteriaUserRepository cafeteriaUsers(TransactionalContext autoTx);

    /**
     *
     * @param autoTx declares if the repository should be created in auto
     * transaction mode or if the caller will take care of transactions
     * @return
     */
    SignupRequestRepository signupRequests(TransactionalContext autoTx);

    DishRepository dishes();

    MaterialRepository materials();
}
