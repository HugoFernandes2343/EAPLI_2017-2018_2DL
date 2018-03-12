/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates and open the template
 * in the editor.
 */
package eapli.framework.domain.ddd;

import java.util.Calendar;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import eapli.framework.util.DateTime;

/**
 *
 * @author Paulo Gandra Sousa
 */
public abstract class DomainEventBase implements DomainEvent {

    private static final long serialVersionUID = 1L;

    @Temporal(TemporalType.DATE)
    private final Calendar occuredAt;
    @Temporal(TemporalType.DATE)
    private final Calendar registeredAt;

    public DomainEventBase() {
        occuredAt = registeredAt = DateTime.now();
    }

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
