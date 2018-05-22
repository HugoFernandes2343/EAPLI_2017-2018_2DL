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
public class Answer implements ValueObject, Serializable{
    
    private static final long serialVersionUID = 1L; 
    private String answer;

    /**
     * Needed for ORM
     */
    protected Answer() {

    }

    public Answer(String answer) {
        validateAnswer(answer);
        this.answer = answer;
    }
    
    private void validateAnswer(String answer){
        if(Strings.isNullOrEmpty(answer)){
            throw new IllegalArgumentException("The answer to a comment can´t be empty!");
        }
    }
    
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 97 * hash + Objects.hashCode(this.answer);
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
        final Answer other = (Answer) obj;
        if (!Objects.equals(this.answer, other.answer)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Answer to the comment: " + answer;
    }
    
    
}
