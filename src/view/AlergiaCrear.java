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

import model.Alergia;
import model.AlergiaDAO;
import model.Orfanato;
import model.OrfanatoDAO;
import model.PacienteDAO;

import properties.MyProperties;

public class AlergiaCrear extends JDialog {
	private JTextField nombreAlergia;
	private JTextArea descAlergia = new JTextArea(4, 12);
	private JComboBox alergiasList;
	private MyProperties prop;
	private JDialog pacAlCrear;
	
	public AlergiaCrear(JDialog parent, JComboBox alergiasList, MyProperties prop) {
		super(parent, prop.getProperty("titulo7"), ModalityType.DOCUMENT_MODAL);
        this.prop = prop;
        this.alergiasList = alergiasList;
        this.pacAlCrear = parent;
		
        this.setSize(400, 260);
        this.setLocationRelativeTo(parent);
    	this.setLayout(new BorderLayout());
    	
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
        JLabel l = new JLabel(prop.getProperty("aalergia2"), SwingConstants.LEFT);
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
        
        //ClienteDAO clienteDao = new ClienteDAO();
            
        JPanel form = new JPanel();
        form.setLayout(new FlowLayout(FlowLayout.LEADING));
        Font labelFont = new Font("sans-serif",Font.BOLD,12);
        Font textfFont = new Font("sans-serif",Font.BOLD,12);
        
        try {  
        
            //listaClientes = clienteDao.getTListaClientes();
            //claves = listaClientes.keySet().toArray();
        	float[] hsb;
            hsb = Color.RGBtoHSB(240,240,240,new float[3]);
            form.setBackground(Color.getHSBColor(hsb[0], hsb[1], hsb[2]));
            
            /* CAMPOS */
            
            /* Nombre */
            
            // - Label
            JLabel nombreLabel = new JLabel(prop.getProperty("nombreal"));
            nombreLabel.setFont(labelFont);
            nombreLabel.setHorizontalAlignment(JLabel.CENTER);
            nombreLabel.setPreferredSize(new Dimension(200, 25));
            nombreLabel.setSize(200, 25);
            nombreLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 10));
            // - Field
            nombreAlergia = new JTextField();
            nombreAlergia.setFont(textfFont);
            nombreAlergia.setColumns(13);
            nombreAlergia.setSize(new Dimension(200, 25));
            nombreAlergia.setPreferredSize(new Dimension(200, 25));
            
            // - Add
            form.add(nombreLabel);
            form.add(nombreAlergia);
            
            
            /* Descripcion */

            // - Label
            JLabel descAlergiaLabel = new JLabel(prop.getProperty("descal"));
            descAlergiaLabel.setFont(labelFont);
            descAlergiaLabel.setHorizontalAlignment(JLabel.CENTER);
            descAlergiaLabel.setPreferredSize(new Dimension(200, 25));
            descAlergiaLabel.setSize(200, 25);
            descAlergiaLabel.setMaximumSize(new Dimension(200, 25));
            descAlergiaLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 10));
            // - Field
            descAlergia.setFont(textfFont);
            descAlergia.setEditable(true);
            descAlergia.setSize(280, 2);
            JScrollPane scroll = new JScrollPane(descAlergia);
            scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

            // - Add
            form.add(descAlergiaLabel);
            form.add(scroll);
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
            int length = nombreAlergia.getText().length();
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
                    	Alergia alergia = new Alergia();
                    	if(checkNombreField()) {
                    		alergia.setNombreAlergia(nombreAlergia.getText());
                    		AlergiaDAO alergiaDao = new AlergiaDAO(prop);
                            alergiaDao.crearAlergia(alergia);
                            /*
                             * Actualizamos el JComboBox
                             */
                            Map fkAlergias = null;
							try {
								fkAlergias = alergiaDao.fkAlergias();
							} catch (ParseException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							} 
                            alergiasList.setModel(new DefaultComboBoxModel(fkAlergias.values().toArray()));
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
    			prop.getProperty("error5"),
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
    			prop.getProperty("error5"+String.valueOf(i)),
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.ERROR_MESSAGE,
                null,
                options,
                prop.getProperty("cerrar"));
    	JDialog dialog = new JDialog();
    	dialog = infoPane.createDialog(pacAlCrear, prop.getProperty("error"));
    	dialog.setLocationRelativeTo(pacAlCrear);
    	dialog.setVisible(true);
    }

}
