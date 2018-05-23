/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.domain.ratings;

import eapli.framework.domain.ddd.ValueObject;
import eapli.framework.util.Strings;
import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Embeddable;

/**
 *
 * @author João Santiago <1160696@isep.ipp.pt>
 */
@Embeddable
public class Reply implements ValueObject, Serializable{
    
    private static final long serialVersionUID = 1L; 
    private String reply;

    /**
     * Needed for ORM
     */
    protected Reply() {

    }

    public Reply(String reply) {
        validateReply(reply);
        this.reply = reply;
    }
    
    private void validateReply(String answer){
        if(Strings.isNullOrEmpty(answer)){
            throw new IllegalArgumentException("The reply to a comment can´t be empty!");
        }
    }
    
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 97 * hash + Objects.hashCode(this.reply);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Reply other = (Reply) obj;
        if (!Objects.equals(this.reply, other.reply)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Reply to the comment: " + reply;
    }
    
    
}
