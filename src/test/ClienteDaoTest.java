package test;

import model.Cliente;
import model.ClienteDAO;
import java.sql.SQLException;

/**
 *
 * @author diaz
 */
public class ClienteDaoTest {
    private static String modulo = "ClienteDaoTest::";
    private ClienteDAO clienteDao;
    private Cliente tobi, pipo;
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        ClienteDaoTest clienteDaoTest = new ClienteDaoTest();
        clienteDaoTest.doTest();
    }

    public ClienteDaoTest() {
        clienteDao = new ClienteDAO();
        
    }
    
    public void doTest() {
        
        //SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
            
        try {
            print("Test 1: Crear cliente");            
            tobi = new Cliente(10,1,"Charlatan","Calle sin nombre", null, "juan@prueba.com");
            clienteDao.crearCliente(tobi);
            print("Test 1: " + tobi + " creado.");
            
            print("Test 2: Buscar cliente");
            pipo = clienteDao.leerCliente(tobi.getidCliente());
            print("Test 2: " + pipo + " leída.");
            
            print("Test 3: Cambiar nombre del Cliente");
            pipo.setnombreCliente("pipo");
            clienteDao.actualizarCliente(pipo);
            tobi = clienteDao.leerCliente(pipo.getidCliente());
            print("Test 3: " + tobi + " modificado.");
            
            print("Test 4: Borrar cliente " + tobi.getidCliente() + " (" + tobi.getnombreCliente() + ")");
            clienteDao.borrarCliente(tobi.getidCliente());
            print("Test 4: Cliente borrado");
            
            print("Todos los test correctos");
            
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException ex) {
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
