package view;

import model.PacienteDAO;
import model.VisitaDAO;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;

import properties.MyProperties;

/**
 *
 * @author diaz
 */
public class VisitaBotones extends JPanel {
	private MyProperties prop;
	private static JPanel panel2;
    private static JButton[] button2 = new JButton[3];
    private static String[] buttonName2 = new String[3];
    private static String[] buttonAction2 = { "insertar", "editar", "borrar" };
    private JTable visitaTable;
    private String [] dialogs = new String[3];
    
    public VisitaBotones(JTable visitaTable, MyProperties prop, PacienteVisitas pacienteVisitas) {
        this.prop = prop;
    	this.visitaTable = visitaTable;
        pacienteButtonListener pacienteButtonListener = new pacienteButtonListener(pacienteVisitas);
        this.setLayout(new BorderLayout());
        this.setBorder(null);
        
        // Idioma
        buttonName2[0] = prop.getProperty("nvisita");
        buttonName2[1] = prop.getProperty("evisita");
        buttonName2[2] = prop.getProperty("bvisita");
		 
		panel2 = new JPanel();
		panel2.setLayout(new GridLayout(0,3, 10, 10));
        panel2.setPreferredSize(new Dimension(500, 65));
        panel2.setSize(500, 65);
        panel2.setMaximumSize(new Dimension(500, 65));
		panel2.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
        for (int i = 0; i < button2.length; i++) {
            button2[i] = new JButton(buttonName2[i]);
            button2[i].setActionCommand(buttonAction2[i]);
            button2[i].addActionListener(pacienteButtonListener);
            panel2.add(button2[i]);
        }
        this.add(panel2); 

    }
     
    private class pacienteButtonListener implements ActionListener {
    	private PacienteVisitas pacienteVisitas;
    	
    	public pacienteButtonListener(PacienteVisitas pacienteVisitas) {
    		this.pacienteVisitas = pacienteVisitas;
    	}
    	
        @Override
        public void actionPerformed(ActionEvent event) {
            
            int row, idvisita, idpaciente;

            String key   = event.getActionCommand();
            switch (key) {
                case "insertar":
                    new VisitaCrear(visitaTable, pacienteVisitas, prop);
                    break;
                case "editar":
                    row = visitaTable.getSelectedRow();
                    if (row == -1) {
                        showSelectionMessage();
                    }
                	idvisita = (int)visitaTable.getModel().getValueAt(row, 0);
                	new VisitaEditar(visitaTable, pacienteVisitas, prop, idvisita);
                	/*
                    row = visitaTable.getSelectedRow();
                    if (row == -1) {
                        showSelectionMessage();
                    }
                    else {
                        idvisita = (int)visitaTable.getModel().getValueAt(row, 0);
                        VisitaEditar visitaEditar = new VisitaEditar(idvisita, visitaTable);
                    }
                    */
                    break;
                case "borrar":
                    // Fila seleccionada
                    row = visitaTable.getSelectedRow();
                    if (row == -1) {
                        showSelectionMessage();
                    }
                    else {
                        idvisita = (int)visitaTable.getModel().getValueAt(row, 0);
                        VisitaBorrar visitaBorrar = new VisitaBorrar(prop, pacienteVisitas, idvisita);
                        visitaBorrar.init(visitaTable, pacienteVisitas, prop);
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
        	dialog = infoPane.createDialog(pacienteVisitas, dialogs[1]);
        	dialog.setLocationRelativeTo(pacienteVisitas);
        	dialog.setVisible(true);
        }
    }
}