package view;

import model.ItemMap;
import model.Paciente;
import model.PacienteDAO;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Map;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.MatteBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import properties.MyProperties;

/**
 *
 * @author diaz
 */
public class PacienteCrear extends JDialog {
    
    private JComboBox idOrfanato;
    private Map fkOrfanatos;
    private Timestamp fecha;
    private JTextField nombrePaciente = new JTextField();
    private JTextField apellidosPaciente = new JTextField();
    private JComboBox genPaciente;
    private JComboBox edadPaciente;
    private JTextArea antecedPaciente = new JTextArea();
    private JFileChooser fotoPaciente = new JFileChooser();
    private JTextField urlFoto = new JTextField();
    
    private JTable pacienteTable;
    private MyProperties prop;
    private PacienteDAO pacienteDao;
    //private HashMap<String, Integer> listaClientes = new HashMap();
    //private Object [] claves;
        
    public PacienteCrear(JTable pacienteTable, PacienteView frame, MyProperties prop) {
            
    	super(frame, prop.getProperty("titulo4"), ModalityType.DOCUMENT_MODAL);
        this.prop = prop;
        this.pacienteTable = pacienteTable;
        this.setSize(600, 400);
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
        JLabel l = new JLabel(prop.getProperty("anino"), SwingConstants.LEFT);
        float[] hsb;
        hsb = Color.RGBtoHSB(230,230,230,new float[3]); 
        c.setBackground(Color.getHSBColor(hsb[0], hsb[1], hsb[2]));
        c.setBorder(BorderFactory.createEmptyBorder(5, 0, 10, 0));
        
        hsb = Color.RGBtoHSB(200,200,200,new float[3]); 
        Border border = new MatteBorder(0, 0, 1, 0, Color.getHSBColor(hsb[0], hsb[1], hsb[2]));
        hsb = Color.RGBtoHSB(153,153,153,new float[3]); 
        l.setForeground(Color.getHSBColor(hsb[0], hsb[1], hsb[2]));
        l.setPreferredSize(new Dimension(800, 45));
        l.setSize(800, 45);
        l.setMaximumSize(new Dimension(800, 45));
        l.setVerticalAlignment(SwingConstants.CENTER);
        l.setHorizontalAlignment(JLabel.CENTER);
        l.setFont(new Font("sans-serif",Font.PLAIN,22));
        l.setBorder(border);
        c.add(l);
        
        return c;
    }
    
    private JPanel createForm() {

        JPanel form = new JPanel();
        Font labelFont = new Font("sans-serif",Font.BOLD,12);
        Font textfFont = new Font("sans-serif",Font.BOLD,12);
        
        try {  
        
        	float[] hsb;
            form.setLayout(new GridLayout(0,2));
            hsb = Color.RGBtoHSB(240,240,240,new float[3]);
            form.setBackground(Color.getHSBColor(hsb[0], hsb[1], hsb[2]));
            
            /* CAMPOS */
            pacienteDao = new PacienteDAO();
            
            /* Orfanato */
            
            // - Label
            JLabel idOrfanatoLabel = new JLabel(prop.getProperty("centro"));
            idOrfanatoLabel.setFont(labelFont);
            idOrfanatoLabel.setHorizontalAlignment(JLabel.CENTER);
            idOrfanatoLabel.setPreferredSize(new Dimension(300, 25));
            idOrfanatoLabel.setSize(300, 25);
            idOrfanatoLabel.setMaximumSize(new Dimension(300, 25));
            idOrfanatoLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 10));
            // - Field
            ComboBoxModel model;
            fkOrfanatos = pacienteDao.fkOrfanatos(); 
            model = new DefaultComboBoxModel(fkOrfanatos.values().toArray());
            idOrfanato = new JComboBox(model);
            idOrfanato.setFont(textfFont);
            // - Add
            form.add(idOrfanatoLabel);
            form.add(idOrfanato);
            
            
            /* Fecha */
            
            // - Field
            Calendar calendar = Calendar.getInstance();
            java.sql.Timestamp fecha = new java.sql.Timestamp(calendar.getTime().getTime());

            
            /* Nombre */

            // - Label
            JLabel nombrePacienteLabel = new JLabel(prop.getProperty("nombren"));
            nombrePacienteLabel.setFont(labelFont);
            nombrePacienteLabel.setHorizontalAlignment(JLabel.CENTER);
            nombrePacienteLabel.setPreferredSize(new Dimension(300, 25));
            nombrePacienteLabel.setSize(300, 25);
            nombrePacienteLabel.setMaximumSize(new Dimension(300, 25));
            nombrePacienteLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 10));
            // - Field
            nombrePaciente.setFont(textfFont);
            nombrePaciente.setColumns(30);
            // - Add
            form.add(nombrePacienteLabel);
            form.add(nombrePaciente);

            
            /* Apellidos */

            // - Label
            JLabel apellidosPacienteLabel = new JLabel(prop.getProperty("apellidosn"));
            apellidosPacienteLabel.setFont(labelFont);
            apellidosPacienteLabel.setHorizontalAlignment(JLabel.CENTER);
            apellidosPacienteLabel.setPreferredSize(new Dimension(300, 25));
            apellidosPacienteLabel.setSize(300, 25);
            apellidosPacienteLabel.setMaximumSize(new Dimension(300, 25));
            apellidosPacienteLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 10));
            // - Field
            apellidosPaciente.setFont(textfFont);
            apellidosPaciente.setColumns(30);
            // - Add
            form.add(apellidosPacienteLabel);
            form.add(apellidosPaciente);
            
            
            /* Genero */
            
            // - Label
            JLabel genPacienteLabel = new JLabel(prop.getProperty("genn"));
            genPacienteLabel.setFont(labelFont);
            genPacienteLabel.setHorizontalAlignment(JLabel.CENTER);
            genPacienteLabel.setPreferredSize(new Dimension(300, 25));
            genPacienteLabel.setSize(300, 25);
            genPacienteLabel.setMaximumSize(new Dimension(300, 25));
            genPacienteLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 10));
            // - Field
            genPaciente = new JComboBox();
            genPaciente.setFont(textfFont);
            genPaciente.setModel(new DefaultComboBoxModel(new String[] {"hombre","mujer"}));
            // - Add
            form.add(genPacienteLabel);
            form.add(genPaciente);
            
            
            /* Edad */

            // - Label
            JLabel edadPacienteLabel = new JLabel(prop.getProperty("edadn"));
            edadPacienteLabel.setFont(labelFont);
            edadPacienteLabel.setHorizontalAlignment(JLabel.CENTER);
            edadPacienteLabel.setPreferredSize(new Dimension(300, 25));
            edadPacienteLabel.setSize(300, 25);
            edadPacienteLabel.setMaximumSize(new Dimension(300, 25));
            edadPacienteLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 10));
            // - Field
            edadPaciente = new JComboBox();
            edadPaciente.setFont(textfFont);
            String edades[] = new String[50];
            for(int i = 0; i < edades.length; i++) edades[i]=String.valueOf(i+1);
            edadPaciente.setModel(new DefaultComboBoxModel(edades));
            // - Add
            form.add(edadPacienteLabel);
            form.add(edadPaciente);
            
            
            /* Antecedentes */

            // - Label
            JLabel antecedPacienteLabel = new JLabel(prop.getProperty("antecedn"));
            antecedPacienteLabel.setFont(labelFont);
            antecedPacienteLabel.setHorizontalAlignment(JLabel.CENTER);
            antecedPacienteLabel.setPreferredSize(new Dimension(300, 25));
            antecedPacienteLabel.setSize(300, 25);
            antecedPacienteLabel.setMaximumSize(new Dimension(300, 25));
            antecedPacienteLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 10));
            // - Field
            antecedPaciente.setFont(textfFont);
            antecedPaciente.setEditable(true);
            antecedPaciente.setSize(280, 2);
            JScrollPane scroll = new JScrollPane(antecedPaciente);
            scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

            // - Add
            form.add(antecedPacienteLabel);
            form.add(scroll);
            
            
            /* Foto */

            // - Label
            JLabel fotoPacienteLabel = new JLabel(prop.getProperty("picturen"));
            fotoPacienteLabel.setFont(labelFont);
            fotoPacienteLabel.setHorizontalAlignment(JLabel.CENTER);
            fotoPacienteLabel.setPreferredSize(new Dimension(300, 25));
            fotoPacienteLabel.setSize(300, 25);
            fotoPacienteLabel.setMaximumSize(new Dimension(300, 25));
            fotoPacienteLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 10));
            // - Field
            JButton openButton = new JButton("Open a File...");
            openButton.setActionCommand("openfile");
            openButton.addActionListener(new ButtonListener(this));
            urlFoto.setEditable(false);
            // - Add
            form.add(fotoPacienteLabel);
            form.add(openButton);
            form.add(urlFoto);

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
    	@Override
        public void actionPerformed(ActionEvent event) {
            try {
            	ItemMap item;
                String key = event.getActionCommand();
                switch (key) {
                    case "botonpaciente":
                        Paciente paciente = new Paciente();
                        
                        item = (ItemMap) idOrfanato.getSelectedItem();
                        paciente.setidOrfanato(item.getId());
                        paciente.setFecha(fecha);
                        paciente.setNombrePaciente(nombrePaciente.getText());
                        paciente.setApellidosPaciente(apellidosPaciente.getText());
                        String genero = genPaciente.getSelectedItem().toString();
                        if(genero.equalsIgnoreCase("hombre")) genero = "H";
                        else genero = "M";
                        paciente.setGenPaciente(genero);
                        paciente.setEdadPaciente(Integer.parseInt(edadPaciente.getSelectedItem().toString()));
                        paciente.setAntecedPaciente(antecedPaciente.getText());
                        paciente.setFotoPaciente(urlFoto.getText());
                        /*
                        COPIAR FOTO A CARPETA DE APLICACIÓN OCULTA EN CARPETA DE USUARIO
                        */

                        pacienteDao.crearPaciente(paciente);
                        /*
                         * Actualizamos el modelo
                         */
                        pacienteTable.setModel(pacienteDao.getTablaPacientes());
                        pacienteTable.updateUI();
                         /*
                         * Cerramos la ventana
                         */
                        dispose();
                        break;
                    case "openfile":
                    	FileNameExtensionFilter filter = new FileNameExtensionFilter(
                    	        "JPG, PNG & GIF Images", "jpg", "gif", "png");
                    	fotoPaciente.setFileFilter(filter);
                    	int returnVal = fotoPaciente.showOpenDialog(dialog);
                    	if (returnVal == JFileChooser.APPROVE_OPTION) {
                            File file = fotoPaciente.getSelectedFile();
                            //This is where a real application would open the file.
                            urlFoto.setText(file.getAbsolutePath());
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
            	try {
					pacienteDao.closeConn();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					System.out.println(e1.getMessage());
				}
            	String a;
            	a = e.getMessage();
            	a = a.substring(a.indexOf("'") + 1);
            	a = a.substring(0, a.indexOf("'"));
            	
            	switch(a) {
            	case "nombre":
            		showErrorMessage(a);
            		break;
            	case "apellidos":
            		showErrorMessage(a);
            		break;
            	case "antecedentes":
            		showErrorMessage(a);
            		break;
            	default:
            		showErrorMessage();
            	}
            }
        }
    }
    
    private void showErrorMessage() {
    	Object[] options = {prop.getProperty("cerrar")};
    	JOptionPane infoPane = new JOptionPane(
    			prop.getProperty("error2"),
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