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
        try{
            cafeteriaUser = theController.findCafeteriaUserByMecNumber(mecanographicNumber);
            System.out.println(cafeteriaUser.toString());
            double value = Console.readDouble("Insert the amount to recharge");
            if(value > 0){
                String opt = Console.readLine("Confirm operation? (Y/N)");
                if(opt.contains("Y")|opt.equalsIgnoreCase("Y")){
                    theController.rechargeCard(cafeteriaUser, value);
                    System.out.println("Cartao Carregado com sucesso!");
                }
            } else {
                System.out.println("Amount error, please enter a valid amount");
            }      
        }catch(javax.persistence.NoResultException ex){
            System.out.println("CafeteriaUser not found");
        }
        return false;
        
    }

    @Override
    public String headline() {
        return "Recharge Card";
    }
    
}
