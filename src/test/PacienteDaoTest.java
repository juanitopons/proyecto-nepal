package test;

import model.Paciente;
import model.PacienteDAO;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.Calendar;

/**
 *
 * @author diaz
 */
public class PacienteDaoTest {
    private static String modulo = "PacienteDaoTest::";
    private PacienteDAO pacienteDao;
    private Paciente tobi, pipo;
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        PacienteDaoTest pacienteDaoTest = new PacienteDaoTest();
        pacienteDaoTest.doTest();
    }

    public PacienteDaoTest() {
        pacienteDao = new PacienteDAO();
        
    }
    
    public void doTest() {
        
        //SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
            
        try {
            print("Test 1: Crear paciente");    
            Calendar calendar = Calendar.getInstance();
            java.sql.Timestamp ourJavaTimestampObject = new java.sql.Timestamp(calendar.getTime().getTime());
            tobi = new Paciente(1, ourJavaTimestampObject, "Juan", "Perez Hernandez", "H", 14, "No hay ningún antecedente", "foto.jpg");
            pacienteDao.crearPaciente(tobi);
            print("Test 1: " + tobi + " creado.");
            
            print("Test 2: Buscar paciente");
            pipo = pacienteDao.leerPaciente(tobi.getidPaciente());
            print("Test 2: " + pipo + " leída.");
            
            print("Test 3: Cambiar nombre del Paciente");
            pipo.setNombrePaciente("Carlos");
            pacienteDao.actualizarPaciente(pipo);
            tobi = pacienteDao.leerPaciente(pipo.getidPaciente());
            print("Test 3: " + tobi + " modificado.");
            
            
            /*print("Test 4: Borrar paciente " + tobi.getidPaciente() + " (" + tobi.getNombrePaciente() + ")");
            pacienteDao.borrarPaciente(tobi.getidPaciente());
            print("Test 4: Paciente borrado");
            */
            
            print("Todos los test correctos");
            
        } catch (ParseException | ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException ex) {
            print("Error ejecutando tests *************************");
            print(" Excepción: " + ex.getMessage());
            print("");
            ex.printStackTrace();
        }
    }
    
    private void print(String string) {
        System.out.println(modulo + string);
    }
}
