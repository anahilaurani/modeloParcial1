package ar.edu.unlam;

public class EventoComisario {

	private Evento evento;
	private Comisario comisario;
	
	public EventoComisario(Evento evento, Comisario comisario) {
	 this.evento = evento;
	 this.comisario= comisario;
	}

	public Evento getEvento() {
		return evento;
	}

	public void setEvento(Evento evento) {
		this.evento = evento;
	}

	public Comisario getComisario() {
		return comisario;
	}

	public void setComisario(Comisario comisario) {
		this.comisario = comisario;
	}

	
}
