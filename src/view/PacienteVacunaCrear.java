package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Dialog.ModalityType;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.border.MatteBorder;

import model.VacunaDAO;
import model.ItemMap;
import properties.MyProperties;

public class PacienteVacunaCrear extends JDialog {
	//Fields
	private JTextField idPaciente;
	private JComboBox idVacuna;
    private JComboBox dia = new JComboBox();
    private JComboBox mes = new JComboBox();
    private JComboBox anyo = new JComboBox();
    private JComboBox dosis = new JComboBox();
	// Resources
	private JTable vacunasTable;
	private PacienteVacunas parent;
	private MyProperties prop;
	private int idpaciente;
	private  VacunaDAO vacunaDao;
	
	PacienteVacunaCrear(JTable vacunasTable, PacienteVacunas parent, MyProperties prop, int idpaciente) {
		super(parent, prop.getProperty("titulo12"), ModalityType.DOCUMENT_MODAL);
        this.prop = prop;
        this.vacunasTable = vacunasTable;
        this.parent = parent;
        this.setSize(600, 280);
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
        this.setVisible(true);
	}
	
    private JDialog getInstance() {
    	return this;
    }
	
    private JPanel createCabecera() {
        JPanel c = new JPanel();
        JLabel l = new JLabel(prop.getProperty("avacunapac"), SwingConstants.LEFT);
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
            form.setLayout(new FlowLayout(FlowLayout.LEADING));
            hsb = Color.RGBtoHSB(240,240,240,new float[3]);
            form.setBackground(Color.getHSBColor(hsb[0], hsb[1], hsb[2]));
            
            /* CAMPOS */
            vacunaDao = new VacunaDAO(prop);
            
            /* IdPaciente */

            // - Label
            JLabel idPacienteLabel = new JLabel(prop.getProperty("idninal"));
            idPacienteLabel.setFont(labelFont);
            idPacienteLabel.setHorizontalAlignment(JLabel.CENTER);
            idPacienteLabel.setPreferredSize(new Dimension(280, 20));
            idPacienteLabel.setSize(280, 20);
            idPacienteLabel.setMaximumSize(new Dimension(280, 25));
            // - Field
            idPaciente = new JTextField();
            idPaciente.setFont(textfFont);
            idPaciente.setColumns(5);
            idPaciente.setPreferredSize(new Dimension(300, 25));
            idPaciente.setSize(300, 25);
            idPaciente.setMaximumSize(new Dimension(300, 25));
            idPaciente.setEditable(false);
            idPaciente.setText(String.valueOf(parent.getIdPaciente()));
            idPaciente.setBorder(BorderFactory.createEmptyBorder());
            
            form.add(idPacienteLabel);
            form.add(idPaciente);
            
            /* Vacuna */
            
            // - Label
            JLabel idVacunaLabel = new JLabel(prop.getProperty("nombrevacunaa"));
            idVacunaLabel.setFont(labelFont);
            idVacunaLabel.setHorizontalAlignment(JLabel.CENTER);
            idVacunaLabel.setBorder(null);
            idVacunaLabel.setPreferredSize(new Dimension(280, 25));
            idVacunaLabel.setSize(280, 25);
            idVacunaLabel.setMaximumSize(new Dimension(280, 25));
            // - Field
            ComboBoxModel model;
            Map fkVacunas;
            fkVacunas = vacunaDao.fkVacunas(); 
            model = new DefaultComboBoxModel(fkVacunas.values().toArray());
            idVacuna = new JComboBox(model);
            idVacuna.setFont(textfFont);
            idVacuna.setSize(new Dimension(200, 25));
            idVacuna.setPreferredSize(new Dimension(200, 25));
            idVacuna.setSelectedItem(null);
            // - Add
            form.add(idVacunaLabel);
            JPanel moreless = new JPanel();
            moreless.setLayout(new FlowLayout(FlowLayout.LEADING, 0, 0));
            moreless.setSize(new Dimension(300, 25));
            moreless.setPreferredSize(new Dimension(300, 25));
            moreless.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
            idVacuna.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 10));
            moreless.add(idVacuna);
            //botones mas menos
            JButton more, less;
            ImageIcon plus = new ImageIcon(getClass().getClassLoader().getResource("resources/plus.png"));
            more = new JButton(plus);
            more.setContentAreaFilled(false);
            more.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e)
                {
                    //Execute when button is pressed
                    VacunaCrear crearVacuna = new VacunaCrear(getInstance(), idVacuna, prop);
                    crearVacuna.setVisible(true);
                }
            });
            //more.setVerticalAlignment(SwingConstants.TOP);
            more.setBorder(BorderFactory.createEmptyBorder());
            more.setSize(new Dimension(16, 16));
            more.setPreferredSize(new Dimension(16, 16));
            moreless.add(more);
            moreless.add(Box.createHorizontalStrut(5));
            
            ImageIcon less_ic = new ImageIcon(getClass().getClassLoader().getResource("resources/less.png"));
            less = new JButton(less_ic);
            less.setContentAreaFilled(false);
            less.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e)
                {
                	if(idVacuna.getSelectedItem()==null) {
                		showErrorMessage("novacuna");
                	} else {
                    //Execute when button is pressed
                		new VacunaBorrar(getInstance(), idVacuna, prop, (ItemMap)idVacuna.getSelectedItem());
                	}
                    
                }
            });
            //less.setVerticalAlignment(SwingConstants.TOP);
            less.setBorder(BorderFactory.createEmptyBorder());
            less.setSize(new Dimension(16, 16));
            less.setPreferredSize(new Dimension(16, 16));
            
            moreless.add(less);
            
            form.add(moreless);
            
            /* Dosis */

            // - Label
            JLabel dosisLabel = new JLabel(prop.getProperty("dosisvac"));
            dosisLabel.setFont(labelFont);
            dosisLabel.setHorizontalAlignment(JLabel.CENTER);
            dosisLabel.setPreferredSize(new Dimension(280, 25));
            dosisLabel.setSize(280, 25);
            dosisLabel.setMaximumSize(new Dimension(280, 25));
            // - Field
            dosis.setFont(textfFont);
            dosis.setSize(new Dimension(190, 25));
            dosis.setPreferredSize(new Dimension(190, 25));
            String[] edades = new String[5];
            int inicio = 0;
            for(int i=0; i<edades.length; i++) {
            	inicio = inicio+1;
            	edades[i] = String.valueOf(inicio);
            }
            dosis.setModel(new DefaultComboBoxModel(edades));

            // - Add
            form.add(dosisLabel);
            form.add(dosis);
            
            /* FECHA */
            
            JLabel fechaNacimientoLabel = new JLabel(prop.getProperty("fechavacuna"));
            fechaNacimientoLabel.setFont(labelFont);
            fechaNacimientoLabel.setHorizontalAlignment(JLabel.CENTER);
            fechaNacimientoLabel.setPreferredSize(new Dimension(280, 25));
            fechaNacimientoLabel.setSize(280, 25);
            fechaNacimientoLabel.setMaximumSize(new Dimension(280, 25));
            form.add(fechaNacimientoLabel);
            
            
            JPanel fechaPanel = new JPanel();
            fechaPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
            fechaPanel.setPreferredSize(new Dimension(300, 40));
            fechaPanel.setSize(300, 40);
            fechaPanel.setMaximumSize(new Dimension(300, 40));
            
            dia.setModel(new DefaultComboBoxModel(new String[] {"01","02","03","04","05","06","07","08","09","10",
                    "11","12","13","14","15","16","17","18","19","20",
                    "21","22","23","24","25","26","27","28","29","30","31"}));
            mes.setModel(new DefaultComboBoxModel(new String[] {"01","02","03","04","05","06","07","08","09","10",
                "11","12"}));
            
            String[] year = new String[Calendar.getInstance().get(Calendar.YEAR)-1988];
            inicio = 1988;
            for(int i=0; i<year.length; i++) {
            	inicio = inicio+1;
            	year[i] = String.valueOf(inicio);
            }
            anyo.setModel(new DefaultComboBoxModel(year));
            
            fechaPanel.add(dia);
            fechaPanel.add(new JLabel("-"));
            fechaPanel.add(mes);
            fechaPanel.add(new JLabel("-"));
            fechaPanel.add(anyo);
            
            form.add(fechaPanel);
            
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
        private JDialog dialog;
        public ButtonListener(){}
    	public ButtonListener(JDialog dialog) {
        	this.dialog = dialog;
        }
    	
    	/*protected boolean checkDateField() {
            boolean wasValid = true;
            
            // LENGTH control
            int length = nombreOrfanato.getText().length();
            if(length <= 0 || length > 60) {
            	wasValid = false;
            }
            return wasValid;
        }*/
    	
    	@Override
        public void actionPerformed(ActionEvent event) {
            try {
            	ItemMap item;
                String key = event.getActionCommand();
                switch (key) {
                    case "botonpaciente":
                    	if(idVacuna.getSelectedItem()==null) {
                    		showErrorMessage("novacuna2");
                    	} else {

                    		// id vacuna
	                        item = (ItemMap) idVacuna.getSelectedItem();
	                        
	                        // fecha
	                        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
	                        String f = (String)dia.getSelectedItem() + "-" + 
	                                (String)mes.getSelectedItem() + "-" +
	                                (String)anyo.getSelectedItem();
							java.sql.Date sqlDate = null;
							try {
								sqlDate = new java.sql.Date(sdf.parse(f).getTime());
							} catch (ParseException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
	                        vacunaDao.crearPacVac(Integer.parseInt(idPaciente.getText()), item.getId(), sqlDate, Integer.parseInt(dosis.getSelectedItem().toString()));

	                        /*
	                         * Actualizamos el modelo
	                         */
	                        vacunasTable.setModel(vacunaDao.getTablaPacVacunas(parent.getIdPaciente()));
	                        vacunasTable.updateUI();
	                         /*
	                         * Cerramos la ventana
	                         */
	                        dispose();
                    	}
                        break;
                    default:
                        System.out.println("pacienteCrear: Accion '" + key + "' no reconocida.");
                        break;
                }
            }
            catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException e) {                
            	try {
    				vacunaDao.closeConn();
    			} catch (SQLException e1) {
    				// TODO Auto-generated catch block
    			}
            	showErrorMessage();
            }
    	}
    }
    
    private void showErrorMessage() {
    	Object[] options = {prop.getProperty("cerrar")};
    	JOptionPane infoPane = new JOptionPane(
    			prop.getProperty("error8"),
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
    
    private void showErrorMessage(String fieldError) {
    	Object[] options = {prop.getProperty("cerrar")};
    	JOptionPane infoPane = new JOptionPane(
    			prop.getProperty("error"+ fieldError),
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
