/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import model.Paciente;
import model.PacienteDAO;

import java.sql.SQLException;
import java.text.ParseException;

import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JTable;

import properties.MyProperties;

/**
 *
 * @author juanitopons
 */
public class PacienteBorrar {
    private String[][] dialogs = new String[2][4];
    private PacienteDAO pacienteDao;
    private Paciente paciente;
	
    public void cambiarIdioma(MyProperties prop) {    	
    	dialogs[0][0] = prop.getProperty("dialog21")+" '" + paciente.getNombrePaciente() + " " + paciente.getApellidosPaciente() + "' (ID = " + paciente.getidPaciente() + ") ?";
    	dialogs[0][1] = prop.getProperty("dialog22");
    	dialogs[0][2] = prop.getProperty("aceptar");
    	dialogs[0][3] = prop.getProperty("cancelar");
    	
    	dialogs[1][0] = prop.getProperty("errorp");
    	dialogs[1][1] = prop.getProperty("error");
    	dialogs[1][2] = prop.getProperty("cerrar");
    	//.setText a todo lo que deba hacerse setText (mirar Strings)
    }
    
    public PacienteBorrar(MyProperties prop, JDialog pacienteView, int idpaciente) {
    	// Recuperamos la paciente a través de la clave primaria
    	pacienteDao = new PacienteDAO();
    	try {
    		paciente = pacienteDao.leerPaciente(idpaciente);
    	} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException | ParseException e) {
    		errorMessage(pacienteView);
    	}
    	cambiarIdioma(prop);
    }
    
    public void init(JTable pacienteTable, JDialog pacienteView) {

        try {
            // Dialogo de confirmación
            Object[] options = {dialogs[0][2], dialogs[0][3]};
        	JOptionPane delPane = new JOptionPane(
                    dialogs[0][0],
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.YES_NO_OPTION,
                    null,
                    options,
                    dialogs[0][3]);
        	JDialog dialog = new JDialog();
        	dialog = delPane.createDialog(pacienteView, dialogs[0][1]);
        	dialog.setLocationRelativeTo(pacienteView);
        	dialog.setVisible(true);
        	
        	String reply = (String) delPane.getValue();

        	if(reply!=null) {
	            if (reply.equalsIgnoreCase(dialogs[0][2])) {
	                    // Borramos la paciente de la base de datos
	                    pacienteDao.borrarPaciente(paciente.getidPaciente());
	                    /*
	                    * Actualizamos el modelo
	                    */
	                    pacienteTable.setModel(pacienteDao.getTablaPacientes());
	                    pacienteTable.updateUI();
                }
        	}
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException e) {
        	try {
				pacienteDao.closeConn();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
        	errorMessage(pacienteView);
        }
    }
    
    public void errorMessage(JDialog pacienteView) {
    	Object[] options = {dialogs[1][2]};
    	JOptionPane infoPane = new JOptionPane(
                dialogs[1][0],
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.ERROR_MESSAGE,
                null,
                options,
                dialogs[1][2]);
    	JDialog dialog = new JDialog();
    	dialog = infoPane.createDialog(pacienteView, dialogs[1][1]);
    	dialog.setLocationRelativeTo(pacienteView);
    	dialog.setVisible(true);
    }
}
