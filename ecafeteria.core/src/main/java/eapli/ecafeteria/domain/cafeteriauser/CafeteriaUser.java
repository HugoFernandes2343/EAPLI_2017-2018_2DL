package eapli.ecafeteria.domain.cafeteriauser;

import eapli.ecafeteria.domain.allergens.Allergen;
import eapli.ecafeteria.domain.authz.SystemUser;
import eapli.framework.domain.ddd.AggregateRoot;
import java.io.Serializable;
import javax.persistence.*;

/**
 * A Cafeteria User.
 *
 * This class represents cafeteria users. It follows a DDD approach where User
 * is the root entity of the Cafeteria User Aggregate and all of its properties
 * are instances of value objects. A Cafeteria User references a System User
 *
 * This approach may seem a little more complex than just having String or
 * native type attributes but provides for real semantic of the domain and
 * follows the Single Responsibility Pattern
 *
 * @author Jorge Santos ajs@isep.ipp.pt
 *
 */
@Entity
public class CafeteriaUser implements AggregateRoot<MecanographicNumber>, Serializable {

    private static final long serialVersionUID = 1L;

    @Version
    private Long version;

    @EmbeddedId
    private MecanographicNumber mecanographicNumber;
    
    @OneToOne(cascade = CascadeType.ALL)
    private AccountCard accountcard;

    /**
     * cascade = CascadeType.NONE as the systemUser is part of another aggregate
     */
    @OneToOne()
    private SystemUser systemUser;

    //@OneToOne(cascade = CascadeType.ALL)
    private NutritionalProfile nutritionalProfile;
    
    public CafeteriaUser(SystemUser user, MecanographicNumber mecanographicNumber) {
        if (mecanographicNumber == null || user == null) {
            throw new IllegalArgumentException();
        }
        this.systemUser = user;
        this.mecanographicNumber = mecanographicNumber;
        this.accountcard = new AccountCard(mecanographicNumber);
        nutritionalProfile = new NutritionalProfile();
    }

    protected CafeteriaUser() {
        // for ORM only
    }

    public SystemUser user() {
        return this.systemUser;
    }
    
    public AccountCard accountCard(){
        return this.accountcard;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof CafeteriaUser)) {
            return false;
        }

        final CafeteriaUser other = (CafeteriaUser) o;
        return this.mecanographicNumber.equals(other.mecanographicNumber);
    }

    @Override
    public int hashCode() {
        return this.mecanographicNumber.hashCode();
    }

    @Override
    public boolean sameAs(Object other) {
        if (!(other instanceof CafeteriaUser)) {
            return false;
        }

        final CafeteriaUser that = (CafeteriaUser) other;
        if (this == that) {
            return true;
        }
        return (this.mecanographicNumber.equals(that.mecanographicNumber)
                && this.systemUser.sameAs(that.systemUser));
    }

    public MecanographicNumber mecanographicNumber() {
        return id();
    }

    @Override
    public MecanographicNumber id() {
        return this.mecanographicNumber;
    }

    public boolean updateNutritionalProfileFieldValue (NutritionalProfileField field){
        return nutritionalProfile.updateFieldValue(field, field.value());
    }
    
    
    @Override
    public String toString() {
        return "CafeteriaUser mecanographicNumber " + mecanographicNumber;
    }

    /**
     * 
     * @return A deep clone, to ensure no accidental modifications. Explicitly use "updateNutritionalProfileFieldValue" instead
     */
    public NutritionalProfile getNutritionalProfile() {
        return nutritionalProfile.clone();
    }
    
    public boolean addAllergen(Allergen newAllergen){
        return nutritionalProfile.addAllergen(newAllergen);
    }
    
    public boolean removeAllergen (Allergen allergen){
        return nutritionalProfile.removeAllergen(allergen);
    }
}
