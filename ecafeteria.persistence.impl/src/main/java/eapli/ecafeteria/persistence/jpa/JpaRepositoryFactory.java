package eapli.ecafeteria.persistence.jpa;

import eapli.ecafeteria.Application;
import eapli.ecafeteria.persistence.DishRepository;
import eapli.ecafeteria.persistence.DishTypeRepository;
import eapli.ecafeteria.persistence.MaterialRepository;
import eapli.ecafeteria.persistence.OrganicUnitRepository;
import eapli.ecafeteria.persistence.RepositoryFactory;
import eapli.ecafeteria.persistence.SignupRequestRepository;
import eapli.ecafeteria.persistence.UserRepository;
import eapli.framework.persistence.repositories.TransactionalContext;
import eapli.framework.persistence.repositories.impl.jpa.JpaTransactionalContext;

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
    public DishTypeRepository dishTypes() {
	return new JpaDishTypeRepository();
    }

    @Override
    public OrganicUnitRepository organicUnits() {
	return new JpaOrganicUnitRepository();
    }

    @Override
    public JpaCafeteriaUserRepository cafeteriaUsers(TransactionalContext autoTx) {
	return new JpaCafeteriaUserRepository(autoTx);
    }

    @Override
    public SignupRequestRepository signupRequests(TransactionalContext autoTx) {
	return new JpaSignupRequestRepository(autoTx);
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
	return new JpaTransactionalContext(Application.settings().getPersistenceUnitName());
    }
}
