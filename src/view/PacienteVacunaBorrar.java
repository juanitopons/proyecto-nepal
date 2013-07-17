package view;

import java.sql.SQLException;

import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JTable;

import model.VacunaDAO;
import properties.MyProperties;

public class PacienteVacunaBorrar {
    private String[][] dialogs = new String[2][4];
    private VacunaDAO vacunaDao;
    private JDialog pacienteVacunas;
    private JTable vacunasTable;
    int data[] = new int[2];
	    
    public void cambiarIdioma(MyProperties prop) {    	
    	dialogs[0][0] = prop.getProperty("dialog61")+" (ID1 = " + data[0] + "; ID2 = " + data[1] + ") ?";
    	dialogs[0][1] = prop.getProperty("dialog62");
    	dialogs[0][2] = prop.getProperty("aceptar");
    	dialogs[0][3] = prop.getProperty("cancelar");
    	
    	dialogs[1][0] = prop.getProperty("errorvac");
    	dialogs[1][1] = prop.getProperty("error");
    	dialogs[1][2] = prop.getProperty("cerrar");
    	//.setText a todo lo que deba hacerse setText (mirar Strings)
    }
	    
    public PacienteVacunaBorrar(JTable vacunasTable, JDialog parent, MyProperties prop, int idpaciente, int idvacuna) {
        this.vacunasTable = vacunasTable;
        this.pacienteVacunas = parent;
        this.data[0] = idpaciente;
        this.data[1] = idvacuna;
    	vacunaDao = new VacunaDAO(prop);
    	cambiarIdioma(prop);
    	init();
    }
    
    public void init() {

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
        	dialog = delPane.createDialog(pacienteVacunas, dialogs[0][1]);
        	dialog.setLocationRelativeTo(pacienteVacunas);
        	dialog.setVisible(true);
        	
        	String reply = (String) delPane.getValue();

        	if(reply!=null) {
	            if (reply.equalsIgnoreCase(dialogs[0][2])) {
	                    // Borramos la visita de la base de datos
	                    vacunaDao.borrarPacVac(data[0], data[1]);
	                    /*
	                    * Actualizamos el modelo
	                    */
	                    vacunasTable.setModel(vacunaDao.getTablaPacVacunas(data[0]));
	                    vacunasTable.updateUI();
                }
        	}
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException e) {
        	errorMessage();
        }
    }

    public void errorMessage() {
    	Object[] options = {dialogs[1][2]};
    	JOptionPane infoPane = new JOptionPane(
                dialogs[1][0],
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.ERROR_MESSAGE,
                null,
                options,
                dialogs[1][2]);
    	JDialog dialog = new JDialog();
    	dialog = infoPane.createDialog(pacienteVacunas, dialogs[1][1]);
    	dialog.setLocationRelativeTo(pacienteVacunas);
    	dialog.setVisible(true);
    }
}
