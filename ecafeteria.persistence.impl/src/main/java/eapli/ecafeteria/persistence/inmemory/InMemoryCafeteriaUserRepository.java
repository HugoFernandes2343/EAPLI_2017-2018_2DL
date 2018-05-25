package eapli.ecafeteria.persistence.inmemory;

import eapli.ecafeteria.domain.authz.Username;
import eapli.ecafeteria.domain.cafeteriauser.CafeteriaUser;
import eapli.ecafeteria.domain.cafeteriauser.MecanographicNumber;
import eapli.ecafeteria.persistence.CafeteriaUserRepository;
import eapli.framework.persistence.repositories.impl.inmemory.InMemoryRepository;
import java.util.Optional;

/**
 *
 * @author Jorge Santos ajs@isep.ipp.pt 02/04/2016
 */
public class InMemoryCafeteriaUserRepository extends InMemoryRepository<CafeteriaUser, MecanographicNumber> implements CafeteriaUserRepository {

    @Override
    protected MecanographicNumber newKeyFor(CafeteriaUser u) {
        return u.id();
    }

    @Override
    public Optional<CafeteriaUser> findByUsername(Username name) {
        return matchOne(e -> e.user().username().equals(name));
    }

    @Override
    public Optional<CafeteriaUser> findByMecanographicNumber(MecanographicNumber number) {
        return Optional.of(data().get(number));
    }

    @Override
    public Iterable<CafeteriaUser> findAllActive() {
        return match(e -> e.user().isActive());
    }

    @Override
    public void updateUser(CafeteriaUser user) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
}
