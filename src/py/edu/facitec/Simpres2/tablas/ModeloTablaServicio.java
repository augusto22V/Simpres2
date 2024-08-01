package py.edu.facitec.Simpres2.tablas;

import java.util.ArrayList; 
import java.util.List;

import javax.swing.table.AbstractTableModel;

import py.edu.facitec.Simpres2.entidades.Servicios;

public class ModeloTablaServicio extends AbstractTableModel {

    private static final long serialVersionUID = 1L;
    private List<Servicios> lista = new ArrayList<>();
    private final String[] columnas = { "Descripción", "Precio" };

    @Override
    public int getColumnCount() {
        return columnas.length;
    }

    @Override
    public int getRowCount() {
        return lista.size();
    }

    @Override
    public String getColumnName(int column) {
        return columnas[column];
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Servicios servicio = lista.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return servicio.getDescripcion();
            case 1:
                return servicio.getPrecio();
            default:
                return null;
        }
    }

    public void setLista(List<Servicios> lista) {
        this.lista = lista;
        fireTableDataChanged();
    }

    public List<Servicios> getLista() {
        return lista;
    }

    public void agregarServicio(Servicios servicio) {
        lista.add(servicio);
        fireTableRowsInserted(lista.size() - 1, lista.size() - 1);
    }

    public void limpiar() {
        lista.clear();
        fireTableDataChanged();
    }
}
