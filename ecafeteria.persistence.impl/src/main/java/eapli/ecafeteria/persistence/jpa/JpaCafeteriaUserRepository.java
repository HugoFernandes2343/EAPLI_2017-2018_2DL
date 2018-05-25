package eapli.ecafeteria.persistence.jpa;

import eapli.ecafeteria.Application;
import eapli.ecafeteria.domain.authz.Username;
import eapli.ecafeteria.domain.cafeteriauser.CafeteriaUser;
import eapli.ecafeteria.domain.cafeteriauser.MecanographicNumber;
import eapli.ecafeteria.persistence.CafeteriaUserRepository;
import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;
import eapli.framework.persistence.repositories.TransactionalContext;
import eapli.framework.persistence.repositories.impl.jpa.JpaAutoTxRepository;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Jorge Santos ajs@isep.ipp.pt 02/04/2016
 */
class JpaCafeteriaUserRepository extends JpaAutoTxRepository<CafeteriaUser, MecanographicNumber>
        implements CafeteriaUserRepository {

    public JpaCafeteriaUserRepository(TransactionalContext autoTx) {
        super(autoTx);
    }

    public JpaCafeteriaUserRepository(String puname) {
        super(puname, Application.settings().getExtendedPersistenceProperties());
    }

    @Override
    public Optional<CafeteriaUser> findByUsername(Username name) {
        final Map<String, Object> params = new HashMap<>();
        params.put("name", name);
        return matchOne("e.systemUser.username=:name", params);
    }

    @Override
    public Optional<CafeteriaUser> findByMecanographicNumber(MecanographicNumber number) {
        final Map<String, Object> params = new HashMap<>();
        params.put("number", number);
        return matchOne("e.mecanographicNumber=:number", params);
    }

    @Override
    public Iterable<CafeteriaUser> findAllActive() {
        return match("e.systemUser.active = true");
    }

    @Override
    public void updateUser(CafeteriaUser user) {
        try {
            save(user);
        } catch (DataConcurrencyException ex) {
            System.out.println ("Failed to update User - DataConcurrencyException occurred, please retry later.");
        } catch (DataIntegrityViolationException ex) {
            System.out.println ("Failed to update User - DataIntegrityViolationException occurred, please retry later.");
        }
    }
    
    
}
