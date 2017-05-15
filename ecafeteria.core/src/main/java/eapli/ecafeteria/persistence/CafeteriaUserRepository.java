package eapli.ecafeteria.persistence;

import eapli.ecafeteria.domain.authz.Username;
import eapli.ecafeteria.domain.cafeteria.CafeteriaUser;
import eapli.ecafeteria.domain.cafeteria.MecanographicNumber;
import eapli.framework.persistence.repositories.DataRepository;

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
    CafeteriaUser findByUsername(Username name);

    /**
     * returns the cafeteria user (utente) with the given mecanographic number
     *
     * @param number
     * @return
     */
    CafeteriaUser findByMecanographicNumber(MecanographicNumber number);
}
