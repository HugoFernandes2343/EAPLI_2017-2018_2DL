package eapli.ecafeteria.persistence.jpa;

import eapli.ecafeteria.domain.meals.Dish;
import eapli.ecafeteria.persistence.DishRepository;
import eapli.framework.domain.Designation;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Jorge Santos ajs@isep.ipp.pt 02/04/2016
 */
class JpaDishRepository extends CafeteriaJpaRepositoryBase<Dish, Designation> implements DishRepository {

    @Override
    public Dish findByName(Designation name) {
        Map<String, Object> params = new HashMap<>();
        params.put("name", name);
        return matchOne("e.name=:name", params);
    }
}
