package eapli.ecafeteria.domain.authz;

import eapli.framework.domain.EmailAddress;
import eapli.framework.domain.ddd.Factory;
import eapli.util.Strings;
import java.util.Calendar;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * A factory for User entities.
 *
 * This class demonstrates the use of the factory (DDD) pattern using a fluent
 * interface. it acts as a Builder (GoF).
 */
public class SystemUserBuilder implements Factory<SystemUser> {

    private Username username;
    private Password password;
    private String firstName;
    private String lastName;
    private Name name;
    private EmailAddress email;
    private final RoleSet roles;
    private Calendar createdOn;

    public SystemUserBuilder() {
        this.roles = new RoleSet();
    }

    public SystemUserBuilder withUsername(String username) {
        this.username = new Username(username);
        return this;
    }

    public SystemUserBuilder withUsername(Username username) {
        this.username = username;
        return this;
    }

    public SystemUserBuilder withPassword(String password) {
        this.password = new Password(password);
        return this;
    }

    public SystemUserBuilder withPassword(Password password) {
        this.password = password;
        return this;
    }

    public SystemUserBuilder withFirstName(String firstName) {
        this.firstName = firstName;
        if (!Strings.isNullOrEmpty(this.lastName)) {
            this.name = new Name(this.firstName, this.lastName);
        }
        return this;
    }

    public SystemUserBuilder withLastName(String lastName) {
        this.lastName = lastName;
        if (!Strings.isNullOrEmpty(this.firstName)) {
            this.name = new Name(this.firstName, this.lastName);
        }
        return this;
    }

    public SystemUserBuilder withName(Name name) {
        this.name = name;
        return this;
    }

    public SystemUserBuilder withEmail(String email) {
        this.email = EmailAddress.valueOf(email);
        return this;
    }

    public SystemUserBuilder withEmail(EmailAddress email) {
        this.email = email;
        return this;
    }

    public SystemUserBuilder withRole(RoleType role) {
        this.roles.add(new Role(role));
        return this;
    }

    public SystemUserBuilder withRole(Role role) {
        this.roles.add(role);
        return this;
    }

    public SystemUserBuilder withCreatedOn(Calendar createdOn) {
        this.createdOn = createdOn;
        return this;
    }

    @Override
    public SystemUser build() {
        // since the factory knows that all the parts are needed it could throw
        // an exception. however, we will leave that to the constructor
        if (this.createdOn != null) {
            return new SystemUser(this.username, this.password, this.name, this.email, this.roles, this.createdOn);
        } else {
            return new SystemUser(this.username, this.password, this.name, this.email, this.roles);
        }
    }

    public SystemUserBuilder withRoles(Set<RoleType> someRoles) {
        List<Role> theRoles;
        if (this.createdOn == null) {
            theRoles = someRoles.stream().map(rt -> new Role(rt)).collect(Collectors.toList());
        } else {
            theRoles = someRoles.stream().map(rt -> new Role(rt, this.createdOn)).collect(Collectors.toList());
        }
        this.roles.addAll(theRoles);
        return this;
    }

    public SystemUserBuilder withRoles(RoleSet roles) {
        this.roles.addAll(roles);
        return this;
    }
}
