package clases;

public class CamionTrailerComun extends Transporte {
	private double seguroDeCarga;
	private double costoTercerizado;
	private boolean tieneRefrigeracion;
	
	public CamionTrailerComun(String id, double caM, double capM, double cos, double seguro, boolean refri) {
		super(id, caM, capM, cos);
		this.seguroDeCarga = seguro;
		this.tieneRefrigeracion = refri;
		this.costoTercerizado = 0;
	}

	@Override
	public boolean puedeAsignarDestino(String destino, double distancia) {
		return destino != null && distancia > 0 && distancia < 500;
	}

	@Override
	public void agregaCostoTercerizado(double costoPorTonelada) {
		costoTercerizado = costoTercerizado + costoPorTonelada;
	}

	@Override
	public double obtenerCostoViaje(double distancia) {
		return (distancia * this.costoPorKm) + seguroDeCarga + costoTercerizado;
	}
	
	@Override
	public boolean tieneRefrigeracion() {
		return tieneRefrigeracion;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("\n Camion Trailer Comun [ID=").append(idTransporte).append(", cargaMaxima=").append(pesoMaximo)
				.append(", capacidadMaxima=").append(volumenMaximo).append(", costoPorKm=").append(costoPorKm)
				.append(", estaEnViaje=").append(estaEnViaje).append(", destino=").append(destino)
				.append(", refrigeracion=").append(tieneRefrigeracion).append(", pesoCargado=").append(pesoCargado)
				.append(", volumenCargado=").append(volumenCargado).append("]\n");
		return sb.toString();
	}

}
