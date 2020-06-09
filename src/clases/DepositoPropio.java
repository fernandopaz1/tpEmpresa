package clases;

public class DepositoPropio extends Deposito {

	public DepositoPropio(Integer id, boolean esFri, double capMax) {
		super(id, esFri, capMax);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("\n Deposito Propio");
		if (esFrigorifico) {
			sb.append(" Frigorigico ");
		}
		sb.append(" [ ID=").append(idDeposito).append(", capacidadMaxima=").append(capacidadMaxima)
				.append(", volumenDisponible=").append(volumenDisponible).append("]\n");
		return sb.toString();
	}
}
