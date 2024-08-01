package componentes;

import javax.swing.JDialog;  

import javax.swing.JToolBar;

import py.edu.facitec.Simpres2.Interfaces.AccionesABM;

import javax.swing.JPanel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.Color;

public class JDialogGenerico extends JDialog implements ActionListener {

    private BotonesJtoollbarPersonalizado btnNuevo;
    private BotonesJtoollbarPersonalizado btnModificar;
    private BotonesJtoollbarPersonalizado btnEliminar;
    private BotonesJtoollbarPersonalizado btnSalir;
    private JPanel panelformulario;
    private JTable table;
    private BotonesJtoollbarPersonalizado btnGuardar;
    private BotonesJtoollbarPersonalizado btnCancelar;
    private JLabel lblTitulo;
    private JtextPersonalizado tfbuscador;
    private AccionesABM accionesABM;
    
    public void setAcciones(AccionesABM accionesABM) {
        this.accionesABM = accionesABM;
    }

    /**
     * Create the dialog.
     */
    public JDialogGenerico() {

        setBounds(100, 100, 800, 600);
        setLocationRelativeTo(this);
        setModal(true);
        getContentPane().setLayout(null);

        JToolBar toolBar = new JToolBar();
        toolBar.setFloatable(false);
        toolBar.setBounds(23, 11, 402, 61);
        getContentPane().add(toolBar);

        btnNuevo = new BotonesJtoollbarPersonalizado();
        btnNuevo.setText("Nuevo");
        toolBar.add(btnNuevo);

        btnModificar = new BotonesJtoollbarPersonalizado();
        btnModificar.setText("Modificar");
        toolBar.add(btnModificar);

        btnEliminar = new BotonesJtoollbarPersonalizado();
        btnEliminar.setText("Eliminar");
        toolBar.add(btnEliminar);

        btnSalir = new BotonesJtoollbarPersonalizado();
        btnSalir.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        btnSalir.setText("Salir");
        toolBar.add(btnSalir);
        panelformulario = new JPanel();
        panelformulario.setBounds(15, 107, 370, 351);
        getContentPane().add(panelformulario);
        panelformulario.setLayout(null);
        

        JLabel lblBuscar = new JLabel("Buscar");
        lblBuscar.setBounds(403, 82, 46, 14);
        getContentPane().add(lblBuscar);

        btnGuardar = new BotonesJtoollbarPersonalizado();
        btnGuardar.setBounds(23, 459, 101, 61);
        getContentPane().add(btnGuardar);
        btnGuardar.setText("Guardar");

        btnCancelar = new BotonesJtoollbarPersonalizado();
        btnCancelar.setBounds(280, 459, 105, 61);
        getContentPane().add(btnCancelar);
        btnCancelar.setText("Cancelar");

        tfbuscador = new JtextPersonalizado();
        tfbuscador.setBounds(448, 78, 300, 21);
        getContentPane().add(tfbuscador);
        
        lblTitulo = new JLabel("Titulo");

        lblTitulo.setForeground(Color.BLUE);
        lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Tahoma", Font.PLAIN, 17));
        lblTitulo.setBounds(424, 25, 358, 40);
        getContentPane().add(lblTitulo);
        
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(403, 107, 345, 348);
        getContentPane().add(scrollPane);
        

        table = new JTable();
        scrollPane.setViewportView(table);

        // Establecer eventos
        setearEventos();
    }

    public JLabel getLblTitulo() {
		return lblTitulo;
	}

	public void setLblTitulo(JLabel lblTitulo) {
		this.lblTitulo = lblTitulo;
	}

	public void setearEventos() {
        btnModificar.addActionListener(this);
        btnEliminar.addActionListener(this);
        btnGuardar.addActionListener(this);
        btnCancelar.addActionListener(this);
        btnSalir.addActionListener(this);
        btnNuevo.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "Nuevo":
                accionesABM.nuevo();
                break;
            case "Salir":
                accionesABM.salir();
                break;
            case "Modificar":
                accionesABM.modificar();
                break;
            case "Eliminar":
                accionesABM.eliminar();
                break;
            case "Actualizar":
                accionesABM.actualizar();
                break;
            case "Cancelar":
                accionesABM.cancelar();
                break;
            case "Guardar":
                accionesABM.guardar();
                break;
            
            default:
        }
    }

    public AccionesABM getAccionesABM() {
        return accionesABM;
    }

    public void setAccionesABM(AccionesABM accionesABM) {
        this.accionesABM = accionesABM;
    }

    public BotonesJtoollbarPersonalizado getBtnNuevo() {
        return btnNuevo;
    }

    public void setBtnNuevo(BotonesJtoollbarPersonalizado btnNuevo) {
        this.btnNuevo = btnNuevo;
    }

    public BotonesJtoollbarPersonalizado getBtnModificar() {
        return btnModificar;
    }

    public void setBtnModificar(BotonesJtoollbarPersonalizado btnModificar) {
        this.btnModificar = btnModificar;
    }

    public BotonesJtoollbarPersonalizado getBtnEliminar() {
        return btnEliminar;
    }

    public void setBtnEliminar(BotonesJtoollbarPersonalizado btnEliminar) {
        this.btnEliminar = btnEliminar;
    }

    public BotonesJtoollbarPersonalizado getBtnSalir() {
        return btnSalir;
    }

    public void setBtnSalir(BotonesJtoollbarPersonalizado btnSalir) {
        this.btnSalir = btnSalir;
    }

    public JPanel getPanelformulario() {
        return panelformulario;
    }

    public void setPanelformulario(JPanel panelformulario) {
        this.panelformulario = panelformulario;
    }

    public BotonesJtoollbarPersonalizado getBtnGuardar() {
        return btnGuardar;
    }

    public void setBtnGuardar(BotonesJtoollbarPersonalizado btnGuardar) {
        this.btnGuardar = btnGuardar;
    }

    public BotonesJtoollbarPersonalizado getBtnCancelar() {
        return btnCancelar;
    }

    public void setBtnCancelar(BotonesJtoollbarPersonalizado btnCancelar) {
        this.btnCancelar = btnCancelar;
    }

    public JtextPersonalizado getTfbuscador() {
        return tfbuscador;
    }

    public void setTfbuscador(JtextPersonalizado tfbuscador) {
        this.tfbuscador = tfbuscador;
    }

    public JTable getTable() {
        return table;
    }

    public void setTable(JTable table) {
        this.table = table;
    }
}
