/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.persistence.jpa;

import eapli.ecafeteria.domain.menu.Menu;
import eapli.ecafeteria.domain.menu.MenuState;
import eapli.ecafeteria.persistence.MenuRepository;
import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;
import eapli.framework.util.DateTime;
import java.util.Date;
import java.util.Optional;
import javax.persistence.TypedQuery;

/**
 *
 * @author João Vieira
 */
public class JpaMenuRepository extends CafeteriaJpaRepositoryBase<Menu, Long> implements MenuRepository {

    public JpaMenuRepository() {
    }

    @Override
    public Menu findByDate(Date date) {
  
         Optional<Menu> m = matchOne("e.startDate<=:date and e.endingDate>=:date", "date", DateTime.dateToCalendar(date));
         
         if( m.isPresent()){
             Menu ms = m.get();
             return ms;
         } else {
             return null;
         }
         
         
    }

    @Override
    public void delete(Menu entity) throws DataIntegrityViolationException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(Long entityId) throws DataIntegrityViolationException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Menu save(Menu entity) throws DataConcurrencyException, DataIntegrityViolationException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
        TypedQuery<Menu> q = entityManager().createQuery("select me from Menu me where me.state =:st", Menu.class);
        q.setParameter("st", MenuState.WORKING_MENU);
        return q.getResultList();
    }
    
    @Override
    public Iterable<Menu> findPublishedMenu() {
        TypedQuery<Menu> q = entityManager().createQuery("select me from Menu me where me.state =:st", Menu.class);
        q.setParameter("st", MenuState.PUBLISHED_MENU);
        return q.getResultList();
    }
    
}
