package py.edu.facitec.Simpres2.Controller;

import java.awt.event.ActionEvent;  
import java.awt.event.ActionListener;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import net.sf.jasperreports.engine.JRException;
import py.edu.facitec.Simpres2.DAO.FuncionarioDAO;
import py.edu.facitec.Simpres2.entidades.Funcionario;
import py.edu.facitec.Simpres2.tablas.ModeloTablaListadoFuncionarios;
import componentes.ConexionReportes;
import componentes.UtilidadesFecha;
import py.edu.facitec.Simpres2.vista.ListadoFuncionariosVentana;

public class ListadoFuncionarioController {
	
	private List<Funcionario> funcionarios;
	private ModeloTablaListadoFuncionarios modeloTablaListadoFuncionario;
	private FuncionarioDAO dao;
	private ListadoFuncionariosVentana ventana;
	private String dNombre, hNombre, dApellido, hApellido;

	public ListadoFuncionarioController(ListadoFuncionariosVentana listadoFuncionariosVentana) {
		super();
		this.ventana = listadoFuncionariosVentana;
		dao = new FuncionarioDAO();
		modeloTablaListadoFuncionario = new ModeloTablaListadoFuncionarios();
		this.ventana.getTable().setModel(modeloTablaListadoFuncionario);
		
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
		funcionarios = dao.filtroListadoFuncionarios(dNombre, hNombre, dApellido, hApellido, this.ventana.getCbOrder().getSelectedItem().toString());
		modeloTablaListadoFuncionario.setLista(funcionarios);
		if (funcionarios.size()<=0) {
			this.ventana.getBtnImprimir().setEnabled(false);
		} else {
			this.ventana.getBtnImprimir().setEnabled(true);
		}
	}
	
	private void cargarFiltros() {
		dNombre = "A";
		hNombre = "Z";
		dApellido = "A";
		hApellido = "Z";
		if(!this.ventana.getTfDesdeNombre().getText().isEmpty()) dNombre = this.ventana.getTfDesdeNombre().getText();
		if(!this.ventana.getTfHastaNombre().getText().isEmpty()) hNombre = this.ventana.getTfHastaNombre().getText();
		if(!this.ventana.getTfDesdeApellido().getText().isEmpty()) dApellido = this.ventana.getTfDesdeApellido().getText();
		if(!this.ventana.getTfHastaApellido().getText().isEmpty()) hApellido = this.ventana.getTfHastaApellido().getText();
	}

	private void imprimir() {
		
		HashMap<String, Object> parametros = new HashMap<>();
		parametros.put("desdeNombre", dNombre);
		parametros.put("hastaNombre", hNombre);
		parametros.put("desdeApellido", dApellido);
		parametros.put("hastaApellido", hApellido);
		parametros.put("fecha", UtilidadesFecha.fechaAString(new Date()));
		parametros.put("order", this.ventana.getCbOrder().getSelectedItem().toString());
		
		ConexionReportes<Funcionario> conexionReportes = new ConexionReportes<Funcionario>();
		try {
			conexionReportes.generarReporte(funcionarios, parametros, "ListadoFuncionarios");
			conexionReportes.ventanaReporte.setLocationRelativeTo(ventana);
			conexionReportes.ventanaReporte.setVisible(true);
		} catch (JRException e) {
			e.printStackTrace();
		}
	}
}
