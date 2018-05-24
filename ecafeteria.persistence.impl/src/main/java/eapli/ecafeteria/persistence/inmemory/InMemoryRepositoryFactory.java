package eapli.ecafeteria.persistence.inmemory;

import eapli.ecafeteria.bootstrapers.ECafeteriaBootstrapper;

import eapli.ecafeteria.persistence.*;
import eapli.framework.persistence.repositories.TransactionalContext;

/**
 *
 * Created by nuno on 20/03/16.
 */
public class InMemoryRepositoryFactory implements RepositoryFactory {

    static {
        // only needed because of the in memory persistence
        new ECafeteriaBootstrapper().execute();
    }

    @Override
    public UserRepository users(TransactionalContext tx) {
        return new InMemoryUserRepository();
    }

    @Override
    public UserRepository users() {
        return users(null);
    }

    @Override
    public DishTypeRepository dishTypes() {
        return new InMemoryDishTypeRepository();
    }

    @Override
    public CafeteriaUserRepository cafeteriaUsers(TransactionalContext tx) {

        return new InMemoryCafeteriaUserRepository();
    }

    @Override
    public CafeteriaUserRepository cafeteriaUsers() {
        return cafeteriaUsers(null);
    }

    @Override
    public NutritionalProfileRepository nutritionalProfiles() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
    @Override
    public SignupRequestRepository signupRequests() {
        return signupRequests(null);
    }

    @Override
    public SignupRequestRepository signupRequests(TransactionalContext tx) {
        return new InMemorySignupRequestRepository();
    }

    @Override
    public DishRepository dishes() {
        return new InMemoryDishRepository();
    }

    @Override
    public MaterialRepository materials() {
        return new InMemoryMaterialRepository();
    }

    @Override
    public TransactionalContext buildTransactionalContext() {
        // in memory does not support transactions...
        return null;
    }

    @Override
    public DishReportingRepository dishReporting() {
        return new InMemoryDishReportingRepository();
    }

    @Override
    public ReservationRepository reservations() {
        return new InMemoryReservationRepository();
    }

    public ComplaintRepository complaints() {   return new InMemoryComplaintRepository();   }

    public MovementRepository movements() {
        return new InMemoryMovementRepository();
    }

    @Override
    public POSRepository pos() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public MealRepository meals() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public MenuRepository menus() {
        return new InMemoryMenuRepository();
    }

    @Override
    public MealPlanRepository mealPlan() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public CafeteriaShiftRepository cafeteriaShift() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    
    }
    @Override
    public MealPlanItemRepository mealPlanItemRepository() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public KitchenAlertsRepository KitchenAlertsRepository() {
       return new InMemoryKitchenAlertRepository();
    }

    @Override
    public MealRatingRepository ratings() {
        return new InMemoryMealRatingRepository();
    }
    
    @Override
    public AccountCardRepository accounts() {
        return new InMemoryAccountCardRepository();
    }

    @Override
    public LotRepository lots(){
        return new InMemoryLotRepository();

    }
}
