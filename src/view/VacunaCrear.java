package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Dialog.ModalityType;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.border.MatteBorder;

import model.Vacuna;
import model.VacunaDAO;
import properties.MyProperties;

public class VacunaCrear extends JDialog {
	private JTextField nombreVacuna;
	private JComboBox edadMin;
	private JComboBox edadMax;
	private JComboBox vacunasList;
	private MyProperties prop;
	private JDialog pacVacCrear;
	
	public VacunaCrear(JDialog parent, JComboBox vacunasList, MyProperties prop) {
		super(parent, prop.getProperty("titulo10"), ModalityType.DOCUMENT_MODAL);
        this.prop = prop;
        this.vacunasList = vacunasList;
        this.pacVacCrear = parent;
		
        this.setSize(400, 260);
        this.setLocationRelativeTo(parent);
    	this.setLayout(new BorderLayout());
    	this.setResizable(false);
    	
        /* Cabecera */
        JPanel cabecera = createCabecera();
        this.add(cabecera, BorderLayout.NORTH);
  
        /* Formulario */
        JPanel form = createForm();
        this.add(form, BorderLayout.CENTER);
        
        /* Botón */
        JPanel boton = createButton();
        this.add(boton, BorderLayout.SOUTH);
        
	}
	
    private JPanel createCabecera() {
        JPanel c = new JPanel();
        JLabel l = new JLabel(prop.getProperty("avacuna2"), SwingConstants.LEFT);
        float[] hsb;
        hsb = Color.RGBtoHSB(230,230,230,new float[3]); 
        c.setBackground(Color.getHSBColor(hsb[0], hsb[1], hsb[2]));
        c.setBorder(BorderFactory.createEmptyBorder(5, 0, 10, 0));
        
        hsb = Color.RGBtoHSB(200,200,200,new float[3]); 
        Border border = new MatteBorder(0, 0, 1, 0, Color.getHSBColor(hsb[0], hsb[1], hsb[2]));
        hsb = Color.RGBtoHSB(153,153,153,new float[3]); 
        l.setForeground(Color.getHSBColor(hsb[0], hsb[1], hsb[2]));
        l.setPreferredSize(new Dimension(600, 45));
        l.setSize(600, 45);
        l.setMaximumSize(new Dimension(600, 45));
        l.setVerticalAlignment(SwingConstants.CENTER);
        l.setHorizontalAlignment(JLabel.CENTER);
        l.setFont(new Font("sans-serif",Font.PLAIN,22));
        l.setBorder(border);
        c.add(l);
        
        return c;
    }
    
    private JPanel createForm() {
        JPanel form = new JPanel();
        form.setLayout(new FlowLayout(FlowLayout.LEADING));
        Font labelFont = new Font("sans-serif",Font.BOLD,12);
        Font textfFont = new Font("sans-serif",Font.BOLD,12);
        
        try {  
        	float[] hsb;
            hsb = Color.RGBtoHSB(240,240,240,new float[3]);
            form.setBackground(Color.getHSBColor(hsb[0], hsb[1], hsb[2]));
            
            /* CAMPOS */
            
            /* Nombre */
            
            // - Label
            JLabel nombreLabel = new JLabel(prop.getProperty("nombrevac"));
            nombreLabel.setFont(labelFont);
            nombreLabel.setHorizontalAlignment(JLabel.CENTER);
            nombreLabel.setPreferredSize(new Dimension(200, 25));
            nombreLabel.setSize(200, 25);
            // - Field
            nombreVacuna = new JTextField();
            nombreVacuna.setFont(textfFont);
            nombreVacuna.setColumns(13);
            nombreVacuna.setSize(new Dimension(200, 25));
            nombreVacuna.setPreferredSize(new Dimension(200, 25));
            
            // - Add
            form.add(nombreLabel);
            form.add(nombreVacuna);
            
            
            /* Edad Mínima */

            // - Label
            JLabel edadminVacunaLabel = new JLabel(prop.getProperty("edadminvac"));
            edadminVacunaLabel.setFont(labelFont);
            edadminVacunaLabel.setHorizontalAlignment(JLabel.CENTER);
            edadminVacunaLabel.setPreferredSize(new Dimension(200, 25));
            edadminVacunaLabel.setSize(200, 25);
            edadminVacunaLabel.setMaximumSize(new Dimension(200, 25));
            // - Field
            edadMin = new JComboBox();
            edadMin.setFont(textfFont);
            edadMin.setSize(new Dimension(120, 25));
            edadMin.setPreferredSize(new Dimension(120, 25));
            String[] edades = new String[99];
            int inicio = 0;
            for(int i=0; i<edades.length; i++) {
            	inicio+=1;
            	edades[i] = String.valueOf(inicio);
            }
            edadMin.setModel(new DefaultComboBoxModel(edades));

            // - Add
            form.add(edadminVacunaLabel);
            form.add(edadMin);
            
            /* Edad Máxima */

            // - Label
            JLabel edadMaxVacunaLabel = new JLabel(prop.getProperty("edadmaxvac"));
            edadMaxVacunaLabel.setFont(labelFont);
            edadMaxVacunaLabel.setHorizontalAlignment(JLabel.CENTER);
            edadMaxVacunaLabel.setPreferredSize(new Dimension(200, 25));
            edadMaxVacunaLabel.setSize(200, 25);
            edadMaxVacunaLabel.setMaximumSize(new Dimension(200, 25));
            // - Field
            edadMax = new JComboBox();
            edadMax.setFont(textfFont);
            edadMax.setSize(new Dimension(120, 25));
            edadMax.setPreferredSize(new Dimension(120, 25));
            edadMax.setModel(new DefaultComboBoxModel(edades));

            // - Add
            form.add(edadMaxVacunaLabel);
            form.add(edadMax);
        }
        catch (Exception e) {
        	showErrorMessage();
        }
        finally {
            return form;
        }
    }
    
    private JPanel createButton() { 
        JPanel botonPanel = new JPanel();
        JButton boton = new JButton(prop.getProperty("anadir"));
        boton.setActionCommand("botonpaciente");
        boton.addActionListener(new ButtonListener());
        botonPanel.add(boton);
        botonPanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        return botonPanel;
    }
    
    private class ButtonListener implements ActionListener {
        public ButtonListener(){}
        
    	protected boolean checkNombreField() {
            boolean wasValid = true;
            
            // LENGTH control
            int length = nombreVacuna.getText().length();
            if(length <= 0 || length > 50) {
            	wasValid = false;
            }
            return wasValid;
        }
    	
    	@Override
        public void actionPerformed(ActionEvent event) {
            try {
                String key = event.getActionCommand();
                switch (key) {
                    case "botonpaciente":
                    	Vacuna vacuna = new Vacuna();
                    	if(checkNombreField()) {
                    		vacuna.setNombreVacuna(nombreVacuna.getText());
                    		VacunaDAO vacunaDao = new VacunaDAO(prop);
                            vacunaDao.crearVacuna(vacuna);
                            /*
                             * Actualizamos el JComboBox
                             */
                            Map fkVacunas = null;
							try {
								fkVacunas = vacunaDao.fkVacunas();
							} catch (ParseException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							} 
                            vacunasList.setModel(new DefaultComboBoxModel(fkVacunas.values().toArray()));
                            dispose();
                    	} else {
                    		showErrorMessage(1);
                    	}
                }
            }
            catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException e) {                
            	showErrorMessage();
            }
        }
    }
    
    private void showErrorMessage() {
    	Object[] options = {prop.getProperty("cerrar")};
    	JOptionPane infoPane = new JOptionPane(
    			prop.getProperty("error6"),
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.ERROR_MESSAGE,
                null,
                options,
                prop.getProperty("cerrar"));
    	JDialog dialog = new JDialog();
    	dialog = infoPane.createDialog(dialog, prop.getProperty("error"));
    	dialog.setLocationRelativeTo(dialog);
    	dialog.setVisible(true);
    }
    
    private void showErrorMessage(int i) {
    	Object[] options = {prop.getProperty("cerrar")};
    	JOptionPane infoPane = new JOptionPane(
    			prop.getProperty("error6"+String.valueOf(i)),
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.ERROR_MESSAGE,
                null,
                options,
                prop.getProperty("cerrar"));
    	JDialog dialog = new JDialog();
    	dialog = infoPane.createDialog(pacVacCrear, prop.getProperty("error"));
    	dialog.setLocationRelativeTo(pacVacCrear);
    	dialog.setVisible(true);
    }

}
