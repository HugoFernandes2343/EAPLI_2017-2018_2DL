/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates and open the template
 * in the editor.
 */
package eapli.ecafeteria.persistence;

import eapli.ecafeteria.domain.cafeteria.OrganicUnit;
import eapli.framework.persistence.repositories.DataRepository;

/**
 *
 * @author arocha
 */
public interface OrganicUnitRepository extends DataRepository<OrganicUnit, Long> {

    OrganicUnit findByAcronym(String acronym);
}
