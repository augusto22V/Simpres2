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
import py.edu.facitec.Simpres2.DAO.ClienteDao;
import py.edu.facitec.Simpres2.Interfaces.AccionesABM;
import py.edu.facitec.Simpres2.entidades.Cliente;
import py.edu.facitec.Simpres2.tablas.ModeloTablaCliente;
import py.edu.facitec.Simpres2.vista.VentanaCliente;

public class ClienteController implements AccionesABM {
	private VentanaCliente ventana;
	private ModeloTablaCliente modeloTablaCliente;
	private Cliente cliente;
	private List<Cliente> clientes;
	private ClienteDao clientedao;
	private ButtonGroup grupoSexo;

	public ClienteController(VentanaCliente ventanaCliente) {
		super();
		this.ventana = ventanaCliente;
		this.ventana.setAcciones(this);
		modeloTablaCliente = new ModeloTablaCliente();
		this.ventana.getTable().setModel(modeloTablaCliente);

		setUpEvents();
		clientedao = new ClienteDao();
		cliente = new Cliente();
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
					consultarClientes();
				;
			}
		});

	}

	private void estadoinicial() {
		consultarClientes();
		ventana.getBtnCancelar().setEnabled(false);
		ventana.getBtnModificar().setEnabled(false);
		ventana.getBtnEliminar().setEnabled(false);
		ventana.getBtnSalir().setEnabled(true);
		ventana.getBtnGuardar().setEnabled(false);
		ventana.getBtnGuardar().setText("Guardar");
		EventosGenericos.estadosJtexField(ventana.getPanelformulario(), false);
		EventosGenericos.limpiarJtexField(ventana.getPanelformulario());
		ventana.getBtnNuevo().setEnabled(true);
		ventana.getBtnNuevo().setText("Nuevo");
		ventana.getTable().setEnabled(true);
		ventana.getTfFecha().setValue(null);
		ventana.getTfFecha().setEnabled(false);
		ventana.getRdbtnHombre().setEnabled(false);
		ventana.getRdbtnMujer().setEnabled(false);
		grupoSexo = new ButtonGroup();
		grupoSexo.add(ventana.getRdbtnHombre());
		grupoSexo.add(ventana.getRdbtnMujer());

	}

	@Override
	public void nuevo() {
		ventana.getBtnNuevo().setEnabled(false);
		ventana.getBtnSalir().setEnabled(false);
		ventana.getBtnCancelar().setEnabled(true);
		ventana.getBtnGuardar().setEnabled(true);
		ventana.getBtnEliminar().setEnabled(false);
		EventosGenericos.estadosJtexField(ventana.getPanelformulario(), true);
		ventana.getRdbtnHombre().setEnabled(true);
		ventana.getRdbtnMujer().setEnabled(true);
		grupoSexo.clearSelection();

		ventana.getTfFecha().setEnabled(true);
		ventana.getTable().setEnabled(false);

	}

	@Override
	public void modificar() {
		EventosGenericos.estadosJtexField(ventana.getPanelformulario(), true);
		ventana.getTfFecha().setEnabled(true);
		ventana.getBtnCancelar().setEnabled(true);
		ventana.getBtnGuardar().setEnabled(true);
		ventana.getBtnGuardar().setText("Actualizar");
		ventana.getTfFecha().setEnabled(true);

		ventana.getRdbtnHombre().setEnabled(true);
		ventana.getRdbtnMujer().setEnabled(true);
		grupoSexo.add(ventana.getRdbtnHombre());
		grupoSexo.add(ventana.getRdbtnMujer());
		if (cliente.getSexo().equals("Masculino")) {
			ventana.getRdbtnHombre().setSelected(true);
		} else if (cliente.getSexo().equals("Femenino")) {
			ventana.getRdbtnMujer().setSelected(true);
		}
	}

	@Override
	public void eliminar() {
	    if (cliente == null) {
	        JOptionPane.showMessageDialog(null, "Cliente no encontrado");
	        return;
	    }

	    int respuesta = JOptionPane.showConfirmDialog(null, "¿Estás seguro que deseas eliminar el cliente " + cliente.getNombre() + "?",
	            "Atención", JOptionPane.YES_NO_OPTION);
	    if (respuesta == JOptionPane.YES_OPTION) {
	        try {
	            clientedao.eliminar(cliente);
	            clientedao.commit();
	            estadoinicial();
	            consultarClientes();
	        } catch (Exception e) {
	            clientedao.rollback();
	            if (e.getCause() != null && e.getCause().getMessage().contains("violates foreign key constraint")) {
	                JOptionPane.showMessageDialog(null, "No se puede eliminar el cliente porque está siendo utilizado en otro proceso.");
	            } else {
	                JOptionPane.showMessageDialog(null, "No se puede eliminar el cliente porque está siendo utilizado en otro proceso.");
	                e.printStackTrace();
	            }
	        }
	    }
	}

	public void guardar() {
	    if (!validarCampos()) {
	        return; 
	    }
	    
	    cliente = new Cliente();
	    cargarEntidad();
	    try {
	        clientedao.guardar(cliente);
	        clientedao.commit();
	        consultarClientes();
	        EventosGenericos.limpiarJtexField(ventana.getPanelformulario());
	    	JOptionPane.showMessageDialog(null, "El cliente ha sido registrado correctamente");
	        estadoinicial();

	    } catch (Exception e) {
	        clientedao.rollback();
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
			clientedao.guardar(cliente);
			clientedao.commit();
			consultarClientes();
			estadoinicial();
		} catch (Exception e) {
			clientedao.rollback();
			e.printStackTrace();
		}
		grupoSexo.clearSelection();

	}

	private void cargarEntidad() {
		cliente.setNombre(this.ventana.getTfNombre().getText());
		cliente.setApellido(this.ventana.getTfApellido().getText());
		cliente.setDocumento(this.ventana.getTfDocumento().getText());
		cliente.setDireccion(this.ventana.getTfDireccion().getText());
		cliente.setTelefono(this.ventana.getTfTelefono().getText());
		cliente.setCorreo(this.ventana.getTfEmail().getText());
		cliente.setFecha_nacimiento(UtilidadesFecha.stringAFecha(this.ventana.getTfFecha().getText()));
		if (ventana.getRdbtnHombre().isSelected()) {
			cliente.setSexo("Masculino");
		} else if (ventana.getRdbtnMujer().isSelected()) {
			cliente.setSexo("Femenino");
		}

	}

	private void Recuperarporfiltro() {
		clientes = clientedao.filtrarClientes(this.ventana.getTfbuscador().getText());
		modeloTablaCliente.setLista(clientes);
	}

	private void seleccionarRegistro(int index) {
		if (index < 0)
			return;
		cliente = clientes.get(index);
		ventana.getTfNombre().setText(cliente.getNombre());
		ventana.getTfApellido().setText(cliente.getApellido());
		ventana.getTfDocumento().setText(cliente.getDocumento());
		ventana.getTfTelefono().setText(cliente.getTelefono());
		ventana.getTfDireccion().setText(cliente.getDireccion());
		ventana.getTfEmail().setText(cliente.getCorreo());
		ventana.getTfFecha().setText(UtilidadesFecha.fechaAString(cliente.getFecha_nacimiento()));
		ventana.getBtnCancelar().setEnabled(true);
		ventana.getBtnNuevo().setEnabled(false);
		ventana.getBtnSalir().setEnabled(false);
		ventana.getBtnModificar().setEnabled(true);
		ventana.getBtnEliminar().setEnabled(true);

		grupoSexo.clearSelection();

		String sexo = cliente.getSexo();

		if (sexo != null) {
			if (sexo.equals("Masculino")) {
				ventana.getRdbtnHombre().setSelected(true);
			} else if (sexo.equals("Femenino")) {
				ventana.getRdbtnMujer().setSelected(true);
			}
		}

	}

	private void consultarClientes() {
		clientes = clientedao.filtrarClientes(this.ventana.getTfbuscador().getText());
		modeloTablaCliente.setLista(clientes);
	}

	private boolean validarCampos() {
	    boolean isValid = true;

	    // Validar y cambiar el color de fondo de los campos
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
	    if (!documento.isEmpty() && clientedao.verificarCedula(documento) != null) {
	        if (cliente == null || cliente.getId() != clientedao.verificarCedula(documento).getId()) {
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
