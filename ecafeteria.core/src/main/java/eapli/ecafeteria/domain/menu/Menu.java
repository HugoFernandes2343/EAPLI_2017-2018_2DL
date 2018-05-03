/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.domain.menu;

import eapli.ecafeteria.domain.dishes.Dish;
import eapli.ecafeteria.domain.meals.Meal;
import eapli.ecafeteria.domain.meals.Meal;
import eapli.ecafeteria.domain.menu.MenuState;
import eapli.framework.domain.Designation;
import eapli.framework.domain.ddd.AggregateRoot;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.Version;

/**
 *
 * @author David Santiago
 */
@Entity
public class Menu implements AggregateRoot<Long>, Serializable {

    private static final long serialVersionUID = 1L;

    // ORM primary key
    @Id
    @GeneratedValue
    private Long menuID;
    @Version
    private Long version;
    
    
 
    private Designation name;

    @Column(unique = true)
    @Temporal(javax.persistence.TemporalType.DATE)
    private Calendar startDate;
    @Column(unique = true)
    @Temporal(javax.persistence.TemporalType.DATE)
    private Calendar endingDate;

    @Enumerated(EnumType.STRING)
    private MenuState state;

    @OneToMany(mappedBy = "menu", cascade = CascadeType.ALL)
    private List<Meal> mealList;

    protected Menu() {
        // for ORM
    }

    public Menu(Calendar startingDate, Calendar endingDate,Designation name) {
        
        if (startingDate==null || endingDate==null || name==null || !startingDate.before(endingDate))
            throw new IllegalArgumentException("Nenhum campo pode ser NULL");
        
        Calendar finishDate = startingDate;
        finishDate.add(Calendar.DAY_OF_MONTH, 7); // adds 7 days to starting day
        
        if (!finishDate.equals(endingDate))
            throw new IllegalArgumentException("As datas têm de distar obrigatoriamente 7 dias");
        
        
        
        this.name= name;
        this.state = MenuState.WORKING_MENU;
        this.mealList = new ArrayList<>();
        this.startDate = startingDate;
        this.endingDate = endingDate;

    }

    public void setID(long menuID) {
        this.menuID = menuID;
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

    public List<Meal> listMeals() {
        return this.mealList;
    }

    public boolean containsMeal(Meal m) {
        return mealList.contains(m);
    }

    public boolean addMeal(Meal meal) {
        if (mealList.contains(meal)) {        
            throw new IllegalArgumentException("Não pode adicionar uma meal já existente ao menu");
        }
        
        //if (meal.date().compareTo(this.startDate)<=0 || meal.date().compareTo(this.endingDate)>=0 ){
            //throw new IllegalArgumentException("a data da meal não se encontra dentro do intervalo de funcionamento do menu");
        //}
        
       
        return mealList.add(meal);

    }

    public boolean removeMeal(Meal meal) {
        if (!mealList.contains(meal)) {
            return false;
        } else {
            return mealList.remove(meal);
        }

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

        return "Menu Starting date " + startDate.getTime() + " Menu Ending Date= " + endingDate.getTime() + " Menu State: " + this.state.toString();

    }

    @Override
    public boolean sameAs(Object other) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public boolean hasThisDay(Calendar date) {
        return true;
    }

}
