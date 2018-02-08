/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates and open the template
 * in the editor.
 */
package eapli.ecafeteria.application.cafeteria;

import java.util.Optional;

import eapli.ecafeteria.domain.authz.Username;
import eapli.ecafeteria.domain.cafeteria.CafeteriaUser;
import eapli.ecafeteria.domain.cafeteria.MecanographicNumber;
import eapli.ecafeteria.persistence.CafeteriaUserRepository;
import eapli.ecafeteria.persistence.PersistenceContext;

/**
 *
 * @author mcn
 */
public class CafeteriaUserService {

	private final CafeteriaUserRepository repo = PersistenceContext.repositories().cafeteriaUsers(null);

	public Optional<CafeteriaUser> findCafeteriaUserByMecNumber(String mecNumber) {
		return this.repo.findByMecanographicNumber(new MecanographicNumber(mecNumber));
	}

	public Optional<CafeteriaUser> findCafeteriaUserByUsername(Username user) {
		return this.repo.findByUsername(user);
	}
}
