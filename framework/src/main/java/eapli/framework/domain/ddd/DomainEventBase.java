/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates and open the template
 * in the editor.
 */
package eapli.framework.domain.ddd;

import java.util.Calendar;

import eapli.util.DateTime;

/**
 *
 * @author Paulo Gandra Sousa
 */
public abstract class DomainEventBase implements DomainEvent {

    private static final long serialVersionUID = 1L;

    private final Calendar occuredAt;
    private final Calendar registeredAt;

    public DomainEventBase(Calendar occuredAt) {
	this.occuredAt = occuredAt;
	registeredAt = DateTime.now();
    }

    @Override
    public Calendar occurredAt() {
	return occuredAt;
    }

    @Override
    public Calendar registeredAt() {
	return registeredAt;
    }
}
