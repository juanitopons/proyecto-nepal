package model;

public class Vacuna {
	private int idVacuna;
	private String nombreVacuna;
	private int edadMin;
	private int edadMax;
	
	public Vacuna() {}

	public Vacuna(String nombreVacuna, int edadMin, int edadMax) {
		super();
		this.nombreVacuna = nombreVacuna;
		this.edadMin = edadMin;
		this.edadMax = edadMax;
	}

	/**
	 * @return the idVacuna
	 */
	public int getIdVacuna() {
		return idVacuna;
	}

	/**
	 * @param idVacuna the idVacuna to set
	 */
	public void setIdVacuna(int idVacuna) {
		this.idVacuna = idVacuna;
	}

	/**
	 * @return the nombreVacuna
	 */
	public String getNombreVacuna() {
		return nombreVacuna;
	}

	/**
	 * @param nombreVacuna the nombreVacuna to set
	 */
	public void setNombreVacuna(String nombreVacuna) {
		this.nombreVacuna = nombreVacuna;
	}

	/**
	 * @return the edadMin
	 */
	public int getEdadMin() {
		return edadMin;
	}

	/**
	 * @param edadMin the edadMin to set
	 */
	public void setEdadMin(int edadMin) {
		this.edadMin = edadMin;
	}

	/**
	 * @return the edadMax
	 */
	public int getEdadMax() {
		return edadMax;
	}

	/**
	 * @param edadMax the edadMax to set
	 */
	public void setEdadMax(int edadMax) {
		this.edadMax = edadMax;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Vacuna [idVacuna=" + idVacuna + ", nombreVacuna="
				+ nombreVacuna + ", edadMin=" + edadMin + ", edadMax="
				+ edadMax + "]";
	}

}
