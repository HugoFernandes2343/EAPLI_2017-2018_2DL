package eapli.ecafeteria.backoffice.consoleapp.presentation.kitchen;

import eapli.ecafeteria.application.kitchen.ListMaterialController;
import eapli.ecafeteria.domain.kitchen.Material;
import eapli.framework.application.Controller;
import eapli.framework.presentation.console.AbstractListUI;
import eapli.framework.visitor.Visitor;

/**
 * This classes lists dish types by using the AbstractListUI from the framework.
 *
 * Created by MCN on 29/03/2016.
 */
public class ListMaterialUI extends AbstractListUI<Material> {

    private final ListMaterialController theController = new ListMaterialController();

    protected Controller controller() {
        return this.theController;
    }

    @Override
    protected Iterable<Material> listOfElements() {
        return this.theController.all();
    }

    @Override
    protected Visitor<Material> elementPrinter() {
        return new MaterialPrinter();
    }

    @Override
    protected String elementName() {
        return "Material";
    }
}
