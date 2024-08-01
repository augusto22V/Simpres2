package py.edu.facitec.Simpres2.Buscadores;

import java.util.List;

import javax.swing.JDialog;
import javax.swing.JTextField;

import py.edu.facitec.Simpres2.DAO.HabitacionDAO;
import py.edu.facitec.Simpres2.Interfaces.InterfaceHabitacion;
import py.edu.facitec.Simpres2.entidades.Habitacion;
import py.edu.facitec.Simpres2.tablas.ModeloTablaHabitacion;

import javax.swing.JTable;
import javax.swing.JScrollPane;

import java.awt.BorderLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class BuscadorHabitacion extends JDialog {

	private JTextField tfbuscador;
	private JTable table;
	private HabitacionDAO habitacionDAO;
	private List<Habitacion> habitaciones;
	private InterfaceHabitacion interfaceHabitacion;
	private ModeloTablaHabitacion modeloTablaHabitacion;

	public void setInterfaceHabitacion(InterfaceHabitacion interfaceHabitacion) {
		this.interfaceHabitacion = interfaceHabitacion;
	}

	public BuscadorHabitacion() {
		setTitle("BuscadorHabitacion");
		setBounds(100, 100, 500, 500);
		setModal(true);
		setLocationRelativeTo(this);

		tfbuscador = new JTextField();
		tfbuscador.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				if (e.getKeyChar() == KeyEvent.VK_ENTER)
					filtrarHabitacion();
			}
		});
		getContentPane().add(tfbuscador, BorderLayout.NORTH);
		tfbuscador.setColumns(10);

		JScrollPane scrollPane = new JScrollPane();
		getContentPane().add(scrollPane, BorderLayout.CENTER);

		modeloTablaHabitacion = new ModeloTablaHabitacion();
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					seleccionarRegistro(table.getSelectedRow());
				}
			}
		});
		table.setModel(modeloTablaHabitacion);
		scrollPane.setViewportView(table);
		habitacionDAO = new HabitacionDAO();
		filtrarHabitacion();
	}

	private void filtrarHabitacion() {
	    String textoBusqueda = tfbuscador.getText().trim(); 

	    if (textoBusqueda.isEmpty()) {
	        habitaciones = habitacionDAO.filtrarHabitaciones("");
	    } else {
	        habitaciones = habitacionDAO.filtrarHabitaciones(textoBusqueda);
	    }

	    modeloTablaHabitacion.setLista(habitaciones);
	}


	private void seleccionarRegistro(int index) {
		if (index < 0)
			return;
		interfaceHabitacion.seleccionarHabitacion(habitaciones.get(index));
		dispose();
	}
}
