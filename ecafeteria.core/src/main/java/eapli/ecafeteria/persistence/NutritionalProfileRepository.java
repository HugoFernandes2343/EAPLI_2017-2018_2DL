/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.persistence;

import eapli.ecafeteria.domain.authz.Username;
import eapli.ecafeteria.domain.cafeteriauser.CafeteriaUser;
import eapli.ecafeteria.domain.cafeteriauser.NutritionalProfile;
import eapli.ecafeteria.domain.cafeteriauser.NutritionalProfileField;
import eapli.framework.persistence.repositories.DataRepository;
import java.util.Optional;

/**
 *
 * @author Rodrigo Soares <1140420@isep.ipp.pt>
 */
public interface NutritionalProfileRepository extends DataRepository<NutritionalProfile, Long>{
    Optional<NutritionalProfile> findByUsername(Username name);
    
    boolean updateField (NutritionalProfileField field);
}
