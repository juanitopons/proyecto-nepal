/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import model.PacienteDAO;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.MatteBorder;
import javax.swing.table.JTableHeader;

import properties.MyProperties;

    
/**
 *
 * @author diaz
 */
public class PacienteView extends JDialog {

    private JTable pacienteTable;
    private JLabel pacienteLabel;
    private JPanel pacienteBotones;
    private MyProperties prop;
    private PacienteDAO pacienteDao = new PacienteDAO();
    
    public PacienteView(JFrame frame, MyProperties prop) {
    	
    	super(frame, prop.getProperty("titulo3"), ModalityType.DOCUMENT_MODAL);
    	this.prop = prop;
    	this.setSize(700, 500);
    	this.setLocationRelativeTo(frame);
        
        /* Cabecera */
        JPanel cabecera = createCabecera();
        // Añadimos al JDialog
        this.add(cabecera, BorderLayout.NORTH);
        
        /* Tabla */
        pacienteTable = createTablePanel();
        pacienteTable.setModel(pacienteDao.getTablaPacientes());
        // Ponemos la tabla dentro de un JScrollPane
        JScrollPane jsp = new JScrollPane(pacienteTable);
        // Añadimos al JDialog
        this.add(jsp, BorderLayout.CENTER);
        
        
        /* Botonera */
        pacienteBotones = new PacienteBotones(pacienteTable, prop, this);
        pacienteBotones.setLayout(new BoxLayout(pacienteBotones, BoxLayout.PAGE_AXIS));
        // Añadimos al JDialog
        this.add(pacienteBotones, BorderLayout.SOUTH);

    }
    
    private JPanel createCabecera() {
        JPanel c = new JPanel();
        pacienteLabel = new JLabel(prop.getProperty("ninos"), SwingConstants.LEFT);
        float[] hsb;
        hsb = Color.RGBtoHSB(230,230,230,new float[3]); 
        c.setBackground(Color.getHSBColor(hsb[0], hsb[1], hsb[2]));
        c.setBorder(BorderFactory.createEmptyBorder(5, 0, 10, 0));
        
        hsb = Color.RGBtoHSB(200,200,200,new float[3]); 
        Border border = new MatteBorder(0, 0, 1, 0, Color.getHSBColor(hsb[0], hsb[1], hsb[2]));
        hsb = Color.RGBtoHSB(153,153,153,new float[3]); 
        pacienteLabel.setForeground(Color.getHSBColor(hsb[0], hsb[1], hsb[2]));
        pacienteLabel.setPreferredSize(new Dimension(800, 45));
        pacienteLabel.setSize(800, 45);
        pacienteLabel.setMaximumSize(new Dimension(800, 45));
        pacienteLabel.setVerticalAlignment(SwingConstants.CENTER);
        pacienteLabel.setHorizontalAlignment(JLabel.CENTER);
        pacienteLabel.setFont(new Font("sans-serif",Font.PLAIN,22));
        pacienteLabel.setBorder(border);
  
        c.add(pacienteLabel);
        
        return c;
    }
 
    private JTable createTablePanel() {
        
        JTable tp = new JTable();
        float[] hsb;
        /*
         * Cabecera de la tabla
         */
        JTableHeader th = tp.getTableHeader();
        th.setFont(new Font("sans-serif",Font.BOLD,12));
        th.setBackground(Color.GRAY);
        hsb = Color.RGBtoHSB(240,240,240,new float[3]);
        th.setForeground(Color.getHSBColor(hsb[0], hsb[1], hsb[2]));
        hsb = Color.RGBtoHSB(200,200,200,new float[3]); 
        Border border = new MatteBorder(0, 0, 1, 0, Color.getHSBColor(hsb[0], hsb[1], hsb[2]));
        th.setBorder(border);
        th.setPreferredSize(new Dimension(800, 25));
        th.setSize(800, 25);
        th.setMaximumSize(new Dimension(800, 25));
        
        /*
         * Caracteristicas de la tabla
         */
        tp.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        tp.setFont(new Font("sans-serif",Font.BOLD,12));
        tp.setRowHeight(22);
        
        return tp;
    }
}
