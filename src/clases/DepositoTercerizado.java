package clases;

public class DepositoTercerizado extends Deposito {
	private double costoPorTonelada;

	public DepositoTercerizado(Integer id, boolean esFri, double capMax, double costo) {
		super(id, esFri, capMax);
		this.costoPorTonelada = costo;
	}

	@Override
	public void agregarCostoPorTonelada(Paquete p, Transporte t) {
		if (this.esFrigorifico) {
			t.agregaCostoTercerizado(costoPorTonelada * p.getPeso() / 1000);
		}
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("\n Deposito Tercerizado");
		if (esFrigorifico) {
			sb.append(" Frigorigico ");
		}
		sb.append("[ID=").append(idDeposito).append(", capacidadMaxima=").append(capacidadMaxima)
				.append(", volumenDisponible=").append(volumenDisponible).append(", Costo por tonelada= ")
				.append(costoPorTonelada).append("]\n");
		return sb.toString();
	}

}
