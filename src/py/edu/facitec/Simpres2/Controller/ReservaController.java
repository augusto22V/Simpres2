package py.edu.facitec.Simpres2.Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.JOptionPane;

import componentes.EventosGenericos;
import componentes.UtilidadesFecha;
import py.edu.facitec.Simpres2.Buscadores.BuscadorCliente;
import py.edu.facitec.Simpres2.Buscadores.BuscadorFuncionario;
import py.edu.facitec.Simpres2.Buscadores.BuscadorHabitacion;
import py.edu.facitec.Simpres2.Buscadores.BuscadorServicio;
import py.edu.facitec.Simpres2.DAO.ReservasDAO;
import py.edu.facitec.Simpres2.DAO.ReservasDetalleDAO;
import py.edu.facitec.Simpres2.Interfaces.AccionesABM;
import py.edu.facitec.Simpres2.Interfaces.InterfaceCliente;
import py.edu.facitec.Simpres2.Interfaces.InterfaceFuncionario;
import py.edu.facitec.Simpres2.Interfaces.InterfaceHabitacion;
import py.edu.facitec.Simpres2.Interfaces.InterfaceReserva;
import py.edu.facitec.Simpres2.Interfaces.InterfaceServicio;
import py.edu.facitec.Simpres2.entidades.Cliente;
import py.edu.facitec.Simpres2.entidades.Funcionario;
import py.edu.facitec.Simpres2.entidades.Habitacion;
import py.edu.facitec.Simpres2.entidades.Reserva_detalle;
import py.edu.facitec.Simpres2.entidades.Reservas;
import py.edu.facitec.Simpres2.entidades.Servicios;
import py.edu.facitec.Simpres2.tablas.ModeloTablaReservaDetalle;
import py.edu.facitec.Simpres2.vista.VentanaReserva;

public class ReservaController implements AccionesABM, InterfaceCliente, InterfaceReserva, InterfaceHabitacion,
		InterfaceFuncionario, InterfaceServicio {

	private ReservasDAO reservasDAO;
	private Reservas reservas;
	private Cliente cliente;
	private ReservasDetalleDAO reservasDetalleDAO;
	private Funcionario funcionario;
	private Servicios servicios;
	private List<Reserva_detalle> detalles;
	private Habitacion habitacion;
	private VentanaReserva ventana;
	private ModeloTablaReservaDetalle modeloTablaReservaDetalle;

	public ReservaController(VentanaReserva ventanaReserva) {
		this.ventana = ventanaReserva;

		reservas = new Reservas();
		reservasDAO = new ReservasDAO();
		reservasDetalleDAO = new ReservasDetalleDAO();
		new Reserva_detalle();
		detalles = new ArrayList<>();
		modeloTablaReservaDetalle = new ModeloTablaReservaDetalle();
		this.ventana.getTable().setModel(modeloTablaReservaDetalle);
		setUpEvents();
		estadoinicial();

	}

	private void setUpEvents() {
		ventana.getBtnNuevo().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				nuevo();
			}
		});
		ventana.getBtnguardar().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				guardar();
			}
		});

		ventana.getBtnCancelar().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				cancelar();
			}
		});
		ventana.getBtnBuscarCliente().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				abrirbuscadorCliente();
			}
		});
		ventana.getBtnAgregar().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				anhadirReserva();
			}
		});
		ventana.getBtnbuscarNroH().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				abrirbuscadorHabitaciones();
			}
		});

		ventana.getBtnBuscarFunc().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				abrirbuscadorFuncionario();
			}
		});
		ventana.getBtnbuscarServicio().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				abrirBuscadorServicio();
			}
		});
		ventana.getBtnSalir().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				salir();
			}
		});
		ventana.getBtnquitar().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				quitarReserva();
			}
		});

		ventana.getTable().addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2)
					seleccionarItem(ventana.getTable().getSelectedRow());
			}
		});
		 ventana.getTable().addMouseListener(new MouseAdapter() {
		        @Override
		        public void mouseClicked(MouseEvent e) {
		            if (ventana.getTable().getSelectedRow() != -1) {
		                ventana.getBtnquitar().setEnabled(true);
		            }
		        }
		    });
	}

	private void estadoinicial() {
		ventana.getBtnbuscarNroH().setEnabled(false);
		ventana.getBtnBuscarCliente().setEnabled(false);
		ventana.getBtnBuscarFunc().setEnabled(false);
		ventana.getBtnbuscarServicio().setEnabled(false);
		ventana.getTfCliente().setEnabled(false);
		ventana.getTfdocumentoF().setEnabled(false);
		ventana.getTfNombre().setEnabled(false);
		ventana.getTfCantPersonas().setEnabled(false);
		ventana.getTfTelefono().setEnabled(false);
		ventana.getTfNombre().setEnabled(false);
		ventana.getTfFechaIngreso().setEnabled(false);
		ventana.getTfFechaSalida().setEnabled(false);
		ventana.getTfCantPersonas().setEnabled(false);
		ventana.getTfnroHab().setEnabled(false);
		ventana.getBtnbuscarNroH().setEnabled(false);
		ventana.getTfFecha().setText(UtilidadesFecha.fechaAString(new Date()));
		ventana.getBtnAgregar().setEnabled(false);
		ventana.getBtnAnular().setEnabled(false);
		ventana.getBtnquitar().setEnabled(false);
		ventana.getBtnguardar().setEnabled(false);
		ventana.getTfCantDias().setEnabled(false);
		ventana.getTfprecioservicio().setEnabled(false);

	}

	private void limpiar() {
		System.out.println("Limpiando...");
		EventosGenericos.limpiarJtexField(ventana.getContentPane());
		ventana.getTfFechaIngreso().setValue(null);
		ventana.getTfFechaSalida().setValue(null);
		ventana.getLblCostoTotal().setText("");
		ventana.getTfnroHab().setText("");
		ventana.getTfCliente().setText("");
		ventana.getTfNombre().setText("");
		ventana.getTfTelefono().setText("");
		ventana.getTfdocumentoF().setText("");
		ventana.getTfnombreF().setText("");
		ventana.getTftelefonoF().setText("");
		ventana.getTFuncionario().setText("");
		ventana.getTfFechaIngreso().setValue(null);
		ventana.getTfFechaSalida().setValue(null);
		ventana.getTfCantPersonas().setText("");
		ventana.getTfCantDias().setText("");
		ventana.getTfprecioservicio().setText("");
		detalles = new ArrayList<Reserva_detalle>();
		modeloTablaReservaDetalle.setListaReservasDetalle(detalles);
		System.out.println("Limpieza completada.");
	}

	@Override
	public void nuevo() {
		limpiar();
		ventana.getBtnBuscarCliente().setEnabled(true);
		ventana.getBtnBuscarCliente().setEnabled(true);
		ventana.getBtnbuscarServicio().setEnabled(true);
		ventana.getTfCliente().setEnabled(true);
		ventana.getTfdocumentoF().setEnabled(true);
		ventana.getTfNombre().setEnabled(true);
		ventana.getTfCantPersonas().setEnabled(true);
		ventana.getTfTelefono().setEnabled(true);
		ventana.getTfNombre().setEnabled(true);
		ventana.getTfFechaIngreso().setEnabled(true);
		ventana.getTfFechaSalida().setEnabled(true);
		ventana.getTfCantPersonas().setEnabled(true);
		ventana.getTfnroHab().setEnabled(true);
		ventana.getBtnBuscarFunc().setEnabled(true);
		ventana.getBtnbuscarNroH().setEnabled(true);
		ventana.getBtnguardar().setEnabled(true);
		ventana.getBtnAnular().setEnabled(true);
		ventana.getBtnquitar().setEnabled(true);
		ventana.getBtnAgregar().setEnabled(true);
		ventana.getTfCantDias().setEnabled(true);
		ventana.getTfprecioservicio().setEnabled(true);

		reservas = new Reservas();
		detalles = new ArrayList<Reserva_detalle>();
	}

	private void seleccionarItem(int index) {
		if (index >= 0 && index < detalles.size()) {
			detalles.get(index);
		}
	}

	public void mostrarPrecio(double precio) {
		ventana.getTfprecioservicio().setText(String.valueOf(precio));
	}

	@Override
	public void seleccionarCliente(Cliente cliente) {
		this.cliente = cliente;
		ventana.getTfNombre().setText(cliente.getNombre() + " " + cliente.getApellido());
		ventana.getTfTelefono().setText(cliente.getTelefono());
		ventana.getTfCliente().setText(cliente.getDocumento());

	}

	private void abrirbuscadorCliente() {
		BuscadorCliente buscadorCliente = new BuscadorCliente();
		buscadorCliente.setInterfaceCliente(this);
		buscadorCliente.setVisible(true);
	}

	@Override
	public void seleccionarFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
		ventana.getTfnombreF().setText(funcionario.getNombre() + " " + funcionario.getApellido());
		ventana.getTftelefonoF().setText(funcionario.getTelefono());
		ventana.getTFuncionario().setText(funcionario.getDocumento());
	}

	private void abrirbuscadorFuncionario() {
		BuscadorFuncionario buscadorFuncionario = new BuscadorFuncionario();
		buscadorFuncionario.setInterfaceFuncionario(this);
		buscadorFuncionario.setVisible(true);
	}

	@Override
	public void seleccionarHabitacion(Habitacion habitacion) {
		this.habitacion = habitacion;
		ventana.getTfnroHab().setText(habitacion.getNumero());
	}

	private void abrirbuscadorHabitaciones() {
		BuscadorHabitacion buscadorHabitacion = new BuscadorHabitacion();
		buscadorHabitacion.setInterfaceHabitacion(this);
		buscadorHabitacion.setVisible(true);
	}

	@Override
	public void seleccionarServicio(Servicios servicios) {
		this.servicios = servicios;

		ventana.getTfprecioservicio().setText(String.valueOf(servicios.getPrecio()));
	}

	private void abrirBuscadorServicio() {
		System.out.println("Abriendo buscador de servicios...");
		BuscadorServicio buscadorServicio = new BuscadorServicio();
		buscadorServicio.setInterfaceServicio(this);
		buscadorServicio.setVisible(true);
	}

	private void cargarReserva() {
	    double costoTotal = calcularCostoTotal();

	    reservas = new Reservas();
	    reservas.setCliente(cliente);
	    reservas.setFuncionario(funcionario);
	    reservas.setServicios(servicios);
	    reservas.setFecha_ingreso(UtilidadesFecha.stringAFechaConFormato(ventana.getTfFechaIngreso().getText()));
	    reservas.setFecha_salida(UtilidadesFecha.stringAFechaConFormato(ventana.getTfFechaSalida().getText()));
	    reservas.setDetalles(detalles);
	    reservas.setCantida_personas(Integer.parseInt(ventana.getTfCantPersonas().getText()));

	    reservas.setCosto_total(costoTotal);

	    for (Reserva_detalle detalle : detalles) {
	        detalle.setReservas(reservas);
	    }

	    ventana.getLblCostoTotal().setText(String.valueOf(costoTotal));
	}

	private void anhadirReserva() {
	    if (cliente == null || funcionario == null || habitacion == null || ventana.getTfCantDias().getText().isEmpty()) {
	        JOptionPane.showMessageDialog(null, "Debe llenar todos los datos antes de añadir la reserva.", "Error", JOptionPane.ERROR_MESSAGE);
	        return;
	    }

	    Reserva_detalle nuevoDetalle = new Reserva_detalle();
	    nuevoDetalle.setHabitacion(habitacion);
	    nuevoDetalle.setCantidad_dias(Integer.parseInt(ventana.getTfCantDias().getText()));
	    
	    if (!ventana.getTfprecioservicio().getText().isEmpty()) {
	        nuevoDetalle.setPrecio(Double.parseDouble(ventana.getTfprecioservicio().getText()));
	    } else {
	        nuevoDetalle.setPrecio(0.0); // Precio por defecto si no se proporciona
	    }
	    
	    detalles.add(nuevoDetalle);

	    modeloTablaReservaDetalle.setListaReservasDetalle(detalles);

	    double costoTotal = calcularCostoTotal();
	    ventana.getLblCostoTotal().setText(String.valueOf(costoTotal));

	    ventana.getBtnAgregar().setEnabled(true);
	    ventana.getBtnquitar().setEnabled(false); // Deshabilitar hasta que se seleccione una fila
	}

	
	private void quitarReserva() {
	    int selectedIndex = ventana.getTable().getSelectedRow();
	    if (selectedIndex == -1) {
	        JOptionPane.showMessageDialog(null, "Debe seleccionar una reserva para eliminar.", "Error", JOptionPane.ERROR_MESSAGE);
	        return;
	    }

	    detalles.remove(selectedIndex);
	    modeloTablaReservaDetalle.setListaReservasDetalle(detalles);

	    double costoTotal = calcularCostoTotal();
	    ventana.getLblCostoTotal().setText(String.valueOf(costoTotal));

	    ventana.getBtnquitar().setEnabled(false); // Deshabilitar después de la eliminación
	}


	private double calcularCostoTotal() {
	    double costoTotal = 0.0;

	    for (Reserva_detalle detalle : detalles) {
	        double costoHabitacion = detalle.getHabitacion().getPrecio();
	        double costoReserva = detalle.getPrecio();
	        int cantidadDias = detalle.getCantidad_dias();
	        costoTotal += (costoHabitacion * cantidadDias) + costoReserva;
	    }

	    return costoTotal;
	}

	@Override
	public void guardar() {
		cargarReserva();
		try {
			reservasDAO.guardar(reservas);
			reservasDAO.commit();
			estadoinicial();
			JOptionPane.showMessageDialog(null, "Reserva guardada exitosamente");
		} catch (Exception e) {
			reservasDAO.rollback();
			reservasDetalleDAO.rollback();

			JOptionPane.showMessageDialog(null, "Error al guardar la reserva: " + e.getMessage(), "Error",
					JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
			limpiar();
			estadoinicial();
		}
	}

	@Override
	public void cancelar() {
		estadoinicial();

		limpiar();

	}

	@Override
	public void salir() {
		ventana.dispose();
	}

	@Override
	public void seleccionarReserva(Reservas reservas) {

	}

	@Override
	public void modificar() {
	}

	@Override
	public void eliminar() {
		// TODO Auto-generated method stub

	}

	@Override
	public void actualizar() {
		// TODO Auto-generated method stub

	}
}
