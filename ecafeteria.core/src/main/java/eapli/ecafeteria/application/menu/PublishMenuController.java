/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.application.menu;

import eapli.ecafeteria.domain.menu.Menu;
import eapli.ecafeteria.persistence.MenuRepository;
import eapli.ecafeteria.persistence.PersistenceContext;
import eapli.framework.application.Controller;
import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;

/**
 *
 * @author jsant
 */
public class PublishMenuController implements Controller{
    
    private final MenuRepository mr = PersistenceContext.repositories().menus();
    private Menu me;
    
    
    public Iterable<Menu> workingMenuList() {
        
        
        return mr.findWorkingMenu();
        
    }
    
    public void menu(Menu m){
        this.me = m;
    }
    
    public Menu publish(Menu m) throws DataConcurrencyException, DataIntegrityViolationException{
        if(!m.publishedMenu()){
            return null;
        }
        
        return mr.save(m);
    }
}
