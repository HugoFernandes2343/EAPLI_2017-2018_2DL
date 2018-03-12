package eapli.framework.persistence.repositories.impl.inmemory;

import java.io.Serializable;

import eapli.framework.domain.ddd.DomainEntity;

public abstract class InMemoryRepositoryOfDomainEntity<T extends DomainEntity<K>, K extends Serializable>
	extends InMemoryRepository<T, K> {

    public InMemoryRepositoryOfDomainEntity() {
	super();
    }

    @Override
    protected K newKeyFor(T e) {
	// just returning the id as by definition no domain entity can be
	// created without its identity already set
	return e.id();
    }
}