package eapli.ecafeteria.persistence.jpa;

import eapli.ecafeteria.Application;
import eapli.ecafeteria.domain.authz.Username;
import eapli.ecafeteria.domain.cafeteria.CafeteriaUser;
import eapli.ecafeteria.domain.cafeteria.MecanographicNumber;
import eapli.ecafeteria.persistence.CafeteriaUserRepository;
import eapli.framework.persistence.repositories.TransactionalContext;
import eapli.framework.persistence.repositories.impl.jpa.JpaAutoTxRepository;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Jorge Santos ajs@isep.ipp.pt 02/04/2016
 */
class JpaCafeteriaUserRepository extends JpaAutoTxRepository<CafeteriaUser, MecanographicNumber>
        implements CafeteriaUserRepository {

    public JpaCafeteriaUserRepository(TransactionalContext autoTx) {
        super(Application.settings().getPersistenceUnitName(), autoTx);
    }

    @Override
    public CafeteriaUser findByUsername(Username name) {
        Map<String, Object> params = new HashMap<>();
        params.put("name", name);
        return repo.matchOne("e.systemUser.username=:name", params);
    }

    @Override
    public CafeteriaUser findByMecanographicNumber(MecanographicNumber number) {
        Map<String, Object> params = new HashMap<>();
        params.put("number", number);
        return repo.matchOne("e.mecanographicNumber=:number", params);
    }
}
