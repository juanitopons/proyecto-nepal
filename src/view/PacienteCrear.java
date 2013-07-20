package view;

import model.ItemMap;
import model.Paciente;
import model.PacienteDAO;
import model.VisitaDAO;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.MatteBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import properties.MyProperties;
import view.OrfanatoCrear;
import view.OrfanatoBorrar;

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
    private JTextArea antecedPaciente = new JTextArea(4, 22);
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
        this.setSize(600, 500);
    	this.setLocationRelativeTo(frame);
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
    
    private JDialog getInstance() {
    	return this;
    }
    
    private JPanel createForm() {

        JPanel form = new JPanel();
        form.setBorder(BorderFactory.createEmptyBorder(5, 0, 5, 5));
        Font labelFont = new Font("sans-serif",Font.BOLD,12);
        Font textfFont = new Font("sans-serif",Font.BOLD,12);
        
        try {  
        
        	float[] hsb;
        	form.setLayout(new FlowLayout(FlowLayout.LEADING));
            hsb = Color.RGBtoHSB(240,240,240,new float[3]);
            form.setBackground(Color.getHSBColor(hsb[0], hsb[1], hsb[2]));
            
            /* CAMPOS */
            pacienteDao = new PacienteDAO();
            
            /* Orfanato */
            
            // - Label
            JLabel idOrfanatoLabel = new JLabel(prop.getProperty("centro"));
            idOrfanatoLabel.setFont(labelFont);
            idOrfanatoLabel.setHorizontalAlignment(JLabel.CENTER);
            idOrfanatoLabel.setPreferredSize(new Dimension(280, 28));
            idOrfanatoLabel.setSize(280, 28);
            idOrfanatoLabel.setMaximumSize(new Dimension(280, 28));
            idOrfanatoLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 10));
            // - Field
            ComboBoxModel model;
            fkOrfanatos = pacienteDao.fkOrfanatos(); 
            model = new DefaultComboBoxModel(fkOrfanatos.values().toArray());
            idOrfanato = new JComboBox(model);
            idOrfanato.setFont(textfFont);
            idOrfanato.setSize(new Dimension(200, 25));
            idOrfanato.setPreferredSize(new Dimension(200, 25));
            idOrfanato.setSelectedItem(null);
            // - Add
            form.add(idOrfanatoLabel);
            JPanel moreless = new JPanel();
            moreless.setLayout(new FlowLayout(FlowLayout.LEADING, 0, 0));
            moreless.setSize(new Dimension(280, 25));
            moreless.setPreferredSize(new Dimension(280, 25));
            moreless.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
            idOrfanato.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 10));
            moreless.add(idOrfanato);
            //botones mas menos
            JButton more, less;
            ImageIcon plus = new ImageIcon(getClass().getClassLoader().getResource("resources/plus.png"));
            more = new JButton(plus);
            more.setContentAreaFilled(false);
            more.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e)
                {
                    //Execute when button is pressed
                    OrfanatoCrear crearOrf = new OrfanatoCrear(getInstance(), idOrfanato, prop);
                    crearOrf.setVisible(true);
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
                	if(idOrfanato.getSelectedItem()==null) {
                		showErrorMessage("noshelter");
                	} else {
                    //Execute when button is pressed
                		new OrfanatoBorrar(getInstance(), idOrfanato, prop, (ItemMap)idOrfanato.getSelectedItem());
                	}
                    
                }
            });
            //less.setVerticalAlignment(SwingConstants.TOP);
            less.setBorder(BorderFactory.createEmptyBorder());
            less.setSize(new Dimension(16, 16));
            less.setPreferredSize(new Dimension(16, 16));
            
            moreless.add(less);
            
            form.add(moreless);
            
            
            /* Fecha */
            
            // - Field
            Calendar calendar = Calendar.getInstance();
            java.sql.Timestamp fecha = new java.sql.Timestamp(calendar.getTime().getTime());

            
            /* Nombre */

            // - Label
            JLabel nombrePacienteLabel = new JLabel(prop.getProperty("nombren"));
            nombrePacienteLabel.setFont(labelFont);
            nombrePacienteLabel.setHorizontalAlignment(JLabel.CENTER);
            nombrePacienteLabel.setPreferredSize(new Dimension(280, 28));
            nombrePacienteLabel.setSize(280, 28);
            nombrePacienteLabel.setMaximumSize(new Dimension(280, 28));
            // - Field
            nombrePaciente.setFont(textfFont);
            nombrePaciente.setColumns(23);
            nombrePaciente.setPreferredSize(new Dimension(280, 28));
            nombrePaciente.setSize(280, 28);
            nombrePaciente.setMaximumSize(new Dimension(280, 28));
            // - Add
            form.add(nombrePacienteLabel);
            form.add(nombrePaciente);

            
            /* Apellidos */

            // - Label
            JLabel apellidosPacienteLabel = new JLabel(prop.getProperty("apellidosn"));
            apellidosPacienteLabel.setFont(labelFont);
            apellidosPacienteLabel.setHorizontalAlignment(JLabel.CENTER);
            apellidosPacienteLabel.setPreferredSize(new Dimension(280, 28));
            apellidosPacienteLabel.setSize(280, 28);
            apellidosPacienteLabel.setMaximumSize(new Dimension(280, 28));
            // - Field
            apellidosPaciente.setFont(textfFont);
            apellidosPaciente.setColumns(23);
            apellidosPaciente.setPreferredSize(new Dimension(280, 28));
            apellidosPaciente.setSize(280, 28);
            apellidosPaciente.setMaximumSize(new Dimension(280, 28));
            // - Add
            form.add(apellidosPacienteLabel);
            form.add(apellidosPaciente);
            
            
            /* Genero */
            
            // - Label
            JLabel genPacienteLabel = new JLabel(prop.getProperty("genn"));
            genPacienteLabel.setFont(labelFont);
            genPacienteLabel.setHorizontalAlignment(JLabel.CENTER);
            genPacienteLabel.setPreferredSize(new Dimension(280, 28));
            genPacienteLabel.setSize(280, 28);
            genPacienteLabel.setMaximumSize(new Dimension(280, 28));
            // - Field
            genPaciente = new JComboBox();
            genPaciente.setFont(textfFont);
            genPaciente.setModel(new DefaultComboBoxModel(new String[] {"hombre","mujer"}));
            genPaciente.setPreferredSize(new Dimension(280, 28));
            genPaciente.setSize(280, 28);
            genPaciente.setMaximumSize(new Dimension(280, 28));
            // - Add
            form.add(genPacienteLabel);
            form.add(genPaciente);
            
            
            /* Edad */

            // - Label
            JLabel edadPacienteLabel = new JLabel(prop.getProperty("edadn"));
            edadPacienteLabel.setFont(labelFont);
            edadPacienteLabel.setHorizontalAlignment(JLabel.CENTER);
            edadPacienteLabel.setPreferredSize(new Dimension(280, 28));
            edadPacienteLabel.setSize(280, 28);
            edadPacienteLabel.setMaximumSize(new Dimension(280, 28));
            // - Field
            edadPaciente = new JComboBox();
            edadPaciente.setFont(textfFont);
            String edades[] = new String[50];
            for(int i = 0; i < edades.length; i++) edades[i]=String.valueOf(i+1);
            edadPaciente.setModel(new DefaultComboBoxModel(edades));
            edadPaciente.setPreferredSize(new Dimension(280, 28));
            edadPaciente.setSize(280, 28);
            edadPaciente.setMaximumSize(new Dimension(280, 28));
            // - Add
            form.add(edadPacienteLabel);
            form.add(edadPaciente);
            
            
            /* Antecedentes */

            // - Label
            JLabel antecedPacienteLabel = new JLabel(prop.getProperty("antecedn"));
            antecedPacienteLabel.setFont(labelFont);
            antecedPacienteLabel.setHorizontalAlignment(JLabel.CENTER);
            antecedPacienteLabel.setPreferredSize(new Dimension(300, 25));
            antecedPacienteLabel.setPreferredSize(new Dimension(280, 28));
            antecedPacienteLabel.setSize(280, 28);
            antecedPacienteLabel.setMaximumSize(new Dimension(280, 28));
            // - Field
            antecedPaciente.setFont(textfFont);
            antecedPaciente.setEditable(true);
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
            fotoPacienteLabel.setPreferredSize(new Dimension(280, 28));
            fotoPacienteLabel.setSize(280, 28);
            fotoPacienteLabel.setMaximumSize(new Dimension(280, 28));
            // - Field
            JButton openButton = new JButton("Open a File...");
            openButton.setActionCommand("openfile");
            openButton.addActionListener(new ButtonListener(this));
            openButton.setPreferredSize(new Dimension(280, 28));
            openButton.setSize(280, 28);
            openButton.setMaximumSize(new Dimension(280, 28));
            urlFoto.setEditable(false);
            urlFoto.setPreferredSize(new Dimension(480, 35));
            urlFoto.setSize(480, 35);
            urlFoto.setBorder(null);
            urlFoto.setMaximumSize(new Dimension(480, 35));
            // - Add
            form.add(fotoPacienteLabel);
            
            JPanel foto = new JPanel();
            foto.setBorder(null);
            foto.setLayout(new BoxLayout(foto, BoxLayout.Y_AXIS));
            foto.setPreferredSize(new Dimension(280, 65));
            foto.setSize(280, 65);
            foto.setMaximumSize(new Dimension(280, 65));
            foto.add(openButton);
            foto.add(urlFoto);
            form.add(foto);

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
                    	if(idOrfanato.getSelectedItem()==null) {
                    		showErrorMessage("noshelter2");
                    	} else {
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
	                        
	                        File hideDire = new File(".proyectonepal");
	                        if(!hideDire.exists()){
	                        	hideDire.mkdir();
	                        }
	                        File foto = new File(urlFoto.getText());
	                        // Extensión
	                        int dotposition = urlFoto.getText().lastIndexOf(".");
	                        String extension = urlFoto.getText().substring(dotposition + 1, urlFoto.getText().length()); 
	                        File foto2 = new File(".proyectonepal/" + nombrePaciente.getText().toLowerCase().replaceAll("\\s","") +""+apellidosPaciente.getText().toLowerCase().replaceAll("\\s","")+"."+extension);
	                        if(!foto2.exists()) {
	                        	foto.renameTo(foto2);
	                        }
	                        paciente.setFotoPaciente(foto2.getAbsolutePath());
	
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
                    	}
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
