/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.persistence;

import eapli.ecafeteria.domain.menu.Menu;
import eapli.framework.persistence.repositories.DataRepository;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author David
 */
public interface MenuRepository extends DataRepository<Menu, Long> {

    public Menu findByDate(Date date);

    public Iterable<Menu> findWorkingMenu();

    public Iterable<Menu> findPublishedMenu();

    public Menu findByID(long id);
    
    public Iterable<Menu> findMenuBetweenDates(Calendar start,Calendar end);
}
