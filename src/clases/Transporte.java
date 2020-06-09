package clases;

import java.util.ArrayList;

public class Transporte {
	protected String idTransporte;
	protected double pesoMaximo;
	protected double volumenMaximo;
	protected double costoPorKm;
	protected boolean estaEnViaje;
	protected String destino;
	protected double pesoCargado;
	protected double volumenCargado;
	protected ArrayList<Paquete> carga;

	public Transporte(String id, double caM, double capM, double cos) {
		this.carga = new ArrayList<Paquete>();
		if (id == null) {
			throw new RuntimeException("Id no valida");
		}
		if (pesoMaximo < 0) {
			throw new RuntimeException("Peso Maximo no valido");
		}
		this.idTransporte = id;
		this.pesoMaximo = caM;
		this.volumenMaximo = capM;
		this.costoPorKm = cos;
		this.estaEnViaje = false;
		this.pesoCargado = 0;
		this.volumenCargado = 0;
	}

	public boolean puedeCargarTransporte(Paquete paquete) {
		if (!paquete.getDestino().equals(destino)) {
			return false;
		}
		if (paquete.necesitaFrio() != this.tieneRefrigeracion()) {
			return false;
		}
		if ((volumenMaximo - volumenCargado) < paquete.getVolumen()) {
			return false;
		}
		if ((pesoMaximo - pesoCargado) < paquete.getPeso()) {
			return false;
		}
		return true;
	}

	public boolean cargarTransporte(Paquete paquete) {
		boolean puede = this.puedeCargarTransporte(paquete);
		if (puede) {
			carga.add(paquete);
			volumenCargado = volumenCargado + paquete.getVolumen();
			pesoCargado = pesoCargado + paquete.getPeso();
		}
		return puede;
	}

	public boolean puedeAsignarDestino(String destino, double distancia) {
		return true; // Usa la funcion de cada subclase aca solo declaramos la funcion
	}

	public void asignarDestino(String destino, double distancia) {
		if (this.puedeAsignarDestino(destino, distancia)) {
			this.destino = destino;
		}
	}

	public double obtenerCostoViaje(double distancia) {
		return 0; // Utiliza los metodos de la subclase
	}

	public void setEstaEnViaje(boolean estaEnViaje) {
		this.estaEnViaje = estaEnViaje;
	}

	public double getVolumenCargado() {
		return volumenCargado;
	}

	public double getPesoCargado() {
		return pesoCargado;
	}

	public void vaciarCarga() {
		this.carga = new ArrayList<Paquete>();
	}

	public void agregaCostoTercerizado(double costoPorTonelada) {
		return;
	}

	public void borrarDestino() {
		this.destino = null;
	}

	public void finalizarViaje() {
		if (!this.estaEnViaje) {
			return;
		}
		this.setEstaEnViaje(false);
		this.vaciarCarga();
		this.borrarDestino();
	}

	public boolean estaEnViaje() {
		return estaEnViaje;
	}

	public boolean tieneRefrigeracion() {
		return false;
	}

	public boolean tieneCarga() {
		if (this.carga.size() > 0 && this.getPesoCargado() > 0 && this.getVolumenCargado() > 0) {
			return true;
		}
		return false;
	}

	public String getDestino() {
		return destino;
	}

	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((carga == null) ? 0 : carga.hashCode());
		result = prime * result + ((destino == null) ? 0 : destino.hashCode());
		return result;
	}

	public boolean mismaCarga(Transporte otro) {
		boolean estaEnElOtro = false;
		boolean mismaCarga = true;
		for (Paquete p : this.carga) {
			estaEnElOtro = false;
			for (Paquete p2 : otro.carga) {
				estaEnElOtro = estaEnElOtro || p.equals(p2);
			}
			mismaCarga = mismaCarga && estaEnElOtro;
		}
		return mismaCarga;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Transporte other = (Transporte) obj;
		if (carga == null) {
			if (other.carga != null)
				return false;
		} else if (!this.mismaCarga(other))
			return false;
		if (destino == null) {
			if (other.destino != null)
				return false;
		} else if (!destino.equals(other.destino))
			return false;
		return true;
	}

}
