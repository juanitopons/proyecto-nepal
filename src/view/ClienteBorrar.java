/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import model.Cliente;
import model.ClienteDAO;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.JTable;

/**
 *
 * @author juanitopons
 */
public class ClienteBorrar {
    
    public ClienteBorrar(int idcliente, JTable clienteTable) {
    
        ClienteDAO clienteDao = new ClienteDAO();
        Cliente cliente;

        try {
            // Recuperamos la cliente a través de la clave primaria
            cliente = clienteDao.leerCliente(idcliente);
        
            // Dialogo de confirmación
            int reply = JOptionPane.showConfirmDialog(
                null,
                "¿Borrar el cliente '" + cliente.getnombreCliente() + "' (idcliente = " + idcliente + ")?",
                "Borrar cliente",
                JOptionPane.YES_NO_OPTION);

            if (reply == JOptionPane.YES_OPTION) {
                    // Borramos la cliente de la base de datos
                    clienteDao.borrarCliente(idcliente);
                    /*
                    * Actualizamos el modelo
                    */
                    clienteTable.setModel(clienteDao.getTablaClientes());
                    clienteTable.updateUI();
                }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException e) {
            JOptionPane.showMessageDialog(
                null,
                "Error borrando cliente: " + e.getMessage(),
                "Atención",
                JOptionPane.ERROR_MESSAGE);
        }
    }
}
