package py.edu.facitec.Simpres2.Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import componentes.ConexionReportes;
import componentes.UtilidadesFecha;
import net.sf.jasperreports.engine.JRException;
import py.edu.facitec.Simpres2.vista.InformeReservaVentana;
import py.edu.facitec.Simpres2.DAO.ReservasDAO;
import py.edu.facitec.Simpres2.entidades.Reservas;
import py.edu.facitec.Simpres2.tablas.ModeloTablaReservas;

public class InformeReservasController {
	private List<Reservas> reservas;
	private ReservasDAO dao;
	private InformeReservaVentana ventana;
	private ModeloTablaReservas modeloTablaReserva;
	private String dNombre, hNombre;

	public InformeReservasController(InformeReservaVentana informeReservaVentana) {
		super();
		ventana = informeReservaVentana;
		dao = new ReservasDAO();
		modeloTablaReserva = new ModeloTablaReservas();
		ventana.getTable().setModel(modeloTablaReserva);
		filtrar();
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

	private void cargarFiltros() {
		dNombre = "A";
		hNombre = "Z";

		if (!this.ventana.getTfDesdeCliente().getText().isEmpty())
			dNombre = this.ventana.getTfDesdeCliente().getText();
		if (!this.ventana.getTfHastaCliente().getText().isEmpty())
			hNombre = this.ventana.getTfHastaCliente().getText();
	}

	private void filtrar() {
		cargarFiltros();
		reservas = dao.filtroInformeReserva(dNombre, hNombre);
		dNombre = ventana.getTfDesdeCliente().getText();
		hNombre = ventana.getTfHastaCliente().getText();
		reservas = dao.filtroInformeReserva(dNombre, hNombre);
		modeloTablaReserva.setLista(reservas);
		if (reservas.size() > 0) {
			ventana.getBtnImprimir().setEnabled(true);

		} else {
			ventana.getBtnImprimir().setEnabled(false);
		}
	}

	private void imprimir() {
		HashMap<String, Object> parametros = new HashMap<>();
		parametros.put("desde", dNombre);
		parametros.put("hasta", hNombre);
		parametros.put("clienteDesde", ventana.getTfDesdeCliente().getText());
		parametros.put("clienteHasta", ventana.getTfHastaCliente().getText());
		parametros.put("fecha", UtilidadesFecha.fechaAString(new Date()));

		ConexionReportes<Reservas> conexionReportes = new ConexionReportes<Reservas>();
		try {
			if (ventana.getCbInforme().getSelectedIndex() == 0) {
				conexionReportes.generarReporte(reservas, parametros, "InformeReservasSimples");
			} else {
				conexionReportes.generarReporte(reservas, parametros, "InformeReservasDetallado");
			}
			conexionReportes.ventanaReporte.setLocationRelativeTo(ventana);
			conexionReportes.ventanaReporte.setVisible(true);
		} catch (JRException e) {
			e.printStackTrace();
		}
	}

}
