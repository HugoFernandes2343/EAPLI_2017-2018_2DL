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
import eapli.ecafeteria.persistence.MenuRepository;
import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;
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
        
         Optional<Menu> m = matchOne(e -> e.startDate().equals(date));
         
         if( m.isPresent()){
             Menu ms = m.get();
             return ms;
         } else {
             return null;
         }
    }

    @Override
    public Iterable<Menu> findAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
    
}
