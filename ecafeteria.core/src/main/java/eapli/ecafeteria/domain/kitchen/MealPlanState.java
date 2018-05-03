/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.domain.kitchen;

/**
 *
 * @author André Santos
 */
public enum MealPlanState {
    IN_PROGRESS {

        @Override
        public String toString() {
            return "Progress";
        }
    },
    PUBLISHED {

        @Override
        public String toString() {
            return "Published";
        }
    };
}
