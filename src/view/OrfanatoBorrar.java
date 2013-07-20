package view;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.Map;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JTable;

import model.ItemMap;
import model.Orfanato;
import model.OrfanatoDAO;
import model.Paciente;
import model.PacienteDAO;
import properties.MyProperties;

public class OrfanatoBorrar {
    private String[][] dialogs = new String[2][4];
    private OrfanatoDAO orfanatoDao;
    private ItemMap orfanato;
    private JDialog pacienteCrear;
    private JComboBox orfanatosList;
	
    public void cambiarIdioma(MyProperties prop) {    	
    	dialogs[0][0] = prop.getProperty("dialog41")+" '" + orfanato.getNombre() +"' (ID = " + orfanato.getId() + ") ?";
    	dialogs[0][1] = prop.getProperty("dialog42");
    	dialogs[0][2] = prop.getProperty("aceptar");
    	dialogs[0][3] = prop.getProperty("cancelar");
    	
    	dialogs[1][0] = prop.getProperty("erroro");
    	dialogs[1][1] = prop.getProperty("error");
    	dialogs[1][2] = prop.getProperty("cerrar");
    	//.setText a todo lo que deba hacerse setText (mirar Strings)
    }

    public OrfanatoBorrar(JDialog parent, JComboBox orfanatosList, MyProperties prop, ItemMap orfanato) {
        this.orfanatosList = orfanatosList;
        this.pacienteCrear = parent;
        this.orfanato = orfanato;
    	orfanatoDao = new OrfanatoDAO();
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
        	dialog = delPane.createDialog(pacienteCrear, dialogs[0][1]);
        	dialog.setLocationRelativeTo(pacienteCrear);
        	dialog.setVisible(true);
        	
        	String reply = (String) delPane.getValue();

        	if(reply!=null) {
	            if (reply.equalsIgnoreCase(dialogs[0][2])) {
	                    // Borramos el orfanato de la BD
	                    orfanatoDao.borrarOrfanato(orfanato.getId());
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
                }
        	}
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException e) {
        	try {
				orfanatoDao.closeConn();
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
    	dialog = infoPane.createDialog(pacienteCrear, dialogs[1][1]);
    	dialog.setLocationRelativeTo(pacienteCrear);
    	dialog.setVisible(true);
    }
}
