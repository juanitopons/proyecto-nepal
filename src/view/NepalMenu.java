package view;

import java.awt.*;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JRadioButtonMenuItem;

import properties.MyProperties;

/**
 *
 * @author juanitopons
 */
public class NepalMenu extends JMenuBar {
    private JMenu file, recursos, explorer, tools, idiomas;

    private JMenuItem [] fileItem = new JMenuItem[2];
    private String [] fileItemName = { "Exportar BBDD...", "Salir" };
    private String [] fileItemAction = { "menuExportar", "menuExit" };
    
    private JMenuItem [] tablaItem = new JMenuItem[4];
    private String [] tablaItemName = { "Niños", "Centros", "Alergias", "Vacunas" };
    private String [] tablaItemAction = { "menuPacientes", "menuCentros", "menuAlergias", "menuVacunas" };

    
    private JRadioButtonMenuItem [] languageRadioButton = new JRadioButtonMenuItem[2];
    private String [] languageButtonName = { "Español", "Inglés" };
    private String [] languageButtonAction = { "spanish", "english" };
    private String [] languageButtonIcon = { "src/resources/sp.png", "src/resources/en.png" };
    
    public void cambiarIdioma(MyProperties prop) {
    	//.setText a todo lo que deba hacerse setText (mirar Strings)
    	file.setText(prop.getProperty("fichero"));
    	recursos.setText(prop.getProperty("recursos"));
    	//quitar explorer
    	explorer.setText(prop.getProperty("explorar"));
    	//
    	tools.setText(prop.getProperty("herramientas"));
    	idiomas.setText(prop.getProperty("idiomas"));
    	
    	fileItem[0].setText(prop.getProperty("exportardb"));
    	fileItem[1].setText(prop.getProperty("salir"));
    	
    	tablaItem[0].setText(prop.getProperty("pacientes"));
    	tablaItem[1].setText(prop.getProperty("centros"));
    	tablaItem[2].setText(prop.getProperty("alergias"));
    	tablaItem[3].setText(prop.getProperty("vacunas"));
    	
    	
    	languageRadioButton[0].setText(prop.getProperty("espanol"));
    	languageRadioButton[1].setText(prop.getProperty("ingles"));
    }
    
    public NepalMenu() throws HeadlessException {
        Font menuFont = new Font("Courier", Font.BOLD, 12);
        
        /******************************************
         * Menu Fichero
         */
        file = new JMenu("Fichero");
        file.setFont(menuFont);
        
        // Para cada una de las entradas del menu file...
        for (int i = 0; i < fileItem.length; i++) {
            fileItem[i] = new JMenuItem(fileItemName[i]);
            fileItem[i].setActionCommand(fileItemAction[i]);
            file.add(fileItem[i]);
        }
        
        // Anadimos el menu a la barra de menu
        add(file);
        
       /*******************************************
        * Menu Consultar
        */
        recursos = new JMenu("Recursos");
        recursos.setFont(menuFont);
        
        // Para cada una de las entradas del menu file...
        for (int i = 0; i < tablaItem.length; i++) {
            tablaItem[i] = new JMenuItem(tablaItemName[i]);
            tablaItem[i].setActionCommand(tablaItemAction[i]);
            recursos.add(tablaItem[i]);
        }
        
        // Anadimos el menu a la barra de menu
        add(recursos);
        
        /*******************************************
        * Menu Tools
        */
        tools = new JMenu("Herramientas");
        tools.setFont(menuFont);
        
        idiomas = new JMenu("Idioma");
        idiomas.setFont(menuFont);
        tools.add(idiomas);
        
        ButtonGroup bg = new ButtonGroup();
        
        // Para cada una de las entradas del menu file...
            //toolsItem[0].setActionCommand(toolsItemAction[0]);
        	Icon icon;
            for (int i = 0; i < languageRadioButton.length; i++) {
            	icon = new ImageIcon(languageButtonIcon[i]);
	            languageRadioButton[i] = new JRadioButtonMenuItem(icon);
	            languageRadioButton[i].setText(languageButtonName[i]);
	            languageRadioButton[i].setHorizontalTextPosition(JMenuItem.RIGHT);
	            languageRadioButton[i].setActionCommand(languageButtonAction[i]);
	            languageRadioButton[i].setBorder(BorderFactory.createEmptyBorder(6, 6, 6, 6));
	            bg.add(languageRadioButton[i]);
            }
            languageRadioButton[0].setSelected(true);
            
            for (int i = 0; i < languageRadioButton.length; i++) {
            idiomas.add(languageRadioButton[i]);
            }

        
        // Anadimos el menu a la barra de menu
        add(tools);
    }

    public void setMenuActionListener(ActionListener menuActionListener) {
        for (JMenuItem mi : tablaItem) {
            mi.addActionListener(menuActionListener);
        }
        for (JMenuItem mi : fileItem) {
            mi.addActionListener(menuActionListener);
        }
        for (JRadioButtonMenuItem mi : languageRadioButton) {
            mi.addActionListener(menuActionListener);
        }
    }

}
