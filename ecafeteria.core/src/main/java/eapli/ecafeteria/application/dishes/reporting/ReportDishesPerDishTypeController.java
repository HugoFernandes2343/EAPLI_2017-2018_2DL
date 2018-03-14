/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.application.dishes.reporting;

import eapli.ecafeteria.domain.dishes.reporting.DishesPerDishType;
import eapli.ecafeteria.persistence.DishReportingRepository;
import eapli.ecafeteria.persistence.PersistenceContext;
import eapli.framework.application.Controller;

/**
 *
 * @author pgsou_000
 */
public class ReportDishesPerDishTypeController implements Controller {

    private final DishReportingRepository repo = PersistenceContext.repositories().dishReporting();

    public Iterable<DishesPerDishType> all() {
        return repo.dishesPerDishType();
    }
}
