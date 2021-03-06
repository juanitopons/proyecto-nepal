package model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;

import javax.swing.table.DefaultTableModel;

import properties.MyProperties;

public class AlergiaDAO {
    /*
     * Parámetros de conexión a la base de datos
     */
    public static final String DRIVER = "com.mysql.jdbc.Driver";
    public static final String DBURL = "jdbc:mysql://localhost/nepal";
    public static final String USERNAME = "root";
    public static final String PASSWORD = "@juan@2468";
    /*
     * Consultas Alergias
     *
     * IDALERGIA	NUMBER(3,0)
     * NOMBRE	VARCHAR(50)
     * DESCRIPCION	VARCHAR(150)
     */
    
    /*
     * Consultas Pacientes_Alergias
     *
     * IDPACIENTE	NUMBER(5, 0)
     * IDALERGIA	NUMBER(3,0)
     * FECHA	TIMESTAMP
     */
    
    private static final String CREATE = 
            "INSERT INTO pacientes_alergias (idpaciente, idalergia, fecha) " +
            "VALUES (?,?,?)";
    
    private static final String CREATE2 = 
            "INSERT INTO alergias (nombre, descripcion) " +
            "VALUES (?,?)";
    
    private static final String FIND =
    		"SELECT idalergia AS ID FROM alergias WHERE nombre = ?";
    
    private static final String READALL = 
            "SELECT CONCAT(b.nombre, ' ', b.apellidos) AS NIÑO, c.nombre AS ALERGIA, a.fecha AS FECHA " +
            " FROM pacientes_alergias a " +
            " JOIN pacientes b ON a.idpaciente = b.idpaciente " +
            " JOIN alergias c ON a.idalergia = c.idalergia " +
            " WHERE a.idpaciente = ? " +
            " ORDER BY a.fecha";
    
    private static final String DELETE =
            "DELETE FROM pacientes_alergias " +
            " WHERE idalergia = ? AND idpaciente = ?";
    
    private static final String DELETE2 =
            "DELETE FROM alergias " +
            " WHERE idalergia = ?";
    
    private static final String FKIDALERGIAS =
            "SELECT idalergia, nombre FROM alergias ORDER BY idalergia ASC ";
    
    private MyProperties prop;
    Connection oracleConn;

    public AlergiaDAO(MyProperties prop) {
    	this.prop = prop;
    }
    
    public void closeConn() throws SQLException {
    	oracleConn.commit();
        oracleConn.setAutoCommit(true);
        oracleConn.close();
    }
    
    public Map fkAlergias() throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException, ParseException {
        Map<Integer, ItemMap> orfanatosMap = new HashMap<>();
        
        Class.forName(DRIVER).newInstance();
        oracleConn = DriverManager.getConnection(DBURL,USERNAME,PASSWORD);
           
        // Sentencia de FK
        PreparedStatement read = oracleConn.prepareStatement(FKIDALERGIAS);
        ResultSet rs = read.executeQuery();
        
        while(rs.next()) {
            ItemMap p = new ItemMap(rs.getInt("idalergia"), rs.getString("nombre"));
            orfanatosMap.put(p.getId(), p);
        }
        oracleConn.close();
        return orfanatosMap;
    }
    
    public void crearPacAl(int idpaciente, int idalergia, Date fecha) throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
		/*
		* Conexion a la base de datos
		*/
		Class.forName(DRIVER).newInstance();
		oracleConn = DriverManager.getConnection(DBURL,USERNAME,PASSWORD);
		   
		oracleConn.setAutoCommit(false);
		// Sentencia de insert
		PreparedStatement insert = oracleConn.prepareStatement(CREATE);
		insert.setInt(1, idpaciente);
		insert.setInt(2, idalergia);
		insert.setDate(3, fecha);
		insert.executeUpdate();
		
		oracleConn.commit();
		oracleConn.setAutoCommit(true);
		oracleConn.close();
	}
    
    public int findIdAl(String name) throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
		/*
		* Conexion a la base de datos
		*/
		Class.forName(DRIVER).newInstance();
		oracleConn = DriverManager.getConnection(DBURL,USERNAME,PASSWORD);
		   
		oracleConn.setAutoCommit(false);
		// Sentencia de insert
		PreparedStatement s = oracleConn.prepareStatement(FIND);
		s.setString(1, name);
		ResultSet rs = s.executeQuery();
		
		int idalergia = 0;
		while (rs.next()) {
			idalergia = rs.getInt("ID");
		}
		
		oracleConn.commit();
		oracleConn.setAutoCommit(true);
		oracleConn.close();
		return idalergia;
	}
    
    public void crearAlergia(Alergia alergia) throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
		/*
		* Conexion a la base de datos
		*/
		Class.forName(DRIVER).newInstance();
		oracleConn = DriverManager.getConnection(DBURL,USERNAME,PASSWORD);
		   
		oracleConn.setAutoCommit(false);
		// Sentencia de insert
		PreparedStatement insert = oracleConn.prepareStatement(CREATE2);
		insert.setString(1, alergia.getNombreAlergia());
		insert.setString(2, alergia.getDescAlergia());
		insert.executeUpdate();
		
		oracleConn.commit();
		oracleConn.setAutoCommit(true);
		oracleConn.close();
	}
    
    public void borrarPacAl(int idPaciente, int idAlergia) throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
		 /*
		 * Conexion a la base de datos
		 */
		 Class.forName(DRIVER).newInstance();
		 oracleConn = DriverManager.getConnection(DBURL,USERNAME,PASSWORD);
		     
		 oracleConn.setAutoCommit(false);
		 
		 // Sentencia de borrado
		 PreparedStatement delete = oracleConn.prepareStatement(DELETE);
		 delete.setInt(1, idAlergia);
		 delete.setInt(2, idPaciente);
		 delete.executeUpdate();
		 
		 oracleConn.commit();
		 oracleConn.setAutoCommit(true);
		 oracleConn.close();
	}
    
    public void borrarAlergia(int idAlergia) throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
		 /*
		 * Conexion a la base de datos
		 */
		 Class.forName(DRIVER).newInstance();
		 oracleConn = DriverManager.getConnection(DBURL,USERNAME,PASSWORD);
		     
		 oracleConn.setAutoCommit(false);
		 
		 // Sentencia de borrado
		 PreparedStatement delete = oracleConn.prepareStatement(DELETE2);
		 delete.setInt(1, idAlergia);
		 delete.executeUpdate();
		 
		 oracleConn.commit();
		 oracleConn.setAutoCommit(true);
		 oracleConn.close();
	}
    
    public DefaultTableModel getTablaPacAlergias(int idpaciente) {
        
        DefaultTableModel tablaVisitas = new DefaultTableModel();
        
        try {
            /*
            * Conexion a la base de datos
            */
            Class.forName(DRIVER).newInstance();
            Connection oracleConn = DriverManager.getConnection(DBURL,USERNAME,PASSWORD);
            
            PreparedStatement s = oracleConn.prepareStatement(READALL);
            s.setInt(1, idpaciente);
            ResultSet rs = s.executeQuery();
            ResultSetMetaData rsMd = rs.getMetaData();
            //La cantidad de columnas que tiene la consulta
            int numeroColumnas = rsMd.getColumnCount();
            //Establecer como cabezeras el nombre de las colimnas
            for (int i = 1; i <= numeroColumnas; i++) {
                tablaVisitas.addColumn(prop.getProperty("colAlergias"+(i-1)));
            }
            //Creando las filas para el JTable
            while (rs.next()) {       
                Object[] fila = new Object[numeroColumnas];
                fila[0] = rs.getString("NIÑO");
                fila[1] = rs.getString("ALERGIA");
                fila[2] = rs.getDate("FECHA");

                tablaVisitas.addRow(fila);
            }
            oracleConn.close();
        }
        catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException e) {
            System.out.println("MVisitasDAO::getTablaVisitas -- " + e.getMessage());
            oracleConn.close();
        }
        finally {
            return tablaVisitas;
        }
    }

}
