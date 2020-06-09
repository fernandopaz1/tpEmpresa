package clases;

public class Flete extends Transporte {
	private int cantAcompaniante;
	private double costoPorAcompaniante;

	public Flete(String id, double caM, double capM, double cos, int cantAcom, double costoAcom) {
		super(id, caM, capM, cos);
		this.cantAcompaniante = cantAcom;
		this.costoPorAcompaniante = costoAcom;
	}

	@Override
	public boolean puedeAsignarDestino(String destino, double distancia) {
		return destino != null && distancia > 0;
	}

	@Override
	public double obtenerCostoViaje(double distancia) {
		return distancia * this.costoPorKm + (cantAcompaniante * costoPorAcompaniante);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("\n Flete [ID=").append(idTransporte).append(", cargaMaxima=").append(pesoMaximo)
				.append(", capacidadMaxima=").append(volumenMaximo).append(", costoPorKm=").append(costoPorKm)
				.append(", estaEnViaje=").append(estaEnViaje).append(", destino=").append(destino)
				.append(", pesoCargado=").append(pesoCargado).append(", volumenCargado=").append(volumenCargado)
				.append("]\n");
		return sb.toString();
	}
}
