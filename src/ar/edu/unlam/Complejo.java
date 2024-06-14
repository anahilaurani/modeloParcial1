package ar.edu.unlam;

import java.util.HashSet;
import java.util.Iterator;
import java.util.TreeSet;

public abstract class Complejo {
	
	private String nombre;
	private Double areaTotalOcupada;
	private HashSet<EventoComisario> eventosComisarios;

	public Complejo(String nombre, Double areaTotalOcupada) {
		this.nombre=nombre;
		this.areaTotalOcupada = areaTotalOcupada;
		this.eventosComisarios = new HashSet<>() ;
	}

	public Boolean agregarEvento(Evento evento1) {
		EventoComisario ec = new EventoComisario (evento1, null);
		return eventosComisarios.add(ec);		
	}
	
	public String getNombre() {
		return this.nombre;
	}

	public HashSet<EventoComisario> getEventosComisarios() {
		return eventosComisarios;
	}


	public Boolean agregarComisarioAEvento(EventoComisario eventoComisario) {
		return eventosComisarios.add(eventoComisario) ;
		
		
	}
	public Integer getCantidadDEParticipantes() {
		Integer total = 0;
		for (EventoComisario ec: eventosComisarios) {
			total += ec.getEvento().getNroParticipantes();
		}
		return total; 
	}

    public Double calcularPromedioDeObservadores(Evento evento1) {
    	Double total =0.0;
    	Integer cantidad = 0;
    	for (EventoComisario ec : eventosComisarios) {
			if (ec.getEvento().equals(evento1)) {
			  if (ec.getComisario() instanceof ComisarioObservador) {
				cantidad++;
				total +=ec.getComisario().getEdad();
				}
		}
		
	}
    	total /= cantidad;
		return total;
    }

	public TreeSet<Comisario> obtenerLosJuecesDeUnEvento(Evento evento1) {
		TreeSet<Comisario>lista = new TreeSet<Comisario>();
		
        for (EventoComisario ec : eventosComisarios) {
        	if (ec.getEvento().equals(evento1)) {
				if (ec.getComisario() instanceof ComisarioJuez ) {
				   lista.add(ec.getComisario());
			   }
			}
			
		}
		return lista;
		
		
	}
    
}
