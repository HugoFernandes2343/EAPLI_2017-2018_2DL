/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.application.menu;

import eapli.ecafeteria.domain.menu.Menu;
import eapli.framework.domain.Designation;
import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;
import java.util.Calendar;

/**
 *
 * @author David Santiago <1161109@isep.ipp.pt>
 */
public class CopyMenuController {
    
    CopyMenuService cms = new CopyMenuService();
    
    public void CopyMenu(Menu m,Calendar startDate,Designation newName) throws DataConcurrencyException, DataIntegrityViolationException{
        
        cms.copyMenu(m, startDate, newName);
    }
    
}
