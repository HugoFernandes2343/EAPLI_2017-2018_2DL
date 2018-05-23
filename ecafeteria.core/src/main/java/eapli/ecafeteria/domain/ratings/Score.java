/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.domain.ratings;

import eapli.framework.domain.ddd.ValueObject;
import java.io.Serializable;
import javax.persistence.Embeddable;

/**
 *
 * @author Andre Rodrigues <1151136@isep.ipp.pt>
 */
@Embeddable

public class Score implements ValueObject, Serializable {

    private static final long serialVersionUID = 1L;

    private int score;

    public Score() {
    }

    public Score(int score) {
        this.score = score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int score() {
        return score;
    }

}
