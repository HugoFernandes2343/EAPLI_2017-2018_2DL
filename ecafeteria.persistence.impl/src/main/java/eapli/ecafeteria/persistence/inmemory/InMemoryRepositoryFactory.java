package eapli.ecafeteria.persistence.inmemory;

//import eapli.ecafeteria.bootstrapers.ECafeteriaBootstraper;
import eapli.ecafeteria.bootstrapers.ECafeteriaBootstraper;
import eapli.ecafeteria.persistence.CafeteriaUserRepository;
import eapli.ecafeteria.persistence.DishRepository;
import eapli.ecafeteria.persistence.DishTypeRepository;
import eapli.ecafeteria.persistence.MaterialRepository;
import eapli.ecafeteria.persistence.OrganicUnitRepository;
import eapli.ecafeteria.persistence.RepositoryFactory;
import eapli.ecafeteria.persistence.SignupRequestRepository;
import eapli.ecafeteria.persistence.UserRepository;
import eapli.framework.persistence.repositories.TransactionalContext;

/**
 *
 * Created by nuno on 20/03/16.
 */
public class InMemoryRepositoryFactory implements RepositoryFactory {

    static {
	// only needed because of the in memory persistence
	new ECafeteriaBootstraper().execute();
    }

    @Override
    public UserRepository users(TransactionalContext tx) {
	return new InMemoryUserRepository();
    }

    @Override
    public DishTypeRepository dishTypes() {
	return new InMemoryDishTypeRepository();
    }

    @Override
    public OrganicUnitRepository organicUnits() {
	return new InMemoryOrganicUnitRepository();
    }

    @Override
    public CafeteriaUserRepository cafeteriaUsers(TransactionalContext tx) {

	return new InMemoryCafeteriaUserRepository();
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
}
