/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates and open the template
 * in the editor.
 */
package eapli.ecafeteria.app.backoffice.console.presentation.meals;

import eapli.framework.util.Console;

/**
 *
 * @author Jorge Santos ajs@isep.ipp.pt
 */
public class NutricionalInfoDataWidget {

    private Integer calories;
    private Integer salt;

    public void show() {
        this.calories = Console.readInteger("Calories");
        this.salt = Console.readInteger("Salt");
    }

    public Integer calories() {
        return this.calories;
    }

    public Integer salt() {
        return this.salt;
    }
}
