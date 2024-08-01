package py.edu.facitec.Simpres2.vista;

import java.awt.EventQueue;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import py.edu.facitec.Simpres2.Controller.InformeReservasController;
import javax.swing.DefaultComboBoxModel;

public class InformeReservaVentana extends JDialog {
    private JTextField tfDesdeCliente;
    private JTextField tfHastaCliente;
    private JTable table;
    private JButton btnImprimir;
    private JButton btnFiltrar;
    private JComboBox<String> cbInforme;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    InformeReservaVentana dialog = new InformeReservaVentana();
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
        new InformeReservasController(this);
    }

    public InformeReservaVentana() {
        setTitle("Informe de Reservas");
        setBounds(100, 100, 800, 600);
        setLocationRelativeTo(this);
        setModal(true);
        getContentPane().setLayout(null);

        JLabel lblDesdeCliente = new JLabel("Desde Cliente:");
        lblDesdeCliente.setHorizontalAlignment(SwingConstants.RIGHT);
        lblDesdeCliente.setBounds(12, 34, 100, 16);
        getContentPane().add(lblDesdeCliente);

        JLabel lblHastaCliente = new JLabel("Hasta Cliente:");
        lblHastaCliente.setHorizontalAlignment(SwingConstants.RIGHT);
        lblHastaCliente.setBounds(292, 34, 100, 16);
        getContentPane().add(lblHastaCliente);

        tfDesdeCliente = new JTextField();
        tfDesdeCliente.setBounds(120, 31, 129, 22);
        getContentPane().add(tfDesdeCliente);
        tfDesdeCliente.setColumns(10);

        tfHastaCliente = new JTextField();
        tfHastaCliente.setBounds(402, 31, 129, 22);
        getContentPane().add(tfHastaCliente);
        tfHastaCliente.setColumns(10);

        btnFiltrar = new JButton("Filtrar");
        btnFiltrar.setBounds(552, 89, 100, 25);
        getContentPane().add(btnFiltrar);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(12, 139, 758, 341);
        getContentPane().add(scrollPane);

        table = new JTable();
        scrollPane.setViewportView(table);

        btnImprimir = new JButton("Imprimir");
        btnImprimir.setEnabled(false);
        btnImprimir.setBounds(663, 502, 97, 38);
        getContentPane().add(btnImprimir);
        
        JLabel lblOrdenarPor = new JLabel("Tpo de Informe:");
		lblOrdenarPor.setHorizontalAlignment(SwingConstants.RIGHT);
		lblOrdenarPor.setBounds(526, 34, 106, 16);
		getContentPane().add(lblOrdenarPor);
		
		cbInforme = new JComboBox();
		cbInforme.setModel(new DefaultComboBoxModel(new String[] {"Simples", "Detallado"}));
		cbInforme.setBounds(644, 31, 116, 22);
		getContentPane().add(cbInforme);
    }

    public JTextField getTfDesdeCliente() {
        return tfDesdeCliente;
    }

    public JTextField getTfHastaCliente() {
        return tfHastaCliente;
    }

    public JTable getTable() {
        return table;
    }

    public JButton getBtnImprimir() {
        return btnImprimir;
    }

    public JButton getBtnFiltrar() {
        return btnFiltrar;
    }

    public JComboBox<String> getCbInforme() {
        return cbInforme;
    }
}
