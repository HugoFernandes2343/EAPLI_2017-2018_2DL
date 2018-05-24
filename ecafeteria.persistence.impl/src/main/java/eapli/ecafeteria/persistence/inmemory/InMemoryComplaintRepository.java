package eapli.ecafeteria.persistence.inmemory;

import eapli.ecafeteria.domain.complaints.Complaint;
import eapli.ecafeteria.persistence.ComplaintRepository;
import eapli.framework.persistence.repositories.impl.inmemory.InMemoryRepositoryWithLongPK;

/**
 * @author <1160777@isep.ipp.pt>Marco Carneiro</1160777@isep.ipp.pt>
 */
public class InMemoryComplaintRepository extends InMemoryRepositoryWithLongPK<Complaint> implements ComplaintRepository {

    /**
     * [PH]InMemory in case of needing to fetch data from the memory
     */
}
