package py.edu.facitec.Simpres2.tablas;

import java.util.ArrayList; 
import java.util.List;

import javax.swing.table.AbstractTableModel;
import py.edu.facitec.Simpres2.entidades.Cliente;

public class ModeloTablaListadoClientes extends AbstractTableModel {
	
	private String clumnas[] = {"Id", "Nombre", "Apellido", "CI", "Telefono"};
	private List<Cliente> lista = new ArrayList<Cliente>();
	
	public void setLista(List<Cliente> lista) {
		this.lista=lista;
		fireTableDataChanged();
	}

	@Override
	public int getColumnCount() {
		return clumnas.length;
	}

	@Override
	public int getRowCount() {
		return lista.size();
	}		
	
	public String getColumnName(int i) {
		return clumnas[i];
	}

	@Override
	public Object getValueAt(int r, int c) {
		switch (c) {
		case 0:
			return lista.get(r).getId();
		case 1:
			return lista.get(r).getNombre();
		case 2:
			return lista.get(r).getApellido();
		case 3:
			return lista.get(r).getDocumento();
		case 4:
			return lista.get(r).getTelefono();
		}
		return null;
	
		
		}
}
