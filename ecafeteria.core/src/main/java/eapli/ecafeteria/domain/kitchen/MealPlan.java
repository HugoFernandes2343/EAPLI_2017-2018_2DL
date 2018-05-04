/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.domain.kitchen;

import static eapli.ecafeteria.domain.kitchen.MealPlanState.IN_PROGRESS;
import static eapli.ecafeteria.domain.kitchen.MealPlanState.PUBLISHED;
import eapli.ecafeteria.domain.menu.Menu;
import eapli.ecafeteria.persistence.MealPlanItemRepository;
import eapli.ecafeteria.persistence.MealPlanRepository;
import eapli.framework.util.DateTime;
import java.io.Serializable;
import java.util.Calendar;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

/**
 *
 * @author André Santos
 */
@Entity
public class MealPlan<M, Q> implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private Long id;

    @Enumerated(EnumType.STRING)
    private MealPlanState mealPlanState;

    @OneToOne
    private Menu menu;

    public MealPlan(Menu menu) {
        this.mealPlanState = IN_PROGRESS;
        this.menu = menu;
    }

    protected MealPlan() {

    }

    public void closeMealPlan() {
        this.mealPlanState = PUBLISHED;
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
}
