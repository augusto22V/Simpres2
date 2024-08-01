package py.edu.facitec.Simpres2.Controller;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.JOptionPane;

import componentes.EventosGenericos;
import py.edu.facitec.Simpres2.DAO.HabitacionDAO;
import py.edu.facitec.Simpres2.Interfaces.AccionesABM;
import py.edu.facitec.Simpres2.entidades.Habitacion;
import py.edu.facitec.Simpres2.tablas.ModeloTablaHabitacion;
import py.edu.facitec.Simpres2.vista.VentanaHabitacion;

public class HabitacionController implements AccionesABM {
    private VentanaHabitacion ventana;
    private ModeloTablaHabitacion modeloTablaHabitacion;
    private Habitacion habitacion;
    private List<Habitacion> habitaciones;
    private HabitacionDAO habitacionDAO;

    public HabitacionController(VentanaHabitacion ventanaHabitacion) {
        super();
        this.ventana = ventanaHabitacion;
        this.ventana.setAcciones(this);
        modeloTablaHabitacion = new ModeloTablaHabitacion();
        this.ventana.getTable().setModel(modeloTablaHabitacion);

        habitacionDAO = new HabitacionDAO();
        habitacion = new Habitacion();
        consultarHabitaciones();
        setUpEvents();

        estadoInicial();
    }

    private void setUpEvents() {
        ventana.getTable().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2)
                    seleccionarRegistro(ventana.getTable().getSelectedRow());
            }
        });
        ventana.getTfbuscador().addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyChar() == KeyEvent.VK_ENTER)
                    consultarHabitaciones();
            }
        });
    }

    private void estadoInicial() {
        consultarHabitaciones();
        ventana.getBtnCancelar().setEnabled(false);
        ventana.getBtnModificar().setEnabled(false);
        ventana.getBtnEliminar().setEnabled(false);
        ventana.getBtnSalir().setEnabled(true);
        ventana.getBtnGuardar().setEnabled(false);

        EventosGenericos.estadosJtexField(ventana.getPanelformulario(), false);
        EventosGenericos.limpiarJtexField(ventana.getPanelformulario());

        ventana.getBtnNuevo().setEnabled(true);
        ventana.getTable().setEnabled(true);
    }

    @Override
    public void nuevo() {
        ventana.getTable().setEnabled(false);
        ventana.getBtnNuevo().setEnabled(false);
        ventana.getBtnSalir().setEnabled(false);
        ventana.getBtnCancelar().setEnabled(true);
        ventana.getBtnGuardar().setEnabled(true);
        ventana.getBtnEliminar().setEnabled(false);
        ventana.getBtnGuardar().setText("Guardar");

        EventosGenericos.estadosJtexField(ventana.getPanelformulario(), true);
    }

    @Override
    public void modificar() {
        EventosGenericos.estadosJtexField(ventana.getPanelformulario(), true);
        ventana.getBtnCancelar().setEnabled(true);
        ventana.getBtnGuardar().setEnabled(true);

        ventana.getBtnGuardar().setText("Actualizar");
    }

    @Override
    public void eliminar() {
        if (habitacion == null) {
            JOptionPane.showMessageDialog(null, "Habitacion no encontrada");
            return;
        }
        int respuesta = JOptionPane.showConfirmDialog(null,
                "Estas seguro que deseas eliminar la habitacion " + habitacion.getNumero() + "?", "Atención",
                JOptionPane.YES_NO_OPTION);
        if (respuesta == JOptionPane.YES_OPTION) {
            try {
            	   EventosGenericos.limpiarJtexField(ventana.getPanelformulario());
       	    	JOptionPane.showMessageDialog(null, "El Funcionario ha sido eliminado correctamente");
                habitacionDAO.eliminar(habitacion);
                habitacionDAO.commit();
                estadoInicial();
                consultarHabitaciones();
            } catch (Exception e) {
                habitacionDAO.rollback();
                e.printStackTrace();
            }
        }
    }

    @Override
    public void guardar() {
        if (!validarCampos()) {
	        return;
        }
    
        habitacion = new Habitacion();
        cargarEntidad();

        try {
            habitacionDAO.guardar(habitacion);
            habitacionDAO.commit();
            EventosGenericos.limpiarJtexField(ventana.getPanelformulario());
	    	JOptionPane.showMessageDialog(null, "La habitacion ha sido registrado correctamente");            consultarHabitaciones();
            estadoInicial();
        } catch (Exception e) {
            habitacionDAO.rollback();
            e.printStackTrace();
            estadoInicial();
        }
    }

    @Override
    public void cancelar() {
        estadoInicial();
    }

    @Override
    public void salir() {
        ventana.dispose();
    }

    @Override
    public void actualizar() {
        if (!validarCampos()) {
            estadoInicial(); 
            return; 
        }
        cargarEntidad();
        try {
            habitacionDAO.guardar(habitacion);
            habitacionDAO.commit();
            consultarHabitaciones();
            estadoInicial();
        } catch (Exception e) {
            habitacionDAO.rollback();
            e.printStackTrace();
        }
    }

    private void cargarEntidad() {
        habitacion.setTipo(this.ventana.getTfTipo().getText());
        habitacion.setNumero(this.ventana.getTfNumero().getText());
        habitacion.setObservaciones(this.ventana.getTfObservaciones().getText());
        habitacion.setEstado(this.ventana.getTfestadoh().getText());

        String precioText = ventana.getTfPrecio().getText();
        if (!precioText.isEmpty()) {
            try {
                double precio = Double.parseDouble(precioText);
                habitacion.setPrecio(precio);
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }
    }

    private void seleccionarRegistro(int index) {
        if (index < 0)
            return;
        habitacion = habitaciones.get(index);

        ventana.getTfTipo().setText(habitacion.getTipo());
        ventana.getTfNumero().setText(habitacion.getNumero());
        ventana.getTfPrecio().setText(String.valueOf(habitacion.getPrecio()));
        ventana.getTfObservaciones().setText(habitacion.getObservaciones());
        ventana.getTfestadoh().setText(habitacion.getEstado());

        ventana.getBtnCancelar().setEnabled(true);
        ventana.getBtnNuevo().setEnabled(false);
        ventana.getBtnSalir().setEnabled(false);
        ventana.getBtnModificar().setEnabled(true);
        ventana.getBtnEliminar().setEnabled(true);
    }

    private void consultarHabitaciones() {
        System.out.println("Consultando habitaciones...");
        habitaciones = habitacionDAO.filtrarHabitaciones(this.ventana.getTfbuscador().getText());
        System.out.println("Habitaciones encontradas: " + habitaciones.size());
        modeloTablaHabitacion.setLista(habitaciones);
        modeloTablaHabitacion.fireTableDataChanged(); // Notify the table that the model has been updated
    }

    private boolean validarCampos() {
        if (ventana.getTfTipo().getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "El campo Tipo es obligatorio");
            return false;
        }
        if (ventana.getTfNumero().getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "El campo Numero es obligatorio");
            return false;
        }
        return true;
    }
}
