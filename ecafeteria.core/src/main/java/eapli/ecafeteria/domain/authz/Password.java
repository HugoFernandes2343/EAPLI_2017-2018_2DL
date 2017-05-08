/**
 *
 */
package eapli.ecafeteria.domain.authz;

import eapli.framework.domain.ddd.ValueObject;
import eapli.util.Strings;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * a password of a user.
 *
 * passwords must be at least 6 characters long and have at least one digit and
 * one capital letter
 *
 * TODO passwords should never be stored in plain format
 *
 * @author Paulo Gandra Sousa
 */
@Embeddable
public class Password implements ValueObject, Serializable {

    private static final long serialVersionUID = 1L;
    @Column(name = "pass")
    private String thePassword;

    protected Password() {
        // for ORM only
    }

    public Password(String password) {
        if (!meetsMinimumRequirements(password)) {
            throw new IllegalStateException();
        }
        thePassword = password;
    }

    private boolean meetsMinimumRequirements(String password) {
        // sanity check
        if (Strings.isNullOrEmpty(password)) {
            return false;
        }

        // at least 6 characters long
        if (password.length() < 6) {
            return false;
        }

        // at least one digit
        if (!Strings.containsDigit(password)) {
            return false;
        }

        // at least one capital letter
        if (!Strings.containsCapital(password)) {
            return false;
        }

        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Password)) {
            return false;
        }

        final Password password1 = (Password) o;

        return thePassword.equals(password1.thePassword);

    }

    @Override
    public int hashCode() {
        return thePassword.hashCode();
    }

    /**
     * Check how strong a password is
     *
     * @return how strong a password is
     */
    public PasswordStrength strength() {
        PasswordStrength passwordStrength = PasswordStrength.Weak;
        if (3 > thePassword.length()) {
            passwordStrength = PasswordStrength.Weak;
        }
        return passwordStrength;
        // TODO implement the rest of the method
    }

    @Override
    public String toString() {
        return thePassword;
    }

    public enum PasswordStrength {
        Weak, Good, Excelent,
    }
}
