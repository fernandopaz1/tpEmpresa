package clases;

public class CamionMegaTrailer extends Transporte {
	private double seguroDeCarga;
	private double costoFijoPorViaje;
	private double comidaDelConductor;
	private double costoTercerizado;
	private boolean tieneRefrigeracion;

	public CamionMegaTrailer(String id, double caM, double capM, double cos, double seguro, boolean refri,
			double costoFijo, double comidaCon) {
		super(id, caM, capM, cos);
		this.tieneRefrigeracion = refri;
		this.seguroDeCarga = seguro;
		this.costoFijoPorViaje = costoFijo;
		this.comidaDelConductor = comidaCon;
		this.costoTercerizado = 0;
	}

	@Override
	public boolean puedeAsignarDestino(String destino, double distancia) {
		return !(destino == null || distancia < 500);
	}

	@Override
	public boolean tieneRefrigeracion() {
		return tieneRefrigeracion;
	}

	@Override
	public void agregaCostoTercerizado(double costoPorTonelada) {
		costoTercerizado = costoTercerizado + costoPorTonelada;
	}

	public double obtenerCostoViaje(double distancia) {
		return distancia * this.costoPorKm + costoFijoPorViaje + seguroDeCarga + comidaDelConductor + costoTercerizado;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("\n Camion Mega Trailer [ID=").append(idTransporte).append(", cargaMaxima=").append(pesoMaximo)
				.append(", capacidadMaxima=").append(volumenMaximo).append(", costoPorKm=").append(costoPorKm)
				.append(", estaEnViaje=").append(estaEnViaje).append(", destino=").append(destino)
				.append(", pesoCargado=").append(pesoCargado).append(", volumenCargado=").append(volumenCargado)
				.append("]\n");
		return sb.toString();
	}
}
