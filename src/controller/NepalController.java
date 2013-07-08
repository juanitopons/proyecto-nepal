/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import view.NepalMenu;
import view.NepalView;
import view.PacienteView;
import java.awt.Component;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.text.View;

import properties.MyProperties;

/**
 *
 * @author juanitopons
 */
public class NepalController {
    private Component c;
    private NepalView v;
    private NepalMenu menu;
    
    public NepalController(NepalView view) {
    	v = view;
        view.setMenuActionListener(new MenuActionListener());
    }
    
    class MenuActionListener implements ActionListener {
    	MyProperties prop = new MyProperties();
        @Override
        public void actionPerformed(ActionEvent event) {
       
            String key  = event.getActionCommand();
            switch (key) {
                case "menuExportar":
                    System.out.println("MenuActionListener: Accion '" + key + "' no implementada.");
                    break;
                case "menuExit":
                    System.exit(0);
                    break;
                case "menuPacientes":
                	JDialog pacienteView = new PacienteView(v, prop);
                	pacienteView.setVisible(true);
                    break;
                case "spanish":
                	prop.setLanguage("spanish");
                	prop.init();
                	menu = v.getMenu();
                	// We change menu language
                	menu.cambiarIdioma(prop);
                	System.out.println("Idioma seleccionado: Español");
                	break;
                case "english":
                	prop.setLanguage("english");
                	prop.init();
                	menu = v.getMenu();
                	// We change menu language
                	menu.cambiarIdioma(prop);
                	System.out.println("Idioma seleccionado: Inglés");
                	break;
                default:
                    System.out.println("MenuActionListener: Acción '" + key + "' desconocida.");
                    break;
            }
        }
    }
}
