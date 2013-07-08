package model;

import java.sql.Timestamp;


/**
*
* @author juanitopons
*/
public class Visita {
	
    private int idPaciente;
    private int idVisita;
    private Timestamp fecha;
    private String pelo;
    private String vision;
    private String oidos;
    private String dientes;
    private String higiene;
    private double altura;
    private double peso;
    //private Date fechaNacimiento;
    private double imc;
    private int fc;
    private int tamax;
    private int tamin;
    private String observaciones;
    
    public Visita() {}
    
    public Visita(int idPaciente, Timestamp fecha, String pelo, String vision, String oidos, String dientes, String higiene, double altura, double peso, double imc, int fc, int tamax, int tamin, String observaciones) {
    	this.idPaciente = idPaciente;
    	this.fecha = fecha;
    	this.pelo = pelo;
    	this.vision = vision;
    	this.oidos = oidos;
    	this.dientes = dientes;
    	this.higiene = higiene;
    	this.altura = altura;
    	this.peso = peso;
    	this.imc = imc;
    	this.fc = fc;
    	this.tamax = tamax;
    	this.tamin = tamin;
    	this.observaciones = observaciones;
    }

	/**
	 * @return the idPaciente
	 */
	public int getIdPaciente() {
		return idPaciente;
	}

	/**
	 * @param idPaciente the idPaciente to set
	 */
	public void setIdPaciente(int idPaciente) {
		this.idPaciente = idPaciente;
	}

	/**
	 * @return the idVisita
	 */
	public int getIdVisita() {
		return idVisita;
	}

	/**
	 * @param idVisita the idVisita to set
	 */
	public void setIdVisita(int idVisita) {
		this.idVisita = idVisita;
	}

	/**
	 * @return the fecha
	 */
	public Timestamp getFecha() {
		return fecha;
	}

	/**
	 * @param fecha the fecha to set
	 */
	public void setFecha(Timestamp fecha) {
		this.fecha = fecha;
	}

	/**
	 * @return the pelo
	 */
	public String getPelo() {
		return pelo;
	}

	/**
	 * @param pelo the pelo to set
	 */
	public void setPelo(String pelo) {
		this.pelo = pelo;
	}

	/**
	 * @return the vision
	 */
	public String getVision() {
		return vision;
	}

	/**
	 * @param vision the vision to set
	 */
	public void setVision(String vision) {
		this.vision = vision;
	}

	/**
	 * @return the oidos
	 */
	public String getOidos() {
		return oidos;
	}

	/**
	 * @param oidos the oidos to set
	 */
	public void setOidos(String oidos) {
		this.oidos = oidos;
	}

	/**
	 * @return the dientes
	 */
	public String getDientes() {
		return dientes;
	}

	/**
	 * @param dientes the dientes to set
	 */
	public void setDientes(String dientes) {
		this.dientes = dientes;
	}

	/**
	 * @return the higiene
	 */
	public String getHigiene() {
		return higiene;
	}

	/**
	 * @param higiene the higiene to set
	 */
	public void setHigiene(String higiene) {
		this.higiene = higiene;
	}

	/**
	 * @return the altura
	 */
	public double getAltura() {
		return altura;
	}

	/**
	 * @param altura the altura to set
	 */
	public void setAltura(double altura) {
		this.altura = altura;
	}

	/**
	 * @return the peso
	 */
	public double getPeso() {
		return peso;
	}

	/**
	 * @param peso the peso to set
	 */
	public void setPeso(double peso) {
		this.peso = peso;
	}

	/**
	 * @return the imc
	 */
	public double getImc() {
		return imc;
	}

	/**
	 * @param imc the imc to set
	 */
	public void setImc(double imc) {
		this.imc = imc;
	}

	/**
	 * @return the fc
	 */
	public int getFc() {
		return fc;
	}

	/**
	 * @param fc the fc to set
	 */
	public void setFc(int fc) {
		this.fc = fc;
	}

	/**
	 * @return the tamax
	 */
	public int getTamax() {
		return tamax;
	}

	/**
	 * @param tamax the tamax to set
	 */
	public void setTamax(int tamax) {
		this.tamax = tamax;
	}

	/**
	 * @return the tamin
	 */
	public int getTamin() {
		return tamin;
	}

	/**
	 * @param tamin the tamin to set
	 */
	public void setTamin(int tamin) {
		this.tamin = tamin;
	}

	/**
	 * @return the observaciones
	 */
	public String getObservaciones() {
		return observaciones;
	}

	/**
	 * @param observaciones the observaciones to set
	 */
	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Visita [idPaciente=" + idPaciente + ", idVisita=" + idVisita
				+ ", fecha=" + fecha + ", pelo=" + pelo + ", vision=" + vision
				+ ", oidos=" + oidos + ", dientes=" + dientes + ", higiene="
				+ higiene + ", altura=" + altura + ", peso=" + peso + ", imc="
				+ imc + ", fc=" + fc + ", tamax=" + tamax + ", tamin=" + tamin
				+ ", observaciones=" + observaciones + "]";
	}

}
