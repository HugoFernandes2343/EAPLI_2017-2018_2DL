package eapli.ecafeteria.persistence.jpa;

import eapli.ecafeteria.Application;
import eapli.ecafeteria.persistence.*;
import eapli.framework.persistence.repositories.TransactionalContext;
import eapli.framework.persistence.repositories.impl.jpa.JpaAutoTxRepository;

/**
 *
 * Created by nuno on 21/03/16.
 */
public class JpaRepositoryFactory implements RepositoryFactory {

    @Override
    public UserRepository users(TransactionalContext autoTx) {
        return new JpaUserRepository(autoTx);
    }

    @Override
    public UserRepository users() {
        return new JpaUserRepository(Application.settings().getPersistenceUnitName());
    }

    @Override
    public DishTypeRepository dishTypes() {
        return new JpaDishTypeRepository();
    }

    @Override
    public JpaCafeteriaUserRepository cafeteriaUsers(TransactionalContext autoTx) {
        return new JpaCafeteriaUserRepository(autoTx);
    }

    @Override
    public JpaCafeteriaUserRepository cafeteriaUsers() {
        return new JpaCafeteriaUserRepository(Application.settings().getPersistenceUnitName());
    }

    @Override
    public SignupRequestRepository signupRequests(TransactionalContext autoTx) {
        return new JpaSignupRequestRepository(autoTx);
    }

    @Override
    public SignupRequestRepository signupRequests() {
        return new JpaSignupRequestRepository(Application.settings().getPersistenceUnitName());
    }

    @Override
    public DishRepository dishes() {
        return new JpaDishRepository();
    }

    @Override
    public MaterialRepository materials() {
        return new JpaMaterialRepository();
    }

    @Override
    public TransactionalContext buildTransactionalContext() {
        return JpaAutoTxRepository
                .buildTransactionalContext(Application.settings().getPersistenceUnitName(), Application.settings().getExtendedPersistenceProperties());
    }

    @Override
    public DishReportingRepository dishReporting() {
        return new JpaDishReportingRepository();
    }

    @Override
    public ReservationRepository reservations() {
        return new JpaReservationRepository();
    }

    @Override
    public MovementRepository movements() {
        return new JpaMovementRepository();
    }

    @Override
    public MenuRepository menus() {
        return new JpaMenuRepository();
    }

    @Override
    public POSRepository pos() {
        return new JpaPOSRepository();
    }

    @Override
    public MealRepository meals() {
        return new JpaMealRepository();
    }

    @Override
    public MealPlanRepository mealPlan() {
        return new JpaMealPlanRepository();
    }

    @Override
    public CafeteriaShiftRepository cafeteriaShift() {
        return new JPACafeteriaShiftRepository();
    }

    @Override
    public MealPlanItemRepository mealPlanItemRepository() {
        return new JpaMealPlanItemRepository();
    }

    @Override
    public KitchenAlertsRepository KitchenAlertsRepository() {
       return new JpaKitchenAlertRepository();
    }

    public MealRatingRepository ratings() {
        return new JpaMealRatingRepository();
    }
    
    @Override
    public AccountCardRepository accounts() {
        return new JpaAccountCardRepository();
    }

    @Override
    public ComplaintRepository complaints() {   return new JpaComplaintRepository();    }

    @Override
    public eapli.ecafeteria.persistence.LotRepository lots() {
        return new JpaLotRepository();
    }
}
