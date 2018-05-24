/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.persistence.jpa;

import eapli.ecafeteria.domain.authz.Username;
import eapli.ecafeteria.domain.cafeteriauser.NutritionalProfile;
import eapli.ecafeteria.domain.cafeteriauser.NutritionalProfileField;
import eapli.ecafeteria.persistence.NutritionalProfileRepository;
import java.util.Optional;

/**
 *
 * @author Rodrigo Soares <1140420@isep.ipp.pt>
 */
public class JpaNutritionalProfileRepository extends CafeteriaJpaRepositoryBase<NutritionalProfile, Long> implements NutritionalProfileRepository{

    @Override
    public Optional<NutritionalProfile> findByUsername(Username name) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean updateField(NutritionalProfileField field) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
}
