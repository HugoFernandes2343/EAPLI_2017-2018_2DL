/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.application.meal;

import eapli.ecafeteria.domain.meals.Meal;
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
//    private final RatingRepository rr = PersistenceContext.repositories().ratings();
    
    public Iterable<Meal> allMeals(){
        return mr.findAll();
    }
    
//    public Iterable<Rating> allRatings(Meal m){
//        return mrs.mealsRatingList(m);
//        
//    }
//    
//    public void answerToComment(Rating r, String answer){
//        Comment c = rating.comment(answer);
//        c.answer(answer);
//        rr.save(r);
//    }
}
