/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.domain.kitchen;

import java.io.Serializable;
import javax.persistence.*;


/**
 *
 * @author 1150425
 */
@Entity
@Table(name = "KitchenAlerts")
public class KitchenAlerts implements Serializable {

    @Id
    @GeneratedValue
    private int id;
    private String name;

    @Column(name = "YellowAlert")
    private int yellowAlert;

    @Column(name = "RedAlert")
    private int redAlert;

    /**
     *
     * @param name
     * @param yellowAlert
     * @param redAlert
     */
    public KitchenAlerts(String name, int yellowAlert, int redAlert) {
        this.name = name;
        this.yellowAlert = yellowAlert;
        this.redAlert = redAlert;
    }

    public KitchenAlerts(String name) {
        this.name = name;
    }

    /**
     * Orm Purposes
     * The Alerts will have the default values explicited in the Project's
     * Guideline
     */
    public KitchenAlerts() {
        //
    }

    public int yellowAlertValue() {
        return this.yellowAlert;
    }

    public int redAlertValue() {
        return this.redAlert;

    }

    public String alertName() {
        return this.name;
    }

    /**
     * Method for the Administrator to change the Yellow Alert Value
     *
     * @param yellowAlert
     */
    public void changeYellowAlert(int yellowAlert) {
        if (yellowAlert != this.yellowAlert
                && 0 < yellowAlert && yellowAlert < 100
                && yellowAlert < this.redAlert) {
            this.yellowAlert = yellowAlert;
        } else {
            System.out.println("Invalid Input Value");
        }
    }

    /**
     * Method for the Administrator to change only the red Alert value
     *
     * @param redAlert
     */
    public void changeRedAlert(int redAlert) {
        if (redAlert != this.yellowAlert
                && 0 < redAlert && redAlert < 100
                && this.yellowAlert < redAlert) {
            this.redAlert = redAlert;
        } else {
            System.out.println("Invalid Input Value");
        }
    }

    public String alertValues() {
        return "Currently the " + this.name + " Yellow Alert value is " + this.yellowAlert + "% and the Red Alert value is " + this.redAlert + "%.";
    }

    public int id() {
        return this.id;
    }
    
}
