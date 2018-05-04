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
     * @param autoTx the transactional context to enrol
     * @return
     */
    UserRepository
            users(TransactionalContext autoTx);

    /**
     * repository will be created in auto transaction mode
     *
     * @return
     */
    UserRepository users();

    DishTypeRepository dishTypes();

    /**
     *
     * @param autoTx the transactional context to enroll
     * @return
     */
    CafeteriaUserRepository cafeteriaUsers(TransactionalContext autoTx);

    /**
     * repository will be created in auto transaction mode
     *
     * @return
     */
    CafeteriaUserRepository cafeteriaUsers();

    /**
     *
     * @param autoTx the transactional context to enroll
     * @return
     */
    SignupRequestRepository signupRequests(TransactionalContext autoTx);

    /**
     * repository will be created in auto transaction mode
     *
     * @return
     */
    SignupRequestRepository signupRequests();

    DishRepository dishes();

    MaterialRepository materials();

    MealRepository meals();

    MenuRepository menus();

    /**
     * respository to return reservations
     *
     * @return
     */
    ReservationRepository reservations();

    /**
     * respository to return pos
     *
     * @return
     */
    POSRepository pos();

    /**
     * Repository to get movements
     *
     * @return
     */
    MovementRepository movements();

    MealPlanRepository mealPlan();

    /**
     * ************************
     * reporting ************************
     */
    /**
     * @return
     */
    DishReportingRepository dishReporting();

    CafeteriaShiftRepository cafeteriaShift();
}
