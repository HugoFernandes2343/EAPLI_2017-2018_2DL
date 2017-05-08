/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates and open the template
 * in the editor.
 */
package eapli.ecafeteria.domain.cafeteria;

import eapli.framework.domain.ddd.AggregateRoot;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Version;

/**
 * TODO javadoc
 *
 * @author arocha
 */
@Entity
public class OrganicUnit implements AggregateRoot<String>, Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    private Long pk;
    @Version
    private Long version;

    @Column(unique = true)
    private String acronym;
    private String name;
    private String description;
    private boolean active;

    protected OrganicUnit() {
        // for ORM
    }

    public OrganicUnit(String acronym, String name, String description) {
        if (acronym == null || name == null || description == null || acronym.trim().isEmpty()) {
            throw new IllegalStateException();
        }
        this.acronym = acronym;
        // TODO name and description provably should not be empty
        this.name = name;
        this.description = description;
        this.active = true;
    }

    @Override
    public String id() {
        return this.acronym;
    }

    @Override
    public boolean is(String acronym) {
        return acronym.equalsIgnoreCase(this.acronym);
    }

    public boolean isActive() {
        return this.active;
    }

    public String name() {
        return this.name;
    }

    public String description() {
        return this.description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof OrganicUnit)) {
            return false;
        }

        final OrganicUnit other = (OrganicUnit) o;
        return this.id().equals(other.id());
    }

    @Override
    public int hashCode() {
        return this.acronym.hashCode();
    }

    @Override
    public boolean sameAs(Object other) {
        if (!(other instanceof OrganicUnit)) {
            return false;
        }

        final OrganicUnit that = (OrganicUnit) other;
        if (this == that) {
            return true;
        }

        return this.acronym.equals(that.acronym) && name.equals(that.name) && description.equals(that.description)
                && active == that.active;
    }
}
