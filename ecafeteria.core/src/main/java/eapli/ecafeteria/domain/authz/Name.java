package eapli.ecafeteria.domain.authz;

import eapli.framework.domain.ddd.ValueObject;
import eapli.util.Strings;
import java.io.Serializable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.persistence.Embeddable;

/**
 * Person name
 *
 */
@Embeddable
public class Name implements ValueObject, Serializable {

    private static final long serialVersionUID = 1L;
    public static final Pattern VALID_NAME_REGEX = Pattern.compile("^[A-Z]+$", Pattern.CASE_INSENSITIVE);
    private String firstName;
    private String lastName;

    public Name(String firstName, String lastName) {
        if (Strings.isNullOrEmpty(firstName) || Strings.isNullOrEmpty(lastName)) {
            throw new IllegalStateException("first name and last name should neither be null nor empty");
        }

        Matcher matcher = VALID_NAME_REGEX.matcher(firstName);
        if (!matcher.find()) {
            throw new IllegalStateException("Invalid First Name");
        }

        matcher = VALID_NAME_REGEX.matcher(lastName);
        if (!matcher.find()) {
            throw new IllegalStateException("Invalid Last Name");
        }

        this.firstName = firstName;
        this.lastName = lastName;
    }

    protected Name() {
    }

    public String firstName() {
        return this.firstName;
    }

    public String lastName() {
        return this.lastName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Name)) {
            return false;
        }

        final Name name = (Name) o;

        if (!this.firstName.equals(name.firstName)) {
            return false;
        }
        return this.lastName.equals(name.lastName);

    }

    @Override
    public int hashCode() {
        int result = this.firstName.hashCode();
        result = 31 * result + this.lastName.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return this.firstName + " " + this.lastName;
    }
}
