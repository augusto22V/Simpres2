package py.edu.facitec.Simpres2.tablas;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;
import py.edu.facitec.Simpres2.entidades.Funcionario;

public class ModeloTablaListadoFuncionarios extends AbstractTableModel {

    private String[] columnas = { "Id", "Nombre", "Apellido", "CI", "Telefono", "Direccion" };
    private List<Funcionario> lista = new ArrayList<Funcionario>();

    public void setLista(List<Funcionario> lista) {
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

    @Override
    public String getColumnName(int columnIndex) {
        return columnas[columnIndex];
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Funcionario funcionario = lista.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return funcionario.getId();
            case 1:
                return funcionario.getNombre();
            case 2:
                return funcionario.getApellido();
            case 3:
                return funcionario.getDocumento();
            case 4:
                return funcionario.getTelefono();
            case 5:
                return funcionario.getDireccion();
            default:
                return null;
        }
    }
}
