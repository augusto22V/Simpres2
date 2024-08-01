package py.edu.facitec.Simpres2.tablas;


import java.util.ArrayList; 
import java.util.List;

import javax.swing.table.AbstractTableModel;

import py.edu.facitec.Simpres2.entidades.Funcionario;

public class ModeloTablaFuncionario extends AbstractTableModel{

	private static final long serialVersionUID = 1L;
	private String[] columnas = {"Nombre", "Apellido", "Documento"};
	private List<Funcionario> lista = new ArrayList<Funcionario>();
	
	public void setLista(List<Funcionario> lista) {
		this.lista = lista;
		fireTableDataChanged();
	}

	@Override
	public int getRowCount() {
		return lista.size();
	}

	@Override
	public int getColumnCount() {
		return columnas.length;
	}
	
	@Override
	public String getColumnName(int i) {
		return columnas[i];
	}

	@Override
	public Object getValueAt(int r, int c) {
		switch (c) {
		case 0:
			return lista.get(r).getNombre();
		case 1:
			return lista.get(r).getApellido();
		case 2:
			return lista.get(r).getDocumento();
		}
		return null;
	}
}

