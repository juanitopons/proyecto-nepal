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

import model.VacunaDAO;
import properties.MyProperties;

public class VacunaBotones extends JPanel {

	private MyProperties prop;
	private static JPanel panel2;
    private static JButton[] button2 = new JButton[2];
    private static String[] buttonName2 = new String[2];
    private static String[] buttonAction2 = { "insertar", "borrar" };
    private JTable vacunaTable;
    private String [] dialogs = new String[3];
    
    public VacunaBotones(JTable vacunaTable, MyProperties prop, PacienteVacunas parent) {
        this.prop = prop;
    	this.vacunaTable = vacunaTable;
        vacunaButtonListener vacunaButtonListener = new vacunaButtonListener(parent);
        this.setLayout(new BorderLayout());
        this.setBorder(null);

        // Idioma
        buttonName2[0] = prop.getProperty("avacuna");
        buttonName2[1] = prop.getProperty("bvacuna");
        
		panel2 = new JPanel();
		panel2.setLayout(new GridLayout(0,2, 10, 10));
        panel2.setPreferredSize(new Dimension(500, 65));
        panel2.setSize(500, 65);
        panel2.setMaximumSize(new Dimension(500, 65));
		panel2.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
        for (int i = 0; i < button2.length; i++) {
            button2[i] = new JButton(buttonName2[i]);
            button2[i].setActionCommand(buttonAction2[i]);
            button2[i].addActionListener(vacunaButtonListener);
            panel2.add(button2[i]);
        }
        this.add(panel2); 
    }
    
    private class vacunaButtonListener implements ActionListener {
    	private PacienteVacunas parent;
    	
    	public vacunaButtonListener(PacienteVacunas parent) {
    		this.parent = parent;
    	}
    	
        @Override
        public void actionPerformed(ActionEvent event) {
            
            int row, idvacuna = 0, idpaciente;

            String key   = event.getActionCommand();
            switch (key) {
                case "insertar":
                	idpaciente = parent.getIdPaciente();
                    PacienteVacunaCrear pacVacunaCrear = new PacienteVacunaCrear(vacunaTable, parent, prop, idpaciente);
                    break;
                case "borrar":
                    // Fila seleccionada
                    row = vacunaTable.getSelectedRow();
                    if (row == -1) {
                        showSelectionMessage();
                    }
                    else {
                    	VacunaDAO vacunaDao = new VacunaDAO(prop);
                    	try {
							idvacuna = vacunaDao.findIdAl(vacunaTable.getModel().getValueAt(row, 1).toString());
						} catch (ClassNotFoundException
								| InstantiationException
								| IllegalAccessException | SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
                        idpaciente = parent.getIdPaciente();
                        new PacienteVacunaBorrar(vacunaTable, (JDialog)parent, prop, idpaciente, idvacuna);
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