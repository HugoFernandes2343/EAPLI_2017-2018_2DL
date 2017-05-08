package eapli.ecafeteria.persistence;

import eapli.ecafeteria.domain.kitchen.Material;
import eapli.framework.persistence.repositories.DataRepository;

/**
 * the repository for Materials.
 *
 */
public interface MaterialRepository extends DataRepository<Material, Long> {

    Material findByAcronym(String acronym);
}
