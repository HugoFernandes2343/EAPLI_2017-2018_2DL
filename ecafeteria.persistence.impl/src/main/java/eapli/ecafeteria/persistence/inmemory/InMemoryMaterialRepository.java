package eapli.ecafeteria.persistence.inmemory;

import eapli.ecafeteria.domain.kitchen.Material;
import eapli.ecafeteria.persistence.MaterialRepository;
import eapli.framework.persistence.repositories.impl.inmemory.InMemoryRepositoryWithLongPK;

/**
 * Created by MCN on 29/03/2016.
 */
public class InMemoryMaterialRepository extends InMemoryRepositoryWithLongPK<Material> implements MaterialRepository {

    @Override
    public Material findByAcronym(String acronym) {
        return matchOne(e -> e.id().equals(acronym));
    }
}
