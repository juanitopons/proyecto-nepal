package model;

public class Orfanato {
	
	private int idOrfanato;
	private String nombreOrfanato;
	
	public Orfanato() {}
	
	public Orfanato(String nombreOrfanato) {
		this.nombreOrfanato = nombreOrfanato;
	}

	/**
	 * @return the idOrfanato
	 */
	public int getIdOrfanato() {
		return idOrfanato;
	}

	/**
	 * @param idOrfanato the idOrfanato to set
	 */
	public void setIdOrfanato(int idOrfanato) {
		this.idOrfanato = idOrfanato;
	}

	/**
	 * @return the nombreOrfanato
	 */
	public String getNombreOrfanato() {
		return nombreOrfanato;
	}

	/**
	 * @param nombreOrfanato the nombreOrfanato to set
	 */
	public void setNombreOrfanato(String nombreOrfanato) {
		this.nombreOrfanato = nombreOrfanato;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Orfanato [idOrfanato=" + idOrfanato + ", nombreOrfanato="
				+ nombreOrfanato + "]";
	}

	
}
