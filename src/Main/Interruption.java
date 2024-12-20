package Main;

public class Interruption implements Comparable<Interruption>{
	
	private InterruptType tipo;
	
	public Interruption(InterruptType tipo) {
		this.tipo = tipo;
	}
	public InterruptType getTipo() {
		return tipo;
	}
	public int getPrioridade() {
		return tipo.getPrioridade();
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
