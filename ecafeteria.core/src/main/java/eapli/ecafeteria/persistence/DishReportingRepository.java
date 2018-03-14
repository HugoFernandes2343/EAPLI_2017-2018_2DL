package eapli.ecafeteria.persistence;

import eapli.ecafeteria.domain.dishes.reporting.DishesPerDishType;
import eapli.framework.persistence.repositories.ReportingRepository;

/**
 *
 * @author PAG
 */
public interface DishReportingRepository extends ReportingRepository {

    public Iterable<DishesPerDishType> dishesPerDishType();
}
