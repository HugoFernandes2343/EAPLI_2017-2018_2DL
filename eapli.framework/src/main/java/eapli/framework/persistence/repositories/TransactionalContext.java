/**
 *
 */
package eapli.framework.persistence.repositories;

/**
 * an abstraction to the actual transaction management mechanism.
 *
 * @author pgsou_000
 *
 */
public interface TransactionalContext {
    /**
     * start a transaction in the persistence store
     */
    void beginTransaction();

    /**
     * commit changes to the persistence store
     */
    void commit();

    /**
     * rollback all changes from the beginning of the transaction
     */
    void rollback();

    /**
     * close the connection to the persistence store and rollback any pending
     * transaction
     */
    void close();
}
