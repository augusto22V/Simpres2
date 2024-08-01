package py.edu.facitec.Simpres2.Controller;

import java.awt.Color;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.JOptionPane;

import componentes.EventosGenericos;
import componentes.UtilidadesFecha;
import py.edu.facitec.Simpres2.DAO.FuncionarioDAO;
import py.edu.facitec.Simpres2.Interfaces.AccionesABM;
import py.edu.facitec.Simpres2.entidades.Funcionario;
import py.edu.facitec.Simpres2.tablas.ModeloTablaFuncionario;
import py.edu.facitec.Simpres2.vista.VentanaFuncionario;

public class FuncionarioController implements AccionesABM {

	private VentanaFuncionario ventana;
	private ModeloTablaFuncionario modeloTablaFuncionario;
	private Funcionario funcionario;
	private List<Funcionario> funcionarios;
	private FuncionarioDAO funcionarioDAO;
	private ButtonGroup grupoSexo;

	public FuncionarioController(VentanaFuncionario ventanaFuncionario) {
		super();
		this.ventana = ventanaFuncionario;
		this.ventana.setAcciones(this);
		modeloTablaFuncionario = new ModeloTablaFuncionario();
		this.ventana.getTable().setModel(modeloTablaFuncionario);
		funcionarioDAO = new FuncionarioDAO();
		consultarFuncionarios();
		estadoinicial();
		setUpEvents();

		grupoSexo = new ButtonGroup();
		grupoSexo.add(ventana.getRdbtnHombre());
		grupoSexo.add(ventana.getRdbtnMujer());
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
					consultarFuncionarios();
			}
		});

	}

	private void estadoinicial() {
		consultarFuncionarios();
		ventana.getBtnCancelar().setEnabled(false);
		ventana.getBtnModificar().setEnabled(false);
		ventana.getBtnEliminar().setEnabled(false);
		ventana.getBtnSalir().setEnabled(true);
		ventana.getBtnGuardar().setEnabled(false);
		ventana.getTfFecha().setValue(null);
		ventana.getTfFecha().setEnabled(false);
		ventana.getRdbtnHombre().setEnabled(false);
		ventana.getRdbtnMujer().setEnabled(false);

		EventosGenericos.estadosJtexField(ventana.getPanelformulario(), false);
		EventosGenericos.limpiarJtexField(ventana.getPanelformulario());

		ventana.getBtnNuevo().setEnabled(true);
		ventana.getBtnNuevo().setText("Nuevo");
		ventana.getTable().setEnabled(true);
	}

	@Override
	public void nuevo() {
		ventana.getBtnNuevo().setEnabled(false);
		ventana.getBtnSalir().setEnabled(false);
		ventana.getBtnCancelar().setEnabled(true);
		ventana.getBtnGuardar().setEnabled(true);
		ventana.getBtnEliminar().setEnabled(false);
		ventana.getRdbtnHombre().setEnabled(true);
		ventana.getRdbtnMujer().setEnabled(true);
		EventosGenericos.estadosJtexField(ventana.getPanelformulario(), true);

		ventana.getRdbtnHombre().setSelected(false);
		ventana.getRdbtnMujer().setSelected(false);
		ventana.getTfFecha().setEnabled(true);
		ventana.getTable().setEnabled(false);

	}

	@Override
	public void modificar() {
		EventosGenericos.estadosJtexField(ventana.getPanelformulario(), true);
		ventana.getTfFecha().setEnabled(true);
		ventana.getBtnCancelar().setEnabled(true);
		ventana.getBtnGuardar().setEnabled(true);

		ventana.getRdbtnHombre().setEnabled(true);
		ventana.getRdbtnMujer().setEnabled(true);

		ventana.getBtnGuardar().setText("Actualizar");
	}

	@Override
	public void eliminar() {
		if (funcionario == null) {
			JOptionPane.showMessageDialog(null, "Funcionario no encontrado");
			return;
		}
		int respuesta = JOptionPane.showConfirmDialog(null,
				"¿Estás seguro que desea eliminar el funcionario " + funcionario.getNombre() + "?", "Atención",
				JOptionPane.YES_NO_OPTION);
		if (respuesta == JOptionPane.YES_OPTION) {
			try {
				funcionarioDAO.eliminar(funcionario);
				funcionarioDAO.commit();
				estadoinicial();
				consultarFuncionarios();
			} catch (Exception e) {
				funcionarioDAO.rollback();
				e.printStackTrace();
			}
		}
	}

	@Override
	public void guardar() {
	    if (!validarCampos()) {
	        return;
	    }

	    cargarEntidad();

	    try {
	        funcionarioDAO.guardar(funcionario);
	        funcionarioDAO.commit();
	        
	        consultarFuncionarios();
	        
	        EventosGenericos.limpiarJtexField(ventana.getPanelformulario());
	    	JOptionPane.showMessageDialog(null, "El Funcionario ha sido registrado correctamente");
	        estadoinicial();
	    } catch (Exception e) {
	        funcionarioDAO.rollback();
	        e.printStackTrace();
	    }
	}


	@Override
	public void cancelar() {
		estadoinicial();
		grupoSexo.clearSelection();

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
			funcionarioDAO.guardar(funcionario);
			funcionarioDAO.commit();
			consultarFuncionarios();
			estadoinicial();

			EventosGenericos.limpiarJtexField(ventana.getPanelformulario());
			estadoinicial();
		} catch (Exception e) {
			funcionarioDAO.rollback();
			e.printStackTrace();
		}
		grupoSexo.clearSelection();

	}

	private void cargarEntidad() {
		funcionario.setNombre(this.ventana.getTfNombre().getText());
		funcionario.setApellido(this.ventana.getTfApellido().getText());
		funcionario.setDocumento(this.ventana.getTfDocumento().getText());
		funcionario.setDireccion(this.ventana.getTfDireccion().getText());
		funcionario.setTelefono(this.ventana.getTfTelefono().getText());
		funcionario.setCorreo(this.ventana.getTfEmail().getText());
		funcionario.setEstado(this.ventana.getTfEstado().getText());

		funcionario.setFecha_nacimiento(UtilidadesFecha.stringAFecha(this.ventana.getTfFecha().getText()));

		if (ventana.getRdbtnHombre().isSelected()) {
			funcionario.setSexo("Hombre");
		} else if (ventana.getRdbtnMujer().isSelected()) {
			funcionario.setSexo("Mujer");
		}
	}

	private void seleccionarRegistro(int index) {
		if (index < 0)
			return;
		funcionario = funcionarios.get(index);
		ventana.getTfNombre().setText(funcionario.getNombre());
		ventana.getTfApellido().setText(funcionario.getApellido());
		ventana.getTfDocumento().setText(funcionario.getDocumento());
		ventana.getTfTelefono().setText(funcionario.getTelefono());
		ventana.getTfDireccion().setText(funcionario.getDireccion());
		ventana.getTfEmail().setText(funcionario.getCorreo());
		ventana.getTfEstado().setText(funcionario.getEstado());
		ventana.getTfFecha().setText(UtilidadesFecha.fechaAString(funcionario.getFecha_nacimiento()));

		ventana.getBtnNuevo().setEnabled(false);
		ventana.getBtnSalir().setEnabled(false);
		ventana.getBtnModificar().setEnabled(true);
		ventana.getBtnEliminar().setEnabled(true);
		ventana.getBtnCancelar().setEnabled(true);

		grupoSexo.add(ventana.getRdbtnHombre());
		grupoSexo.add(ventana.getRdbtnMujer());
		grupoSexo.clearSelection();

		if (funcionario.getSexo().equals("Hombre")) {
			ventana.getRdbtnHombre().setSelected(true);
			ventana.getRdbtnMujer().setSelected(false);
		} else if (funcionario.getSexo().equals("Mujer")) {
			ventana.getRdbtnMujer().setSelected(true);
			ventana.getRdbtnHombre().setSelected(false);
		}
	}

	private void consultarFuncionarios() {
		funcionarios = funcionarioDAO.filtrarFuncionarios(this.ventana.getTfbuscador().getText());
		modeloTablaFuncionario.setLista(funcionarios);
	}

	private boolean validarCampos() {
	    boolean isValid = true;

	    if (ventana.getTfNombre().getText().isEmpty()) {
	        ventana.getTfNombre().setBackground(Color.RED);
	        JOptionPane.showMessageDialog(null, "El campo Nombre es obligatorio");
	        isValid = false;
	    } else {
	        ventana.getTfNombre().setBackground(Color.WHITE);
	    }

	    if (ventana.getTfApellido().getText().isEmpty()) {
	        ventana.getTfApellido().setBackground(Color.RED);
	        JOptionPane.showMessageDialog(null, "El campo Apellido es obligatorio");
	        isValid = false;
	    } else {
	        ventana.getTfApellido().setBackground(Color.WHITE);
	    }

	    if (ventana.getTfDocumento().getText().isEmpty()) {
	        ventana.getTfDocumento().setBackground(Color.RED);
	        JOptionPane.showMessageDialog(null, "El campo Documento es obligatorio");
	        isValid = false;
	    } else {
	        ventana.getTfDocumento().setBackground(Color.WHITE);
	    }

	    if (ventana.getTfTelefono().getText().isEmpty()) {
	        ventana.getTfTelefono().setBackground(Color.RED);
	        JOptionPane.showMessageDialog(null, "El campo Telefono es obligatorio");
	        isValid = false;
	    } else {
	        ventana.getTfTelefono().setBackground(Color.WHITE);
	    }

	    if (ventana.getTfEmail().getText().isEmpty()) {
	        ventana.getTfEmail().setBackground(Color.RED);
	        JOptionPane.showMessageDialog(null, "El campo Email es obligatorio");
	        isValid = false;
	    } else {
	        ventana.getTfEmail().setBackground(Color.WHITE);
	    }

	    if (ventana.getTfDireccion().getText().isEmpty()) {
	        ventana.getTfDireccion().setBackground(Color.RED);
	        JOptionPane.showMessageDialog(null, "El campo Direccion es obligatorio");
	        isValid = false;
	    } else {
	        ventana.getTfDireccion().setBackground(Color.WHITE);
	    }

	    String fechaText = ventana.getTfFecha().getText();
	    if (fechaText.isEmpty() || UtilidadesFecha.stringAFecha(fechaText) == null) {
	        ventana.getTfFecha().setBackground(Color.RED);
	        JOptionPane.showMessageDialog(null, "Ingrese una fecha válida");
	        isValid = false;
	    } else {
	        ventana.getTfFecha().setBackground(Color.WHITE);
	    }

	    String documento = ventana.getTfDocumento().getText();
	    if (!documento.isEmpty() && funcionarioDAO.verificarCedula(documento) != null) {
	        if (funcionario == null || funcionario.getId() != funcionarioDAO.verificarCedula(documento).getId()) {
	            ventana.getTfDocumento().setBackground(Color.RED);
	            JOptionPane.showMessageDialog(null, "Documento Duplicado");
	            isValid = false;
	        } else {
	            ventana.getTfDocumento().setBackground(Color.WHITE);
	        }
	    } else {
	        ventana.getTfDocumento().setBackground(Color.WHITE);
	    }

	    return isValid;
	}


}
