package eapli.ecafeteria.app.backoffice.console.presentation.kitchen;

import eapli.ecafeteria.application.kitchen.ListMealsByLotController;
import eapli.ecafeteria.domain.kitchen.Lot;
import eapli.framework.application.Controller;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.presentation.console.SelectWidget;


import java.util.List;

public class ListMealsByLotUI extends AbstractUI {

    private final ListMealsByLotController theController = new ListMealsByLotController();
    protected Controller controller() {
        return this.theController;
    }

    @Override
    protected boolean doShow() {
        List<Lot> lmp = (List) theController.findLots();

        if (lmp.isEmpty()) {
            System.out.println("Don´t exist Lots");
        } else {

            SelectWidget<Lot> lotSelector = new SelectWidget<>("Lots:", theController.findLots(), new LotPrinter());
            lotSelector.show();
            final Lot lot=lotSelector.selectedElement();

            theController.showMealsUsed(lot);
        }
        return true;
    }

    @Override
    public String headline() {
        return "Meals Used by one Lot";
    }
}

