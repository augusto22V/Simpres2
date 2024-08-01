package py.edu.facitec.Simpres2.tablas;

import java.util.ArrayList;
import java.util.List; 
import javax.swing.table.AbstractTableModel;

import py.edu.facitec.Simpres2.entidades.Cliente;
import py.edu.facitec.Simpres2.entidades.Funcionario;
import py.edu.facitec.Simpres2.entidades.Reserva_detalle;
import py.edu.facitec.Simpres2.entidades.Reservas;
  
public class ModeloTablaReservas extends AbstractTableModel {
	private String[] columnNames = { "ID", " Cliente", "Funcionario", "Habitacion" };
	private List<Reservas> reservasList = new ArrayList<Reservas>();


	public void setLista(List<Reservas> lista) {
	    this.reservasList = lista;
	    fireTableDataChanged();
	}


	public void setReservasList(List<Reservas> reservasList) {
		this.reservasList = reservasList;
		fireTableDataChanged(); 
	}

	@Override
	public int getRowCount() {
		return reservasList.size();
	}

	@Override
	public int getColumnCount() {
		return columnNames.length;
	}

	

	public String[] getColumnNames() {
		return columnNames;
	}

	public void setColumnNames(String[] columnNames) {
		this.columnNames = columnNames;
	}

	public List<Reservas> getReservasList() {
		return reservasList;
	}

	@Override
	public String getColumnName(int column) {
		return columnNames[column];
	}
	@Override
	public Object getValueAt(int r, int c) {
	    switch (c) {
	        case 0:
	            return reservasList.get(r).getId();
	        case 1:
	        	Cliente cliente = reservasList.get(r).getCliente();
	            return cliente.getNombre() + " " + cliente.getApellido();
	        case 2:
	        	Funcionario funcionario = reservasList.get(r).getFuncionario();
	        	return funcionario.getNombre() + " " + funcionario.getApellido();
	        case 3:
	            List<Reserva_detalle> reservaDetalles = reservasList.get(r).getDetalles();
	            if (!reservaDetalles.isEmpty()) {
	                return reservaDetalles.get(0).getHabitacion().getNumero();
	            } else {
	                return "Sin habitacion";
	            }
	        default:
	            return null;
	    }
	}

}
