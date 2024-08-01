package py.edu.facitec.Simpres2.Controller;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.JOptionPane;

import componentes.EventosGenericos;
import py.edu.facitec.Simpres2.DAO.ServicioDao;
import py.edu.facitec.Simpres2.Interfaces.AccionesABM;
import py.edu.facitec.Simpres2.entidades.Servicios;
import py.edu.facitec.Simpres2.tablas.ModeloTablaServicio;
import py.edu.facitec.Simpres2.vista.VentanaServicio;

public class ServicioController implements AccionesABM {
	private VentanaServicio ventana;
	private ModeloTablaServicio modeloTablaServicio;
	private Servicios servicio;
	private List<Servicios> servicios;
	private ServicioDao servicioDao;

	public ServicioController(VentanaServicio ventanaServicio) {
		super();
		this.ventana = ventanaServicio;
		this.ventana.setAcciones(this);
		modeloTablaServicio = new ModeloTablaServicio();
		this.ventana.getTable().setModel(modeloTablaServicio);

		setUpEvents();
		servicioDao = new ServicioDao();
		servicio = new Servicios();
		Recuperarporfiltro();
		estadoinicial();
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
					consultarServicios();
			}
		});
	}

	private void estadoinicial() {
		consultarServicios();
		ventana.getBtnCancelar().setEnabled(false);
		ventana.getBtnModificar().setEnabled(false);
		ventana.getBtnEliminar().setEnabled(false);
		ventana.getBtnSalir().setEnabled(true);
		ventana.getBtnGuardar().setEnabled(false);
		ventana.getTfPrecio().setEnabled(false);
		EventosGenericos.estadosJtexField(ventana.getPanelformulario(), false);
		EventosGenericos.limpiarJtexField(ventana.getPanelformulario());
		ventana.getBtnNuevo().setEnabled(true);
		ventana.getBtnNuevo().setText("Nuevo");
		ventana.getTable().setEnabled(true);

	}

	@Override
	public void nuevo() {
		limpiarCampos();
		ventana.getBtnGuardar().setText("Guardar");
		ventana.getBtnNuevo().setEnabled(false);
		ventana.getBtnSalir().setEnabled(false);
		ventana.getBtnCancelar().setEnabled(true);
		ventana.getBtnGuardar().setEnabled(true);
		ventana.getBtnEliminar().setEnabled(false);
		ventana.getTfPrecio().setEnabled(true);
		EventosGenericos.estadosJtexField(ventana.getPanelformulario(), true);
		EventosGenericos.limpiarJtexField(ventana.getPanelformulario());

	}

	@Override
	public void modificar() {
		EventosGenericos.estadosJtexField(ventana.getPanelformulario(), true);
		ventana.getTfPrecio().setEnabled(true);
		ventana.getBtnCancelar().setEnabled(true);
		ventana.getBtnGuardar().setEnabled(true);
		ventana.getBtnGuardar().setText("Actualizar");
	}

	@Override
	public void eliminar() {
		if (servicio == null) {
			JOptionPane.showMessageDialog(null, "Servicio no encontrado");
			return;
		}
		int respuesta = JOptionPane.showConfirmDialog(null,
				"Est�s seguro que deseas eliminar el servicio " + servicio.getDescripcion(), "Atenci�n",
				JOptionPane.YES_NO_OPTION);
		if (respuesta == JOptionPane.YES_OPTION) {
			try {
				servicioDao.eliminar(servicio);
				servicioDao.commit();
				estadoinicial();
				consultarServicios();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

	@Override
	public void guardar() {
		 if (!validarCampos()) {
		        estadoinicial(); 
		        return; 
		    }
		servicio = new Servicios();

		cargarEntidad();

		try {
			servicioDao.guardar(servicio);
			servicioDao.commit();
			consultarServicios();
			estadoinicial();
			EventosGenericos.limpiarJtexField(ventana.getPanelformulario());
			ventana.getTfPrecio().setText("");
		} catch (Exception e) {
			servicioDao.rollback();
			e.printStackTrace();

		}

	}

	@Override
	public void cancelar() {
		estadoinicial();
		ventana.getTfPrecio().setText("");
		ventana.getBtnGuardar().setText("Guardar");

	}

	@Override
	public void salir() {
		ventana.dispose();
	}

	@Override
	public void actualizar() {
		if (!validarCampos())
			return;
		cargarEntidad();
		try {
			servicioDao.guardar(servicio);
			servicioDao.commit();
			consultarServicios();
			ventana.getTfPrecio().setText("");
			estadoinicial();
		} catch (Exception e) {
			servicioDao.rollback();
			e.printStackTrace();
		}
		limpiarCampos();
	}

	private void cargarEntidad() {
		servicio.setDescripcion(this.ventana.getTfDescripcion().getText());
		servicio.setPrecio(Double.parseDouble(this.ventana.getTfPrecio().getText()));
		ventana.mostrarPrecio(servicio.getPrecio());
	}

	private void Recuperarporfiltro() {
		servicios = servicioDao.filtrarServicios(this.ventana.getTfbuscador().getText());
		modeloTablaServicio.setLista(servicios);
	}

	private void seleccionarRegistro(int index) {
		if (index < 0 || index >= servicios.size())
			return;

		servicio = servicios.get(index);
		ventana.getTfDescripcion().setText(servicio.getDescripcion());
		ventana.mostrarPrecio(servicio.getPrecio());
		ventana.getBtnCancelar().setEnabled(true);
		ventana.getBtnNuevo().setEnabled(false);
		ventana.getBtnSalir().setEnabled(false);
		ventana.getBtnModificar().setEnabled(true);
		ventana.getBtnEliminar().setEnabled(true);
		ventana.getTfPrecio().setEnabled(false);
		ventana.getTfDescripcion().setEnabled(false);

	}

	private void limpiarCampos() {
		EventosGenericos.limpiarJtexField(ventana.getPanelformulario());
		estadoinicial();
	}

	private void consultarServicios() {
		servicios = servicioDao.filtrarServicios(this.ventana.getTfbuscador().getText());
		modeloTablaServicio.setLista(servicios);
	}

	private boolean validarCampos() {
		if (ventana.getTfDescripcion().getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "El campo Descripcion es obligatorio");
			return false;
		}
		if (ventana.getTfPrecio().getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "El campo Precio es obligatorio");
			return false;
		}
		return true;
	}

}
