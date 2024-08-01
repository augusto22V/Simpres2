package py.edu.facitec.Simpres2.vista;

import java.awt.EventQueue; 

import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import componentes.JDialogGenerico;
import py.edu.facitec.Simpres2.Controller.ServicioController;
import componentes.JtextPersonalizado;

public class VentanaServicio extends JDialogGenerico {
	private JtextPersonalizado tfDescripcion;
	private JFormattedTextField tfPrecio;
	private JLabel lblPrecio;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaServicio dialog = new VentanaServicio();
					dialog.setUpControlador();
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialog.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}

			}
		});

	}

	public void setUpControlador() {
		new ServicioController(this);
	}

	public VentanaServicio() {

		getBtnCancelar().setBounds(272, 459, 113, 61);
		getBtnGuardar().setBounds(23, 459, 113, 61);
		setTitle("Servicios");
		getLblTitulo().setText("Registro Servicio");

		getPanelformulario().setLayout(null);

		JLabel lblDescripcion = new JLabel("Descripcion");
		lblDescripcion.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDescripcion.setBounds(21, 89, 69, 14);
		getPanelformulario().add(lblDescripcion);

		tfDescripcion = new JtextPersonalizado();
		tfDescripcion.setBounds(100, 85, 160, 21);
		getPanelformulario().add(tfDescripcion);

		tfPrecio = new JFormattedTextField();
		tfPrecio.setBounds(100, 121, 98, 21);
		getPanelformulario().add(tfPrecio);

		lblPrecio = new JLabel("Precio");
		lblPrecio.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPrecio.setBounds(21, 124, 69, 14);
		getPanelformulario().add(lblPrecio);

	}

	public void mostrarPrecio(double precio) {
		tfPrecio.setText(String.valueOf(precio));
	}

	public JtextPersonalizado getTfDescripcion() {
		return tfDescripcion;
	}

	public void setTfDescripcion(JtextPersonalizado tfDescripcion) {
		this.tfDescripcion = tfDescripcion;
	}

	public JFormattedTextField getTfPrecio() {
		return tfPrecio;
	}

	public void setTfPrecio(JFormattedTextField tfPrecio) {
		this.tfPrecio = tfPrecio;
	}
}
