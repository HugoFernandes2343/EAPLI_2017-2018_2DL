/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.domain.kitchen;

import static eapli.ecafeteria.domain.kitchen.MealPlanState.IN_PROGRESS;
import static eapli.ecafeteria.domain.kitchen.MealPlanState.PUBLISHED;
import eapli.ecafeteria.domain.menu.Menu;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

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

    public Long id() {
        return id;
    }

}
