package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
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
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.border.MatteBorder;

import model.Orfanato;
import model.OrfanatoDAO;
import model.PacienteDAO;

import properties.MyProperties;

public class OrfanatoCrear extends JDialog {
	private JTextField nombreOrfanato;
	private JComboBox orfanatosList;
	private MyProperties prop;
	private JDialog pacienteCrear;
	private OrfanatoDAO orfanatoDao;

	public OrfanatoCrear(JDialog parent, JComboBox orfanatosList, MyProperties prop) {
		super(parent, prop.getProperty("titulo7"), ModalityType.DOCUMENT_MODAL);
        this.prop = prop;
        this.orfanatosList = orfanatosList;
        this.pacienteCrear = parent;
		
        this.setSize(400, 200);
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
        JLabel l = new JLabel(prop.getProperty("aorfanto"), SwingConstants.LEFT);
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
            JLabel nombreLabel = new JLabel(prop.getProperty("nombreo"));
            nombreLabel.setFont(labelFont);
            nombreLabel.setHorizontalAlignment(JLabel.CENTER);
            nombreLabel.setPreferredSize(new Dimension(200, 25));
            nombreLabel.setSize(200, 25);
            nombreLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 10));
            // - Field
            nombreOrfanato = new JTextField();
            nombreOrfanato.setFont(textfFont);
            nombreOrfanato.setColumns(13);
            nombreOrfanato.setSize(new Dimension(200, 25));
            nombreOrfanato.setPreferredSize(new Dimension(200, 25));
            
            // - Add
            form.add(nombreLabel);
            form.add(nombreOrfanato);
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
            int length = nombreOrfanato.getText().length();
            if(length <= 0 || length > 60) {
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
                    	Orfanato orfanato = new Orfanato();
                    	if(checkNombreField()) {
                    		orfanato.setNombreOrfanato(nombreOrfanato.getText());
                    		orfanatoDao = new OrfanatoDAO();
                            orfanatoDao.crearOrfanato(orfanato);
                            /*
                             * Actualizamos el JComboBox
                             */
                            PacienteDAO pacienteDao = new PacienteDAO();
                            Map fkOrfanatos = null;
							try {
								fkOrfanatos = pacienteDao.fkOrfanatos();
							} catch (ParseException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							} 
                            orfanatosList.setModel(new DefaultComboBoxModel(fkOrfanatos.values().toArray()));
                            dispose();
                    	} else {
                    		showErrorMessage(1);
                    	}
                }
            }
            catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException e) {                
            	try {
    				orfanatoDao.closeConn();
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
    			prop.getProperty("error4"),
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
    			prop.getProperty("error4"+String.valueOf(i)),
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.ERROR_MESSAGE,
                null,
                options,
                prop.getProperty("cerrar"));
    	JDialog dialog = new JDialog();
    	dialog = infoPane.createDialog(pacienteCrear, prop.getProperty("error"));
    	dialog.setLocationRelativeTo(pacienteCrear);
    	dialog.setVisible(true);
    }
	
}
