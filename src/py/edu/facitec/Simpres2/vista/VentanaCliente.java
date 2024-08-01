package py.edu.facitec.Simpres2.vista;

import java.awt.EventQueue;

import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.SwingConstants;

import componentes.JDialogGenerico;
import componentes.JtextPersonalizado;
import componentes.UtilidadesFecha;
import py.edu.facitec.Simpres2.Controller.ClienteController;

public class VentanaCliente extends JDialogGenerico {
	private JtextPersonalizado tfNombre;
	private JtextPersonalizado tfApellido;
	private JtextPersonalizado tfDocumento;
	private JtextPersonalizado tfTelefono;
	private JtextPersonalizado tfEmail;
	private JtextPersonalizado tfDireccion;

	private JRadioButton rdbtnHombre;
	private JRadioButton rdbtnMujer;
	private JLabel lblNombre;
	private JLabel lblApellido;
	private JLabel lblDocumento;
	private JLabel lblTelefono;
	private JLabel lblEmail;
	private JLabel lblDireccin;
	private JLabel lblSexo;
	private JLabel lblFechaNacimiento;
	private JFormattedTextField tffecha;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaCliente dialog = new VentanaCliente();
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
		new ClienteController(this);
	}

	public VentanaCliente() {

		getBtnCancelar().setBounds(272, 459, 113, 61);
		getBtnGuardar().setBounds(23, 459, 113, 61);
		setTitle("Clientes");
		getLblTitulo().setText("Registro Cliente");

		getPanelformulario().setLayout(null);

		lblNombre = new JLabel("Nombre");
		lblNombre.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNombre.setBounds(29, 47, 69, 14);
		getPanelformulario().add(lblNombre);

		lblApellido = new JLabel("Apellido:");
		lblApellido.setHorizontalAlignment(SwingConstants.RIGHT);
		lblApellido.setBounds(29, 83, 69, 14);
		getPanelformulario().add(lblApellido);

		lblDocumento = new JLabel("Documento:");
		lblDocumento.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDocumento.setBounds(29, 108, 69, 14);
		getPanelformulario().add(lblDocumento);

		lblTelefono = new JLabel("Telefono:");
		lblTelefono.setHorizontalAlignment(SwingConstants.RIGHT);
		lblTelefono.setBounds(29, 147, 69, 14);
		getPanelformulario().add(lblTelefono);

		lblEmail = new JLabel("Email:");
		lblEmail.setHorizontalAlignment(SwingConstants.RIGHT);
		lblEmail.setBounds(29, 178, 69, 14);
		getPanelformulario().add(lblEmail);

		lblDireccin = new JLabel("Direcci\u00F3n:");
		lblDireccin.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDireccin.setBounds(29, 218, 69, 14);
		getPanelformulario().add(lblDireccin);

		lblSexo = new JLabel("Sexo:");
		lblSexo.setHorizontalAlignment(SwingConstants.RIGHT);
		lblSexo.setBounds(29, 252, 69, 14);
		getPanelformulario().add(lblSexo);

		lblFechaNacimiento = new JLabel("Fecha Nac.");
		lblFechaNacimiento.setHorizontalAlignment(SwingConstants.RIGHT);
		lblFechaNacimiento.setBounds(0, 289, 98, 14);
		getPanelformulario().add(lblFechaNacimiento);

		tfNombre = new JtextPersonalizado();
		tfNombre.setBounds(108, 43, 160, 21);
		getPanelformulario().add(tfNombre);

		tfApellido = new JtextPersonalizado();
		tfApellido.setBounds(108, 79, 160, 21);
		getPanelformulario().add(tfApellido);

		tfDocumento = new JtextPersonalizado();
		tfDocumento.setBounds(108, 108, 98, 21);
		getPanelformulario().add(tfDocumento);

		tfTelefono = new JtextPersonalizado();
		tfTelefono.setBounds(108, 143, 98, 21);
		getPanelformulario().add(tfTelefono);

		tfEmail = new JtextPersonalizado();
		tfEmail.setBounds(108, 173, 160, 21);
		getPanelformulario().add(tfEmail);

		tfDireccion = new JtextPersonalizado();
		tfDireccion.setBounds(108, 214, 160, 21);
		getPanelformulario().add(tfDireccion);

		rdbtnHombre = new JRadioButton("Hombre");
		rdbtnHombre.setBounds(104, 248, 73, 23);
		getPanelformulario().add(rdbtnHombre);

		rdbtnMujer = new JRadioButton("Mujer");
		rdbtnMujer.setBounds(195, 248, 69, 23);
		getPanelformulario().add(rdbtnMujer);

		tffecha = new JFormattedTextField(UtilidadesFecha.getFormato());
		tffecha.setBounds(108, 286, 69, 21);
		getPanelformulario().add(tffecha);

	}

	public JFormattedTextField getTfFecha() {
		return tffecha;
	}

	public JtextPersonalizado getTfNombre() {
		return tfNombre;
	}

	public void setTfNombre(JtextPersonalizado tfNombre) {
		this.tfNombre = tfNombre;
	}

	public JtextPersonalizado getTfApellido() {
		return tfApellido;
	}

	public void setTfApellido(JtextPersonalizado tfApellido) {
		this.tfApellido = tfApellido;
	}

	public JtextPersonalizado getTfDocumento() {
		return tfDocumento;
	}

	public void setTfDocumento(JtextPersonalizado tfDocumento) {
		this.tfDocumento = tfDocumento;
	}

	public JtextPersonalizado getTfTelefono() {
		return tfTelefono;
	}

	public void setTfTelefono(JtextPersonalizado tfTelefono) {
		this.tfTelefono = tfTelefono;
	}

	public JtextPersonalizado getTfEmail() {
		return tfEmail;
	}

	public void setTfEmail(JtextPersonalizado tfEmail) {
		this.tfEmail = tfEmail;
	}

	public JtextPersonalizado getTfDireccion() {
		return tfDireccion;
	}

	public void setTfDireccion(JtextPersonalizado tfDireccion) {
		this.tfDireccion = tfDireccion;
	}

	public JRadioButton getRdbtnHombre() {
		return rdbtnHombre;
	}

	public void setRdbtnHombre(JRadioButton rdbtnHombre) {
		this.rdbtnHombre = rdbtnHombre;
	}

	public JRadioButton getRdbtnMujer() {
		return rdbtnMujer;
	}

	public void setRdbtnMujer(JRadioButton rdbtnMujer) {
		this.rdbtnMujer = rdbtnMujer;
	}

	public JLabel getLblNombre() {
		return lblNombre;
	}

	public void setLblNombre(JLabel lblNombre) {
		this.lblNombre = lblNombre;
	}

	public JLabel getLblApellido() {
		return lblApellido;
	}

	public void setLblApellido(JLabel lblApellido) {
		this.lblApellido = lblApellido;
	}

	public JLabel getLblDocumento() {
		return lblDocumento;
	}

	public void setLblDocumento(JLabel lblDocumento) {
		this.lblDocumento = lblDocumento;
	}

	public JLabel getLblTelefono() {
		return lblTelefono;
	}

	public void setLblTelefono(JLabel lblTelefono) {
		this.lblTelefono = lblTelefono;
	}

	public JLabel getLblEmail() {
		return lblEmail;
	}

	public void setLblEmail(JLabel lblEmail) {
		this.lblEmail = lblEmail;
	}

	public JLabel getLblDireccin() {
		return lblDireccin;
	}

	public void setLblDireccin(JLabel lblDireccin) {
		this.lblDireccin = lblDireccin;
	}

	public JLabel getLblSexo() {
		return lblSexo;
	}

	public void setLblSexo(JLabel lblSexo) {
		this.lblSexo = lblSexo;
	}

	public JLabel getLblFechaNacimiento() {
		return lblFechaNacimiento;
	}

	public void setLblFechaNacimiento(JLabel lblFechaNacimiento) {
		this.lblFechaNacimiento = lblFechaNacimiento;
	}

	public JFormattedTextField getTffecha() {
		return tffecha;
	}

	public void setTffecha(JFormattedTextField tffecha) {
		this.tffecha = tffecha;
	}

}
