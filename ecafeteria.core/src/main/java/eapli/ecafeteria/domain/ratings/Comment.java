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
public class Comment implements ValueObject, Serializable{
    
    private String comment;
    private Reply reply;

    public Comment() {
    }

    public Comment(String comment) {
        this.comment = comment;
        this.reply = null;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
    
    public String reply(){
        if (this.reply != null){
            return this.reply.toString();
        }
        return null;
    }
    
    public void answerReply(String answer){
        this.reply = new Reply(answer);
    }
       
}
