/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

/**
 *
 * @author juanitopons
 */
import model.ClienteDAO;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import javax.swing.*;
import javax.swing.table.JTableHeader;

public class ClienteView extends JFrame {

    private JTable clienteTable;
    private JPanel clienteBotones;
    
    private ClienteDAO clienteDao = new ClienteDAO();
    
    public ClienteView() {
        super("Gestión de clientes");
        
        Container cp = this.getContentPane();
        cp.setLayout(new BorderLayout());
        
        /* Cabecera */
        JPanel cabecera = createCabecera();
  
        /* Tabla */
        clienteTable = createTablePanel();
        clienteTable.setModel(clienteDao.getTablaClientes());
        // Ponemos la tabla dentro de un JScrollPane
        JScrollPane jsp = new JScrollPane(clienteTable);

        /* Botonera */
        clienteBotones = new ClienteBotones(clienteTable);
        
        /* Añadimos todos los paneles al Container */
        cp.add(cabecera, BorderLayout.NORTH);
        cp.add(jsp, BorderLayout.CENTER);
        cp.add(clienteBotones, BorderLayout.SOUTH);
        
        setSize(800,400);
        setVisible(true);
    }
   
    private JPanel createCabecera() {
        JPanel c = new JPanel();
        JLabel l = new JLabel("Lista de clientes");
        l.setForeground(Color.RED);
        l.setHorizontalAlignment(JLabel.CENTER);
        l.setFont(new Font("Arial",Font.BOLD | Font.ITALIC,24));
        c.add(l);
        
        return c;
    }
 
    private JTable createTablePanel() {
        
        JTable tp = new JTable();
        /*
         * Cabecera de la tabla
         */
        JTableHeader th = tp.getTableHeader();
        th.setFont(new Font("Arial",Font.BOLD | Font.ITALIC,14));
        th.setBackground(Color.DARK_GRAY);
        th.setForeground(Color.WHITE);
        
        /*
         * Caracteristicas de la tabla
         */
        tp.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        tp.setFont(new Font("Arial",Font.BOLD,12));
        tp.setRowHeight(22);
        
        return tp;
    }
}
