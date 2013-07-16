package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;

import model.AlergiaDAO;
import model.ItemMap;

import properties.MyProperties;

public class AlergiaBotones extends JPanel {
	private MyProperties prop;
	private static JPanel panel2;
    private static JButton[] button2 = new JButton[2];
    private static String[] buttonName2 = new String[2];
    private static String[] buttonAction2 = { "insertar", "borrar" };
    private JTable alergiaTable;
    private String [] dialogs = new String[3];
    
    public AlergiaBotones(JTable alergiaTable, MyProperties prop, PacienteAlergias parent) {
        this.prop = prop;
    	this.alergiaTable = alergiaTable;
        alergiaButtonListener alergiaButtonListener = new alergiaButtonListener(parent);
        this.setLayout(new BorderLayout());
        this.setBorder(null);

        // Idioma
        buttonName2[0] = prop.getProperty("aalergia");
        buttonName2[1] = prop.getProperty("balergia");
        
		panel2 = new JPanel();
		panel2.setLayout(new GridLayout(0,2, 10, 10));
        panel2.setPreferredSize(new Dimension(500, 65));
        panel2.setSize(500, 65);
        panel2.setMaximumSize(new Dimension(500, 65));
		panel2.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
        for (int i = 0; i < button2.length; i++) {
            button2[i] = new JButton(buttonName2[i]);
            button2[i].setActionCommand(buttonAction2[i]);
            button2[i].addActionListener(alergiaButtonListener);
            panel2.add(button2[i]);
        }
        this.add(panel2); 
    }
    
    private class alergiaButtonListener implements ActionListener {
    	private PacienteAlergias parent;
    	
    	public alergiaButtonListener(PacienteAlergias parent) {
    		this.parent = parent;
    	}
    	
        @Override
        public void actionPerformed(ActionEvent event) {
            
            int row, idalergia = 0, idpaciente;

            String key   = event.getActionCommand();
            switch (key) {
                case "insertar":
                	idpaciente = parent.getIdPaciente();
                    PacienteAlergiaCrear pacAlergiaCrear = new PacienteAlergiaCrear(alergiaTable, parent, prop, idpaciente);
                    break;
                case "borrar":
                    // Fila seleccionada
                    row = alergiaTable.getSelectedRow();
                    if (row == -1) {
                        showSelectionMessage();
                    }
                    else {
                    	AlergiaDAO alergiaDao = new AlergiaDAO(prop);
                    	try {
							idalergia = alergiaDao.findIdAl(alergiaTable.getModel().getValueAt(row, 1).toString());
						} catch (ClassNotFoundException
								| InstantiationException
								| IllegalAccessException | SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
                        idpaciente = parent.getIdPaciente();
                        new PacienteAlergiaBorrar(alergiaTable, (JDialog)parent, prop, idpaciente, idalergia);
                    }

                    break;
                default:
                    System.out.println("pacienteButtonListener: Accion '" + key + "' no reconocida.");
                    break;
            }
        }
        
        private void showSelectionMessage() {
        	dialogs[0] = prop.getProperty("dialog11");
        	dialogs[1] = prop.getProperty("dialog12");
        	dialogs[2] = prop.getProperty("cerrar");
        	Object[] options = {dialogs[2]};
        	JOptionPane infoPane = new JOptionPane(
                    dialogs[0],
                    JOptionPane.DEFAULT_OPTION,
                    JOptionPane.INFORMATION_MESSAGE,
                    null,
                    options,
                    dialogs[2]);
        	JDialog dialog = new JDialog();
        	dialog = infoPane.createDialog(parent, dialogs[1]);
        	dialog.setLocationRelativeTo(parent);
        	dialog.setVisible(true);
        }
    }
        
}
