package clases;

import java.util.HashMap;

public class Empresa {
	public String nombre;
	public String cuit;
	public int nDep; // indica el numero de deposito
	public HashMap<Integer, Deposito> depositos; // <Id deposito, deposito>
	public HashMap<String, Transporte> flota; // <Id transporte, transporte>
	public HashMap<String, Integer> destinos; // relaciona <destino, distancia>

	public Empresa(String cuit, String nombre) {
		if (nombre == null) {
			throw new RuntimeException("Nombre de empresa no valido");
		}
		if (cuit == null) {
			throw new RuntimeException("Cuit no valido");
		}
		depositos = new HashMap<Integer, Deposito>();
		flota = new HashMap<String, Transporte>();
		destinos = new HashMap<String, Integer>();
		this.cuit = cuit;
		this.nombre = nombre;
		nDep = 1;
	}

	public int agregarDeposito(double capacidad, boolean frigorifico, boolean propio) {
		if (propio) {
			DepositoPropio d = new DepositoPropio(nDep, frigorifico, capacidad);
			depositos.put(nDep, d);
			nDep++;
			return nDep;
		}
		if (!frigorifico) {
			DepositoTercerizado dt = new DepositoTercerizado(nDep, false, capacidad, 0);
			depositos.put(nDep, dt);
			nDep++;
		}
		return nDep;
	}

	public int agregarDepTercerizFrio(double capacidad, double costoPorTonelada) {
		DepositoTercerizado depT = new DepositoTercerizado(nDep, true, capacidad, costoPorTonelada);
		depositos.put(nDep, depT);
		nDep++;
		return nDep;
	}

	public void agregarDestino(String destino, int km) {
		if (destino != null && km > 0)
			destinos.put(destino, km);
	}

	public void agregarTrailer(String idTransp, double cargaMax, double capacidad, boolean frigorifico, double costoKm,
			double segCarga) {
		if (!flota.containsKey(idTransp)) {
			CamionTrailerComun trailer = new CamionTrailerComun(idTransp, cargaMax, capacidad, costoKm, segCarga,
					frigorifico);
			flota.put(idTransp, trailer);
		}
	}

	public void agregarMegaTrailer(String idTransp, double cargaMax, double capacidad, boolean frigorifico,
			double costoKm, double segCarga, double costoFijo, double comida) {
		if (!flota.containsKey(idTransp)) {
			CamionMegaTrailer mega = new CamionMegaTrailer(idTransp, cargaMax, capacidad, costoKm, segCarga,
					frigorifico, costoFijo, comida);
			flota.put(idTransp, mega);
		}
	}

	public void agregarFlete(String idTransp, double cargaMax, double capacidad, double costoKm, int acomp,
			double costoPorAcom) {
		if (!flota.containsKey(idTransp)) {
			Flete fle = new Flete(idTransp, cargaMax, capacidad, costoKm, acomp, costoPorAcom);
			flota.put(idTransp, fle);
		}
	}

	public Transporte dameTransporte(String idTransp) {
		return flota.get(idTransp);
	}

	public Deposito dameDeposito(Integer id) {
		return depositos.get(id);
	}

	public void asignarDestino(String idTransp, String destino) {
		if (!destinos.containsKey(destino) || destino == null) {
			return;
		}
		if (dameTransporte(idTransp).tieneCarga()) {
			throw new RuntimeException("No se pudo asignar Destino: El transporte ya tiene mercadereria cargada");
		}

		dameTransporte(idTransp).asignarDestino(destino, destinos.get(destino));
	}

	public boolean incorporarPaquete(String destino, double peso, double volumen, boolean frio) {
		Paquete paq = new Paquete(peso, volumen, destino, frio);
		boolean seIncorporo = false;
		for (Integer id : depositos.keySet()) { // recorremos por Id los depositos
			seIncorporo = dameDeposito(id).incorporarPaquete(paq); // incorpora paquete a deposito
			if (seIncorporo) {
				return seIncorporo;
			}
		}
		return seIncorporo;
	}

	public double cargarTransporte(String idTransp) {
		Transporte t = dameTransporte(idTransp);

		if (t.getDestino() == null) {
			throw new RuntimeException("Error al cargar: El transporte no tiene destino");
		}
		if (t.estaEnViaje()) {
			throw new RuntimeException("Error al cargar: El transporte esta en viaje");
		}
		for (Integer id : this.depositos.keySet()) { // recorremos los depositos
			dameDeposito(id).cargarCamion(t);
		}
		return t.getVolumenCargado();

	}

	public void iniciarViaje(String idTransp) {
		Transporte t = dameTransporte(idTransp);
		if (t.estaEnViaje()) {
			throw new RuntimeException("Error al iniciar viaje: El transporte ya esta en viaje");
		}
		if (t.getDestino() == null) {
			throw new RuntimeException("Error al iniciar viaje: El transporte no tiene destino");
		}
		if (!t.tieneCarga()) {
			throw new RuntimeException("Error al iniciar viaje: El transporte no tiene paquetes cargados");

		}
		t.setEstaEnViaje(true);
	}

	public void finalizarViaje(String idTransp) {
		Transporte t = dameTransporte(idTransp);
		if (!t.estaEnViaje()) {
			throw new RuntimeException("Error al finalizar viaje: El transporte no esta en viaje");
		}
		t.finalizarViaje();
	}

	public double obtenerCostoViaje(String idTransp) {
		Transporte t = dameTransporte(idTransp);
		if (!t.estaEnViaje()) {
			throw new RuntimeException("Error al obtener costo: El transporte no esta en viaje");
		}
		double distancia = destinos.get(t.getDestino());
		return t.obtenerCostoViaje(distancia); // distancia
	}

	public String obtenerTransporteIgual(String idTransp) {
		Transporte t = dameTransporte(idTransp);
		for (String T : flota.keySet()) {
			if (t.equals(dameTransporte(T))) {
				return T;
			}
		}
		return null;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("--------------------------------------------------------------\n Empresa : ").append(nombre)
				.append(", cuit=").append(cuit).append("\n\n Depositos:\n");

		for (Integer id : depositos.keySet()) { // recorremos el id tranporte
			sb.append(dameDeposito(id).toString());
		}
		sb.append("\n\n Flota:\n");

		for (String idT : flota.keySet()) { // recorremos el id tranporte
			sb.append(dameTransporte(idT).toString());
		}
		sb.append("--------------------------------------------------------------\n");

		return sb.toString();
	}

}
