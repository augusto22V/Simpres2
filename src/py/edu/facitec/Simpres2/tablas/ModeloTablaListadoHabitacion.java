package py.edu.facitec.Simpres2.tablas;

import javax.swing.table.AbstractTableModel;
import java.util.List;
import py.edu.facitec.Simpres2.entidades.Habitacion;

public class ModeloTablaListadoHabitacion extends AbstractTableModel {
	private List<Habitacion> habitaciones;
	private String[] columnas = { "Tipo", "Número", "Precio", "Observaciones", "Estado"};

	public ModeloTablaListadoHabitacion(List<Habitacion> habitaciones) {
		this.habitaciones = habitaciones;
	}

	@Override
	public int getRowCount() {
		return habitaciones.size();
	}

	@Override
	public int getColumnCount() {
		return columnas.length;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Habitacion habitacion = habitaciones.get(rowIndex);
		switch (columnIndex) {
			case 0: return habitacion.getTipo();
			case 1: return habitacion.getNumero();
			case 2: return habitacion.getPrecio();
			case 3: return habitacion.getObservaciones();
			case 4: return habitacion.getEstado();
			default: return null;
		}
	}

	@Override
	public String getColumnName(int column) {
		return columnas[column];
	}
}
