/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.app.user.console.presentation.profile;

import eapli.ecafeteria.app.user.console.presentation.CafeteriaUserBaseUI;
import eapli.ecafeteria.application.cafeteriauser.CafeteriaUserBaseController;
import eapli.ecafeteria.application.cafeteriauser.profile.EditAllergensController;
import eapli.ecafeteria.domain.allergens.Allergen;
import eapli.ecafeteria.domain.cafeteriauser.CafeteriaUser;
import eapli.ecafeteria.domain.cafeteriauser.NutritionalProfile;
import eapli.framework.util.Console;
import java.util.List;

/**
 *
 * @author Rodrigo Soares <1140420@isep.ipp.pt>
 */
public class EditAllergensUI extends CafeteriaUserBaseUI{
    private static final int ADD_ALLERGEN_OPTION = 1;
    private static final int REMOVE_ALLERGEN_OPTION = 2;
    private static final int EXIT_OPTION = 0;
    
    private final EditAllergensController controller = new EditAllergensController();
    
    @Override
    protected CafeteriaUserBaseController controller() {
        return new CafeteriaUserBaseController();
    }

    @Override
    protected boolean doShow() {
        CafeteriaUser user = controller.findUser();
        NutritionalProfile nutritionalProfile = user.getNutritionalProfile();
        
        List<Allergen> nutritionalProfileAllergens = nutritionalProfile.getAllergens();
        
        int option = askIfAddOrDelete (nutritionalProfileAllergens);
        
        switch (option){
            case ADD_ALLERGEN_OPTION:
                Allergen newAllergen = askForAllergenToAdd();
                controller.addAllergen(user, newAllergen);
                System.out.println("Allergen " + newAllergen.toString() + " added successfully.");
                break;
            case REMOVE_ALLERGEN_OPTION:
                if (nutritionalProfileAllergens.isEmpty()){
                    System.out.println("You don't have any Allergens to remove.");
                    break;
                }
                Allergen allergen = askForAllergenToRemove (nutritionalProfileAllergens);
                controller.removeAllergen(user, allergen);
                System.out.println("Allergen " + allergen.toString() + " removed successfully.");
                break;
            case EXIT_OPTION:
                break;                               
        }
        return true;
    }
    
    private void showAllergens (List<Allergen> nutritionalProfileAllergens){
        System.out.println("These are your current Allergens: ");
        for (Allergen next : nutritionalProfileAllergens){
            System.out.println ( next.toString() );
        }
    }
    
    private int askIfAddOrDelete (List<Allergen> nutritionalProfileAllergens){
        int option;
        
        showAllergens (nutritionalProfileAllergens);

        System.out.println("These are additional possible Allergens: ");
        for (Allergen next : Allergen.allergens()){
            if (!nutritionalProfileAllergens.contains(next)){
                System.out.println ( next.toString() );
            }
        }

        System.out.println("Would you like to:\n"+
                ADD_ALLERGEN_OPTION + "- Add Allergen\n"
                + REMOVE_ALLERGEN_OPTION + "- Remove Allergen\n"
                + EXIT_OPTION + "- Go back");
        option = Console.readOption(ADD_ALLERGEN_OPTION, REMOVE_ALLERGEN_OPTION, EXIT_OPTION);
            
        return option;
    }
    
    private Allergen askForAllergenToAdd(){
        Allergen newAllergen = null;
        
        while (newAllergen == null){
            try{
                String allergenName = Console.readLine("Please type in the Allergen name you would like to add, exactly according to the names shown before:");
                newAllergen = Allergen.valueOf(allergenName.toUpperCase());
            }catch (IllegalArgumentException ie){
            }   
        }
        
        return newAllergen;
    }
    
    private Allergen askForAllergenToRemove (List<Allergen> nutritionalProfileAllergens){
        Allergen allergen = null;
        
        while (allergen == null){
            try{
                String allergenName = Console.readLine("Please type in the Allergen name you would like to remove, exactly according to the names shown before:");
                allergen = Allergen.valueOf(allergenName.toUpperCase());
                
                if(!nutritionalProfileAllergens.contains(allergen)){
                    allergen = null;
                    System.out.println ("The Allergen you typed in is already not on your list.");
                    showAllergens (nutritionalProfileAllergens);
                }
            }catch (IllegalArgumentException ie){
                showAllergens (nutritionalProfileAllergens);
            }   
        }
        
        return allergen;        
    }
}
