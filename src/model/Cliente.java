/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author juanitopons
 */
public class Cliente {
    
    private int idCliente;
    private int tipoCliente;
    private String nombreCliente;
    private String dirCliente;
    private Integer telCliente;
    private String emailCliente;
    //private Date fechaNacimiento;

    public Cliente() {}

    /**
     * Constructor clase Cliente
     * @param idCliente
     * @param tipoCliente
     * @param nombreCliente
     * @param dirCliente
     * @param telCliente
     * @param emailCliente 
     */
    public Cliente(int idCliente, int tipoCliente, String nombreCliente, String dirCliente, Integer telCliente, String emailCliente) {
        this.idCliente = idCliente;
        this.tipoCliente = tipoCliente;
        this.nombreCliente = nombreCliente;
        this.dirCliente = dirCliente;
        this.telCliente = telCliente;
        this.emailCliente = emailCliente;
        //this.fechaNacimiento = fechaNacimiento;
    }
    
    //sin telefono

    /** public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    } **/
    
    /**
     * Consultor del tipo de cliente
     * @return tipoCliente
     */
    public int gettipoCliente() {
        return tipoCliente;
    }

    /**
     * Modificador del tipo de cliente
     * @param tipoCliente 
     */
    public void settipoCliente(int tipoCliente) {
        this.tipoCliente = tipoCliente;
    }

    /**
     * Consultor id del cliente
     * @return idCliente
     */
    public int getidCliente() {
        return idCliente;
    }

    /**
     * Modificador id del cliente
     * @param idCliente 
     */
    public void setidCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    /**
     * Consultor nombre del cliente
     * @return nombreCliente
     */
    public String getnombreCliente() {
        return nombreCliente;
    }

    /**
     * Modificador nombre del cliente
     * @param nombreCliente 
     */
    public void setnombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }
    
    /**
     * Consultor dirección del cliente
     * @return dirCliente
     */
    public String getdirCliente() {
        return dirCliente;
    }

    /**
     * Modificador dirección del cliente
     * @param dirCliente 
     */
    public void setdirCliente(String dirCliente) {
        this.dirCliente = dirCliente;
    }
    
    /**
     * Consultor teléfono del cliente
     * @return telCliente
     */
    public Integer gettelCliente() {
        return telCliente;
    }
    
    /**
     * Modificador teléfono del cliente
     * @param telCliente 
     */
    public void settelCliente(Integer telCliente) {
        this.telCliente = telCliente;
    }
    
    /**
     * Consultor del email del cliente
     * @return emailCliente
     */
    public String getemailCliente() {
        return emailCliente;
    }
    
    /**
     * Modificador del email del cliente
     * @param emailCliente 
     */
    public void setemailCliente(String emailCliente) {
        this.emailCliente = emailCliente;
    }

    @Override
    public String toString() {
        //SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        
        return "Cliente{" + "idCliente=" + idCliente + 
            ", tipoCliente=" + tipoCliente + 
            ", nombreCliente=" + nombreCliente + 
            ", dirCliente=" + dirCliente +
            ", telCliente=" + telCliente +
            ", emailCliente=" + emailCliente + '}';
    }

}
