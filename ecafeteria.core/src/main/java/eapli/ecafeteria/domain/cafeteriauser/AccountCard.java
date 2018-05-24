package eapli.ecafeteria.domain.cafeteriauser;

import eapli.framework.domain.ddd.AggregateRoot;
import java.io.Serializable;
import javax.persistence.*;

/**
 *
 * @author 1161110 & 1161213
 */
@Entity
public class AccountCard implements AggregateRoot<MecanographicNumber>, Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    private Long menuID;

    @Version
    private Long version;

    private MecanographicNumber mecanographicNumber;

    @Embedded
    private BalanceLimit balanceLimit;

    protected AccountCard() {
        //for ORM
    }

    /**
     * New Account Card Constructor and for ORM use
     *
     * @param mecanographicNumber
     */
    public AccountCard(MecanographicNumber mecanographicNumber) {
        this.balanceLimit = new BalanceLimit();
        this.mecanographicNumber = mecanographicNumber;
    }

    @Override
    public boolean sameAs(Object other) {
        if (!(other instanceof AccountCard)) {
            return false;
        }

        final AccountCard that = (AccountCard) other;
        if (this == that) {
            return true;
        }
        return (this.mecanographicNumber.equals(that.mecanographicNumber)
                && this.balanceLimit.equals(that.balanceLimit));
    }

    @Override
    public MecanographicNumber id() {
        return this.mecanographicNumber;
    }

    public boolean changeBalanceLimit(double value) {
        return this.balanceLimit.defineLimit(value);
    }

    public BalanceLimit balanceLimit() {
        return this.balanceLimit;
    }

    @Override
    public String toString() {
        return "Balance Limit: " + balanceLimit + '\n';
    }

}
