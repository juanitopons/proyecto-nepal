package view;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.Map;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JOptionPane;

import properties.MyProperties;

import model.AlergiaDAO;
import model.ItemMap;
import model.OrfanatoDAO;
import model.PacienteDAO;

public class AlergiaBorrar {
    private String[][] dialogs = new String[2][4];
    private AlergiaDAO alergiaDao;
    private ItemMap alergia;
    private JDialog pacAlCrear;
    private JComboBox alergiasList;
    
    public void cambiarIdioma(MyProperties prop) {    	
    	dialogs[0][0] = prop.getProperty("dialog51")+" '" + alergia.getNombre() +"' (ID = " + alergia.getId() + ") ?";
    	dialogs[0][1] = prop.getProperty("dialog52");
    	dialogs[0][2] = prop.getProperty("aceptar");
    	dialogs[0][3] = prop.getProperty("cancelar");
    	
    	dialogs[1][0] = prop.getProperty("errora");
    	dialogs[1][1] = prop.getProperty("error");
    	dialogs[1][2] = prop.getProperty("cerrar");
    	//.setText a todo lo que deba hacerse setText (mirar Strings)
    }
    
    public AlergiaBorrar(JDialog parent, JComboBox alergiasList, MyProperties prop, ItemMap alergias) {
        this.alergiasList = alergiasList;
        this.pacAlCrear = parent;
        this.alergia = alergias;
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
        	dialog = delPane.createDialog(pacAlCrear, dialogs[0][1]);
        	dialog.setLocationRelativeTo(pacAlCrear);
        	dialog.setVisible(true);
        	
        	String reply = (String) delPane.getValue();

        	if(reply!=null) {
	            if (reply.equalsIgnoreCase(dialogs[0][2])) {
	                    // Borramos el orfanato de la BD
	                    alergiaDao.borrarAlergia(alergia.getId());
                        /*
                         * Actualizamos el JComboBox
                         */
                        Map fkAlergias = null;
						try {
							fkAlergias = alergiaDao.fkAlergias();
						} catch (ParseException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} 
                        alergiasList.setModel(new DefaultComboBoxModel(fkAlergias.values().toArray()));
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
    	dialog = infoPane.createDialog(pacAlCrear, dialogs[1][1]);
    	dialog.setLocationRelativeTo(pacAlCrear);
    	dialog.setVisible(true);
    }

}
