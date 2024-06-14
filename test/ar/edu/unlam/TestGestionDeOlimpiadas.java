package ar.edu.unlam;

import static org.junit.Assert.*;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.TreeMap;
import java.util.TreeSet;

import org.junit.Before;
import org.junit.Test;

public class TestGestionDeOlimpiadas {

	private static final String NOMBRE="anahi";
	private SedeOlimpica sedeOlimpica;
	@Before
	public void test() {
		this.sedeOlimpica = new SedeOlimpica(NOMBRE); 
	}

	@Test
	public void queSePuedaCrearUnComplejoSimpleEnUnaSedeOlimpica(){
		Complejo complejoSimple = new ComplejoSimple("abc",100.50);
		Boolean seCreo = this.sedeOlimpica.sePuedeCrearUnComplejoSimple(complejoSimple);
		assertTrue(seCreo);
	}
	
	@Test
	public void queSePuedaCrearUnComplejoPolideportivoConUnAreaEnUnaSedeOlimpica() {
		TipoUbicacion ubicacion = TipoUbicacion.CENTRO;
		Complejo complejoPolideportivo = new ComplejoPolideportivo("abc",100.50,ubicacion);
		Boolean seCreoPoli = this.sedeOlimpica.sePuedeCrearUnComplejoPolideportivo(complejoPolideportivo);
		assertTrue(seCreoPoli);
	}
	
	@Test
	public void queSePuedaCrearUnComplejoPolideportivoConUnAreaYUnEventoEnUnaSedeOlimpica(){
		TipoUbicacion ubicacion = TipoUbicacion.CENTRO;
		Complejo complejoPolideportivo = new ComplejoPolideportivo("abc",100.50,ubicacion);
		this.sedeOlimpica.sePuedeCrearUnComplejoPolideportivo(complejoPolideportivo);
	
		LocalDate fecha = LocalDate.of(2000,12,12);
		LocalTime duracion = LocalTime.of(1, 50);
		Integer nroParticipantes = 10;
		
		Evento evento1 = new Evento (fecha,duracion,nroParticipantes);
		
		Boolean seCrea = this.sedeOlimpica.seCreaUnEventoAUnComplejoPoli(complejoPolideportivo,evento1);
		
		assertTrue(seCrea);
		
	}
	@Test (expected = NoSePuedoAgregar.class)
	public void queAlAgregarUnAreaAUnPolideportivoConIndicadorYaExistenteLanceUnaExcepcionIndicadorAreaException() throws NoSePuedoAgregar {
		
		TipoUbicacion ubicacion = TipoUbicacion.CENTRO;
		Complejo complejoPolideportivo = new ComplejoPolideportivo("abc",100.50,ubicacion);
		Boolean seCreoPoli = this.sedeOlimpica.validarPolideportivoSiElIdentifiacadorYaExiste(complejoPolideportivo);

		TipoUbicacion ubicacion2 = TipoUbicacion.CENTRO;
		Complejo complejoPolideportivo2 = new ComplejoPolideportivo("ab",100.50,ubicacion2);
		Boolean seCreoPoli2 = this.sedeOlimpica.validarPolideportivoSiElIdentifiacadorYaExiste(complejoPolideportivo2);

	}
	@Test
	public void queSePuedaAgregarUnComisarioJuezAUnEvento() throws NoSePuedeAgregarUnComisarioJuez{
		TipoUbicacion ubicacion = TipoUbicacion.CENTRO;
		Complejo complejoPolideportivo = new ComplejoPolideportivo("abc",100.50,ubicacion);
		this.sedeOlimpica.sePuedeCrearUnComplejoPolideportivo(complejoPolideportivo);
	
		Integer dni = 4290122;
		String nombre ="juez";
		Integer edad = 58;
		Comisario comisarioJuez = new ComisarioJuez (dni,nombre,edad);
		
		LocalDate fecha = LocalDate.of(2000,12,12);
		LocalTime duracion = LocalTime.of(1, 50);
		Integer nroParticipantes = 10;
		Evento evento1 = new Evento (fecha,duracion,nroParticipantes);
		this.sedeOlimpica.seCreaUnEventoAUnComplejoPoli(complejoPolideportivo,evento1);

		Boolean seCrea = this.sedeOlimpica.sePuedaAgregarUnComisarioJuezAUnEvento(complejoPolideportivo,evento1,comisarioJuez);
		
		assertTrue(seCrea);
	}
	@Test (expected = NoSePuedeAgregarUnComisarioJuez.class)
	public void queAlAgregarUnComisarioJuezInexistenteLanceUnaExcepcionComisarioException() throws NoSePuedeAgregarUnComisarioJuez{
		TipoUbicacion ubicacion = TipoUbicacion.CENTRO;
		Complejo complejoPolideportivo = new ComplejoPolideportivo("abc",100.50,ubicacion);
		this.sedeOlimpica.sePuedeCrearUnComplejoPolideportivo(complejoPolideportivo);
	
		LocalDate fecha = LocalDate.of(2000,12,12);
		LocalTime duracion = LocalTime.of(1, 50);
		Integer nroParticipantes = 10;
		Evento evento1 = new Evento (fecha,duracion,nroParticipantes);
		this.sedeOlimpica.seCreaUnEventoAUnComplejoPoli(complejoPolideportivo,evento1);

		Boolean seCrea = this.sedeOlimpica.sePuedaAgregarUnComisarioJuezAUnEvento(complejoPolideportivo, evento1, null);
		
	}
	@Test
	public void queSePuedaCalcularElTotalDeParticipantesDeLosEventosDeUnComplejoSimple(){
		Complejo complejoSimple = new ComplejoSimple("abc",100.50);
	    this.sedeOlimpica.sePuedeCrearUnComplejoSimple(complejoSimple);
	    
		LocalDate fecha = LocalDate.of(2000,12,12);
		LocalTime duracion = LocalTime.of(1, 50);
		Integer nroParticipantes = 10;
		Evento evento1 = new Evento (fecha,duracion,nroParticipantes);
		this.sedeOlimpica.seCreaUnEventoAUnComplejoPoli(complejoSimple, evento1);
		
		LocalDate fecha2 = LocalDate.of(2000,12,12);
		LocalTime duracion2 = LocalTime.of(2, 00);
		Integer nroParticipantes2 = 10;
		Evento evento2 = new Evento (fecha2,duracion2,nroParticipantes2);
		this.sedeOlimpica.seCreaUnEventoAUnComplejoPoli(complejoSimple, evento2);
		
		Integer total = this.sedeOlimpica.calcularElTotalDeParticipantesDeUnComplejoSimple(complejoSimple); 
		
		assertEquals(20, (int)total);
		
	}
	@Test
	public void queSePuedaCalcularElPromedioDeEdadDeLosComisariosObservadoresDeUnEventoEspecifico() throws NoSePuedeAgregarUnComisarioJuez{
		TipoUbicacion ubicacion = TipoUbicacion.CENTRO;
		Complejo complejoPolideportivo = new ComplejoPolideportivo("abc",100.50,ubicacion);
		this.sedeOlimpica.sePuedeCrearUnComplejoPolideportivo(complejoPolideportivo);
	
		LocalDate fecha = LocalDate.of(2000,12,12);
		LocalTime duracion = LocalTime.of(1, 50);
		Integer nroParticipantes = 10;
		Evento evento1 = new Evento (fecha,duracion,nroParticipantes);Integer dni = 4290122;
        this.sedeOlimpica.seCreaUnEventoAUnComplejoPoli(complejoPolideportivo,evento1);
		
        String nombre ="juez";
		Integer edad = 58;
		Comisario comisarioObservador = new ComisarioObservador (dni,nombre,edad);
		this.sedeOlimpica.sePuedaAgregarUnComisarioAUnEventoDirectamente(complejoPolideportivo,evento1,comisarioObservador);
		
		Integer dni2 = 429012;
		String nombre2 ="albert";
		Integer edad2 = 60;
		Comisario comisarioObservador2 = new ComisarioObservador (dni2,nombre2,edad2);
		this.sedeOlimpica.sePuedaAgregarUnComisarioAUnEventoDirectamente(complejoPolideportivo,evento1,comisarioObservador2);
		
		Integer dni3 = 4290122;
		String nombre3 ="juez";
		Integer edad3 = 20;
		Comisario comisarioJuez3 = new ComisarioJuez (dni,nombre,edad);
		this.sedeOlimpica.sePuedaAgregarUnComisarioAUnEventoDirectamente(complejoPolideportivo,evento1,comisarioJuez3);
		
		Double promedioEdad = this.sedeOlimpica.calcularELPromedioDeEdadDeLosObservadores(complejoPolideportivo,evento1);
		Double a = 59.0;
	    assertEquals(a,(Double) promedioEdad);
	}
	@Test
	public void queSePuedaObtenerUnaListaDeComisariosJuecesDeUnEventoEspecificoSinRepeticiones() throws NoSePuedeAgregarUnComisarioJuez{
		TipoUbicacion ubicacion = TipoUbicacion.CENTRO;
		Complejo complejoPolideportivo = new ComplejoPolideportivo("abc",100.50,ubicacion);
		this.sedeOlimpica.sePuedeCrearUnComplejoPolideportivo(complejoPolideportivo);

		LocalDate fecha = LocalDate.of(2000,12,12);
		LocalTime duracion = LocalTime.of(1, 50);
		Integer nroParticipantes = 10;
		Evento evento1 = new Evento (fecha,duracion,nroParticipantes);
		this.sedeOlimpica.seCreaUnEventoAUnComplejoPoli(complejoPolideportivo,evento1);
		
		Integer dni = 4;
		String nombre ="juez";
		Integer edad = 58;
		Comisario comisarioJuez = new ComisarioJuez (dni,nombre,edad);
		this.sedeOlimpica.sePuedaAgregarUnComisarioAUnEventoDirectamente(complejoPolideportivo,evento1,comisarioJuez);

        Comisario comisarioJuez2 = new ComisarioJuez(1,"juan",27);
        this.sedeOlimpica.sePuedaAgregarUnComisarioAUnEventoDirectamente(complejoPolideportivo, evento1, comisarioJuez2);
        
        Comisario comisarioJuez3 = new ComisarioJuez(3,"pedro",45);
        this.sedeOlimpica.sePuedaAgregarUnComisarioAUnEventoDirectamente(complejoPolideportivo, evento1, comisarioJuez3);
        
		TreeSet<Comisario> listaCOmisarioJuez = this.sedeOlimpica.obtnerListaDeJuecesDeUnEvento(complejoPolideportivo,evento1);
	     
		assertEquals(1,(int)listaCOmisarioJuez.first().getDni());
	    assertEquals(4, (int)listaCOmisarioJuez.last().getDni());
	
	}
	
	@Test
	public void queSePuedaObtenerUnMapaDeUnComplejoPolideportivoConNombreDeComplejoYUbicacion(){
		TipoUbicacion ubicacion = TipoUbicacion.CENTRO;
		Complejo complejoPolideportivo = new ComplejoPolideportivo("abc",100.50,ubicacion);
		this.sedeOlimpica.sePuedeCrearUnComplejoPolideportivo(complejoPolideportivo);

		TipoUbicacion ubicacion2 = TipoUbicacion.ESQUINA_DERECHA;
		Complejo complejoPolideportivo2 = new ComplejoPolideportivo("c",100.50,ubicacion2);
		this.sedeOlimpica.sePuedeCrearUnComplejoPolideportivo(complejoPolideportivo2);
		
		TreeMap <String,TipoUbicacion> obtenerListaDeComplejos = this.sedeOlimpica.obtenerMapaDeLosComplejoPolideportivos();
	
		assertEquals(2, obtenerListaDeComplejos.size());
        assertEquals(TipoUbicacion.CENTRO, obtenerListaDeComplejos.get("abc"));		
	}

	
}
