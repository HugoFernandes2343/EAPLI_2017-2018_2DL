/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.domain.menu;

import eapli.ecafeteria.domain.dishes.Dish;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Objects;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.Version;

/**
 *
 * @author David
 */
public class Menu {

    private static final long serialVersionUID = 1L;

    // ORM primary key
    @Id
    @GeneratedValue
    private Long menuID;
    @Version
    private Long version;

    @Column(unique = true)
    @Temporal(javax.persistence.TemporalType.DATE)
    private Calendar startDate;
    @Column(unique = true)
    @Temporal(javax.persistence.TemporalType.DATE)
    private Calendar endingDate;

    @Enumerated(EnumType.STRING)
    private MenuState state;

    @ElementCollection
    @CollectionTable(name = "Menu_Meal")
    @Column(name = "Meal")
    @ManyToOne
    private List<Dish> mealList;

    protected Menu() {
        // for ORM
    }

    public Menu(Calendar startDate, Calendar endingDate) {
        this.startDate = startDate;
        this.endingDate = endingDate;
        this.state = MenuState.WORKING_MENU;
        this.mealList = new ArrayList<>();

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

    public List<Dish> mealList() {
        return this.mealList;
    }

    public boolean addMeal(Dish meal) {
        if (mealList.contains(meal)) {
            return false;
        }
        return mealList.add(meal);
    }

    public boolean removeMeal(Dish meal) {
        if (!mealList.contains(meal)) {
            return false;
        }
        return mealList.remove(meal);
    }

    public boolean containsMeal(Dish m) {
        return mealList.contains(m);
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

    public boolean sameAs(Object other) {
        if (!(other instanceof Menu)) {
            return false;
        }

        final Menu that = (Menu) other;
        if (this == that) {
            return true;
        }

        return id().equals(that.id());
    }

    public boolean is(Long id) {
        return id().equals(id);
    }

    public Long id() {
        return this.menuID;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }

        final Menu other = (Menu) obj;
        if (!Objects.equals(this.startDate, other.startDate)) {
            return false;
        }

        if (!Objects.equals(this.endingDate, other.endingDate)) {
            return false;
        }
        if (!Objects.equals(this.menuID, other.menuID)) {
            return false;
        }
        if (!Objects.equals(this.version, other.version)) {
            return false;
        }

        return true;
    }

    @Override
    public String toString() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        String str1 = sdf.format(startDate.getTime());
        String str2 = sdf.format(endingDate.getTime());
        return "startDate=" + str1 + ", finishDate=" + str2;
    }

}
