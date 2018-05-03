package eapli.ecafeteria.persistence.jpa;

import eapli.ecafeteria.domain.cafeteriauser.CafeteriaUser;
import eapli.ecafeteria.domain.movement.Movement;
import eapli.ecafeteria.persistence.MovementRepository;
import eapli.framework.domain.money.Money;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

/**
 *
 * @author filip
 */
public class JpaMovementRepository extends CafeteriaJpaRepositoryBase<Movement, Long> implements MovementRepository{

    @Override
    public Iterable<Movement> allCafeteriaUserMovements(CafeteriaUser user) {

        final Query q;
        String where = "e.user_number=:number";
        q = entityManager().createQuery("SELECT e FROM Menu e WHERE " + where, this.entityClass);
        q.setParameter("number", user.id());
        return q.getResultList();
        
//        final TypedQuery query = entityManager().createQuery("Select m from Movement m WHERE m.user_number=:number", Movement.class);
//        query.setParameter("number", user.id());
//        return query.getResultList();
    }

    @Override
    public void addBookingMovement(Money price) {
        if (price == null) {
            throw new IllegalArgumentException();
        }
        EntityManager em = entityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        em.persist(price);
        tx.commit();
        em.close();
    }
        
}
