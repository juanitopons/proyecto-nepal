package model;

import java.sql.*;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author juanitopons
 */
public class ClienteDAO {
    /*
     * Parámetros de conexión a la base de datos
     */
    public static final String DRIVER = "oracle.jdbc.OracleDriver";
    public static final String DBURL = "jdbc:oracle:thin:@pokemon2.uv.es:1521:ORCL";
    public static final String USERNAME = "al006";
    public static final String PASSWORD = "al006";
    
    private static final String READ = 
            "SELECT idcliente, tipocliente, nombre, direccion, telefono, email FROM clientes " +
            "WHERE idcliente = ?";
    
    private static final String UPDATE = 
            "UPDATE clientes SET tipocliente = ?, nombre = ?, direccion = ?, telefono = ?, email = ? " +
            "WHERE idcliente = ?";
    
    private static final String DELETE =
            "DELETE FROM clientes " +
            " WHERE idcliente = ?";
    
    private static final String CREATE = 
            "INSERT INTO clientes (idcliente, tipocliente, nombre, direccion, telefono, email) " +
            "VALUES (?,?,?,?,?, ?)";
    
    private static final String READALL = 
            "SELECT idcliente AS ID, tipocliente AS Tipo, nombre AS Cliente, direccion AS Direccion, telefono AS Telefono, email AS Email " +
            "  FROM clientes " +
            " ORDER BY nombre";

    public ClienteDAO() {}
    
    public Cliente leerCliente(int idCliente) throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
        
        Cliente cliente = new Cliente();
        //SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
      
        Class.forName(DRIVER).newInstance();
        Connection oracleConn = DriverManager.getConnection(DBURL,USERNAME,PASSWORD);
           
        // Sentencia de insert
        PreparedStatement read = oracleConn.prepareStatement(READ);
        read.setInt(1, idCliente);
        ResultSet rs = read.executeQuery();
        
        if (rs.next()) {
            cliente.setidCliente(rs.getInt("idcliente"));
            cliente.settipoCliente(rs.getInt("tipocliente"));
            cliente.setnombreCliente(rs.getString("nombre"));
            cliente.setdirCliente(rs.getString("direccion"));
            //Date fechaNacimiento = sdf.parse(rs.getString("fecha"));
            Integer tel = new Integer(rs.getInt("telefono"));
            if(tel == 0) tel = null;
            cliente.settelCliente(tel);
            cliente.setemailCliente(rs.getString("email"));
        }
        return cliente;
    }
    
    public void actualizarCliente(Cliente cliente) throws ClassNotFoundException, 
           InstantiationException, IllegalAccessException, SQLException {
           
        //SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        /*
        * Conexion a la base de datos
        */
        Class.forName(DRIVER).newInstance();
        Connection oracleConn = DriverManager.getConnection(DBURL,USERNAME,PASSWORD);
           
        oracleConn.setAutoCommit(false);
        // Sentencia de insert
        PreparedStatement update = oracleConn.prepareStatement(UPDATE);
        
        update.setInt(1, cliente.gettipoCliente());
        update.setString(2, cliente.getnombreCliente());
        update.setString(3, cliente.getdirCliente());
        if(cliente.gettelCliente() == null) {
            update.setNull(4, java.sql.Types.INTEGER);
        } else {
            update.setInt(4, cliente.gettelCliente());
        }
        update.setString(5, cliente.getemailCliente());
        update.setInt(6, cliente.getidCliente());
        update.executeUpdate();
        
        oracleConn.commit();
        oracleConn.setAutoCommit(true);
        oracleConn.close();
    }
    
        public void borrarCliente(int idCliente) throws ClassNotFoundException, 
           InstantiationException, IllegalAccessException, SQLException {
        /*
        * Conexion a la base de datos
        */
        Class.forName(DRIVER).newInstance();
        Connection oracleConn = DriverManager.getConnection(DBURL,USERNAME,PASSWORD);
            
        oracleConn.setAutoCommit(false);
        
        // Sentencia de borrado
        PreparedStatement delete = oracleConn.prepareStatement(DELETE);
        delete.setInt(1, idCliente);
        delete.executeUpdate();
        
        oracleConn.commit();
        oracleConn.setAutoCommit(true);
        oracleConn.close();
    }
    
    public void crearCliente(Cliente cliente) throws 
            ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
        
        //SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        /*
        * Conexion a la base de datos
        */
        Class.forName(DRIVER).newInstance();
        Connection oracleConn = DriverManager.getConnection(DBURL,USERNAME,PASSWORD);
           
        oracleConn.setAutoCommit(false);
        // Sentencia de insert
        PreparedStatement insert = oracleConn.prepareStatement(CREATE);
        insert.setInt(1, cliente.getidCliente());
        insert.setInt(2, cliente.gettipoCliente());
        insert.setString(3, cliente.getnombreCliente());
        insert.setString(4, cliente.getdirCliente());
        if(cliente.gettelCliente()!=null) {
          insert.setInt(5, cliente.gettelCliente());
        } else {
          insert.setNull(5, java.sql.Types.INTEGER);
        }
        insert.setString(6, cliente.getemailCliente());
        insert.executeUpdate();
        
        oracleConn.commit();
        oracleConn.setAutoCommit(true);
        oracleConn.close();
    }
    
    public DefaultTableModel getTablaClientes() {
        
        DefaultTableModel tablaClientes = new DefaultTableModel();
        
        try {
            /*
            * Conexion a la base de datos
            */
            Class.forName(DRIVER).newInstance();
            Connection oracleConn = DriverManager.getConnection(DBURL,USERNAME,PASSWORD);
            
            PreparedStatement s = oracleConn.prepareStatement(READALL);
            ResultSet rs = s.executeQuery();
            ResultSetMetaData rsMd = rs.getMetaData();
            //La cantidad de columnas que tiene la consulta
            int numeroColumnas = rsMd.getColumnCount();
            //Establecer como cabezeras el nombre de las colimnas
            for (int i = 1; i <= numeroColumnas; i++) {
                tablaClientes.addColumn(rsMd.getColumnLabel(i));
            }
            //Creando las filas para el JTable
            while (rs.next()) {
                Object[] fila = new Object[numeroColumnas];
                fila[0] = rs.getInt("ID");
                fila[1] = typeToName(rs.getInt("Tipo"));
                fila[2] = rs.getString("Cliente");
                fila[3] = rs.getString("Direccion");
                Integer tel = new Integer(rs.getInt("Telefono"));
                if(tel == 0) tel = null;
                fila[4] = tel;
                fila[5] = rs.getString("Email");

                tablaClientes.addRow(fila);
            }
            oracleConn.close();
        }
        catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException e) {
            System.out.println("MClientesDAO::getTablaClientes -- " + e.getMessage());
        }
        finally {
            return tablaClientes;
        }
    }
        
    public String typeToName(int type) {
        String name ="";
        if(type == 0) name = "particular";
        if(type == 1) name = "empresa";
        return name;
    }

}
