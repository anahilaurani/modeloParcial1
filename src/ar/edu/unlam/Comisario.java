package ar.edu.unlam;

public abstract class Comisario implements Comparable<Comisario> {
	
	private Integer dni;
	private String nombre;
	private Integer edad;

	public Comisario(Integer dni, String nombre, Integer edad) {
		this.dni= dni;
		this.nombre= nombre;
	    this.edad =edad;
	}
	
	@Override
	public int compareTo(Comisario o) {
		return this.dni.compareTo(o.getDni());
		
	}

	public Integer getDni() {
		return dni;
	}

	public void setDni(Integer dni) {
		this.dni = dni;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Integer getEdad() {
		return edad;
	}

	public void setEdad(Integer edad) {
		this.edad = edad;
	}

	
}
