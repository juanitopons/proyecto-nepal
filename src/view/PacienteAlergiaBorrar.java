package view;

import java.sql.SQLException;

import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JTable;

import properties.MyProperties;

import model.AlergiaDAO;
import model.ItemMap;
import model.OrfanatoDAO;
import model.VisitaDAO;

public class PacienteAlergiaBorrar {
    private String[][] dialogs = new String[2][4];
    private AlergiaDAO alergiaDao;
    private JDialog pacienteAlergias;
    private JTable alergiasTable;
    int data[] = new int[2];
	    
    public void cambiarIdioma(MyProperties prop) {    	
    	dialogs[0][0] = prop.getProperty("dialog51")+" (ID1 = " + data[0] + "; ID2 = " + data[1] + ") ?";
    	dialogs[0][1] = prop.getProperty("dialog52");
    	dialogs[0][2] = prop.getProperty("aceptar");
    	dialogs[0][3] = prop.getProperty("cancelar");
    	
    	dialogs[1][0] = prop.getProperty("errora");
    	dialogs[1][1] = prop.getProperty("error");
    	dialogs[1][2] = prop.getProperty("cerrar");
    	//.setText a todo lo que deba hacerse setText (mirar Strings)
    }
	    
    public PacienteAlergiaBorrar(JTable alergiasTable, JDialog parent, MyProperties prop, int idpaciente, int idalergia) {
        this.alergiasTable = alergiasTable;
        this.pacienteAlergias = parent;
        this.data[0] = idpaciente;
        this.data[1] = idalergia;
    	alergiaDao = new AlergiaDAO(prop);
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
        	dialog = delPane.createDialog(pacienteAlergias, dialogs[0][1]);
        	dialog.setLocationRelativeTo(pacienteAlergias);
        	dialog.setVisible(true);
        	
        	String reply = (String) delPane.getValue();

        	if(reply!=null) {
	            if (reply.equalsIgnoreCase(dialogs[0][2])) {
	                    // Borramos la visita de la base de datos
	                    alergiaDao.borrarPacAl(data[0], data[1]);
	                    /*
	                    * Actualizamos el modelo
	                    */
	                    alergiasTable.setModel(alergiaDao.getTablaPacAlergias(data[0]));
	                    alergiasTable.updateUI();
                }
        	}
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException e) {
        	try {
				alergiaDao.closeConn();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
			}
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
    	dialog = infoPane.createDialog(pacienteAlergias, dialogs[1][1]);
    	dialog.setLocationRelativeTo(pacienteAlergias);
    	dialog.setVisible(true);
    }
}
