/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.domain.pos;

import eapli.ecafeteria.domain.meals.Meal;
import eapli.framework.domain.ddd.AggregateRoot;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javax.persistence.*;

/**
 *
 * @author hugod
 */
@Entity
@Table(name ="POSShift")
public class POSShift implements Serializable, AggregateRoot<Long>{
    
    @Id
    @GeneratedValue
    private Long id;
    
    private ArrayList<Meal> meals;

    private Calendar date;
   
    
    public POSShift(Calendar date,List<Meal> meals){
        this.date = date;
        this.meals = (ArrayList<Meal>) meals;
    }
    
    protected POSShift(){
         meals = new ArrayList<>();
    }
    
    public Calendar date(){
        return date;
    }
    
    public ArrayList<Meal> meals(){
        return meals;
    }
    
    @Override
    public boolean sameAs(Object other) {
        if(!(other instanceof POSShift)){
            return false;
        }
        
        final POSShift s = (POSShift) other;
        
        if(!this.id.equals(s.id)){
            return false;
        }
        if(!this.date.equals(s.date)){
            return false;
        }
        return this.meals.equals(s.meals);
    }

    @Override
    public boolean is(Long otherId) {
        return id().equals(otherId);
    }

    @Override
    public Long id() {
       return id;
    }
    
}
