package py.edu.facitec.Simpres2.Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import net.sf.jasperreports.engine.JRException;
import py.edu.facitec.Simpres2.DAO.HabitacionDAO;
import py.edu.facitec.Simpres2.entidades.Habitacion;
import py.edu.facitec.Simpres2.tablas.ModeloTablaHabitacion;
import componentes.ConexionReportes;
import componentes.UtilidadesFecha;
import py.edu.facitec.Simpres2.vista.ListadoHabitacionVentana;

public class ListadoHabitacionController {

    private List<Habitacion> habitaciones;
    private ModeloTablaHabitacion modeloTablaHabitacion;
    private HabitacionDAO dao;
    private ListadoHabitacionVentana ventana;
    private String dTipo, hTipo;

    public ListadoHabitacionController(ListadoHabitacionVentana listadoHabitacionVentana) {
        super();
        this.ventana = listadoHabitacionVentana;
        dao = new HabitacionDAO();
        modeloTablaHabitacion = new ModeloTablaHabitacion();
        this.ventana.getTable().setModel(modeloTablaHabitacion);

        setUpEvents();
    }

    private void setUpEvents() {
        ventana.getBtnFiltrar().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                filtrar();
            }
        });
        ventana.getBtnImprimir().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                imprimir();
            }
        });
    }

    private void filtrar() {
        cargarFiltros();
        habitaciones = dao.filtrarPorTipo(dTipo, hTipo, this.ventana.getCbOrder().getSelectedItem().toString());
        ((ModeloTablaHabitacion) ventana.getTable().getModel()).setLista(habitaciones);
        if (habitaciones.size() <= 0) {
            this.ventana.getBtnImprimir().setEnabled(false);
        } else {
            this.ventana.getBtnImprimir().setEnabled(true);
        }
    }


    private void cargarFiltros() {
        dTipo = "A";
        hTipo = "Z";
        if (!this.ventana.getTfDesdeTipo().getText().isEmpty()) dTipo = this.ventana.getTfDesdeTipo().getText();
        if (!this.ventana.getTfHastaTipo().getText().isEmpty()) hTipo = this.ventana.getTfHastaTipo().getText();
    }

    private void imprimir() {
        HashMap<String, Object> parametros = new HashMap<>();
        parametros.put("desdeTipo", dTipo);
        parametros.put("hastaTipo", hTipo);
        parametros.put("fecha", UtilidadesFecha.fechaAString(new Date()));
        parametros.put("order", this.ventana.getCbOrder().getSelectedItem().toString());

        ConexionReportes<Habitacion> conexionReportes = new ConexionReportes<Habitacion>();
        try {
            conexionReportes.generarReporte(habitaciones, parametros, "ListadoHabitaciones");
            conexionReportes.ventanaReporte.setLocationRelativeTo(ventana);
            conexionReportes.ventanaReporte.setVisible(true);
        } catch (JRException e) {
            e.printStackTrace();
        }
    }
}
