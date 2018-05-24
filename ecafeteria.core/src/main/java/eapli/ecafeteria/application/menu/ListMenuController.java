/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.application.menu;

import eapli.ecafeteria.domain.menu.Menu;
import eapli.framework.application.Controller;
import java.util.Date;

/**
 *
 * @author David
 */
public class ListMenuController implements Controller{

    ListMenuService lsc = new ListMenuService();

    private final ListMenuService listMenuS = new ListMenuService();

    public Iterable<Menu> listAll(){
        return lsc.allMenus();
    }


    public Menu listMenu(Date date){

        return lsc.listMenu(date);

    }




}
