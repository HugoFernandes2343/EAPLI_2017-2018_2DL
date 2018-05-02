package eapli.ecafeteria.app.pos.console.presentation;

import eapli.ecafeteria.application.pos.RechargeCardController;
import eapli.ecafeteria.domain.cafeteriauser.CafeteriaUser;
import eapli.framework.application.Controller;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.util.Console;

/**
 *
 * @author 1161110 & 1161213
 */
public class RechargeCardUI extends AbstractUI{
    
    
    private final RechargeCardController theController = new RechargeCardController();
    
    protected Controller controller() {
        return this.theController;
    }

    @Override
    protected boolean doShow() {
        final String mecanographicNumber = Console.readLine("Insert user mecanographic number");
        CafeteriaUser cafeteriaUser;
        cafeteriaUser = theController.findCafeteriaUserByMecNumber(mecanographicNumber);
        cafeteriaUser.toString();
        double value = Console.readDouble("Insert the amount to recharge");
        if(value > 0){
            theController.rechargeCard(cafeteriaUser, value);
        } else {
            System.out.println("Amount error, please enter a valid amount");
        } 
        System.out.println("Cartao Carregado com sucesso!");
        return false;
        
    }

    @Override
    public String headline() {
        return "Recharge Card";
    }
    
}
