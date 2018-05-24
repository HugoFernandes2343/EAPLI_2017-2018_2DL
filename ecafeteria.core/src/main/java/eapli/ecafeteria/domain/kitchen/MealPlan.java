/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.domain.kitchen;

import static eapli.ecafeteria.domain.kitchen.MealPlanState.IN_PROGRESS;
import static eapli.ecafeteria.domain.kitchen.MealPlanState.PUBLISHED;
import eapli.ecafeteria.domain.menu.Menu;
import eapli.ecafeteria.persistence.MealPlanRepository;
import eapli.framework.domain.Designation;
import eapli.framework.domain.ddd.AggregateRoot;
import eapli.framework.util.DateTime;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Version;

/**
 *
 * @author Paulo Jorge
 */
@Entity
public class MealPlan implements AggregateRoot<Designation>, Serializable {

    @Version
    private Long version;

    @EmbeddedId
    private Designation name;

    @Enumerated(EnumType.STRING)
    private MealPlanState mealPlanState;

    @OneToOne
    private Menu menu;
    
    @OneToMany(cascade=CascadeType.ALL)
    private List<MealPlanItem> lmpi;

    public List<MealPlanItem> getLmpi() {
        return lmpi;
    }

    protected MealPlan() {

    }

    public MealPlan(Menu menu, Designation name) {
        this.mealPlanState = IN_PROGRESS;
        this.menu = menu;
        this.name = name;
        this.lmpi = new ArrayList<>();
    }

    public void closeMealPlan() {
        this.mealPlanState = PUBLISHED;
    }

    public boolean addItemToList(MealPlanItem mpi) {
        return this.lmpi.add(mpi);
    }

    public boolean isInTime(MealPlanRepository mpir) {

        List<MealPlanItem> lista = mpir.getMealPlanItemsFromMealPlan(this);
        for (MealPlanItem mpi : lista) {
            if (mpi.getDishQuantity() < 0) {
                return false;
            }
        }
        if (this.menu.startDate().getTimeInMillis() - DateTime.now().getTimeInMillis() <= 172800000) {
            return false;
        }
        return true;
    }

    @Override
    public Designation id() {
        return this.name;
    }

    @Override
    public String toString() {
        return "ID=" + this.name + "\nMeal Plan State=" + mealPlanState + "\nMenu=" + menu.toString();
    }

    @Override
    public boolean sameAs(Object other) {
        if (!(other instanceof MealPlan)) {
            return false;
        }

        final MealPlan that = (MealPlan) other;
        if (this == that) {
            return true;
        }

        return id().equals(that.id()) && menu.equals(that.menu)
                && mealPlanState.equals(that.mealPlanState);
    }

    @Override
    public boolean is(Designation otherId) {
        return id().equals(otherId);
    }

}

