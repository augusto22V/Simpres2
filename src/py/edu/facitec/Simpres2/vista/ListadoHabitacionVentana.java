package py.edu.facitec.Simpres2.vista;

import java.awt.EventQueue;
import javax.swing.JDialog;
import javax.swing.JLabel;
import componentes.JtextPersonalizado;
import py.edu.facitec.Simpres2.Controller.ListadoHabitacionController;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JScrollPane;

public class ListadoHabitacionVentana extends JDialog {
	private JtextPersonalizado tfDesdeTipo;
	private JTable table;
	private JButton btnImprimir;
	private JtextPersonalizado tfHastaTipo;
	private JButton btnFiltrar;
	private JComboBox cbOrder;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ListadoHabitacionVentana dialog = new ListadoHabitacionVentana();
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialog.setUpController();
					dialog.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public void setUpController() {
		new ListadoHabitacionController(this);
	}

	/**
	 * Create the dialog.
	 */
	public ListadoHabitacionVentana() {
		setTitle("Listado de Habitaciones");
		setBounds(100, 100, 800, 600);
		setLocationRelativeTo(this);
		setModal(true);
		getContentPane().setLayout(null);

		JLabel lblDesdeTipo = new JLabel("Desde Tipo:");
		lblDesdeTipo.setBounds(12, 34, 96, 16);
		getContentPane().add(lblDesdeTipo);

		JLabel lblHastaTipo = new JLabel("Hasta Tipo:");
		lblHastaTipo.setBounds(292, 34, 96, 16);
		getContentPane().add(lblHastaTipo);

		tfDesdeTipo = new JtextPersonalizado();
		tfDesdeTipo.setBounds(109, 31, 116, 22);
		getContentPane().add(tfDesdeTipo);
		tfDesdeTipo.setColumns(10);

		tfHastaTipo = new JtextPersonalizado();
		tfHastaTipo.setColumns(10);
		tfHastaTipo.setBounds(385, 32, 116, 22);
		getContentPane().add(tfHastaTipo);

		JLabel lblOrdenarPor = new JLabel("Ordenar por:");
		lblOrdenarPor.setBounds(552, 34, 80, 16);
		getContentPane().add(lblOrdenarPor);

		cbOrder = new JComboBox();
		cbOrder.setModel(new DefaultComboBoxModel(new String[] {"Tipo", "Numero"}));
		cbOrder.setBounds(644, 31, 116, 22);
		getContentPane().add(cbOrder);

		btnFiltrar = new JButton("Filtrar");
		btnFiltrar.setBounds(552, 79, 80, 25);
		getContentPane().add(btnFiltrar);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 122, 758, 360);
		getContentPane().add(scrollPane);

		table = new JTable();
		scrollPane.setViewportView(table);

		btnImprimir = new JButton("Imprimir");
		btnImprimir.setEnabled(false);
		btnImprimir.setBounds(663, 502, 97, 38);
		getContentPane().add(btnImprimir);
	}

	public JtextPersonalizado getTfDesdeTipo() {
		return tfDesdeTipo;
	}

	public void setTfDesdeTipo(JtextPersonalizado tfDesdeTipo) {
		this.tfDesdeTipo = tfDesdeTipo;
	}

	public JTable getTable() {
		return table;
	}

	public void setTable(JTable table) {
		this.table = table;
	}

	public JButton getBtnImprimir() {
		return btnImprimir;
	}

	public void setBtnImprimir(JButton btnImprimir) {
		this.btnImprimir = btnImprimir;
	}

	public JtextPersonalizado getTfHastaTipo() {
		return tfHastaTipo;
	}

	public void setTfHastaTipo(JtextPersonalizado tfHastaTipo) {
		this.tfHastaTipo = tfHastaTipo;
	}

	public JButton getBtnFiltrar() {
		return btnFiltrar;
	}

	public void setBtnFiltrar(JButton btnFiltrar) {
		this.btnFiltrar = btnFiltrar;
	}

	public JComboBox getCbOrder() {
		return cbOrder;
	}

	public void setCbOrder(JComboBox cbOrder) {
		this.cbOrder = cbOrder;
	}
}
