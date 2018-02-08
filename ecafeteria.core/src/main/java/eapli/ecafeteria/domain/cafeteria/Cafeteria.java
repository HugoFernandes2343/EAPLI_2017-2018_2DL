/**
 *
 */
package eapli.ecafeteria.domain.cafeteria;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Version;

import eapli.framework.domain.ddd.AggregateRoot;

/**
 * A Cafeteria.
 *
 * @author SOU03408
 *
 */
@Entity
public class Cafeteria implements AggregateRoot<CafeteriaName> {

	@Version
	private Long version;

	@EmbeddedId
	private CafeteriaName name;
	private String description;

	protected Cafeteria() {
		// for ORM
	}

	public Cafeteria(String name, String description) {
		this.name = new CafeteriaName(name);
		this.description=description;
	}

	@Override
	public boolean sameAs(Object other) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean is(CafeteriaName id) {
		return this.name.equals(id);
	}

	@Override
	public CafeteriaName id() {
		return this.name;
	}
}
