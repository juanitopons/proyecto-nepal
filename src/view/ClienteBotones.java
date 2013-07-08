/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

/**
 *
 * @author juanitopons
 */
import model.ClienteDAO;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;

/**
 *
 * @author diaz
 */
public class ClienteBotones extends JPanel {
    private static JButton[] button = new JButton[3];
    private static String[] buttonName = { "Nuevo cliente", "Editar cliente", "Borrar cliente" };
    private static String[] buttonAction = { "insertar", "editar", "borrar" };
    private JTable clienteTable;

    public ClienteBotones(JTable clienteTable) {
    
        this.clienteTable = clienteTable;
        clienteButtonListener clienteButtonListener = new clienteButtonListener();
        setLayout(new GridLayout(1,3));
        
        for (int i = 0; i < button.length; i++) {
            button[i] = new JButton(buttonName[i]);
            button[i].setActionCommand(buttonAction[i]);
            button[i].addActionListener(clienteButtonListener);
            this.add(button[i]);
        }
    }
     
    private class clienteButtonListener implements ActionListener {
            
        @Override
        public void actionPerformed(ActionEvent event) {
            
            int row, idcliente;
            ClienteDAO clienteDao = new ClienteDAO();
            
            String key   = event.getActionCommand();
            switch (key) {
                case "insertar":
                    ClienteCrear clienteCrear = new ClienteCrear(clienteTable);
                    break;
                case "editar":
                    row = clienteTable.getSelectedRow();
                    if (row == -1) {
                        showSelectionMessage();
                    }
                    else {
                        idcliente = (int)clienteTable.getModel().getValueAt(row, 0);
                        ClienteEditar clienteEditar = new ClienteEditar(idcliente, clienteTable);
                    }
                    break;
                case "borrar":
                    // Fila seleccionada
                    row = clienteTable.getSelectedRow();
                    if (row == -1) {
                        showSelectionMessage();
                    }
                    else {
                        idcliente = (int)clienteTable.getModel().getValueAt(row, 0);
                        ClienteBorrar clienteBorrar = new ClienteBorrar(idcliente, clienteTable);
                    }
                    break;
                default:
                    System.out.println("clienteButtonListener: Accion '" + key + "' no reconocida.");
                    break;
            }
        }
        
        private void showSelectionMessage() {
            JOptionPane.showMessageDialog(
                    null,
                    "Por favor, selecciona una fila de la tabla",
                    "Sin selección",
                    JOptionPane.INFORMATION_MESSAGE);
        }
        
        /*private void clienteBorrar(int row) {                        
            ClienteDAO clienteDao = new ClienteDAO();
        
            // No hay ninguna fila seleccionada
            if (row == -1) {
                JOptionPane.showMessageDialog(
                    null,
                    "Por favor, selecciona una fila de la tabla",
                    "Sin selección",
                    JOptionPane.INFORMATION_MESSAGE);
            }
            else {
                // Clave primaria de la fila seleccionada
                int idcliente = (int)clienteTable.getModel().getValueAt(row, 0);
                String nombrecliente = (String)clienteTable.getModel().getValueAt(row, 1);
            
                // Dialogo de confirmación
                int reply = JOptionPane.showConfirmDialog(
                    null,
                    "¿Borrar la cliente '" + nombrecliente + "' (idcliente = " + idcliente + ")?",
                    "Borrar cliente",
                    JOptionPane.YES_NO_OPTION);
                if (reply == JOptionPane.YES_OPTION) {
                    try {
                        // Borramos la cliente de la base de datos
                        //ClienteDao.borrarCliente(idcliente);
                        // y actualizamos la tabla
                        DefaultTableModel clienteModel = (DefaultTableModel)clienteTable.getModel();
                        clienteModel.removeRow(row);
                        clienteTable.updateUI();
                    }
                    catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException e) {
                        JOptionPane.showMessageDialog(
                            null,
                            "Error borrando cliente: " + e.getMessage(),
                            "Atención",
                            JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        }*/
    }
}
