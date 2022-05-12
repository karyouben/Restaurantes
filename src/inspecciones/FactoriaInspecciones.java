package inspecciones;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Stream;

import util.Checkers;





public class FactoriaInspecciones {
	
	
	private static Inspeccion parsearInspeccion(String lineasCSV) {
		String[]trozos = lineasCSV.split(",");
		Checkers.check("Formato no válido" + lineasCSV, trozos.length == 9);
		String id = trozos[0].trim();
		String nombre = trozos[1].trim();
		Distrito distrito = Distrito.valueOf(trozos[2].trim());
		String tipoCocina = trozos[3].trim();
		LocalDate fecha = LocalDate.parse(trozos[4].trim(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
		String descripcion = trozos[5].trim();
		Boolean esCritica = Boolean.parseBoolean(trozos[6].trim());
		Double score = Double.parseDouble(trozos[7].trim());
		Period tiempoTranscurrido = Period.parse(trozos[8].trim());
		return new Inspeccion(id, nombre, distrito, tipoCocina, fecha, descripcion,
				esCritica, score, tiempoTranscurrido);
		
	}
	
	public static Inspecciones leerInspecciones(String rutaFichero) {
		Inspecciones res = null;
		try {
			Stream<Inspeccion> si =
					Files.lines(Paths.get(rutaFichero))
						.skip(1)
						.map(linea -> parsearInspeccion(linea));
			res = new Inspecciones(si);
		}catch(IOException e) {
			System.out.println("No se ha encontrado el fichero" + rutaFichero);
			e.printStackTrace();
		}
		return res;
	}
	
	public static void main(String[] args) {
		Inspecciones lst = leerInspecciones("./data/inspecciones_restaurantes.csv");
		System.out.println(lst);
	}
	 	
}
