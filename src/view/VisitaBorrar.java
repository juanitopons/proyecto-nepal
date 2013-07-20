package view;

import java.sql.SQLException;
import java.text.ParseException;

import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JTable;

import model.Paciente;
import model.PacienteDAO;
import model.VisitaDAO;
import properties.MyProperties;

public class VisitaBorrar {
    private String[][] dialogs = new String[2][4];
    private PacienteDAO pacienteDao;
    private VisitaDAO visitaDao;
    private Paciente paciente;
    int idvisita;
	
    public void cambiarIdioma(MyProperties prop) {    	
    	dialogs[0][0] = prop.getProperty("dialog31")+" '" + paciente.getNombrePaciente() + " " + paciente.getApellidosPaciente() + "' (IDVISITA = " + idvisita + ") ?";
    	dialogs[0][1] = prop.getProperty("dialog32");
    	dialogs[0][2] = prop.getProperty("aceptar");
    	dialogs[0][3] = prop.getProperty("cancelar");
    	
    	dialogs[1][0] = prop.getProperty("errorv");
    	dialogs[1][1] = prop.getProperty("error");
    	dialogs[1][2] = prop.getProperty("cerrar");
    	//.setText a todo lo que deba hacerse setText (mirar Strings)
    }
    
    public VisitaBorrar(MyProperties prop, PacienteVisitas pacienteVisitas, int idvisita) {
    	// Recuperamos la paciente a través de la clave primaria
    	this.idvisita = idvisita;
    	int idpaciente = pacienteVisitas.getIdPaciente();
    	pacienteDao = new PacienteDAO();
    	try {
    		paciente = pacienteDao.leerPaciente(idpaciente);
    	} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException | ParseException e) {
    		errorMessage(pacienteVisitas);
    	}
    	cambiarIdioma(prop);
    }
    
    public void init(JTable pacienteTable, PacienteVisitas pacienteVisitas, MyProperties prop) {

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
        	dialog = delPane.createDialog(pacienteVisitas, dialogs[0][1]);
        	dialog.setLocationRelativeTo(pacienteVisitas);
        	dialog.setVisible(true);
        	
        	String reply = (String) delPane.getValue();

        	if(reply!=null) {
	            if (reply.equalsIgnoreCase(dialogs[0][2])) {
	                    // Borramos la visita de la base de datos
	            		visitaDao = new VisitaDAO(prop);
	                    visitaDao.borrarVisita(idvisita);
	                    /*
	                    * Actualizamos el modelo
	                    */
	                    pacienteTable.setModel(visitaDao.getTablaVisitas(paciente.getidPaciente()));
	                    pacienteTable.updateUI();
                }
        	}
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException e) {
        	try {
				visitaDao.closeConn();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
			}
        	errorMessage(pacienteVisitas);
        }
    }
    
    public void errorMessage(JDialog pacienteVisita) {
    	Object[] options = {dialogs[1][2]};
    	JOptionPane infoPane = new JOptionPane(
                dialogs[1][0],
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.ERROR_MESSAGE,
                null,
                options,
                dialogs[1][2]);
    	JDialog dialog = new JDialog();
    	dialog = infoPane.createDialog(pacienteVisita, dialogs[1][1]);
    	dialog.setLocationRelativeTo(pacienteVisita);
    	dialog.setVisible(true);
    }

}
