package eapli.ecafeteria.persistence.jpa;

import eapli.ecafeteria.domain.complaints.Complaint;
import eapli.ecafeteria.persistence.ComplaintRepository;

/**
 * @author <1160777@isep.ipp.pt>Marco Carneiro</1160777@isep.ipp.pt>
 */
public class JpaComplaintRepository extends CafeteriaJpaRepositoryBase<Complaint, Long> implements ComplaintRepository {

    /**
     * [PH]JPA in case of needing to fetch data
     */
}
