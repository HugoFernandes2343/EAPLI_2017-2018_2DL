/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.application.cafeteriauser.profile;

import eapli.ecafeteria.application.cafeteriauser.CafeteriaUserBaseController;
import eapli.ecafeteria.application.cafeteriauser.CafeteriaUserService;
import eapli.ecafeteria.domain.cafeteriauser.CafeteriaUser;
import eapli.ecafeteria.domain.cafeteriauser.NutritionalProfileField;
import eapli.framework.application.Controller;
import java.util.Optional;

/**
 *
 * @author Rodrigo Soares <1140420@isep.ipp.pt>
 */
public class EditNutritionalProfileController implements Controller{
    private Optional<CafeteriaUser> user;
    
    public Iterable<NutritionalProfileField> findEditableFields(){
        CafeteriaUserService userService = new CafeteriaUserService();
        
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
