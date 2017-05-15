/**
 *
 */
package eapli.ecafeteria.domain.authz;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 * a set of roles.
 *
 * part of the SystemUser aggregate
 *
 * @author Paulo Gandra Sousa
 */
@Entity
class RoleSet implements Set<Role>, Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue
    private Long pk;

    @OneToMany(cascade = CascadeType.ALL)
    private final Set<Role> data = new HashSet<>();

    @Override
    public boolean add(Role arg0) {
        if (arg0 == null) {
            throw new IllegalStateException();
        }
        // FIXME validations are missing, e.g.,
        // no overlap in roles with the same role type?
        return this.data.add(arg0);
    }

    @Override
    public boolean addAll(Collection<? extends Role> arg0) {
        return this.data.addAll(arg0);
    }

    @Override
    public void clear() {
        this.data.clear();
    }

    @Override
    public boolean contains(Object arg0) {
        return this.data.contains(arg0);
    }

    @Override
    public boolean containsAll(Collection<?> arg0) {

        return this.data.containsAll(arg0);
    }

    @Override
    public boolean isEmpty() {
        return this.data.isEmpty();
    }

    @Override
    public Iterator<Role> iterator() {
        return this.data.iterator();
    }

    @Override
    public boolean remove(Object arg0) {
        return this.data.remove(arg0);
    }

    @Override
    public boolean removeAll(Collection<?> arg0) {
        return this.data.removeAll(arg0);
    }

    @Override
    public boolean retainAll(Collection<?> arg0) {
        return this.data.retainAll(arg0);
    }

    @Override
    public int size() {
        return this.data.size();
    }

    @Override
    public Object[] toArray() {
        return this.data.toArray();
    }

    @Override
    public <T> T[] toArray(T[] arg0) {
        return this.data.toArray(arg0);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof RoleSet)) {
            return false;
        }

        final RoleSet roles = (RoleSet) o;

        // we need to perform a deep equals() as we want to compare values and
        // not object instances, so we cannot do: return
        // this.data.equals(roles.data);
        for (final Role r : this.data) {
            boolean found = false;
            for (final Role or : roles.data) {
                if (r.equals(or)) {
                    found = true;
                    break;
                }
            }
            if (!found) {
                return false;
            }
        }
        return true;
    }

    @Override
    public int hashCode() {
        return this.data.hashCode();
    }

    public Collection<RoleType> roleTypes() {
        final List<RoleType> ret = new ArrayList<>();

        //
        // taking advantage of java 8 iterators and lambda expressions to
        // replace the more conventional for each loop
        //
        // for (final Role role : data) {
        // roleTypes.add(role.type());
        // }
        //
        this.data.forEach(role -> ret.add(role.type()));
        return ret;
    }
}
