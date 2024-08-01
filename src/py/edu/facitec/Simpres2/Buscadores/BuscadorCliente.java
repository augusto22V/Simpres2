package py.edu.facitec.Simpres2.Buscadores;

import java.util.List;

import javax.swing.JDialog;
import javax.swing.JTextField;

import py.edu.facitec.Simpres2.DAO.ClienteDao;
import py.edu.facitec.Simpres2.Interfaces.InterfaceCliente;
import py.edu.facitec.Simpres2.entidades.Cliente;
import py.edu.facitec.Simpres2.tablas.ModeloTablaCliente;

import javax.swing.JTable;
import javax.swing.JScrollPane;

import java.awt.BorderLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class BuscadorCliente extends JDialog {

	private JTextField tfbuscador;
	private JTable table;
	private ClienteDao clienteDao;
	private List<Cliente> clientes;
	private InterfaceCliente interfaceCliente;
	private ModeloTablaCliente modeloTablaCliente;

	public void setInterfaceCliente(InterfaceCliente interfaceCliente) {
		this.interfaceCliente = interfaceCliente;
	}

	public BuscadorCliente() {
		setTitle("BuscadorCliente");
		setBounds(100, 100, 500, 500);
		setModal(true);
		setLocationRelativeTo(this);

		tfbuscador = new JTextField();
		tfbuscador.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				if (e.getKeyChar() == KeyEvent.VK_ENTER)
					filtrarCliente();
				{

				}
			}
		});
		tfbuscador.setBounds(0, 0, 505, 20);
		getContentPane().add(tfbuscador, BorderLayout.NORTH);
		tfbuscador.setColumns(10);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 21, 484, 440);
		getContentPane().add(scrollPane);

		modeloTablaCliente = new ModeloTablaCliente();
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					seleccionarRegistro(table.getSelectedRow());

				}
			}
		});
		table.setModel(modeloTablaCliente);
		scrollPane.setViewportView(table);
		clienteDao = new ClienteDao();
		filtrarCliente();

	}

	private void filtrarCliente() {
		clientes = clienteDao.filtrarClientes(tfbuscador.getText());
		modeloTablaCliente.setLista(clientes);
	}

	private void seleccionarRegistro(int index) {
		if (index < 0)
			return;
		interfaceCliente.seleccionarCliente(clientes.get(index));
		dispose();
	}

}
