package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Dialog.ModalityType;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.border.MatteBorder;
import javax.swing.table.JTableHeader;

import model.VacunaDAO;
import properties.MyProperties;

public class PacienteVacunas extends JDialog {
    private JTable vacunaTable;
    private JLabel vacunaLabel;
    private JPanel vacunaBotones;
    private MyProperties prop;
    private VacunaDAO vacunaDao;
    private int idpaciente;
	
	PacienteVacunas(JDialog parent, MyProperties prop, int idPaciente) {
    	super(parent, prop.getProperty("titulo11"), ModalityType.DOCUMENT_MODAL);
    	this.prop = prop;
    	this.idpaciente = idPaciente;
    	this.setSize(700, 500);
    	this.setLocationRelativeTo(parent);
		
    	vacunaDao = new VacunaDAO(prop);
    	
        /* Cabecera */
        JPanel cabecera = createCabecera();
        // Añadimos al JDialog
        this.add(cabecera, BorderLayout.NORTH);
        
        /* Tabla */
        vacunaTable = createTablePanel();
        
        // Ponemos la tabla dentro de un JScrollPane
        JScrollPane jsp = new JScrollPane(vacunaTable);
        // Añadimos al JDialog
        this.add(jsp, BorderLayout.CENTER);
        
        /* Botonera */
        vacunaBotones = new VacunaBotones(vacunaTable, prop, this);
        vacunaBotones.setLayout(new BoxLayout(vacunaBotones, BoxLayout.PAGE_AXIS));
        // Añadimos al JDialog
        this.add(vacunaBotones, BorderLayout.SOUTH);
	}
	
    public int getIdPaciente() {
    	return idpaciente;
    }
	
    private JPanel createCabecera() {
        JPanel c = new JPanel();
        vacunaLabel = new JLabel(prop.getProperty("vacunas2"), SwingConstants.LEFT);
        float[] hsb;
        hsb = Color.RGBtoHSB(230,230,230,new float[3]); 
        c.setBackground(Color.getHSBColor(hsb[0], hsb[1], hsb[2]));
        c.setBorder(BorderFactory.createEmptyBorder(5, 0, 10, 0));
        
        hsb = Color.RGBtoHSB(200,200,200,new float[3]); 
        Border border = new MatteBorder(0, 0, 1, 0, Color.getHSBColor(hsb[0], hsb[1], hsb[2]));
        hsb = Color.RGBtoHSB(153,153,153,new float[3]); 
        vacunaLabel.setForeground(Color.getHSBColor(hsb[0], hsb[1], hsb[2]));
        vacunaLabel.setPreferredSize(new Dimension(700, 45));
        vacunaLabel.setMaximumSize(new Dimension(700, 45));
        vacunaLabel.setVerticalAlignment(SwingConstants.CENTER);
        vacunaLabel.setHorizontalAlignment(JLabel.CENTER);
        vacunaLabel.setFont(new Font("sans-serif",Font.PLAIN,22));
        vacunaLabel.setBorder(border);
  
        c.add(vacunaLabel);
        
        return c;
        
        
    }
    
    private JTable createTablePanel() {
        
        JTable tp = new JTable();
        tp.setModel(vacunaDao.getTablaPacVacunas(idpaciente));
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
        th.setPreferredSize(new Dimension(700, 25));
        th.setMaximumSize(new Dimension(700, 25));
        
        
        /*
         * Caracteristicas de la tabla
         */
        tp.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0));
        tp.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        tp.setFont(new Font("sans-serif",Font.BOLD,12));
        tp.setRowHeight(22);
        
        return tp;
    }

}
