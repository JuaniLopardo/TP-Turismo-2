package turismo;

import java.util.Comparator;

public class ComparadorDeOfertablesConsigna implements Comparator<Ofertable> {

	@Override
	public int compare(Ofertable o1, Ofertable o2) {
		if (o1 instanceof Atraccion && o2 instanceof Promocion) {
			return 1;
		}
		if (o1 instanceof Promocion && o2 instanceof Atraccion) {
			return -1;
		}
		if (o1.getCosto() < o2.getCosto()) {
			return 1;
		} else if (o1.getCosto() == o2.getCosto()) {
			if (o1.getDuracion() < o2.getDuracion()) {
				return 1;
			} else if (o1.getDuracion() > o2.getDuracion()) {
				return -1;
			} else {
				return 0;
			}
		} else {
			return -1;
		}
	}

}
