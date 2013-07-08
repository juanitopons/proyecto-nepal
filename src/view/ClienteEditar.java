/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

/**
 *
 * @author juanitopons
 */
import model.Cliente;
import model.ClienteDAO;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import javax.swing.*;

public class ClienteEditar extends JFrame {
    
    private JTextField idcliente = new JTextField();
    //private JTextField idCliente = new JTextField();
    //private JComboBox idCliente = new JComboBox();
    private JComboBox tipocliente = new JComboBox();
    private JTextField nombrecliente = new JTextField();
    private JTextField dirCliente = new JTextField();
    private JTextField telCliente = new JTextField();
    private JTextField emailCliente = new JTextField();
    
    private JTable clienteTable;
    private ClienteDAO clienteDao = new ClienteDAO();
    private Cliente cliente;
    //private HashMap<String, Integer> listaClientes = new HashMap();
    //private Object [] claves;
        
    public ClienteEditar(int idcliente, JTable clienteTable) {
            
       super("Editar cliente");
        
       this.clienteTable = clienteTable;
        
       try {
            /*
             * Obtenemos el objeto a editar
             */
            cliente = clienteDao.leerCliente(idcliente);
            
            Container cp = this.getContentPane();
            cp.setLayout(new BorderLayout());
            
            /* Cabecera */
            JPanel cabecera = createCabecera();
      
            /* Formulario */
            JPanel form = createForm();
            
            /* Botón */
            JPanel boton = createButton();

            /* Añadimos todos los paneles al Container */
            cp.add(cabecera, BorderLayout.NORTH);
            cp.add(form, BorderLayout.CENTER);
            cp.add(boton, BorderLayout.SOUTH);
            
            setSize(600,400);
            pack();
            setVisible(true);
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException e) {
            JOptionPane.showMessageDialog(
                null,
                "Error modificiando vino: " + e.getMessage(),
                "Atención",
                JOptionPane.ERROR_MESSAGE);
        }
    }
   
    private JPanel createCabecera() {
        JPanel c = new JPanel();
        JLabel l = new JLabel("Editar cliente");
        l.setForeground(Color.BLUE);
        l.setHorizontalAlignment(JLabel.CENTER);
        l.setFont(new Font("Arial",Font.BOLD | Font.ITALIC,22));
        c.add(l);
        
        return c;
    }
    
    private JPanel createForm() {
        
        //ClienteDAO clienteDao = new ClienteDAO();
            
        JPanel form = new JPanel();
        Font labelFont = new Font("Arial", Font.BOLD, 12);
        Font textfFont = new Font("Arial", Font.PLAIN, 12);
        
        try {  
            //listaClientes = clienteDao.getTListaClientes();
            //claves = listaClientes.keySet().toArray();
            form.setLayout(new GridLayout(6,2));
            
            idcliente.setFont(textfFont);
            idcliente.setColumns(4);
            idcliente.setText((cliente.getidCliente()+""));
            idcliente.setEditable(false);
            idcliente.setEnabled(true);
            
            tipocliente.setFont(textfFont);
            tipocliente.setModel(new DefaultComboBoxModel(new String[] {"particular","empresa"}));
            tipocliente.setSelectedIndex(cliente.gettipoCliente());
            
            //idCliente.setColumns(4);
            nombrecliente.setFont(textfFont);
            nombrecliente.setColumns(30);
            nombrecliente.setText(cliente.getnombreCliente());
            
            dirCliente.setFont(textfFont);
            dirCliente.setColumns(4);
            dirCliente.setText(cliente.getdirCliente());
            
            telCliente.setFont(textfFont);
            telCliente.setColumns(4);
            telCliente.setText(new Integer(cliente.gettelCliente())+"");
            
            emailCliente.setFont(textfFont);
            emailCliente.setColumns(4);
            emailCliente.setText(cliente.getemailCliente());
            
            JLabel idclienteLabel = new JLabel("Id del cliente:");
            idclienteLabel.setFont(labelFont);
            idclienteLabel.setHorizontalAlignment(JLabel.RIGHT);
            form.add(idclienteLabel);
            form.add(idcliente);
            
            JLabel tipoClienteLabel = new JLabel("Tipo de cliente:");
            tipoClienteLabel.setFont(labelFont);
            tipoClienteLabel.setHorizontalAlignment(JLabel.RIGHT);
            form.add(tipoClienteLabel);
            form.add(tipocliente);
            
            /**
            JLabel idClienteLabel = new JLabel("Id de la bodega:");
            idClienteLabel.setFont(labelFont);
            idClienteLabel.setHorizontalAlignment(JLabel.RIGHT);
            form.add(idClienteLabel);
            */
            
            //idCliente.setModel(new DefaultComboBoxModel(claves));
            //idCliente.setColumns(4);
            //form.add(idCliente);

            JLabel nombreclienteLabel = new JLabel("Nombre del cliente:");
            nombreclienteLabel.setFont(labelFont);
            nombreclienteLabel.setHorizontalAlignment(JLabel.RIGHT);
            form.add(nombreclienteLabel);
            form.add(nombrecliente);

            JLabel dirClienteLabel = new JLabel("Dirección del cliente:");
            dirClienteLabel.setFont(labelFont);
            dirClienteLabel.setHorizontalAlignment(JLabel.RIGHT);
            form.add(dirClienteLabel);
            form.add(dirCliente);

            JLabel telClienteLabel = new JLabel("Teléfono cliente:");
            telClienteLabel.setFont(labelFont);
            telClienteLabel.setHorizontalAlignment(JLabel.RIGHT);
            form.add(telClienteLabel);
            form.add(telCliente);
            
            JLabel emailClienteLabel = new JLabel("Email del Cliente:");
            emailClienteLabel.setFont(labelFont);
            emailClienteLabel.setHorizontalAlignment(JLabel.RIGHT);
            form.add(emailClienteLabel);
            form.add(emailCliente);
        }
        catch (Exception e) {
            JOptionPane.showMessageDialog(
                null,
                "Error creando cliente: " + e.getMessage(),
                "Atención",
                JOptionPane.ERROR_MESSAGE);
        }
        finally {
            return form;
        }
    }
        
    private JPanel createButton() {
        
        JPanel botonPanel = new JPanel();
        
        JButton boton = new JButton("Modificar");
        boton.setActionCommand("botoncliente");
        boton.addActionListener(new ButtonListener());
        botonPanel.add(boton);
        return botonPanel;
    }
    
    private int nameToType(String name) {
        int type = 0;
        System.out.println(name);
        if(name.equalsIgnoreCase("particular")) type = 0;
        if(name.equalsIgnoreCase("empresa")) type = 1;
        return type;
    }
    
    private class ButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent event) {
            try {
                String key = event.getActionCommand();
                switch (key) {
                    case "botoncliente":
                        //SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
                        Cliente cliente = new Cliente();
                        cliente.setidCliente(Integer.parseInt(idcliente.getText()));
                        /*
                        String seleccion = (String)claves[idCliente.getSelectedIndex()];
                        int idcli = (int)listaClientes.get(seleccion);
                        cliente.setIdCliente(idcli);
                         */
                        cliente.settipoCliente(nameToType((String)tipocliente.getSelectedItem()));
                        cliente.setnombreCliente(nombrecliente.getText());
                        cliente.setdirCliente(dirCliente.getText());
                        if(!telCliente.getText().isEmpty()) {
                            cliente.settelCliente(Integer.parseInt(telCliente.getText()));
                        }
                        cliente.setemailCliente(emailCliente.getText());
                        /*
                        String f = (String)dia.getSelectedItem() + "-" + 
                            (String)mes.getSelectedItem() + "-" +
                            (String)anyo.getSelectedItem();
                        cliente.setFechaNacimiento(sdf.parse(f));
                        */

                        ClienteDAO clienteDao = new ClienteDAO();
                        clienteDao.actualizarCliente(cliente);
                        /*
                         * Actualizamos el modelo
                         */
                        clienteTable.setModel(clienteDao.getTablaClientes());
                        clienteTable.updateUI();
                         /*
                         * Cerramos la ventana
                         */
                        dispose();
                        break;
                    default:
                        System.out.println("clienteCrear: Accion '" + key + "' no reconocida.");
                        break;
                }
            }
            catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException e) {
                JOptionPane.showMessageDialog(
                null,
                "Error creando cliente: " + e.getMessage(),
                "Atención",
                JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}

