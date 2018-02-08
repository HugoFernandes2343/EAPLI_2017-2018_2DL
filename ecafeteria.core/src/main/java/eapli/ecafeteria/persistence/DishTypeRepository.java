package eapli.ecafeteria.persistence;

import java.util.Optional;

import eapli.ecafeteria.domain.meals.DishType;
import eapli.framework.persistence.repositories.DataRepository;

/**
 * the repository for Dish Types.
 *
 */
public interface DishTypeRepository extends DataRepository<DishType, Long> {

	/**
	 * returns the active dish types
	 *
	 * @return
	 */
	Iterable<DishType> activeDishTypes();

	Optional<DishType> findByAcronym(String acronym);
}
