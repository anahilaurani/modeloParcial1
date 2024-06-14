package ar.edu.unlam;

public class ComplejoPolideportivo extends Complejo {

	private TipoUbicacion tipoUbicacion;
	
	public ComplejoPolideportivo(String nombre, Double areaTotalOcupada, TipoUbicacion tipoubicacion) {
		super(nombre, areaTotalOcupada);
		this.tipoUbicacion= tipoubicacion;
	}

	public TipoUbicacion getTipoUbicacion() {
		return tipoUbicacion;
	}

	public void setTipoUbicacion(TipoUbicacion tipoUbicacion) {
		this.tipoUbicacion = tipoUbicacion;
	}


}
