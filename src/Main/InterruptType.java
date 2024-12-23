package main;

/**
 * Enumeração que representa os diferentes tipos de interrupções, 
 * cada uma com uma associação a um nível de prioridade.
 */

public enum InterruptType {
	/**
     * Interrupção do tipo TIMER, com prioridade 3.
     */
	TIMER(3),
	/**
     * Interrupção do tipo IO (Entrada/Saída), com prioridade 2.
     */
	IO(2),
	/**
     * Interrupção do tipo SYSTEM_ERROR (Erro de Sistema), com prioridade 1.
     */
	SYSTEM_ERROR(1);
	
	/** O nível de prioridade associado ao tipo de interrupção. */
	private final int prioridade;
	
	/**
     * Método Construtor privado que associa um nível de prioridade ao tipo de interrupção, ou seja, 
     * para inicializar o tipo de interrupção com a prioridade especificada.
     *
     * @param prioridade identifica o nível de prioridade associado ao tipo de interrupção.
	 * 
	 */
	
	private InterruptType(int prioridade) {
		this.prioridade = prioridade;
	}
	
	/**
     * Retorna o nível de prioridade associado a tipo de interrupção.
     *
     * @return o nível de prioridade do tipo de interrupção.
     */
	public int getPrioridade() {
		return prioridade;
	}
}