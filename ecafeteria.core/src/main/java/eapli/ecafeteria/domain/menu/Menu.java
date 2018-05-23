/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.domain.menu;

import eapli.ecafeteria.domain.meals.Meal;
import eapli.ecafeteria.persistence.MealRepository;
import eapli.ecafeteria.persistence.MenuRepository;
import eapli.framework.domain.Designation;
import eapli.framework.domain.ddd.AggregateRoot;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Objects;
import java.util.TimeZone;
import javax.persistence.*;

/**
 *
 * @author David Santiago
 */



@Entity
public class Menu implements AggregateRoot<Long>, Serializable {
    MenuRepository jmr = eapli.ecafeteria.persistence.PersistenceContext.repositories().menus();
    
    private static final long serialVersionUID = 1L;

    // ORM primary key
    @Id
    @GeneratedValue
    private Long menuID;
    @Version
    private Long version;
    
    
 
    private Designation name;

    @Column(unique = true)
    @Temporal(TemporalType.DATE)
    private Calendar startDate;
    @Column(unique = true)
    @Temporal(TemporalType.DATE)
    private Calendar endingDate;

    @Enumerated(EnumType.STRING)
    private MenuState state;

//    @OneToMany(mappedBy = "menu", cascade = CascadeType.ALL)
//    private List<Meal> mealList;

    protected Menu() {
        // for ORM
    }

    public Menu(Calendar startingDate, Calendar endingDate,Designation name) {
        
        if (startingDate==null || endingDate==null || name==null || !startingDate.before(endingDate))
            throw new IllegalArgumentException("Nenhum campo pode ser NULL");
        
        Calendar finishDate = (Calendar) startingDate.clone();
        finishDate.add(Calendar.DAY_OF_MONTH, 6); // adds 6 days to starting day
        
        if (!finishDate.equals(endingDate))
            throw new IllegalArgumentException("As datas têm de distar obrigatoriamente 7 dias");
        
        
        Calendar today = Calendar.getInstance(TimeZone.getDefault());
        
        if (startingDate.before(today)){
            throw new IllegalArgumentException("A Data de inicio é anterior à data atual");
        }
        
        
      
        
        if (jmr.findMenuBetweenDates(startingDate, endingDate).iterator().hasNext()){
            throw new IllegalArgumentException("Já existe um menu com as datas sobrepostas às datas do menu que está a tentar criar");
        }       
        
        this.name= name;
        this.state = MenuState.WORKING_MENU;
        this.startDate = startingDate;
        this.endingDate = endingDate;

    }

    public boolean publishedMenu() {
        if (!workingMenu()) {
            return false;
        }
        state = MenuState.PUBLISHED_MENU;
        return true;
    }

    public boolean workingMenu() {
        return this.state.equals(MenuState.WORKING_MENU);
    }

    public Calendar startDate() {
        return this.startDate;
    }

    public Calendar finishDate() {
        return this.endingDate;
    }

    @Override
    public int hashCode() {
        return this.startDate.hashCode();
    }

    public Long id() {
        return this.menuID;
    }

    public boolean compareID(Long id) {
        return id().equals(id);
    }

    @Override
    public boolean equals(Object object) {

        if (this.equals(object)) {
            return true;
        }

        final Menu other = (Menu) object;

        if (!Objects.equals(this.startDate, other.startDate)) {
            return false;
        }

        if (getClass() != object.getClass() || !Objects.equals(this.endingDate, other.endingDate) || !Objects.equals(this.version, other.version)) {
            return false;
        }

        return true;
    }

    @Override
    public String toString() {

        return name + "\nStarting date: " + startDate.getTime() + "\nEnding Date: " + endingDate.getTime() + "\nState: " + this.state.toString();

    }
    
    @Override
    public boolean sameAs(Object other) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public boolean hasThisDay(Calendar date) {
        return true;
    }

    public Designation designation() {
        return this.name;
    }
    


//    public List<Meal> meals(){
//        return this.mealList;
//    }
}
