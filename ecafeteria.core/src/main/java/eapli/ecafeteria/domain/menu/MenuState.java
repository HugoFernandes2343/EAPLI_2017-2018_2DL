/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.domain.menu;

import javax.persistence.Embeddable;

/**
 *
 * @author jsant
 */

@Embeddable
public enum MenuState {
    
    WORKING_MENU {
        
        @Override
        public String toString(){
            return "Working menu";
        }
    },
    
    PUBLISHED_MENU {
        
        @Override
        public String toString(){
            return "Published menu";
        }
    };
}
