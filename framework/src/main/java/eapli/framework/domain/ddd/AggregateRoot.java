/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates and open the template
 * in the editor.
 */
package eapli.framework.domain.ddd;

/**
 * An aggregate root is a Domain-Driven Design pattern for defining scopes of
 * change and "whole-objects". some entities cluster other objects (entities and
 * value objects) which don't make sense to exist outside of that entity (e.g.,
 * OrderLine and Order).
 *
 * the aggregate root is the entity serving as a root for that cluster of
 * objects. these are the only objects that client code can interact directly
 * and that is managed by Repositories. "inside" objects cannot be directly
 * manipulated by client code, instead they need to be manipulated thru the
 * aggregate root.
 *
 * Typical examples are:
 * <ol>
 * <li>Order {OrderLine, Billing Address, Shipping Address}
 * <li>Customer {Home Address}
 * <li>Product
 * </ol>
 *
 * @param <I>
 *            the type of the primary <b>business</b> identity of the entity
 * @author Paulo Gandra Sousa
 */
public interface AggregateRoot<I> extends DomainEntity<I> {

}
