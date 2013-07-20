package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.sql.Date;
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

import model.AlergiaDAO;
import model.ItemMap;

import properties.MyProperties;

public class PacienteAlergiaCrear extends JDialog {
	//Fields
	private JTextField idPaciente;
	private JComboBox idAlergia;
    private JComboBox dia = new JComboBox();
    private JComboBox mes = new JComboBox();
    private JComboBox anyo = new JComboBox();
	// Resources
	private JTable alergiasTable;
	private PacienteAlergias parent;
	private MyProperties prop;
	private int idpaciente;
	private  AlergiaDAO alergiaDao;
	
	PacienteAlergiaCrear(JTable alergiasTable, PacienteAlergias parent, MyProperties prop, int idpaciente) {
		super(parent, prop.getProperty("titulo9"), ModalityType.DOCUMENT_MODAL);
        this.prop = prop;
        this.alergiasTable = alergiasTable;
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
        JLabel l = new JLabel(prop.getProperty("aalergiapac"), SwingConstants.LEFT);
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
            alergiaDao = new AlergiaDAO(prop);
            
            /* IdPaciente */

            // - Label
            JLabel idPacienteLabel = new JLabel(prop.getProperty("idninal"));
            idPacienteLabel.setFont(labelFont);
            idPacienteLabel.setHorizontalAlignment(JLabel.CENTER);
            idPacienteLabel.setPreferredSize(new Dimension(280, 25));
            idPacienteLabel.setSize(280, 25);
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
            
            /* Alergia */
            
            // - Label
            JLabel idAlergiaLabel = new JLabel(prop.getProperty("nombrealergiaa"));
            idAlergiaLabel.setFont(labelFont);
            idAlergiaLabel.setHorizontalAlignment(JLabel.CENTER);
            idAlergiaLabel.setBorder(null);
            idAlergiaLabel.setPreferredSize(new Dimension(280, 25));
            idAlergiaLabel.setSize(280, 25);
            idAlergiaLabel.setMaximumSize(new Dimension(300, 25));
            // - Field
            ComboBoxModel model;
            Map fkAlergias;
            fkAlergias = alergiaDao.fkAlergias(); 
            model = new DefaultComboBoxModel(fkAlergias.values().toArray());
            idAlergia = new JComboBox(model);
            idAlergia.setFont(textfFont);
            idAlergia.setSize(new Dimension(200, 25));
            idAlergia.setPreferredSize(new Dimension(200, 25));
            idAlergia.setSelectedItem(null);
            // - Add
            form.add(idAlergiaLabel);
            JPanel moreless = new JPanel();
            moreless.setLayout(new FlowLayout(FlowLayout.LEADING, 0, 0));
            moreless.setSize(new Dimension(300, 25));
            moreless.setPreferredSize(new Dimension(300, 25));
            moreless.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
            idAlergia.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 10));
            moreless.add(idAlergia);
            //botones mas menos
            JButton more, less;
            ImageIcon plus = new ImageIcon(getClass().getClassLoader().getResource("resources/plus.png"));
            more = new JButton(plus);
            more.setContentAreaFilled(false);
            more.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e)
                {
                    //Execute when button is pressed
                    AlergiaCrear crearAlergia = new AlergiaCrear(getInstance(), idAlergia, prop);
                    crearAlergia.setVisible(true);
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
                	if(idAlergia.getSelectedItem()==null) {
                		showErrorMessage("noalergia");
                	} else {
                    //Execute when button is pressed
                		new AlergiaBorrar(getInstance(), idAlergia, prop, (ItemMap)idAlergia.getSelectedItem());
                	}
                    
                }
            });
            //less.setVerticalAlignment(SwingConstants.TOP);
            less.setBorder(BorderFactory.createEmptyBorder());
            less.setSize(new Dimension(16, 16));
            less.setPreferredSize(new Dimension(16, 16));
            
            moreless.add(less);
            
            form.add(moreless);
            
            
            /* FECHA */
            
            JLabel fechaNacimientoLabel = new JLabel(prop.getProperty("fechaalergia"));
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
            int inicio = 1988;
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
        	e.printStackTrace();
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
                    	if(idAlergia.getSelectedItem()==null) {
                    		showErrorMessage("noalergia2");
                    	} else {

                    		// id alergia
	                        item = (ItemMap) idAlergia.getSelectedItem();
	                        
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
	                        alergiaDao.crearPacAl(Integer.parseInt(idPaciente.getText()), item.getId(), sqlDate);

	                        /*
	                         * Actualizamos el modelo
	                         */
	                        alergiasTable.setModel(alergiaDao.getTablaPacAlergias(parent.getIdPaciente()));
	                        alergiasTable.updateUI();
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
            	/* We just manage the TRIGGER
            	 * `pacientes_before_trigger` BEFORE INSERT ON `pacientes`
            	 */
            }
    	}
    }
    
    private void showErrorMessage() {
    	Object[] options = {prop.getProperty("cerrar")};
    	JOptionPane infoPane = new JOptionPane(
    			prop.getProperty("error7"),
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
