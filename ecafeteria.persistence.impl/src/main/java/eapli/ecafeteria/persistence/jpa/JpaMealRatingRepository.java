/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.persistence.jpa;

import eapli.ecafeteria.domain.ratings.MealRating;
import eapli.ecafeteria.persistence.MealRatingRepository;

/**
 *
 * @author Andre Rodrigues <1151136@isep.ipp.pt>
 */
public class JpaMealRatingRepository extends CafeteriaJpaRepositoryBase<MealRating, Long> implements MealRatingRepository{
    
}
