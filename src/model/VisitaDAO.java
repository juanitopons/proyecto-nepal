package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.text.ParseException;

import javax.swing.table.DefaultTableModel;

import properties.MyProperties;

public class VisitaDAO {
    /*
     * Parámetros de conexión a la base de datos
     */
    public static final String DRIVER = "com.mysql.jdbc.Driver";
    public static final String DBURL = "jdbc:mysql://localhost/nepal";
    public static final String USERNAME = "root";
    public static final String PASSWORD = "@juan@2468";
    /*
     * Consultas Visitas
     *
     * idvisita int(10) NOT NULL AUTO_INCREMENT,
     * idpaciente int(5) NOT NULL,
     * fechavisita timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
     * pelo char(1) NOT NULL,
     * vision char(1) NOT NULL,
     * oidos char(1) NOT NULL,
     * dientes char(1) NOT NULL,
     * higiene char(1) NOT NULL,
     * altura double NOT NULL,
     * peso double NOT NULL,
     * imc int(10) DEFAULT NULL,
     * fc int(4) DEFAULT NULL,
     * tamax int(10) DEFAULT NULL,
     * tamin int(10) DEFAULT NULL,
     * observaciones varchar(255) DEFAULT NULL,
     */ 
    
    private static final String CREATE = 
            "INSERT INTO visitas (idpaciente, fechavisita, " +
            "                         pelo, vision, oidos, dientes, higiene, altura, peso, imc, fc, tamax, tamin, observaciones) " +
            "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
    
    private static final String READ = 
            "SELECT idvisita, idpaciente, fechavisita, pelo, vision, oidos, dientes, higiene, altura, peso, imc, fc, tamax, tamin, observaciones " +
            " FROM visitas" +
            " WHERE idvisita = ?";
    
    private static final String READALL = 
            "SELECT idvisita AS ID, fechavisita AS FECHA, pelo AS PELO, vision AS VISTA, oidos AS OIDOS, dientes AS DIENTES, higiene AS HIGIENE, altura AS ALT, peso AS PESO, imc AS IMC, fc AS FC, tamax AS TAMX, tamin AS TAMIN, observaciones AS OBSERVACIONES " +
            " FROM visitas " +
            " WHERE idpaciente = ?" +
            " ORDER BY fechavisita";
    
    private static final String UPDATE =
            "UPDATE visitas " +
            "   SET pelo = ?, vision = ?, oidos = ?, " +
            "       dientes = ?, higiene = ?, altura = ?, peso = ?, imc = ?, fc = ?, tamax = ?, tamin = ?, observaciones = ? " +
            " WHERE idvisita = ?";
    
    private static final String DELETE =
            "DELETE FROM visitas " +
            " WHERE idvisita = ?";
    
    private MyProperties prop;

    public VisitaDAO(MyProperties prop) {
    	this.prop = prop;
    }
    
    public void crearVisita(Visita visita) throws 
    ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {

		/*
		* Conexion a la base de datos
		*/
		Class.forName(DRIVER).newInstance();
		Connection oracleConn = DriverManager.getConnection(DBURL,USERNAME,PASSWORD);
		   
		oracleConn.setAutoCommit(false);
		// Sentencia de insert
		PreparedStatement insert = oracleConn.prepareStatement(CREATE);
		insert.setInt(1, visita.getIdPaciente());
		insert.setTimestamp(2, visita.getFecha());
		insert.setString(3, visita.getPelo());
		insert.setString(4, visita.getVision());
		insert.setString(5, visita.getOidos());
		insert.setString(6, visita.getDientes());
		insert.setString(7, visita.getHigiene());
		insert.setDouble(8, visita.getAltura());
		insert.setDouble(9, visita.getPeso());
		insert.setDouble(10, visita.getImc());
		insert.setInt(11, visita.getFc());
		insert.setInt(12, visita.getTamax());
		insert.setInt(13, visita.getTamin());
		insert.setString(14, visita.getObservaciones());
		insert.executeUpdate();
		
		oracleConn.commit();
		oracleConn.setAutoCommit(true);
		oracleConn.close();
    }
    
    public Visita leerVisita(int idVisita) throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException, ParseException {
        
        Visita visita = new Visita();
        //SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
      
        Class.forName(DRIVER).newInstance();
        Connection oracleConn = DriverManager.getConnection(DBURL,USERNAME,PASSWORD);
           
        // Sentencia de insert
        PreparedStatement read = oracleConn.prepareStatement(READ);
        read.setInt(1, idVisita);
        ResultSet rs = read.executeQuery();
        
        if (rs.next()) {	
        	visita.setIdVisita(rs.getInt("idvisita"));
    		visita.setIdPaciente(rs.getInt("idpaciente"));
    		visita.setFecha(rs.getTimestamp("fecha"));
    		visita.setPelo(rs.getString("pelo"));
    		visita.setVision(rs.getString("vision"));
    		visita.setOidos(rs.getString("oidos"));
    		visita.setDientes(rs.getString("dientes"));
    		visita.setHigiene(rs.getString("higiene"));
    		visita.setAltura(rs.getDouble("altura"));
    		visita.setPeso(rs.getDouble("peso"));
    		visita.setImc(rs.getInt("imc"));
    		visita.setFc(rs.getInt("fc"));
    		visita.setTamax(rs.getInt("tamax"));
    		visita.setTamin(rs.getInt("tamin"));
    		visita.setObservaciones(rs.getString("observaciones"));
        }
        return visita;
    }
    
    public void actualizarPaciente(Visita visita) throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
		 //SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		 /*
		 * Conexion a la base de datos
		 */
		 Class.forName(DRIVER).newInstance();
		 Connection oracleConn = DriverManager.getConnection(DBURL,USERNAME,PASSWORD);
		    
		 oracleConn.setAutoCommit(false);
		 // Sentencia de insert
		 PreparedStatement update = oracleConn.prepareStatement(UPDATE);
		 
		 update.setString(1, visita.getPelo());
		 update.setString(2, visita.getVision());
		 update.setString(3, visita.getOidos());
		 update.setString(4, visita.getDientes());
		 update.setString(5, visita.getHigiene());
		 update.setDouble(6, visita.getAltura());
		 update.setDouble(7, visita.getPeso());
		 update.setDouble(8, visita.getImc());
		 update.setInt(8, visita.getFc());
		 update.setInt(8, visita.getTamax());
		 update.setInt(8, visita.getTamin());
		 update.setString(5, visita.getObservaciones());
		 update.executeUpdate();
		 
		 oracleConn.commit();
		 oracleConn.setAutoCommit(true);
		 oracleConn.close();
    }
    
    public void borrarVisita(int idVisita) throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
		 /*
		 * Conexion a la base de datos
		 */
		 Class.forName(DRIVER).newInstance();
		 Connection oracleConn = DriverManager.getConnection(DBURL,USERNAME,PASSWORD);
		     
		 oracleConn.setAutoCommit(false);
		 
		 // Sentencia de borrado
		 PreparedStatement delete = oracleConn.prepareStatement(DELETE);
		 delete.setInt(1, idVisita);
		 delete.executeUpdate();
		 
		 oracleConn.commit();
		 oracleConn.setAutoCommit(true);
		 oracleConn.close();
    }
    
    public DefaultTableModel getTablaVisitas(int idpaciente) {
        
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
                tablaVisitas.addColumn(prop.getProperty("colVisitas"+(i-1)));
            }
            //Creando las filas para el JTable
            while (rs.next()) {       
                Object[] fila = new Object[numeroColumnas];
                fila[0] = rs.getInt("ID");
                fila[1] = rs.getTimestamp("FECHA");
                fila[2] = converToString(rs.getString("PELO"));
                fila[3] = converToString(rs.getString("VISTA"));
                fila[4] = converToString(rs.getString("OIDOS"));
                fila[5] = converToString(rs.getString("DIENTES"));
                fila[6] = converToString(rs.getString("HIGIENE"));
                fila[7] = rs.getDouble("ALT");
                fila[8] = rs.getDouble("PESO");
                fila[9] = rs.getInt("IMC");
                fila[10] = rs.getInt("FC");
                fila[11] = rs.getInt("TAMX");
                fila[12] = rs.getInt("TAMIN");
                fila[13] = rs.getString("OBSERVACIONES");

                tablaVisitas.addRow(fila);
            }
            oracleConn.close();
        }
        catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException e) {
            System.out.println("MVisitasDAO::getTablaVisitas -- " + e.getMessage());
        }
        finally {
            return tablaVisitas;
        }
    }
    
    private String converToString(String value) {
    	if(value.equalsIgnoreCase("1")) value = "OK";
    	if(value.equalsIgnoreCase("0")) value = prop.getProperty("mal");
    	return value;
    }
	  
}
