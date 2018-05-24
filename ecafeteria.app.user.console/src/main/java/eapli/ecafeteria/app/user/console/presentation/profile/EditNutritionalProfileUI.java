/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.app.user.console.presentation.profile;

import eapli.ecafeteria.app.user.console.presentation.CafeteriaUserBaseUI;
import eapli.ecafeteria.application.cafeteriauser.CafeteriaUserBaseController;
import eapli.ecafeteria.application.cafeteriauser.profile.EditNutritionalProfileController;
import eapli.ecafeteria.domain.cafeteriauser.CafeteriaUser;
import eapli.ecafeteria.domain.cafeteriauser.NutritionalProfile;
import eapli.ecafeteria.domain.cafeteriauser.NutritionalProfileField;
import eapli.framework.util.Console;
import java.util.Iterator;

/**
 *
 * @author Rodrigo
 */
public class EditNutritionalProfileUI extends CafeteriaUserBaseUI{

    private final EditNutritionalProfileController controller = new EditNutritionalProfileController();
    
    @Override
    protected CafeteriaUserBaseController controller() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected boolean doShow() {
        CafeteriaUser user = controller.findUser();
        NutritionalProfile nutritionalProfile = user.getNutritionalProfile();
        
        Iterable<NutritionalProfileField> nutritionalProfileFields = nutritionalProfile.getFields();
        
        if (!nutritionalProfileFields.iterator().hasNext()){ //If empty
            System.out.println ("Looks like you haven't created your Nutritional Profile yet! Please select the appropriate option in the preceding menu.");
            return true;
        }
        NutritionalProfileField nutriField = askToSelectNutriField ("Please select a Field from the available ones:", nutritionalProfileFields);
        
        editNutritionalProfileFieldValue (nutriField);
        
        controller.updateNutritionalProfileField (user, nutriField);
        displayEditSuccess (true, nutriField);
        
        return true;
    }
    
    private NutritionalProfileField askToSelectNutriField (String prompt, Iterable<NutritionalProfileField> nutritionalProfileFields){
        NutritionalProfileField nutriField = null;
        
        while (nutriField == null){
            System.out.println("Available Fields: ");
            int i=0;
            for (NutritionalProfileField next : nutritionalProfileFields){
                System.out.println ( i + " - " + next.description() + ", " + next.value() );
                i++;
            }
            int nutriFieldInt = Console.readInteger(prompt);

            boolean foundIt = false;
            i=0;
            Iterator<NutritionalProfileField> it = nutritionalProfileFields.iterator();
            while (it.hasNext() && !foundIt){
                NutritionalProfileField next = it.next();
                if (nutriFieldInt == i){
                    nutriField = next;
                    foundIt = true;
                }
                else{
                    i++;
                }                
            }
        }
        return nutriField;
    }
    
    private void editNutritionalProfileFieldValue (NutritionalProfileField nutriField){
        String prompt = ("Please type in a new value for your " + nutriField.description() + "field.");
        double newValue = Console.readDouble (prompt);
        
        nutriField.setValue(newValue);
    }
    
    private void displayEditSuccess (boolean editSuccess, NutritionalProfileField nutriField){
        if(editSuccess){
            System.out.println ("Your " + nutriField.description() + "is now " + nutriField.value());
        }
        else{
            System.out.println ("Failed to save your changes Please retry later.");
        }
    }
    
}
