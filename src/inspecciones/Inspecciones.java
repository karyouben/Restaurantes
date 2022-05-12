package inspecciones;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;


//Criterio de igualdad:
//• Dos objetos de tipo Inspecciones son iguales si todas sus propiedades básicas son iguales.
//Otras operaciones:
//• Inspecciones::añadirInspeccion: método para añadir una Inspeccion a la lista
//inspecciones.

public class Inspecciones {
	private List<Inspeccion> inspecciones;
	
	public Inspecciones() {
		inspecciones = new ArrayList<Inspeccion>();
	}
	
	public Inspecciones(Stream<Inspeccion> inspecciones) {
		this.inspecciones = inspecciones.collect(Collectors.toList());
	}
	
	//Criterio de igualdad
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((inspecciones == null) ? 0 : inspecciones.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof Inspecciones))
			return false;
		Inspecciones other = (Inspecciones) obj;
		if (inspecciones == null) {
			if (other.inspecciones != null)
				return false;
		} else if (!inspecciones.equals(other.inspecciones))
			return false;
		return true;
	}
	
	//Representacion en cadena
	@Override
	public String toString() {
		return "Inspecciones [inspecciones=" + inspecciones + "]";
	}
	
	//Otras operaciones
	public void addInspeccion(Inspeccion p) {
		inspecciones.add(p);
	}
	
	public List<Inspeccion> obtenerInspeccionesMasRecientes(Integer n) {
		return inspecciones.stream()
				.filter(Inspeccion->Inspeccion.tiempoTranscurrido().getYears()<n)
				.collect(Collectors.toList());	
	}
	
	public SortedSet<String> obtenerNombresRestaurantesDeTipoCocina(String cocina) {
		return inspecciones.stream()
				.filter(Inspeccion->Inspeccion.tipoCocina().equals(cocina))
				.map(Inspeccion::nombre)
				.collect(Collectors.toCollection(()->new TreeSet<String>()));
		//.collect(Collectors.toCollection(TreeSet::new));
				//Comparator<String> c = Comparator.reverseOrder();	
	}
	
	public Inspeccion obtenerInspeccionMasReciente() {
		return inspecciones.stream()
				.max(Comparator.comparing(Inspeccion::fecha))
				.orElse(null);
		
	}
	

	
	
	
																			
}
