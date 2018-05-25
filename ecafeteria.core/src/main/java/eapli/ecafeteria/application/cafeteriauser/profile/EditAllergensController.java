/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.application.cafeteriauser.profile;

import eapli.ecafeteria.application.authz.AuthorizationService;
import eapli.ecafeteria.application.cafeteriauser.CafeteriaUserService;
import eapli.ecafeteria.domain.allergens.Allergen;
import eapli.ecafeteria.domain.cafeteriauser.CafeteriaUser;
import eapli.framework.application.Controller;
import java.util.Optional;

/**
 *
 * @author Rodrigo Soares <1140420@isep.ipp.pt>
 */
public class EditAllergensController implements Controller{
    private CafeteriaUserService userService;
    
    public CafeteriaUser findUser(){
        userService = new CafeteriaUserService();
        Optional<CafeteriaUser> user = userService.findCafeteriaUserByUsername(AuthorizationService.session().authenticatedUser().id());
        
        return user.get();
    }
    
    public void addAllergen (CafeteriaUser user, Allergen newAllergen){
        user.addAllergen(newAllergen);        
        userService.updateUser(user);
    }
    
    public void removeAllergen (CafeteriaUser user, Allergen allergen){
        user.removeAllergen (allergen);        
        userService.updateUser(user);
    }
}
