package Main;

public enum InterruptType {
	TIMER(3),
	IO(2),
	SYSTEM_ERROR(1);
	
	private final int prioridade;
	
	private InterruptType(int prioridade) {
		this.prioridade = prioridade;
	}
	public int getPrioridade() {
		return prioridade;
	}
}
