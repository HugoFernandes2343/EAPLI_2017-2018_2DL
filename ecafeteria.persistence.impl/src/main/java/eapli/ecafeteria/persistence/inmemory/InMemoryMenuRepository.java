/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.persistence.inmemory;

import eapli.ecafeteria.domain.menu.Menu;
import eapli.ecafeteria.persistence.MenuRepository;
import eapli.framework.persistence.repositories.impl.inmemory.InMemoryRepository;
import java.util.Calendar;
import java.util.Date;
import java.util.Optional;
import eapli.ecafeteria.domain.menu.Menu;
import eapli.ecafeteria.domain.menu.MenuState;
import eapli.ecafeteria.domain.reservations.Reservation;
import eapli.ecafeteria.persistence.MenuRepository;
import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;
import eapli.framework.util.DateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;
import javax.persistence.TypedQuery;

/**
 *
 * @author João Vieira
 */
public class InMemoryMenuRepository extends InMemoryRepository<Menu, Long> implements MenuRepository {

    public InMemoryMenuRepository() {
    }

    @Override
    public Menu findByDate(Date date) {
        Iterable<Menu> menus =  super.findAll();
     
        for(Menu m : menus){
            if(m.startDate().compareTo(DateTime.dateToCalendar(date)) <= 0 && m.finishDate().compareTo(DateTime.dateToCalendar(date))>=0){
                return m;
            }
        }
        return null;
    }

    @Override
    public Iterable<Menu> findAll() {
        return super.findAll();
    }

    @Override
    public Optional<Menu> findOne(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public long count() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Iterable<Menu> findWorkingMenu() {
        return match(e -> e.workingMenu());
    }

    @Override
    public Iterable<Menu> findPublishedMenu() {
        return match(e -> e.publishedMenu());
    }

    @Override
    protected Long newKeyFor(Menu entity) {
        return entity.id();
    }

    @Override
    public Menu findByID(long id) {
        Optional<Menu> men = matchOne(m -> m.compareID(id));

        if (men.isPresent()) {
            Menu ms = men.get();
            return ms;
        } else {
            return null;
        }
    }

    @Override
    public Iterable<Menu> findMenuBetweenDates(Calendar start, Calendar end) {
        ArrayList<Menu> list_menus = new ArrayList<>();

        list_menus = (ArrayList<Menu>) match(m -> m.startDate().equals(start));

        ArrayList<Menu> ret = new ArrayList<>();

        for (Menu m : list_menus) {
            if (m.finishDate().equals(end)) {
                ret.add(m);
            }
        }
        return ret;
    }

}
