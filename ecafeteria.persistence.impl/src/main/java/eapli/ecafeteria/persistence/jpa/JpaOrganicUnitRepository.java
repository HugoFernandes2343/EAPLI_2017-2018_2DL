/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates and open the template
 * in the editor.
 */
package eapli.ecafeteria.persistence.jpa;

import eapli.ecafeteria.domain.cafeteria.OrganicUnit;
import eapli.ecafeteria.persistence.OrganicUnitRepository;

/**
 *
 * @author arocha
 */
public class JpaOrganicUnitRepository extends CafeteriaJpaRepositoryBase<OrganicUnit, Long>
        implements OrganicUnitRepository {

    @Override
    public OrganicUnit findByAcronym(String acronym) {
        return matchOne("e.acronym=:a", "a", acronym);
    }
}
