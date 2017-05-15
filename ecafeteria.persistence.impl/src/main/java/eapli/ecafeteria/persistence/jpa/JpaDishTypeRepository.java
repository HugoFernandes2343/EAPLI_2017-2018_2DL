package eapli.ecafeteria.persistence.jpa;

import eapli.ecafeteria.domain.meals.DishType;
import eapli.ecafeteria.persistence.DishTypeRepository;

/**
 * Created by MCN on 29/03/2016.
 */
class JpaDishTypeRepository extends CafeteriaJpaRepositoryBase<DishType, Long> implements DishTypeRepository {

    @Override
    public Iterable<DishType> activeDishTypes() {
        return match("e.active=true");
    }

    @Override
    public DishType findByAcronym(String acronym) {
        return matchOne("e.acronym=:acronym", "acronym", acronym);
    }
}
