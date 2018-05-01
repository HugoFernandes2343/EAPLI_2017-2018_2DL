/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.app.backoffice.presentation.menu;

import eapli.ecafeteria.application.menu.PublishMenuController;
import eapli.ecafeteria.domain.menu.Menu;
import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.presentation.console.SelectWidget;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jsant
 */
public class PublishMenuUI extends AbstractUI {

    private PublishMenuController pmc = new PublishMenuController();

    @Override
    protected boolean doShow() {
        System.out.println(headline() + "\n\n");
        List<Menu> lm = (List<Menu>) pmc.workingMenuList();
        if (lm.isEmpty()) {
            System.out.println("No working menus at the moment.");
            return false;
        } else {
            SelectWidget<Menu> mchooser = new SelectWidget("\n\nMenus available to publish:\n", lm);
            mchooser.show();
            pmc.menu(mchooser.selectedElement());
            try {
                if (pmc.publish(mchooser.selectedElement()) == null) {
                    System.out.println("Menu already published. Choose another one.");
                } else {
                    System.out.println("Menu published with success.");
                }
            } catch (DataConcurrencyException | DataIntegrityViolationException ex) {
                Logger.getLogger(PublishMenuUI.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return false;
    }

    
    
    
    @Override
    public String headline() {
        return "PUBLISH MENU";
    }

}
