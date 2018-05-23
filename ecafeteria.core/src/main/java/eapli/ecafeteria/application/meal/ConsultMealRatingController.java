/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.application.meal;

import eapli.ecafeteria.domain.meals.Meal;
import eapli.ecafeteria.domain.ratings.Comment;
import eapli.ecafeteria.domain.ratings.MealRating;
import eapli.ecafeteria.persistence.MealRepository;
import eapli.ecafeteria.persistence.PersistenceContext;
import eapli.framework.application.Controller;

/**
 *
 * @author João Santiago <1160696@isep.ipp.pt>
 */
public class ConsultMealRatingController implements Controller {
    
    private final MealRepository mr = PersistenceContext.repositories().meals();
//    private final MealRatingService mrs = new MealRatingService();
    private final MealRepository rr = PersistenceContext.repositories().ratings();
    
    public Iterable<Meal> allMeals(){
        return mr.findAll();
    }
    
    public Iterable<MealRating> allRatings(Meal m){
        return mrs.mealsRatingList(m);
        
    }
    
    public void answerToComment(MealRating r, String reply){
        Comment c = r.comment(reply);
        c.answer(reply);
        rr.save(r);
    }
}
