package clases;

import java.util.ArrayList;
import java.util.Iterator;

public class Deposito {
	protected Integer idDeposito;
	protected ArrayList<Paquete> paquetesAlmacenados;
	protected boolean esFrigorifico;
	protected double capacidadMaxima;
	protected double volumenDisponible;

	public Deposito(Integer id, boolean esFri, double capMax) {
		if (id < 0) {
			throw new RuntimeException("Id No valido");
		}
		if (capMax < 0) {
			throw new RuntimeException("Capacidad maxima no valida");
		}
		this.idDeposito = id;
		this.paquetesAlmacenados = new ArrayList<Paquete>();
		this.esFrigorifico = esFri;
		this.capacidadMaxima = capMax;
		this.volumenDisponible = capMax;
	}

	public boolean incorporarPaquete(Paquete paquete) {
		if (paquete.necesitaFrio() != esFrigorifico) {
			return false;
		}

		if (volumenDisponible > paquete.getVolumen()) {
			paquetesAlmacenados.add(paquete);
			volumenDisponible = volumenDisponible - paquete.getVolumen();
			return true;
		}
		return false;
	}

	public void cargarCamion(Transporte t) {
		boolean pudoCargar;
		ArrayList<Paquete> cargados = new ArrayList<Paquete>();
		Paquete p;
		Iterator<Paquete> iter = paquetesAlmacenados.iterator();
		while (iter.hasNext()) {
			p = iter.next();
			pudoCargar = t.cargarTransporte(p); // booleano
			if (pudoCargar) {
				cargados.add(p); // en este arreglo van los eliminados
			}
		}

		for (Paquete paq : cargados) {
			this.agregarCostoPorTonelada(paq, t);
			this.eliminarPaquetes(paq);
		}
	}

	public void agregarCostoPorTonelada(Paquete p, Transporte t) {
		return;
	}

	public void eliminarPaquetes(Paquete p) {
		volumenDisponible = volumenDisponible + p.getVolumen();
		paquetesAlmacenados.remove(p);

	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(capacidadMaxima);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + (esFrigorifico ? 1231 : 1237);
		result = prime * result + ((idDeposito == null) ? 0 : idDeposito.hashCode());
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
		Deposito other = (Deposito) obj;
		if (Double.doubleToLongBits(capacidadMaxima) != Double.doubleToLongBits(other.capacidadMaxima))
			return false;
		if (esFrigorifico != other.esFrigorifico)
			return false;
		if (idDeposito == null) {
			if (other.idDeposito != null)
				return false;
		} else if (!idDeposito.equals(other.idDeposito))
			return false;
		return true;
	}

}
