package py.edu.facitec.Simpres2.tablas;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import py.edu.facitec.Simpres2.entidades.Habitacion;

public class ModeloTablaHabitacion extends AbstractTableModel {

	private String columnas[] = { "Tipo", "Numero", "Precio", "Estado" };
	private List<Habitacion> lista = new ArrayList<Habitacion>();

	public void setLista(List<Habitacion> lista) {
		this.lista = lista;
		fireTableDataChanged();
	}

	@Override
	public int getColumnCount() {
		return columnas.length;
	}

	@Override
	public int getRowCount() {
		return lista.size();
	}

	public String getColumnName(int i) {
		return columnas[i];
	}

	@Override
	public Object getValueAt(int r, int c) {
		switch (c) {
		case 0:
			return lista.get(r).getTipo();
		case 1:
			return lista.get(r).getNumero();
		case 2:
			return lista.get(r).getPrecio();
		case 3:
			return lista.get(r).getEstado();
		default:
			return null;
		}
	}

}
