/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.awt.*;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

/**
 *
 * @author diaz
 */
public class NepalView extends JFrame {
    //private JTable tablePanel;
    private NepalMenu menu;
    private JScrollPane panel;
    private JPanel currentPanel;
   
    public NepalView() {

        super("Nepal");
        
        // Menu de la aplicación
        menu = new NepalMenu();
        
        //menu.setMenuActionListener(new MenuActionListener);
        ImageIcon icon = new ImageIcon(getClass().getClassLoader().getResource("resources/nepal_ico.jpg"));
        this.setIconImage(icon.getImage());
        this.setJMenuBar(menu);
        
        Container cp = this.getContentPane();
        cp.setLayout(new BorderLayout());

         /* El panel con la tabla */
        //tablePanel = createTablePanel();
        // Ponemos la tabla dentro de un JScrollPane
        panel = new JScrollPane();
        
        /* Añadimos todos los paneles al Container */
        cp.add(panel,BorderLayout.NORTH);
          
        // Otra forma de decir que si se cierra la ventana finalice la aplicacion      
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(new Dimension(600,600));      
        setVisible(true);           
    }
    
    public JFrame getFrame() {
    	return this;
    }
    
    public NepalMenu getMenu() {
    	return menu;
    }
    
    /*
    public void setTableModel(DefaultTableModel modelo) {
        //tablePanel.setModel(modelo);
    }
    */
    /*
    public void showPanel(JPanel c) {
        if (currentPanel != null) {   
            panel.remove(currentPanel);
        }
        currentPanel = c;
        panel.add(currentPanel);
        panel.setVisible(true);
    }
    */
    public void setMenuActionListener(ActionListener menuActionListener) {
        menu.setMenuActionListener(menuActionListener);
    }
}
