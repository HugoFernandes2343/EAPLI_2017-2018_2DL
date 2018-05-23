/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.app.backoffice.console.presentation.menu;

import eapli.ecafeteria.application.menu.CopyMenuController;
import eapli.ecafeteria.application.menu.ListMenuService;
import eapli.ecafeteria.domain.meals.Meal;
import eapli.ecafeteria.domain.menu.Menu;
import eapli.framework.domain.Designation;
import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.presentation.console.SelectWidget;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author David Santiago <1161109@isep.ipp.pt>
 */
public class CopyMenuUI extends AbstractUI {
    
    CopyMenuController cmc = new CopyMenuController();
    ListMenuService lms = new ListMenuService();
    
    DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
    
    
    private Scanner leitor;

    @Override
    protected boolean doShow() {
        
        System.out.println(headline() + "\n\n");
        
        List<Menu> lm = (List<Menu>) lms.allMenus();
        
        System.out.println(lm.size());
        
        
        if (lm.isEmpty()) {
            System.out.println("Não existem Menus disponiveis.");
            return false;
        } else {
            
            
            
            
            System.out.println("Insira o nome do novo Menu");
            String nome = inserirNome();
        
            System.out.println("Insira a data de inicio do novo Menu");
            Date data = inserirData();
            
            Calendar cal = Calendar.getInstance();
            cal.setTime(data);

            
            

            SelectWidget<Menu> mchooser = new SelectWidget("\n\nMenus available to copy:\n", lm);
            mchooser.show();
            
            try {
                cmc.CopyMenu(mchooser.selectedElement(), cal, Designation.valueOf(nome));
            } catch (DataConcurrencyException ex) {
                Logger.getLogger(CopyMenuUI.class.getName()).log(Level.SEVERE, null, ex);
            } catch (DataIntegrityViolationException ex) {
                Logger.getLogger(CopyMenuUI.class.getName()).log(Level.SEVERE, null, ex);
            }
                
               



   

            }



        return false;
        
    }
    
    
    
    
    
    
    
    
    
    @Override
    public String headline() {
        return "Copy Menu";
    }
    
    
    
    
    
    
    private Date inserirData() {
        Date aux = null;
        do {
            leitor = new Scanner(System.in);
            System.out.println("Digite uma data para o fim do Menu no seguinte formato --> dd/MM/yyyy: ");
            String date = leitor.nextLine();

            try {

                aux = df.parse(date);
                
                
            } catch (ParseException ex) {
                System.out.println("Formato INVÁLIDO");
            }
        } while (aux == null);
        
        return aux;
    }
    
    public String inserirNome() {
        String name;
        do {

            leitor = new Scanner(System.in);
            System.out.println("Nome: ");
            name = leitor.nextLine();

        } while (name.equals(null) || name.equals(""));

        return name;

    }
    
    
}
