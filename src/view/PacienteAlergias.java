package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

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

import model.AlergiaDAO;

import properties.MyProperties;

public class PacienteAlergias extends JDialog {
    private JTable alergiaTable;
    private JLabel alergiaLabel;
    private JPanel alergiaBotones;
    private MyProperties prop;
    private AlergiaDAO alergiaDao;
    private int idpaciente;
	
	PacienteAlergias(JDialog parent, MyProperties prop, int idPaciente) {
    	super(parent, prop.getProperty("titulo8"), ModalityType.DOCUMENT_MODAL);
    	this.prop = prop;
    	this.idpaciente = idPaciente;
    	this.setSize(700, 500);
    	this.setLocationRelativeTo(parent);
		
    	alergiaDao = new AlergiaDAO(prop);
    	
        /* Cabecera */
        JPanel cabecera = createCabecera();
        // Añadimos al JDialog
        this.add(cabecera, BorderLayout.NORTH);
        
        /* Tabla */
        alergiaTable = createTablePanel();
        
        // Ponemos la tabla dentro de un JScrollPane
        JScrollPane jsp = new JScrollPane(alergiaTable);
        // Añadimos al JDialog
        this.add(jsp, BorderLayout.CENTER);
        
        /* Botonera */
        alergiaBotones = new AlergiaBotones(alergiaTable, prop, this);
        alergiaBotones.setLayout(new BoxLayout(alergiaBotones, BoxLayout.PAGE_AXIS));
        // Añadimos al JDialog
        this.add(alergiaBotones, BorderLayout.SOUTH);
	}
	
    public int getIdPaciente() {
    	return idpaciente;
    }
	
    private JPanel createCabecera() {
        JPanel c = new JPanel();
        alergiaLabel = new JLabel(prop.getProperty("alergias2"), SwingConstants.LEFT);
        float[] hsb;
        hsb = Color.RGBtoHSB(230,230,230,new float[3]); 
        c.setBackground(Color.getHSBColor(hsb[0], hsb[1], hsb[2]));
        c.setBorder(BorderFactory.createEmptyBorder(5, 0, 10, 0));
        
        hsb = Color.RGBtoHSB(200,200,200,new float[3]); 
        Border border = new MatteBorder(0, 0, 1, 0, Color.getHSBColor(hsb[0], hsb[1], hsb[2]));
        hsb = Color.RGBtoHSB(153,153,153,new float[3]); 
        alergiaLabel.setForeground(Color.getHSBColor(hsb[0], hsb[1], hsb[2]));
        alergiaLabel.setPreferredSize(new Dimension(700, 45));
        alergiaLabel.setMaximumSize(new Dimension(700, 45));
        alergiaLabel.setVerticalAlignment(SwingConstants.CENTER);
        alergiaLabel.setHorizontalAlignment(JLabel.CENTER);
        alergiaLabel.setFont(new Font("sans-serif",Font.PLAIN,22));
        alergiaLabel.setBorder(border);
  
        c.add(alergiaLabel);
        
        return c;
        
        
    }
    
    private JTable createTablePanel() {
        
        JTable tp = new JTable();
        tp.setModel(alergiaDao.getTablaPacAlergias(idpaciente));
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
