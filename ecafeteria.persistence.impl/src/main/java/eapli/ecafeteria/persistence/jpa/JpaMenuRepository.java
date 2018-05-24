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
import java.util.Calendar;
import java.util.Date;
import java.util.Optional;
import javax.persistence.Query;
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

        if (m.isPresent()) {
            Menu ms = m.get();
            return ms;
        } else {
            return null;
        }

    }

    @Override
    public Iterable<Menu> findWorkingMenu() {
        final Query q;
        String where = "e.state=:mstate";
        q = entityManager().createQuery("SELECT e FROM Menu e WHERE " + where, this.entityClass);
        q.setParameter("mstate", MenuState.WORKING_MENU);
        return q.getResultList();
    }

    @Override
    public Iterable<Menu> findPublishedMenu() {
        final Query q;
        String where = "e.state=:mstate";
        q = entityManager().createQuery("SELECT e FROM Menu e WHERE " + where, this.entityClass);
        q.setParameter("mstate", MenuState.PUBLISHED_MENU);
        return q.getResultList();
    }

    @Override
    public Menu findByID(long menuID) {

        Optional<Menu> m = matchOne("e.menuID=:menuID", "menuID", menuID);

        if (m.isPresent()) {
            Menu ms = m.get();
            return ms;
        } else {
            return null;
        }
    }

    
    @Override
    public Iterable<Menu> findMenuBetweenDates(Calendar start,Calendar end) {
        
        final Query q;
        q = entityManager().createQuery("SELECT e FROM Menu e WHERE e.startDate >=:start and  STARTDATE<=:end  OR  ENDINGDATE>=:start and  ENDINGDATE<=:end", this.entityClass);
        q.setParameter("start",start);
        q.setParameter("end",end);
        return q.getResultList();
        
    }

   

}
