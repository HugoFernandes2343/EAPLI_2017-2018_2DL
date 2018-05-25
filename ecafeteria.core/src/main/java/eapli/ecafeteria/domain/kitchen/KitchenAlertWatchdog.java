/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.domain.kitchen;

import eapli.ecafeteria.domain.meals.Meal;
import java.util.Observable;
import java.util.concurrent.locks.Lock;

/**
 *
 * @author 1150425
 */
public class KitchenAlertWatchdog extends Observable implements Runnable {

    private long millisecondsLeftUntilExpiration;

    private Thread watchdogThread;

    private Lock expirationDateLock;

    private double watchdogExpiration = .5;

    private boolean stopped = false;

    private Meal m;

    public KitchenAlertWatchdog() {

    }

    public void notifyObs(Meal m) {
        notifyObservers();
        this.m = m;
    }

    @Override
    public void run() {
        stopped = false;
        watchdogThread = new Thread(this, "Watchdog");
        watchdogThread.setDaemon(true);
        watchdogThread.start();
    }

    public Meal sendMeal() {
        return this.m;
    }
 
}
