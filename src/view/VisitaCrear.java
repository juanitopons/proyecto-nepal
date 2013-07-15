package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Calendar;

import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.border.MatteBorder;

import model.ItemMap;
import model.Visita;
import model.VisitaDAO;
import properties.MyProperties;

public class VisitaCrear extends JDialog {

    //private JTextField idCliente = new JTextField();
    //private JComboBox idCliente = new JComboBox();
	/*
    * idvisita int(10) NOT NULL AUTO_INCREMENT,
    * idpaciente int(5) NOT NULL,
    * fechavisita timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
    * pelo char(1) NOT NULL,
    * vision char(1) NOT NULL,
    * oidos char(1) NOT NULL,
    * dientes char(1) NOT NULL,
    * higiene char(1) NOT NULL,
    * altura double NOT NULL,
    * peso double NOT NULL,
    * imc double DEFAULT NULL,
    * fc int(3) DEFAULT NULL,
    * tamax int(3) DEFAULT NULL,
    * tamin int(3) DEFAULT NULL,
    * observaciones varchar(255) DEFAULT NULL,
    */ 
	
	private JTextField idPaciente;
    private Timestamp fechavisita;
    private JComboBox pelo;
    private JComboBox vision;
    private JComboBox oidos;
    private JComboBox dientes;
    private JComboBox higiene;
    private JTextField altura;
    private JTextField peso;
    private JTextField imc;
    private JTextField fc;
    private JTextField tamax;
    private JTextField tamin;
    private JTextArea observaciones = new JTextArea();
    
    private JTable visitaTable;
    private MyProperties prop;
    private PacienteVisitas pacienteVisitas;
        
    public VisitaCrear(JTable visitaTable, PacienteVisitas frame, MyProperties prop) {
        //idpaciente = pacienteVisitas.getIdPaciente();
    	super(frame, prop.getProperty("titulo6"), ModalityType.DOCUMENT_MODAL);
        this.prop = prop;
        this.visitaTable = visitaTable;
        this.pacienteVisitas = frame;
        this.setSize(600, 600);
    	this.setLocationRelativeTo(frame);
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
        this.setVisible(true);
        
    }
   
    private JPanel createCabecera() {
        JPanel c = new JPanel();
        JLabel l = new JLabel(prop.getProperty("avisita"), SwingConstants.LEFT);
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
        Font labelFont = new Font("sans-serif",Font.BOLD,12);
        Font textfFont = new Font("sans-serif",Font.BOLD,12);
        
        try {  
        
            //listaClientes = clienteDao.getTListaClientes();
            //claves = listaClientes.keySet().toArray();
        	float[] hsb;
            form.setLayout(new GridLayout(0,2));
            hsb = Color.RGBtoHSB(240,240,240,new float[3]);
            form.setBackground(Color.getHSBColor(hsb[0], hsb[1], hsb[2]));
            
            /* CAMPOS */
            VisitaDAO visitaDao = new VisitaDAO(prop);
            
            /* IdPaciente */

            // - Label
            JLabel idPacienteLabel = new JLabel(prop.getProperty("idpacientev"));
            idPacienteLabel.setFont(labelFont);
            idPacienteLabel.setHorizontalAlignment(JLabel.CENTER);
            idPacienteLabel.setPreferredSize(new Dimension(300, 20));
            idPacienteLabel.setSize(300, 20);
            idPacienteLabel.setMaximumSize(new Dimension(300, 20));
            idPacienteLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 10));
            // - Field
            idPaciente = new JTextField();
            idPaciente.setFont(textfFont);
            idPaciente.setColumns(5);
            idPaciente.setEditable(false);
            idPaciente.setText(String.valueOf(pacienteVisitas.getIdPaciente()));
            
            
            /* Fecha */
            
            // - Add
            form.add(idPacienteLabel);
            form.add(idPaciente);
            
            // - Field
            Calendar calendar = Calendar.getInstance();
            java.sql.Timestamp fechavisita = new java.sql.Timestamp(calendar.getTime().getTime());
            
            
            /* Pelo */
            
            // - Label
            JLabel peloLabel = new JLabel(prop.getProperty("pelov"));
            peloLabel.setFont(labelFont);
            peloLabel.setHorizontalAlignment(JLabel.CENTER);
            peloLabel.setPreferredSize(new Dimension(300, 20));
            peloLabel.setSize(280, 20);
            peloLabel.setMaximumSize(new Dimension(300, 20));
            peloLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 10));
            // - Field
            pelo = new JComboBox();
            pelo.setFont(textfFont);
            pelo.setModel(new DefaultComboBoxModel(new String[] {"Correcto","Deficiente"}));
            // - Add
            form.add(peloLabel);
            form.add(pelo);
            
            
            /* Vision */
            
            // - Label
            JLabel visionLabel = new JLabel(prop.getProperty("visionv"));
            visionLabel.setFont(labelFont);
            visionLabel.setHorizontalAlignment(JLabel.CENTER);
            visionLabel.setPreferredSize(new Dimension(300, 20));
            visionLabel.setSize(300, 20);
            visionLabel.setMaximumSize(new Dimension(300, 20));
            visionLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 10));
            // - Field
            vision = new JComboBox();
            vision.setFont(textfFont);
            vision.setModel(new DefaultComboBoxModel(new String[] {"Correcto","Deficiente"}));
            // - Add
            form.add(visionLabel, BorderLayout.WEST);
            form.add(vision, BorderLayout.EAST);
            
            
            /* Oidos */
            
            // - Label
            JLabel oidosLabel = new JLabel(prop.getProperty("oidosv"));
            oidosLabel.setFont(labelFont);
            oidosLabel.setHorizontalAlignment(JLabel.CENTER);
            oidosLabel.setPreferredSize(new Dimension(300, 20));
            oidosLabel.setSize(300, 20);
            oidosLabel.setMaximumSize(new Dimension(300, 20));
            oidosLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 10));
            // - Field
            oidos = new JComboBox();
            oidos.setFont(textfFont);
            oidos.setModel(new DefaultComboBoxModel(new String[] {"Correcto","Deficiente"}));
            // - Add
            form.add(oidosLabel);
            form.add(oidos);
            
            
            /* Dientes */
            
            // - Label
            JLabel dientesLabel = new JLabel(prop.getProperty("dientesv"));
            dientesLabel.setFont(labelFont);
            dientesLabel.setHorizontalAlignment(JLabel.CENTER);
            dientesLabel.setPreferredSize(new Dimension(300, 20));
            dientesLabel.setSize(300, 20);
            dientesLabel.setMaximumSize(new Dimension(300, 20));
            dientesLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 10));
            // - Field
            dientes = new JComboBox();
            dientes.setFont(textfFont);
            dientes.setModel(new DefaultComboBoxModel(new String[] {"Correcto","Deficiente"}));
            // - Add
            form.add(dientesLabel);
            form.add(dientes);
            
            
            /* Higiene */
            
            // - Label
            JLabel higieneLabel = new JLabel(prop.getProperty("higienev"));
            higieneLabel.setFont(labelFont);
            higieneLabel.setHorizontalAlignment(JLabel.CENTER);
            higieneLabel.setPreferredSize(new Dimension(300, 20));
            higieneLabel.setSize(300, 20);
            higieneLabel.setMaximumSize(new Dimension(300, 20));
            higieneLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 10));
            // - Field
            higiene = new JComboBox();
            higiene.setFont(textfFont);
            higiene.setModel(new DefaultComboBoxModel(new String[] {"Correcto","Deficiente"}));
            // - Add
            form.add(higieneLabel);
            form.add(higiene);
            
            
            /* Altura */
            
            // - Label
            JLabel alturaLabel = new JLabel(prop.getProperty("alturav"));
            alturaLabel.setFont(labelFont);
            alturaLabel.setHorizontalAlignment(JLabel.CENTER);
            alturaLabel.setPreferredSize(new Dimension(300, 20));
            alturaLabel.setSize(300, 20);
            alturaLabel.setMaximumSize(new Dimension(300, 20));
            alturaLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 10));
            // - Field
            altura = new JTextField();
            altura.setFont(textfFont);
            altura.setColumns(8);
            
            // - Add
            form.add(alturaLabel);
            form.add(altura);
            
            
            /* PESO */
            
            // - Label
            JLabel pesoLabel = new JLabel(prop.getProperty("pesov"));
            pesoLabel.setFont(labelFont);
            pesoLabel.setHorizontalAlignment(JLabel.CENTER);
            pesoLabel.setPreferredSize(new Dimension(300, 20));
            pesoLabel.setSize(300, 20);
            pesoLabel.setMaximumSize(new Dimension(300, 20));
            pesoLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 10));
            // - Field
            peso = new JTextField();
            peso.setFont(textfFont);
            peso.setColumns(8);
            
            // - Add
            form.add(pesoLabel);
            form.add(peso);
            
            
            /* IMC */
            
            // - Label
            JLabel imcLabel = new JLabel(prop.getProperty("imcv"));
            imcLabel.setFont(labelFont);
            imcLabel.setHorizontalAlignment(JLabel.CENTER);
            imcLabel.setPreferredSize(new Dimension(300, 20));
            imcLabel.setSize(300, 20);
            imcLabel.setMaximumSize(new Dimension(300, 20));
            imcLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 10));
            // - Field
            imc = new JTextField();
            imc.setFont(textfFont);
            imc.setColumns(8);
            
            // - Add
            form.add(imcLabel);
            form.add(imc);
            
            
            /* FC */
            
            // - Label
            JLabel fcLabel = new JLabel(prop.getProperty("fcv"));
            fcLabel.setFont(labelFont);
            fcLabel.setHorizontalAlignment(JLabel.CENTER);
            fcLabel.setPreferredSize(new Dimension(300, 20));
            fcLabel.setSize(300, 20);
            fcLabel.setMaximumSize(new Dimension(300, 20));
            fcLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 10));
            // - Field
            fc = new JTextField();
            fc.setFont(textfFont);
            fc.setColumns(8);
            
            // - Add
            form.add(fcLabel);
            form.add(fc);
            
            
            /* TAMAX */
            
            // - Label
            JLabel tamaxLabel = new JLabel(prop.getProperty("tamaxv"));
            tamaxLabel.setFont(labelFont);
            tamaxLabel.setHorizontalAlignment(JLabel.CENTER);
            tamaxLabel.setPreferredSize(new Dimension(300, 20));
            tamaxLabel.setSize(300, 20);
            tamaxLabel.setMaximumSize(new Dimension(300, 20));
            tamaxLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 10));
            // - Field
            tamax = new JTextField();
            tamax.setFont(textfFont);
            tamax.setColumns(8);
            
            // - Add
            form.add(tamaxLabel);
            form.add(tamax);
            
            
            /* TAMIN */
            
            // - Label
            JLabel taminLabel = new JLabel(prop.getProperty("taminv"));
            taminLabel.setFont(labelFont);
            taminLabel.setHorizontalAlignment(JLabel.CENTER);
            taminLabel.setPreferredSize(new Dimension(300, 20));
            taminLabel.setSize(300, 20);
            taminLabel.setMaximumSize(new Dimension(300, 20));
            taminLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 10));
            // - Field
            tamin = new JTextField();
            tamin.setFont(textfFont);
            tamin.setColumns(8);
            
            // - Add
            form.add(taminLabel);
            form.add(tamin);
            
            
            /* Observaciones */
            
            // - Label
            JLabel observacionesLabel = new JLabel(prop.getProperty("observacionesv"));
            observacionesLabel.setFont(labelFont);
            observacionesLabel.setHorizontalAlignment(JLabel.CENTER);
            observacionesLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 10));
            // - Field
            observaciones.setFont(textfFont);
            observaciones.setEditable(true);
            observaciones.setSize(280, 1);
            //JScrollPane scroll = new JScrollPane(observaciones);
            //scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

            // - Add
            form.add(observacionesLabel);
            form.add(observaciones);
         
            
            form.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 10));
       
        }
        catch(Exception e) {
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
        
    	protected boolean checkAlturaField() {
            boolean wasValid = true;
            double value;

            //Parse the value.
            try {
                value = Double.parseDouble(altura.getText());
            } catch (NumberFormatException e) {
                e.printStackTrace();
                wasValid = false;
            }
            
            // LENGTH control
            if(wasValid) {
            	int length = altura.getText().length();
            	if(length <= 0) {
            		wasValid = false;
            	
            	}
            }
            return wasValid;
        }
    	
    	protected boolean checkPesoField() {
            boolean wasValid = true;
            double value;

            //Parse the value.
            try {
                value = Double.parseDouble(peso.getText());
            } catch (NumberFormatException e) {
                e.printStackTrace();
                wasValid = false;
            }
            
            // LENGTH control
            if(wasValid) {
            	int length = peso.getText().length();
            	if(length <= 0) {
            		wasValid = false;
            	
            	}
            }
            return wasValid;
        }
    	
    	protected boolean checkImcField() {
            boolean wasValid = true;
            double value;

            //Parse the value.
            try {
                value = Double.parseDouble(imc.getText());
            } catch (NumberFormatException e) {
                e.printStackTrace();
                wasValid = false;
            }
            
            return wasValid;
        }
    	
    	protected boolean checkFcField() {
            boolean wasValid = true;
            Integer value;

            //Parse the value.
            try {
                value = Integer.parseInt(fc.getText());
            } catch (NumberFormatException e) {
                e.printStackTrace();
                wasValid = false;
            }
            
            if(wasValid) {
            	if(fc.getText().length()>3 || fc.getText().length()<=0) {
            		wasValid = false;
            	}
            }
            return wasValid;
        }
    	
    	protected boolean checkTamaxField() {
            boolean wasValid = true;
            Integer value;

            //Parse the value.
            try {
                value = Integer.parseInt(tamax.getText());
            } catch (NumberFormatException e) {
                e.printStackTrace();
                wasValid = false;
            }
            
            if(wasValid) {
            	if(tamax.getText().length()>3) {
            		wasValid = false;
            	}
            }
            return wasValid;
        }
    	
    	protected boolean checkTaminField() {
            boolean wasValid = true;
            Integer value;

            //Parse the value.
            try {
                value = Integer.parseInt(tamin.getText());
            } catch (NumberFormatException e) {
                e.printStackTrace();
                wasValid = false;
            }
            
            if(wasValid) {
            	if(tamin.getText().length()>3) {
            		wasValid = false;
            	}
            }
            return wasValid;
        }
    	
    	@Override
        public void actionPerformed(ActionEvent event) {
            try {
            	ItemMap item;
                String key = event.getActionCommand();
                switch (key) {
                    case "botonpaciente":
                        Visita visita = new Visita();
                        
                        visita.setIdPaciente(Integer.parseInt(idPaciente.getText()));
                        visita.setFecha(fechavisita);
                        visita.setPelo(convertToLogic(pelo.getSelectedItem().toString()));
                        visita.setVision(convertToLogic(vision.getSelectedItem().toString()));
                        visita.setOidos(convertToLogic(oidos.getSelectedItem().toString()));
                        visita.setDientes(convertToLogic(dientes.getSelectedItem().toString()));
                        visita.setHigiene(convertToLogic(higiene.getSelectedItem().toString()));
                        if(checkAlturaField()) { 
                        	visita.setAltura(Double.parseDouble(altura.getText()));    
	                        if(checkPesoField()) {
	                        	visita.setPeso(Double.parseDouble(peso.getText()));
	                        	if(checkImcField()) {
	                        		visita.setImc(Double.parseDouble(imc.getText()));
	                        		if(checkFcField()) {
	                        			visita.setFc(Integer.parseInt(fc.getText()));
	                        			if(checkTamaxField()) { 
	                        				visita.setTamax(Integer.parseInt(tamax.getText()));
	                        				if(checkTaminField()) {
	                        					visita.setTamin(Integer.parseInt(tamin.getText()));
	                        					visita.setObservaciones(observaciones.getText());
	                                            VisitaDAO visitaDao = new VisitaDAO(prop);
	                                            visitaDao.crearVisita(visita);
	                                            /*
	                                             * Actualizamos el modelo
	                                             */
	                                            visitaTable.setModel(visitaDao.getTablaVisitas(Integer.parseInt(idPaciente.getText())));
	                                            visitaTable.updateUI();
	                                             /*
	                                             * Cerramos la ventana
	                                             */
	                                            dispose();
	                        				} else {
	                        					showErrorMessage(6);
	                        				}
	                        			} else {
	                        				showErrorMessage(5);
	                        			}
	                        		} else {
	                        			showErrorMessage(4);
	                        		}
	                        	} else {
	                        		showErrorMessage(3);
	                        	}
	                        } else {
	                        	showErrorMessage(2);
	                        }
                        } else {
                        	showErrorMessage(1);
                        }
                        break;
                    default:
                        System.out.println("pacienteCrear: Accion '" + key + "' no reconocida.");
                        break;
                }
            }
            catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException e) {                
            	showErrorMessage();
            }
        }
    }
    
    private String convertToLogic(String value) {
    	if(value.equalsIgnoreCase("Correcto")) value = "1";
    	if(value.equalsIgnoreCase("Deficiente")) value = "0";
    	return value;
    }
    
    private void showErrorMessage() {
    	Object[] options = {prop.getProperty("cerrar")};
    	JOptionPane infoPane = new JOptionPane(
    			prop.getProperty("error3"),
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
    			prop.getProperty("error3"+String.valueOf(i)),
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
	
}
