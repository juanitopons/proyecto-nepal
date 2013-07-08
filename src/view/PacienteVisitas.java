package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Dialog.ModalityType;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.border.MatteBorder;
import javax.swing.table.JTableHeader;

import model.PacienteDAO;
import model.VisitaDAO;
import properties.MyProperties;

public class PacienteVisitas extends JDialog {
    private JTable visitaTable;
    private JLabel visitaLabel;
    private JPanel visitaBotones;
    private MyProperties prop;
    private VisitaDAO visitaDao;
    private int idpaciente;

    public PacienteVisitas(JDialog frame, MyProperties prop, int idPaciente) {
    	super(frame, prop.getProperty("titulo5"), ModalityType.DOCUMENT_MODAL);
    	this.prop = prop;
    	this.idpaciente = idPaciente;
    	this.setSize(700, 500);
    	this.setLocationRelativeTo(frame);
    	
    	 visitaDao = new VisitaDAO(prop);
    	 
        /* Cabecera */
        JPanel cabecera = createCabecera();
        // Añadimos al JDialog
        this.add(cabecera, BorderLayout.NORTH);
        
        /* Tabla */
        visitaTable = createTablePanel();
        
        // Ponemos la tabla dentro de un JScrollPane
        JScrollPane jsp = new JScrollPane(visitaTable);
        jsp.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        // Añadimos al JDialog
        this.add(jsp, BorderLayout.CENTER);
        
        
        /* Botonera */
        visitaBotones = new VisitaBotones(visitaTable, prop, this);
        visitaBotones.setLayout(new BoxLayout(visitaBotones, BoxLayout.PAGE_AXIS));
        // Añadimos al JDialog
        this.add(visitaBotones, BorderLayout.SOUTH);
    }
    
    public int getIdPaciente() {
    	return idpaciente;
    }
    
    private JPanel createCabecera() {
        JPanel c = new JPanel();
        visitaLabel = new JLabel(prop.getProperty("visitas2"), SwingConstants.LEFT);
        float[] hsb;
        hsb = Color.RGBtoHSB(230,230,230,new float[3]); 
        c.setBackground(Color.getHSBColor(hsb[0], hsb[1], hsb[2]));
        c.setBorder(BorderFactory.createEmptyBorder(5, 0, 10, 0));
        
        hsb = Color.RGBtoHSB(200,200,200,new float[3]); 
        Border border = new MatteBorder(0, 0, 1, 0, Color.getHSBColor(hsb[0], hsb[1], hsb[2]));
        hsb = Color.RGBtoHSB(153,153,153,new float[3]); 
        visitaLabel.setForeground(Color.getHSBColor(hsb[0], hsb[1], hsb[2]));
        visitaLabel.setPreferredSize(new Dimension(1230, 45));
        visitaLabel.setMaximumSize(new Dimension(1230, 45));
        visitaLabel.setVerticalAlignment(SwingConstants.CENTER);
        visitaLabel.setHorizontalAlignment(JLabel.CENTER);
        visitaLabel.setFont(new Font("sans-serif",Font.PLAIN,22));
        visitaLabel.setBorder(border);
  
        c.add(visitaLabel);
        
        return c;
    }
 
    private JTable createTablePanel() {
        
        JTable tp = new JTable();
        tp.setModel(visitaDao.getTablaVisitas());
        float[] hsb;
        /*
         * Cabecera de la tabla
         */
        JTableHeader th = tp.getTableHeader();
        th.getColumnModel().getColumn(0).setPreferredWidth(50);
        th.getColumnModel().getColumn(1).setPreferredWidth(60);
        th.getColumnModel().getColumn(2).setPreferredWidth(50);
        th.getColumnModel().getColumn(3).setPreferredWidth(50);
        th.getColumnModel().getColumn(4).setPreferredWidth(50);
        th.getColumnModel().getColumn(5).setPreferredWidth(60);
        th.getColumnModel().getColumn(6).setPreferredWidth(50);
        th.getColumnModel().getColumn(7).setPreferredWidth(60);
        th.getColumnModel().getColumn(8).setPreferredWidth(60);
        th.getColumnModel().getColumn(9).setPreferredWidth(60);
        th.getColumnModel().getColumn(10).setPreferredWidth(60);
        th.getColumnModel().getColumn(11).setPreferredWidth(60);
        th.getColumnModel().getColumn(12).setPreferredWidth(60);
        th.getColumnModel().getColumn(13).setPreferredWidth(500);
        th.getColumnModel().getColumn(13).setMaxWidth(500);
        
        th.setFont(new Font("sans-serif",Font.BOLD,10));
        th.setBackground(Color.GRAY);
        hsb = Color.RGBtoHSB(240,240,240,new float[3]);
        th.setForeground(Color.getHSBColor(hsb[0], hsb[1], hsb[2]));
        hsb = Color.RGBtoHSB(200,200,200,new float[3]); 
        Border border = new MatteBorder(0, 0, 1, 0, Color.getHSBColor(hsb[0], hsb[1], hsb[2]));
        th.setBorder(border);
        th.setPreferredSize(new Dimension(1230, 25));
        th.setMaximumSize(new Dimension(1230, 25));
        
        
        
        /*
         * Caracteristicas de la tabla
         */
        tp.setFont(new Font("sans-serif",Font.BOLD,10));
        tp.setRowHeight(42);
        tp.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0));
        tp.setAutoResizeMode( JTable.AUTO_RESIZE_OFF );
        
        return tp;
    }
}
