package eapli.ecafeteria.persistence;

import eapli.ecafeteria.domain.authz.Username;
import eapli.ecafeteria.domain.cafeteriauser.CafeteriaUser;
import eapli.ecafeteria.domain.cafeteriauser.MecanographicNumber;
import eapli.framework.persistence.repositories.DataRepository;
import java.util.Optional;

/**
 *
 * @author Jorge Santos ajs@isep.ipp.pt 02/04/2016
 */
public interface CafeteriaUserRepository extends DataRepository<CafeteriaUser, MecanographicNumber> {

    /**
     * returns the cafeteria user (utente) whose username is given
     *
     * @param name the username to search for
     * @return
     */
    Optional<CafeteriaUser> findByUsername(Username name);

    /**
     * returns the cafeteria user (utente) with the given mecanographic number
     *
     * @param number
     * @return
     */
    Optional<CafeteriaUser> findByMecanographicNumber(MecanographicNumber number);

    public Iterable<CafeteriaUser> findAllActive();
    
    void updateUser(CafeteriaUser user);
}
