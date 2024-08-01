package py.edu.facitec.Simpres2.Buscadores;

import java.util.List;

import javax.swing.JDialog;
import javax.swing.JTextField;

import py.edu.facitec.Simpres2.DAO.FuncionarioDAO;
import py.edu.facitec.Simpres2.Interfaces.InterfaceFuncionario;
import py.edu.facitec.Simpres2.entidades.Funcionario;
import py.edu.facitec.Simpres2.tablas.ModeloTablaFuncionario;

import javax.swing.JTable;
import javax.swing.JScrollPane;

import java.awt.BorderLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class BuscadorFuncionario extends JDialog {

	private JTextField tfBuscador;
	private JTable table;
	private FuncionarioDAO funcionarioDAO;
	private List<Funcionario> funcionarios;
	private InterfaceFuncionario interfaceFuncionario;
	private ModeloTablaFuncionario modeloTablaFuncionario;

	public void setInterfaceFuncionario(InterfaceFuncionario interfaceFuncionario) {
		this.interfaceFuncionario = interfaceFuncionario;
	}

	public BuscadorFuncionario() {
		setTitle("Buscador de Funcionarios");
		setBounds(100, 100, 500, 500);
		setModal(true);
		setLocationRelativeTo(this);

		tfBuscador = new JTextField();
		tfBuscador.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				if (e.getKeyChar() == KeyEvent.VK_ENTER) {
					filtrarFuncionario();
				}
			}
		});
		getContentPane().add(tfBuscador, BorderLayout.NORTH);
		tfBuscador.setColumns(10);

		JScrollPane scrollPane = new JScrollPane();
		getContentPane().add(scrollPane, BorderLayout.CENTER);

		modeloTablaFuncionario = new ModeloTablaFuncionario();
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					seleccionarRegistro(table.getSelectedRow());
				}
			}
		});
		table.setModel(modeloTablaFuncionario);
		scrollPane.setViewportView(table);

		funcionarioDAO = new FuncionarioDAO();
		filtrarFuncionario();
	}

	private void filtrarFuncionario() {
		funcionarios = funcionarioDAO.filtrarFuncionarios(tfBuscador.getText());
		modeloTablaFuncionario.setLista(funcionarios);
	}

	private void seleccionarRegistro(int index) {
		if (index < 0)
			return;
		interfaceFuncionario.seleccionarFuncionario(funcionarios.get(index));
		dispose();
	}
}
