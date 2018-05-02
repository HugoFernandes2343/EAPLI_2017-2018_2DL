/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.application.kitchen;

import eapli.ecafeteria.persistence.MenuRepository;
import eapli.ecafeteria.persistence.PersistenceContext;
import eapli.framework.application.Controller;

/**
 *
 * @author Paulo Jorge
 */
public class ElaborateMealPlanController implements Controller{
    
    private final MenuRepository repository = PersistenceContext.repositories().menus();
    
   
}
