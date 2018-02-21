package eapli.ecafeteria.domain.kitchen;

import eapli.framework.domain.ddd.AggregateRoot;
import eapli.framework.util.Strings;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Version;

/**
 * @FIXME javadoc
 */
@Entity
public class Material implements AggregateRoot<String>, Serializable {

    private static final long serialVersionUID = 1L;

    // ORM primary key
    @Id
    @GeneratedValue
    private Long pk;
    @Version
    private Long version;

    // business ID
    @Column(unique = true)
    private String acronym;
    private String description;

    protected Material() {
        // for ORM
    }

    public Material(String name, String description) {
        if (Strings.isNullOrEmpty(name)) {
            throw new IllegalArgumentException();
        }
        this.acronym = name;
        this.description = description;
    }

    public String description() {
        return this.description;
    }

    public void changeDescriptionTo(String newDescription) {
        this.description = newDescription;
    }

    @Override
    public String id() {
        return this.acronym;
    }

    @Override
    public boolean is(String id) {
        return id.equalsIgnoreCase(this.acronym);
    }

    @Override
    public boolean sameAs(Object other) {
        // FIXME implement this method
        return false;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Material)) {
            return false;
        }

        final Material other = (Material) o;
        return id().equals(other.id());
    }

    @Override
    public int hashCode() {
        return this.acronym.hashCode();
    }
}
