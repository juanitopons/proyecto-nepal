package view;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.Map;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JOptionPane;

import model.VacunaDAO;
import model.ItemMap;
import properties.MyProperties;

public class VacunaBorrar {
    private String[][] dialogs = new String[2][4];
    private VacunaDAO vacunaDao;
    private ItemMap vacuna;
    private JDialog pacVacCrear;
    private JComboBox vacunasList;
    
    public void cambiarIdioma(MyProperties prop) {    	
    	dialogs[0][0] = prop.getProperty("dialog61")+" '" + vacuna.getNombre() +"' (ID = " + vacuna.getId() + ") ?";
    	dialogs[0][1] = prop.getProperty("dialog62");
    	dialogs[0][2] = prop.getProperty("aceptar");
    	dialogs[0][3] = prop.getProperty("cancelar");
    	
    	dialogs[1][0] = prop.getProperty("errorvac");
    	dialogs[1][1] = prop.getProperty("error");
    	dialogs[1][2] = prop.getProperty("cerrar");
    	//.setText a todo lo que deba hacerse setText (mirar Strings)
    }
    
    public VacunaBorrar(JDialog parent, JComboBox vacunasList, MyProperties prop, ItemMap vacunas) {
        this.vacunasList = vacunasList;
        this.pacVacCrear = parent;
        this.vacuna = vacunas;
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
        	dialog = delPane.createDialog(pacVacCrear, dialogs[0][1]);
        	dialog.setLocationRelativeTo(pacVacCrear);
        	dialog.setVisible(true);
        	
        	String reply = (String) delPane.getValue();

        	if(reply!=null) {
	            if (reply.equalsIgnoreCase(dialogs[0][2])) {
	                    // Borramos el orfanato de la BD
	                    vacunaDao.borrarVacuna(vacuna.getId());
                        /*
                         * Actualizamos el JComboBox
                         */
                        Map fkVacunas = null;
						try {
							fkVacunas = vacunaDao.fkVacunas();
						} catch (ParseException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} 
                        vacunasList.setModel(new DefaultComboBoxModel(fkVacunas.values().toArray()));
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
    	dialog = infoPane.createDialog(pacVacCrear, dialogs[1][1]);
    	dialog.setLocationRelativeTo(pacVacCrear);
    	dialog.setVisible(true);
    }

}
