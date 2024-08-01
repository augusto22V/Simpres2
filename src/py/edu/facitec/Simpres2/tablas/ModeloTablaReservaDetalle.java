package py.edu.facitec.Simpres2.tablas;

import javax.swing.table.AbstractTableModel;

import componentes.UtilidadesFecha;
import componentes.UtilidadesNumeros;
import py.edu.facitec.Simpres2.entidades.Reserva_detalle;

import java.util.ArrayList;
import java.util.List;

public class ModeloTablaReservaDetalle extends AbstractTableModel {
	private List<Reserva_detalle> listaReservasDetalle;
	private String[] columnas = { "Nro Habitacion", "Precio", "Cant. Dias" };

	public ModeloTablaReservaDetalle() {
		listaReservasDetalle = new ArrayList<>();
	}

	public void setListaReservasDetalle(List<Reserva_detalle> listaReservasDetalle) {
		this.listaReservasDetalle = listaReservasDetalle;
		fireTableDataChanged();
	}

	@Override
	public int getRowCount() {
		return listaReservasDetalle.size();
	}

	@Override
	public int getColumnCount() {
		return columnas.length;
	}

	@Override
	public String getColumnName(int column) {
		return columnas[column];
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Reserva_detalle reservaDetalle = listaReservasDetalle.get(rowIndex);
		switch (columnIndex) {
		case 0:
			return reservaDetalle.getHabitacion().getNumero();
		case 1:
			return UtilidadesNumeros.doubleString(reservaDetalle.getPrecio());
		case 2:
			return UtilidadesNumeros.intergerAString(reservaDetalle.getCantidad_dias());

		default:
			return null;
		}
	}
}
