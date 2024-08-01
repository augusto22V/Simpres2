package py.edu.facitec.Simpres2.vista;

import java.awt.EventQueue;  
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import componentes.JDialogGenerico;
import componentes.JtextPersonalizado;
import py.edu.facitec.Simpres2.Controller.HabitacionController;


public class VentanaHabitacion extends JDialogGenerico {
    private JtextPersonalizado tfTipo;
    private JtextPersonalizado tfNumero;
    private JtextPersonalizado tfPrecio;
    private JtextPersonalizado tfObservaciones;
    private JtextPersonalizado tfestadoh;
    private JLabel lblTipo;
    private JLabel lblNumero;
    private JLabel lblPrecio;
    private JLabel lblObservaciones;
    private JLabel lblEstado;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    VentanaHabitacion dialog = new VentanaHabitacion();
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
        new HabitacionController(this);
    }

    public VentanaHabitacion() {

        getBtnCancelar().setBounds(272, 459, 113, 61);
        getBtnGuardar().setBounds(23, 459, 113, 61);
        
        setTitle("Registro de Habitacion");
        getLblTitulo().setText("Registro de Habitacion");
        getPanelformulario().setLayout(null);
       
        lblTipo = new JLabel("Tipo:");
        lblTipo.setHorizontalAlignment(SwingConstants.RIGHT);
        lblTipo.setBounds(21, 89, 69, 14);
        getPanelformulario().add(lblTipo);

        lblNumero = new JLabel("Numero:");
        lblNumero.setHorizontalAlignment(SwingConstants.RIGHT);
        lblNumero.setBounds(21, 125, 69, 14);
        getPanelformulario().add(lblNumero);

        lblPrecio = new JLabel("Precio:");
        lblPrecio.setHorizontalAlignment(SwingConstants.RIGHT);
        lblPrecio.setBounds(21, 150, 69, 14);
        getPanelformulario().add(lblPrecio);

        lblObservaciones = new JLabel("Observaciones:");
        lblObservaciones.setHorizontalAlignment(SwingConstants.RIGHT);
        lblObservaciones.setBounds(21, 189, 90, 14);
        getPanelformulario().add(lblObservaciones);

        lblEstado = new JLabel("Estado:");
        lblEstado.setHorizontalAlignment(SwingConstants.RIGHT);
        lblEstado.setBounds(21, 224, 69, 14);
        getPanelformulario().add(lblEstado);

        tfTipo = new JtextPersonalizado();
        tfTipo.setBounds(100, 85, 160, 21);
        getPanelformulario().add(tfTipo);

        tfNumero = new JtextPersonalizado();
        tfNumero.setBounds(100, 121, 160, 21);
        getPanelformulario().add(tfNumero);

        tfPrecio = new JtextPersonalizado();
        tfPrecio.setBounds(100, 150, 98, 21);
        getPanelformulario().add(tfPrecio);

        tfObservaciones = new JtextPersonalizado();
        tfObservaciones.setBounds(120, 185, 160, 21);
        getPanelformulario().add(tfObservaciones);
        
        tfestadoh = new JtextPersonalizado();
        tfestadoh.setBounds(100, 220, 113, 18);
        getPanelformulario().add(tfestadoh);
        
    }



	public JtextPersonalizado getTfestadoh() {
		return tfestadoh;
	}

	public void setTfestadoh(JtextPersonalizado tfestadoh) {
		this.tfestadoh = tfestadoh;
	}

	public JtextPersonalizado getTfTipo() {
		return tfTipo;
	}

	public void setTfTipo(JtextPersonalizado tfTipo) {
		this.tfTipo = tfTipo;
	}

	public JtextPersonalizado getTfNumero() {
		return tfNumero;
	}

	public void setTfNumero(JtextPersonalizado tfNumero) {
		this.tfNumero = tfNumero;
	}

	public JtextPersonalizado getTfPrecio() {
		return tfPrecio;
	}

	public void setTfPrecio(JtextPersonalizado tfPrecio) {
		this.tfPrecio = tfPrecio;
	}

	public JtextPersonalizado getTfObservaciones() {
		return tfObservaciones;
	}

	public void setTfObservaciones(JtextPersonalizado tfObservaciones) {
		this.tfObservaciones = tfObservaciones;
	}

	public JLabel getLblTipo() {
		return lblTipo;
	}

	public void setLblTipo(JLabel lblTipo) {
		this.lblTipo = lblTipo;
	}

	public JLabel getLblNumero() {
		return lblNumero;
	}

	public void setLblNumero(JLabel lblNumero) {
		this.lblNumero = lblNumero;
	}

	public JLabel getLblPrecio() {
		return lblPrecio;
	}

	public void setLblPrecio(JLabel lblPrecio) {
		this.lblPrecio = lblPrecio;
	}

	public JLabel getLblObservaciones() {
		return lblObservaciones;
	}

	public void setLblObservaciones(JLabel lblObservaciones) {
		this.lblObservaciones = lblObservaciones;
	}

	public JLabel getLblEstado() {
		return lblEstado;
	}

	public void setLblEstado(JLabel lblEstado) {
		this.lblEstado = lblEstado;
	}
}
