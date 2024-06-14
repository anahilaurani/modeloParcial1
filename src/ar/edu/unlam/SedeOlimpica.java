package ar.edu.unlam;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.TreeMap;
import java.util.TreeSet;

public class SedeOlimpica {

	String nombre;
	private List<Complejo>complejos;
	
	public SedeOlimpica(String nombre) {
		this.nombre = nombre;
		this.complejos = new ArrayList<>();
	}
	public Boolean sePuedeCrearUnComplejoSimple(Complejo complejoSimple) {
		//validacionUno();
		return complejos.add(complejoSimple);
	}
	public Boolean sePuedeCrearUnComplejoPolideportivo(Complejo complejoPolideportivo)  {
		return complejos.add(complejoPolideportivo);
	}

	public Boolean seCreaUnEventoAUnComplejoPoli(Complejo complejoPolideportivo, Evento evento1) {
		for (Complejo c : complejos) {
			if (c.equals(complejoPolideportivo)) {
				c.agregarEvento(evento1);
				return true;
			}
		}
		return null;
	}
	public Boolean validarPolideportivoSiElIdentifiacadorYaExiste(Complejo complejo) throws NoSePuedoAgregar {
		ComplejoPolideportivo complejoPolideportivo = (ComplejoPolideportivo) complejo; 
		for (Complejo c : complejos) {
			if (c instanceof ComplejoPolideportivo) {
				ComplejoPolideportivo cp = (ComplejoPolideportivo) c;
				if (cp.getTipoUbicacion().equals(complejoPolideportivo.getTipoUbicacion())) {
					throw new NoSePuedoAgregar("Ya existe un identificador igual");
				}
			}
		}
		return this.complejos.add(complejo);
	}
	public Boolean sePuedaAgregarUnComisarioJuezAUnEvento(Complejo complejo,Evento evento1, Comisario comisarioJuez) throws NoSePuedeAgregarUnComisarioJuez {
		for (Complejo c : complejos) {
			if (c.equals(complejo)) {
			   for(EventoComisario ec : c.getEventosComisarios()) {
				   if (ec.getEvento().equals(evento1)) {
					validarComisarioJuezNoNull(comisarioJuez);
					ec.setComisario(comisarioJuez);
					return true;
				}
			   }
			}
		}
		return null;
	}
	public Boolean validarComisarioJuezNoNull(Comisario comisarioJuez) throws NoSePuedeAgregarUnComisarioJuez {
	    if (comisarioJuez == null) {
			throw new NoSePuedeAgregarUnComisarioJuez("ComisarioJuezEsNull");
		}
		return true;
	}
	//---------------------------------------------------------------------------------------------------------
	
	public Integer calcularElTotalDeParticipantesDeUnComplejoSimple(Complejo complejoSimple) {
	    Integer total =0;
		for (Complejo c : complejos) {
			//if (c instanceof ComplejoSimple) {
				if (c.equals(complejoSimple)) {
					total = c.getCantidadDEParticipantes();
					return total;
				//}
			}
		}
		return total;
	}
	public Boolean sePuedaAgregarUnComisarioAUnEventoDirectamente(Complejo complejo, Evento evento1,
			Comisario comisarioObservador) {
		for (Complejo c : complejos) {
			if (c.equals(complejo)) {
				c.agregarComisarioAEvento(new EventoComisario(evento1,comisarioObservador));
				return true;
			}
		}
		return null;
	}
	public Double calcularELPromedioDeEdadDeLosObservadores(Complejo complejo, Evento evento1) {
		Double promedio =0.0;
		for (Complejo c : complejos) {
			if (c.equals(complejo)) {
				promedio = c.calcularPromedioDeObservadores(evento1);
			}
		}
		
		return promedio;
	}
	public TreeSet<Comisario> obtnerListaDeJuecesDeUnEvento(Complejo complejoPolideportivo, Evento evento1) {
		TreeSet<Comisario> lista = new TreeSet<Comisario>();
		for (Complejo c : complejos) {
			if (c.equals(complejoPolideportivo)) {
				lista = c.obtenerLosJuecesDeUnEvento(evento1);
			}
		}
		return lista;
	}
	public TreeMap<String,TipoUbicacion> obtenerMapaDeLosComplejoPolideportivos() {
		TreeMap<String,TipoUbicacion> listaMap = new TreeMap<>();
		for (Complejo complejo : complejos) {
			if (complejo instanceof ComplejoPolideportivo) {
				ComplejoPolideportivo cp= (ComplejoPolideportivo)complejo;
				listaMap.put(complejo.getNombre(),cp.getTipoUbicacion() );
			}
		}
		return listaMap;
	}
	
	

}
