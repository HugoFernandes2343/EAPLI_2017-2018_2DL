package eapli.ecafeteria.persistence;

import eapli.ecafeteria.domain.complaints.Complaint;
import eapli.framework.persistence.repositories.DataRepository;

/**
 * @author <1160777@isep.ipp.pt>Marco Carneiro</1160777@isep.ipp.pt>
 */
public interface ComplaintRepository extends DataRepository<Complaint,Long> {

    /**
     * [PH]Interface in the event of needing to fetch Complaints
     */

}
