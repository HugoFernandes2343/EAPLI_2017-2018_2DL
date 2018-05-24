/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.app.backoffice.console.presentation.kitchen;

import eapli.ecafeteria.domain.kitchen.Lot;
import eapli.framework.visitor.Visitor;

/**
 *
 * @author 1161569
 */
public class LotPrinter implements Visitor<Lot> {

    public LotPrinter() {
    }

    @Override
    public void visit(Lot visitee) {
        System.out.println(visitee.toString());
    }
    
}
