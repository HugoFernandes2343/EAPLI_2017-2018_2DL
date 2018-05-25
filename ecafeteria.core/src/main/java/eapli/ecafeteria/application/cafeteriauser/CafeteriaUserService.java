/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates and open the template
 * in the editor.
 */
package eapli.ecafeteria.application.cafeteriauser;

import java.util.Optional;

import eapli.ecafeteria.domain.authz.Username;
import eapli.ecafeteria.domain.cafeteriauser.CafeteriaUser;
import eapli.ecafeteria.domain.cafeteriauser.MecanographicNumber;
import eapli.ecafeteria.persistence.CafeteriaUserRepository;
import eapli.ecafeteria.persistence.PersistenceContext;
import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author mcn
 */
public class CafeteriaUserService {

    private final CafeteriaUserRepository repo = PersistenceContext.repositories().cafeteriaUsers();

    public Optional<CafeteriaUser> findCafeteriaUserByMecNumber(String mecNumber) {
        return this.repo.findByMecanographicNumber(new MecanographicNumber(mecNumber));
    }

    public Optional<CafeteriaUser> findCafeteriaUserByUsername(Username user) {
        return this.repo.findByUsername(user);
    }
    
    public void updateUser(CafeteriaUser user){
        repo.updateUser(user);
    }
}
