package model;

import java.sql.Timestamp;

//import java.text.SimpleDateFormat;
//import java.util.Date;
/**
 *
 * @author juanitopons
 */
public class Paciente {

    private int idPaciente;
    private int idOrfanato;
    private Timestamp fecha;
    private String nombrePaciente;
    private String apellidosPaciente;
    private String genPaciente;
    private int edadPaciente;
    private String antecedPaciente;
    private String fotoPaciente;
    //private Date fechaNacimiento;
    private String nombreOrfanato;

    public Paciente() {}

    public Paciente(int idOrfanato, Timestamp fecha, String nombrePaciente, String apellidosPaciente, String genPaciente, int edadPaciente, String antecedPaciente, String fotoPaciente) {
        this.idOrfanato = idOrfanato;
        this.fecha = fecha;
        this.nombrePaciente = nombrePaciente;
        this.apellidosPaciente = apellidosPaciente;
        this.genPaciente = genPaciente;
        this.edadPaciente = edadPaciente;
        this.antecedPaciente = antecedPaciente;
        this.fotoPaciente = fotoPaciente;
    }

    /* ID Paciente */
    public int getidPaciente() {
        return idPaciente;
    }
    
    public void setidPaciente(int idPaciente) {
        this.idPaciente = idPaciente;
    }
    
    /* ID Orfanato */
    public int getidOrfanato() {
        return idOrfanato;
    }

    public void setidOrfanato(int idOrfanato) {
        this.idOrfanato = idOrfanato;
    }
    
    /* NOMBRE Orfanato */
    public String getNombreOrfanato() {
        return nombreOrfanato;
    }

    public void setNombreOrfanato(String nombreOrfanato) {
        this.nombreOrfanato = nombreOrfanato;
    }
    
    /* Fecha */
    public Timestamp getFecha() {
    	return fecha;
    }
    
    public void setFecha(Timestamp fecha) {
    	this.fecha = fecha;
    }

    /* Nombre Paciente */
    public String getNombrePaciente() {
        return nombrePaciente;
    }

    public void setNombrePaciente(String nombrePaciente) {
        this.nombrePaciente = nombrePaciente;
    }
    
    /* Apellidos Paciente */
    public String getApellidosPaciente() {
        return apellidosPaciente;
    }

    public void setApellidosPaciente(String apellidosPaciente) {
        this.apellidosPaciente = apellidosPaciente;
    }
    
    /* GENERO Paciente */
    public String getGenPaciente() {
        return genPaciente;
    }

    public void setGenPaciente(String genPaciente) {
        this.genPaciente = genPaciente;
    }

    /* EDAD Paciente */
    public int getEdadPaciente() {
        return edadPaciente;
    }

    public void setEdadPaciente(int edadPaciente) {
        this.edadPaciente = edadPaciente;
    }
    
    /* ANTECEDENTES Paciente */
    public String getAntecedPaciente() {
        return antecedPaciente;
    }

    public void setAntecedPaciente(String antecedPaciente) {
        this.antecedPaciente = antecedPaciente;
    }
    
    /* FOTO Paciente */
    public String getFotoPaciente() {
        return fotoPaciente;
    }

    public void setFotoPaciente(String fotoPaciente) {
        this.fotoPaciente = fotoPaciente;
    }

    @Override
    public String toString() {
        //SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        
        return "Paciente{" + "idPaciente=" + idPaciente + 
            ", idOrfanato=" + idOrfanato +
            ", nombreOrfanato=" + nombreOrfanato +
            ", fecha=" + fecha +
            ", nombrePaciente=" + nombrePaciente + 
            ", apellidosPaciente=" + apellidosPaciente +
            ", genPaciente=" + genPaciente +
            ", edadPaciente=" + edadPaciente +
            ", antecedPaciente=" + antecedPaciente +
            ", fotoPaciente=" + fotoPaciente +'}';
    }

}
