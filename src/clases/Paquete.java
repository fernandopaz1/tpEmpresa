package clases;

public class Paquete {
	@Override
	public String toString() {
		return "Paquete [peso=" + peso + ", volumen=" + volumen + ", destino=" + destino + ", necesitaFrio="
				+ necesitaFrio + "]";
	}

	private double peso;
	public double volumen;
	private String destino;
	private boolean necesitaFrio;

	public Paquete(double peso, double volumen, String destino, boolean necesitaFrio) {
		this.peso = peso;
		this.volumen = volumen;
		this.destino = destino;
		this.necesitaFrio = necesitaFrio;
	}

	public double getPeso() {
		return peso;
	}

	public double getVolumen() {
		return volumen;
	}

	public String getDestino() {
		return destino;
	}

	public boolean necesitaFrio() {
		return necesitaFrio;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((destino == null) ? 0 : destino.hashCode());
		result = prime * result + (necesitaFrio ? 1231 : 1237);
		long temp;
		temp = Double.doubleToLongBits(peso);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(volumen);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Paquete other = (Paquete) obj;
		if (destino == null) {
			if (other.destino != null)
				return false;
		} else if (!destino.equals(other.destino))
			return false;
		if (necesitaFrio != other.necesitaFrio)
			return false;
		if (Double.doubleToLongBits(peso) != Double.doubleToLongBits(other.peso))
			return false;
		if (Double.doubleToLongBits(volumen) != Double.doubleToLongBits(other.volumen))
			return false;
		return true;
	}

}
