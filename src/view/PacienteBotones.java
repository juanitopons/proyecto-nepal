package view;

import model.PacienteDAO;
import model.VisitaDAO;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.SwingUtilities;

import properties.MyProperties;

/**
 *
 * @author diaz
 */
public class PacienteBotones extends JPanel {
	private MyProperties prop;
	private static JPanel panel1;
	private static JPanel panel2;
	private static JPanel mainpanel;
    private static JButton[] button = new JButton[3];
    private static JButton[] button2 = new JButton[3];
    private static String[] buttonName = { "Visitas", "Alergias", "Vacunas" };
    private static String[] buttonAction = { "visitas", "alergias", "vacunas" };
    private static String[] buttonName2 = { "Nuevo paciente", "Editar paciente", "Borrar paciente" };
    private static String[] buttonAction2 = { "insertar", "editar", "borrar" };
    private JTable pacienteTable;
    private String [] dialogs = new String[3];
    
    public PacienteBotones(JTable pacienteTable, MyProperties prop, PacienteView pacienteView) {
        this.prop = prop;
    	this.pacienteTable = pacienteTable;
        pacienteButtonListener pacienteButtonListener = new pacienteButtonListener(pacienteView);
        this.setLayout(new BorderLayout());
        this.setBorder(null);
        
        mainpanel = new JPanel();
        mainpanel.setLayout(new BoxLayout(mainpanel, BoxLayout.PAGE_AXIS));
        mainpanel.setPreferredSize(new Dimension(500, 80));
        mainpanel.setSize(500, 80);
        mainpanel.setMaximumSize(new Dimension(500, 80));
        
        panel1 = new JPanel();
        panel1.setLayout(new GridLayout(0,3, 10, 10));
        panel1.setBorder(BorderFactory.createEmptyBorder(5, 0, 10, 0));
		 for (int i = 0; i < button.length; i++) {
	         button[i] = new JButton(buttonName[i]);
	         button[i].setActionCommand(buttonAction[i]);
	         button[i].addActionListener(pacienteButtonListener);
	         panel1.add(button[i]);
	     }
		 button[0].setText(prop.getProperty("visitas"));
		 button[1].setText(prop.getProperty("alergias"));
		 button[2].setText(prop.getProperty("vacunas"));
		 mainpanel.add(panel1, BorderLayout.CENTER);
		 
		panel2 = new JPanel();
		panel2.setLayout(new GridLayout(0,3, 10, 10));
		panel2.setBorder(BorderFactory.createEmptyBorder(5, 0, 10, 0));
        for (int i = 0; i < button2.length; i++) {
            button2[i] = new JButton(buttonName2[i]);
            button2[i].setActionCommand(buttonAction2[i]);
            button2[i].addActionListener(pacienteButtonListener);
            panel2.add(button2[i]);
        }
    	button2[0].setText(prop.getProperty("npaciente"));
    	button2[1].setText(prop.getProperty("epaciente"));
    	button2[2].setText(prop.getProperty("bpaciente"));
        mainpanel.add(panel2, BorderLayout.SOUTH);
        this.add(mainpanel); 

    }
     
    private class pacienteButtonListener implements ActionListener {
    	private PacienteView pacienteView;
    	
    	public pacienteButtonListener(PacienteView pacienteView) {
    		this.pacienteView = pacienteView;
    	}
    	
        @Override
        public void actionPerformed(ActionEvent event) {
            
            int row, idpaciente;
            VisitaDAO visita = new VisitaDAO(prop);

            String key   = event.getActionCommand();
            switch (key) {
            	case "alergias":
            		row = pacienteTable.getSelectedRow();
                    if (row == -1) {
                        showSelectionMessage();
                    }
                    else {
                        idpaciente = (int)pacienteTable.getModel().getValueAt(row, 0);
                        PacienteAlergias pacienteAlergias = new PacienteAlergias(pacienteView, prop, idpaciente);
                        pacienteAlergias.setVisible(true);
                    }
            		break;
            	case "vacunas":
            		break;
            	case "visitas":
            		row = pacienteTable.getSelectedRow();
                    if (row == -1) {
                        showSelectionMessage();
                    }
                    else {
                        idpaciente = (int)pacienteTable.getModel().getValueAt(row, 0);
                        PacienteVisitas pacienteVisitas = new PacienteVisitas(pacienteView, prop, idpaciente);
                        pacienteVisitas.setVisible(true);
                    }
                    break;
                case "insertar":
                    PacienteCrear pacienteCrear = new PacienteCrear(pacienteTable, pacienteView, prop);
                    break;
                case "editar":
                    row = pacienteTable.getSelectedRow();
                    if (row == -1) {
                        showSelectionMessage();
                    }
                    else {
                        idpaciente = (int)pacienteTable.getModel().getValueAt(row, 0);
                        PacienteEditar pacienteEditar = new PacienteEditar(idpaciente, pacienteTable, pacienteView, prop);
                    }
                    break;
                case "borrar":
                    // Fila seleccionada
                    row = pacienteTable.getSelectedRow();
                    if (row == -1) {
                        showSelectionMessage();
                    }
                    else {
                        idpaciente = (int)pacienteTable.getModel().getValueAt(row, 0);
                        PacienteBorrar pacienteBorrar = new PacienteBorrar(prop, pacienteView, idpaciente);
                        pacienteBorrar.init(pacienteTable, pacienteView);
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
        	dialog = infoPane.createDialog(pacienteView, dialogs[1]);
        	dialog.setLocationRelativeTo(pacienteView);
        	dialog.setVisible(true);
        }
    }
}
