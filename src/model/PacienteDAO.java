package model;

import java.sql.*;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author juanitopons
 */
public class PacienteDAO {
    /*
     * Parámetros de conexión a la base de datos
     */
    public static final String DRIVER = "com.mysql.jdbc.Driver";
    public static final String DBURL = "jdbc:mysql://localhost/nepal";
    public static final String USERNAME = "root";
    public static final String PASSWORD = "@juan@2468";
    /*
     * Consultas Pacientes
     *
     * IDPACIENTE	NUMBER(5,0)
     * IDORFANATO	NUMBER(1,0)
     * FECHA	TIMESTAMP
     * NOMBRE	VARCHAR(50)
     * APELLIDOS	VARCHAR(100)
     * GENERO	CHAR(1)
     * EDAD		NUMBER(2,0)
     * ANTECEDENTES		VARCHAR(255)
     * FOTO		VARCHAR(120)
     */

    private static final String CREATE = 
            "INSERT INTO pacientes (idorfanato, fecha, " +
            "                         nombre, apellidos, genero, edad, antecedentes, foto) " +
            "VALUES (?,?,?,?,?,?,?,?)";
    
    private static final String READ = 
            "SELECT e1.idpaciente, e1.idorfanato, e1.fecha, e1.nombre, " +
            "       e1.apellidos, e1.genero, e1.edad, e1.antecedentes, e1.foto, e2.nombre AS ORFANATO" +
            "  FROM pacientes e1 JOIN orfanatos e2 ON e1.idorfanato = e2.idorfanato " +
            " WHERE idpaciente = ?";
    
    private static final String READALL = 
            "SELECT m.idpaciente AS ID, m.nombre AS NOMBRE, " +
            "       m.apellidos AS APELLIDOS, " +
            "       a.nombre AS CENTRO " +
            "  FROM pacientes m, orfanatos a " +
            " WHERE m.idorfanato = a.idorfanato " +
            " ORDER BY m.nombre";
    
    private static final String UPDATE =
            "UPDATE pacientes " +
            "   SET idorfanato = ?, nombre = ?, apellidos = ?, " +
            "       genero = ?, edad = ?, antecedentes = ?, foto = ? " +
            " WHERE idpaciente = ?";
    
    private static final String DELETE =
            "DELETE FROM pacientes " +
            " WHERE idpaciente = ?";
    
    private static final String FKIDORFANATO =
            "SELECT idorfanato, nombre FROM orfanatos ORDER BY idorfanato ASC ";
    
    Connection oracleConn;

    public PacienteDAO() {}
    
    public void closeConn() throws SQLException {
    	oracleConn.commit();
        oracleConn.setAutoCommit(true);
        oracleConn.close();
    }
    
    public Map fkOrfanatos() throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException, ParseException {
        Map<Integer, ItemMap> orfanatosMap = new HashMap<>();
        
        Class.forName(DRIVER).newInstance();
        oracleConn = DriverManager.getConnection(DBURL,USERNAME,PASSWORD);
           
        // Sentencia de FK
        PreparedStatement read = oracleConn.prepareStatement(FKIDORFANATO);
        ResultSet rs = read.executeQuery();
        
        while(rs.next()) {
            ItemMap p = new ItemMap(rs.getInt("idorfanato"), rs.getString("nombre"));
            orfanatosMap.put(p.getId(), p);
        }
        return orfanatosMap;
    }
    
    public void crearPaciente(Paciente paciente) throws 
            ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
        
        //SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        /*
        * Conexion a la base de datos
        */
        Class.forName(DRIVER).newInstance();
        oracleConn = DriverManager.getConnection(DBURL,USERNAME,PASSWORD);
           
        oracleConn.setAutoCommit(false);
        // Sentencia de insert
        PreparedStatement insert = oracleConn.prepareStatement(CREATE);
        insert.setInt(1, paciente.getidOrfanato());
        insert.setTimestamp(2, paciente.getFecha());
        insert.setString(3, paciente.getNombrePaciente());
        insert.setString(4, paciente.getApellidosPaciente());
        insert.setString(5, paciente.getGenPaciente());
        insert.setInt(6, paciente.getEdadPaciente());
        insert.setString(7, paciente.getAntecedPaciente());
        insert.setString(8, paciente.getFotoPaciente());
        insert.executeUpdate();
        
        oracleConn.commit();
        oracleConn.setAutoCommit(true);
        oracleConn.close();
    }
    
    public Paciente leerPaciente(int idPaciente) throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException, ParseException {
        
        Paciente paciente = new Paciente();
        //SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
      
        Class.forName(DRIVER).newInstance();
        oracleConn = DriverManager.getConnection(DBURL,USERNAME,PASSWORD);
           
        // Sentencia de insert
        PreparedStatement read = oracleConn.prepareStatement(READ);
        read.setInt(1, idPaciente);
        ResultSet rs = read.executeQuery();
        
        if (rs.next()) {
            paciente.setidPaciente(rs.getInt("idpaciente"));
            paciente.setidOrfanato(rs.getInt("idorfanato"));
            paciente.setFecha(rs.getTimestamp("fecha"));
            paciente.setNombrePaciente(rs.getString("nombre"));
            paciente.setApellidosPaciente(rs.getString("apellidos"));
            paciente.setGenPaciente(rs.getString("genero"));
            paciente.setEdadPaciente(rs.getInt("edad"));
            //Date fechaNacimiento = sdf.parse(rs.getString("fecha"));
            paciente.setAntecedPaciente(rs.getString("antecedentes"));
            paciente.setFotoPaciente(rs.getString("foto"));
            paciente.setNombreOrfanato(rs.getString("orfanato"));
        }
        return paciente;
    }
    
    public void actualizarPaciente(Paciente paciente) throws ClassNotFoundException, 
           InstantiationException, IllegalAccessException, SQLException {
           
        //SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        /*
        * Conexion a la base de datos
        */
        Class.forName(DRIVER).newInstance();
        oracleConn = DriverManager.getConnection(DBURL,USERNAME,PASSWORD);
           
        oracleConn.setAutoCommit(false);
        // Sentencia de insert
        PreparedStatement update = oracleConn.prepareStatement(UPDATE);
        
        update.setInt(1, paciente.getidOrfanato());
        update.setString(2, paciente.getNombrePaciente());
        update.setString(3, paciente.getApellidosPaciente());
        update.setString(4, paciente.getGenPaciente());
        update.setInt(5, paciente.getEdadPaciente());
        update.setString(6, paciente.getAntecedPaciente());
        update.setString(7, paciente.getFotoPaciente());
        update.setInt(8, paciente.getidPaciente());
        update.executeUpdate();
        
        oracleConn.commit();
        oracleConn.setAutoCommit(true);
        oracleConn.close();
    }
    
    public void borrarPaciente(int idPaciente) throws ClassNotFoundException, 
           InstantiationException, IllegalAccessException, SQLException {
        /*
        * Conexion a la base de datos
        */
        Class.forName(DRIVER).newInstance();
        oracleConn = DriverManager.getConnection(DBURL,USERNAME,PASSWORD);
            
        oracleConn.setAutoCommit(false);
        
        // Sentencia de borrado
        PreparedStatement delete = oracleConn.prepareStatement(DELETE);
        delete.setInt(1, idPaciente);
        delete.executeUpdate();
        
        oracleConn.commit();
        oracleConn.setAutoCommit(true);
        oracleConn.close();
    }

    public DefaultTableModel getTablaPacientes() {
        
        DefaultTableModel tablaPacientes = new DefaultTableModel();
        
        try {
            /*
            * Conexion a la base de datos
            */
            Class.forName(DRIVER).newInstance();
            oracleConn = DriverManager.getConnection(DBURL,USERNAME,PASSWORD);
            
            PreparedStatement s = oracleConn.prepareStatement(READALL);
            ResultSet rs = s.executeQuery();
            ResultSetMetaData rsMd = rs.getMetaData();
            //La cantidad de columnas que tiene la consulta
            int numeroColumnas = rsMd.getColumnCount();
            //Establecer como cabezeras el nombre de las colimnas
            for (int i = 1; i <= numeroColumnas; i++) {
                tablaPacientes.addColumn(rsMd.getColumnLabel(i));
            }
            //Creando las filas para el JTable
            while (rs.next()) {
                Object[] fila = new Object[numeroColumnas];
                fila[0] = rs.getInt("ID");
                fila[1] = rs.getString("NOMBRE");
                fila[2] = rs.getString("APELLIDOS");
                fila[3] = rs.getString("CENTRO");

                tablaPacientes.addRow(fila);
            }
            oracleConn.close();
        }
        catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException e) {
            System.out.println("MPacientesDAO::getTablaPacientes -- " + e.getMessage());
        }
        finally {
            return tablaPacientes;
        }
    }
}
