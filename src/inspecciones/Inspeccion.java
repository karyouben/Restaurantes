package inspecciones;

import java.time.*;

import util.Checkers;


public record Inspeccion(String id, String nombre, Distrito distrito, String tipoCocina,
		LocalDate fecha, String descripcion, Boolean esCritica, Double score) {
	
	public Inspeccion{
		Checkers.check("Fecha inadecuada ", fecha.isAfter(LocalDate.of(1900, 1, 1)));
	}
	
	public Integer tiempoTranscurrido() {
		return fecha.
	}

}
