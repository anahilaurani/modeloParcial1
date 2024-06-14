package ar.edu.unlam;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;

public class Evento {

	private LocalDate fecha;
	private LocalTime duracion;
	private Integer nroParticipantes;

	public Evento(LocalDate fecha, LocalTime duracion, Integer nroParticipantes) {
	this.fecha = fecha;
	this.duracion = duracion;
	this.nroParticipantes = nroParticipantes;
	}

	public LocalDate getFecha() {
		return fecha;
	}

	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}

	public LocalTime getDuracion() {
		return duracion;
	}

	public void setDuracion(LocalTime duracion) {
		this.duracion = duracion;
	}

	public Integer getNroParticipantes() {
		return nroParticipantes;
	}

	public void setNroParticipantes(Integer nroParticipantes) {
		this.nroParticipantes = nroParticipantes;
	}

	@Override
	public int hashCode() {
		return Objects.hash(duracion, fecha, nroParticipantes);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Evento other = (Evento) obj;
		return Objects.equals(duracion, other.duracion) && Objects.equals(fecha, other.fecha)
				&& Objects.equals(nroParticipantes, other.nroParticipantes);
	}

	
}
