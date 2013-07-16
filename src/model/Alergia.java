package model;

public class Alergia {
	private int idAlergia;
	private String nombreAlergia;
	private String descAlergia;
	
	public Alergia() {}

	public Alergia(String nombreAlergia, String descAlergia) {
		super();
		this.nombreAlergia = nombreAlergia;
		this.descAlergia = descAlergia;
	}

	/**
	 * @return the idAlergia
	 */
	public int getIdAlergia() {
		return idAlergia;
	}

	/**
	 * @param idAlergia the idAlergia to set
	 */
	public void setIdAlergia(int idAlergia) {
		this.idAlergia = idAlergia;
	}

	/**
	 * @return the nombreAlergia
	 */
	public String getNombreAlergia() {
		return nombreAlergia;
	}

	/**
	 * @param nombreAlergia the nombreAlergia to set
	 */
	public void setNombreAlergia(String nombreAlergia) {
		this.nombreAlergia = nombreAlergia;
	}

	/**
	 * @return the descAlergia
	 */
	public String getDescAlergia() {
		return descAlergia;
	}

	/**
	 * @param descAlergia the descAlergia to set
	 */
	public void setDescAlergia(String descAlergia) {
		this.descAlergia = descAlergia;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Alergia [idAlergia=" + idAlergia + ", nombreAlergia="
				+ nombreAlergia + ", descAlergia=" + descAlergia + "]";
	}
	
}
