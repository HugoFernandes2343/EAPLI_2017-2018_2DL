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
    
    WAITING {
        
        @Override
        public String toString(){
            return "Waiting to be published";
        }
    },
    
    PUBLISHED {
        
        @Override
        public String toString(){
            return "Published";
        }
    };
}
