package Main;

public class Interruption implements Comparable<Interruption>{
	
	private InterruptType tipo;
	
	public Interruption(InterruptType tipo) {
		this.tipo = tipo;
	}
	public InterruptType getTipo() {
		return tipo;
	}
	/**
	 * 
	 * @return
	 */
	public int getPrioridade() {
		return tipo.getPrioridade();
	}
	
	/**
	 * Descrever o metodo
	 * @param descrever o parametro
	 * @return descrever o retorno
	 */
	@SuppressWarnings("unused")
	private int metodo(int a) {
		return 0;
	}
	
	@Override
	public int compareTo(Interruption o) {
		return Integer.compare(tipo.getPrioridade(), o.getPrioridade());
	}
	
	public String toString() {
        return "Interruption{" +
                "tipo=" + tipo +
                ", prioridade=" + getPrioridade() +
                '}';
    }
}
