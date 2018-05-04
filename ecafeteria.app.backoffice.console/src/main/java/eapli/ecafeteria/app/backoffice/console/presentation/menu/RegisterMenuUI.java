/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.app.backoffice.console.presentation.menu;

import eapli.ecafeteria.application.meal.ListMealService;
import eapli.ecafeteria.application.menu.RegisterMenuController;
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
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author David
 */
public class RegisterMenuUI extends AbstractUI {

    private RegisterMenuController rmc = new RegisterMenuController();
    private ListMealService lms = new ListMealService();
    private Scanner leitor;
    DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
    private Date Begin;
    private Date End;

    @Override
    protected boolean doShow() {

        System.out.println(headline() + "\n\n");

        List<Meal> lm = (List<Meal>) lms.allMeals();

        String nome = inserirNome();

        Begin = inserirData();
        End = inserirData();
        
        Calendar calBeg = Calendar.getInstance();
        calBeg.setTime(Begin);
        
        Calendar calEnd = Calendar.getInstance();
        calEnd.setTime(End);
        
        
        
        if (lm.isEmpty()) {
            System.out.println("Não existem refeições disponiveis.");
            return false;
        } else {

            int op = -1;
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            
            try {
                rmc.MenuMaker(calBeg, calEnd, Designation.valueOf(nome));
            } catch (DataConcurrencyException ex) {
                Logger.getLogger(RegisterMenuUI.class.getName()).log(Level.SEVERE, null, ex);
            } catch (DataIntegrityViolationException ex) {
                Logger.getLogger(RegisterMenuUI.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            while (op != 0) {

                SelectWidget<Meal> mchooser = new SelectWidget("\n\nMeals available to publish:\n", lm);
                mchooser.show();
                
                

                rmc.addMeal(mchooser.selectedElement());

                System.out.println("0 para terminar - outro número para continuar");



                try {
                    op = Integer.parseInt(br.readLine());
                } catch (IOException ex) {
                    Logger.getLogger(RegisterMenuUI.class.getName()).log(Level.SEVERE, null, ex);
                }

            }

            try {
                rmc.saveMenu();
            } catch (DataConcurrencyException | DataIntegrityViolationException ex) {
                Logger.getLogger(PublishMenuUI.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

        return false;

    }

    @Override
    public String headline() {
        return "REGISTER MENU";

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



}
