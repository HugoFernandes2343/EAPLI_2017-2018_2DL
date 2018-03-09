package eapli.ecafeteria.domain.authz;

import java.io.Serializable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.persistence.Embeddable;

import eapli.framework.domain.ddd.ValueObject;
import eapli.framework.util.Strings;

/**
 * Person name
 *
 */
@Embeddable
public class Name implements ValueObject, Serializable {

    private static final long serialVersionUID = 1L;
    public static final Pattern VALID_NAME_REGEX = Pattern.compile("^[A-Z]+[a-zA-Z ]+$",
            Pattern.CASE_INSENSITIVE);
    private final String firstName;
    private final String lastName;

    public Name(final String firstName, final String lastName) {
        if (Strings.isNullOrEmpty(firstName) || Strings.isNullOrEmpty(lastName)) {
            throw new IllegalArgumentException(
                    "First name and last name should neither be null nor empty");
        }

        Matcher matcher = VALID_NAME_REGEX.matcher(firstName);
        if (!matcher.find()) {
            throw new IllegalArgumentException("Invalid First Name: " + firstName);
        }

        matcher = VALID_NAME_REGEX.matcher(lastName);
        if (!matcher.find()) {
            throw new IllegalArgumentException("Invalid Last Name: " + lastName);
        }

        this.firstName = firstName;
        this.lastName = lastName;
    }

    protected Name() {
        // for ORM only
        firstName = lastName = null;
    }

    public String firstName() {
        return firstName;
    }

    public String lastName() {
        return lastName;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Name)) {
            return false;
        }

        final Name name = (Name) o;

        if (!firstName.equals(name.firstName)) {
            return false;
        }
        return lastName.equals(name.lastName);
    }

    @Override
    public int hashCode() {
        int result = firstName.hashCode();
        result = 31 * result + lastName.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return firstName + " " + lastName;
    }
}
