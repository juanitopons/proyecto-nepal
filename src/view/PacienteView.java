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
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;

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
    private PacienteDAO pacienteDao;
    
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
        pacienteDao = new PacienteDAO();
        pacienteTable = createTablePanel(pacienteDao.getTablaPacientes());
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
 
    private JTable createTablePanel(DefaultTableModel model) {
        
        JTable tp = new JTable(model)
        {
            //  Returning the Class of each column will allow different
            //  renderers to be used based on Class
            public Class getColumnClass(int column)
            {
                return getValueAt(0, column).getClass();
            }
        };
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
        tp.setAutoResizeMode(JTable.AUTO_RESIZE_SUBSEQUENT_COLUMNS);
        tp.setFont(new Font("sans-serif",Font.BOLD,12));
        tp.setRowHeight(82);
        // Table Render
        DefaultTableCellRenderer theRenderer = new DefaultTableCellRenderer();
        theRenderer.setHorizontalAlignment( JLabel.LEFT);
        theRenderer.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 0));
        DefaultTableCellRenderer theRenderer2 = new DefaultTableCellRenderer();
        theRenderer.setHorizontalAlignment( JLabel.CENTER);
        //ID
        TableColumn id = tp.getColumnModel().getColumn(1);
        id.setWidth(80);
        id.setResizable(false);
        id.setPreferredWidth(80);
        id.setMaxWidth(80);
        id.setMinWidth(80);
        id.setCellRenderer(theRenderer);
        // All, minus images
        for(int i = 2; i<tp.getColumnCount(); i++) {
        	TableColumn c = tp.getColumnModel().getColumn(i);
        	c.setCellRenderer(theRenderer2);
        }
        // Image
        TableColumn image = tp.getColumnModel().getColumn(0);
        image.setWidth(82);
        image.setResizable(false);
        image.setPreferredWidth(82);
        image.setMaxWidth(82);
        image.setMinWidth(82);
        
        return tp;
    }
}
