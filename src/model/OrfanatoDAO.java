package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class OrfanatoDAO {
	
    /*
     * Parámetros de conexión a la base de datos
     */
    public static final String DRIVER = "com.mysql.jdbc.Driver";
    public static final String DBURL = "jdbc:mysql://localhost/nepal";
    public static final String USERNAME = "root";
    public static final String PASSWORD = "@juan@2468";
    /*
     * Consultas Orfanatos
     *
     * IDORFANATO	NUMBER(2,0)
     * NOMBRE	VARCHAR(60)
     */
    
    private static final String CREATE = 
            "INSERT INTO orfanatos (nombre) " +
            "VALUES (?)";
    
    private static final String DELETE =
            "DELETE FROM orfanatos " +
            " WHERE idorfanato = ?";
    
    Connection oracleConn;

    public OrfanatoDAO() {}
    
    public void closeConn() throws SQLException {
    	oracleConn.commit();
        oracleConn.setAutoCommit(true);
        oracleConn.close();
    }
    
    public void crearOrfanato(Orfanato orfanato) throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
		/*
		* Conexion a la base de datos
		*/
		Class.forName(DRIVER).newInstance();
		oracleConn = DriverManager.getConnection(DBURL,USERNAME,PASSWORD);
		   
		oracleConn.setAutoCommit(false);
		// Sentencia de insert
		PreparedStatement insert = oracleConn.prepareStatement(CREATE);
		insert.setString(1, orfanato.getNombreOrfanato());
		insert.executeUpdate();
        
        oracleConn.commit();
        oracleConn.setAutoCommit(true);
        oracleConn.close();
    }
    
    public void borrarPaciente(int idOrfanato) throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
		 /*
		 * Conexion a la base de datos
		 */
		 Class.forName(DRIVER).newInstance();
		 oracleConn = DriverManager.getConnection(DBURL,USERNAME,PASSWORD);
		     
		 oracleConn.setAutoCommit(false);
		 
		 // Sentencia de borrado
		 PreparedStatement delete = oracleConn.prepareStatement(DELETE);
		 delete.setInt(1, idOrfanato);
		 delete.executeUpdate();
		 
		 oracleConn.commit();
		 oracleConn.setAutoCommit(true);
		 oracleConn.close();
	}
}
