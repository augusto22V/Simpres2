package py.edu.facitec.Simpres2.vista;

import java.awt.EventQueue;    
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JTextField;
import componentes.UtilidadesFecha;
import py.edu.facitec.Simpres2.Controller.ReservaController;
import py.edu.facitec.Simpres2.entidades.Reservas;
import javax.swing.SwingConstants;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.SpringLayout;
import javax.swing.JSeparator;
import componentes.BotonesJtoollbarPersonalizado;
 
public class VentanaReserva extends JDialog {
    private JFormattedTextField tfFechaIngreso;
    private JFormattedTextField tfFechaSalida;
    private JTextField tfCantPersonas;

    private Reservas reserva;
	private JLabel lblNombre;
	private JLabel lblCliente;
	private JLabel lblTelefono;
	private JTextField tfdocumento;
	private JTextField tfNombre;
	private JTextField tfTelefono;
	private JLabel lblNHabitacin;
	private JTextField tfnroHab;
	private JTable table;
	private JSeparator separator;
	private JScrollPane scrollPane;
	private BotonesJtoollbarPersonalizado btnAgregar;
	private BotonesJtoollbarPersonalizado btnAnular;
	private JLabel lblNewLabel;
	private JButton btnBuscarCliente;
	private BotonesJtoollbarPersonalizado btnNuevo;
	private JButton btnbuscarNroH;
	private JLabel lblFuncionario;
	private JTextField tfdocumentoF;
	private JButton btnBuscarFunc;
	private JLabel lbtelefonoF;
	private JTextField tftelefonoF;
	private JLabel lbnombreF;
	private JTextField tfnombreF;
	private BotonesJtoollbarPersonalizado btnSalir;
	private BotonesJtoollbarPersonalizado btnquitar;
	private JTextField tfFecha;
	private BotonesJtoollbarPersonalizado btnguardar;
	private JLabel lblCantDias;
	private JTextField tfCantDias;
	private JLabel lblPrecio;
	private JLabel lblCostoTotal;
	private JButton btnbuscarServicio;
	private JTextField tfprecioservicio;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    VentanaReserva dialog = new VentanaReserva();
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
		new ReservaController(this);
	}
    public VentanaReserva() {
        setTitle("Reservas");
        setBounds(100, 100, 1000, 700);
        SpringLayout springLayout = new SpringLayout();
        getContentPane().setLayout(springLayout);
        
   
        lblNombre = new JLabel("Nombre");
		lblNombre.setHorizontalAlignment(SwingConstants.RIGHT);
		getContentPane().add(lblNombre);
		
		lblCliente = new JLabel("Cliente");
		springLayout.putConstraint(SpringLayout.NORTH, lblNombre, 0, SpringLayout.NORTH, lblCliente);
		springLayout.putConstraint(SpringLayout.WEST, lblCliente, 24, SpringLayout.WEST, getContentPane());
		lblCliente.setHorizontalAlignment(SwingConstants.RIGHT);
		getContentPane().add(lblCliente);
		
		lblTelefono = new JLabel("Telefono:");
		springLayout.putConstraint(SpringLayout.NORTH, lblTelefono, 0, SpringLayout.NORTH, lblNombre);
		springLayout.putConstraint(SpringLayout.EAST, lblTelefono, -401, SpringLayout.EAST, getContentPane());
		lblTelefono.setHorizontalAlignment(SwingConstants.RIGHT);
		
		getContentPane().add(lblTelefono);
		
        JLabel lblFechaIngreso = new JLabel("Fecha de Ingreso:");
        springLayout.putConstraint(SpringLayout.SOUTH, lblFechaIngreso, -485, SpringLayout.SOUTH, getContentPane());
        springLayout.putConstraint(SpringLayout.EAST, lblFechaIngreso, -874, SpringLayout.EAST, getContentPane());
        lblFechaIngreso.setHorizontalAlignment(SwingConstants.RIGHT);
        getContentPane().add(lblFechaIngreso);

        tfFechaIngreso = new JFormattedTextField(UtilidadesFecha.getFormato());
        springLayout.putConstraint(SpringLayout.NORTH, tfFechaIngreso, -3, SpringLayout.NORTH, lblFechaIngreso);
        springLayout.putConstraint(SpringLayout.WEST, tfFechaIngreso, 6, SpringLayout.EAST, lblFechaIngreso);
        springLayout.putConstraint(SpringLayout.EAST, tfFechaIngreso, -799, SpringLayout.EAST, getContentPane());
        getContentPane().add(tfFechaIngreso);
        tfFechaIngreso.setColumns(10);

        JLabel lblFechaSalida = new JLabel("Fecha de Salida:");
        springLayout.putConstraint(SpringLayout.NORTH, lblFechaSalida, 0, SpringLayout.NORTH, lblFechaIngreso);
        springLayout.putConstraint(SpringLayout.WEST, lblFechaSalida, 83, SpringLayout.EAST, tfFechaIngreso);
        springLayout.putConstraint(SpringLayout.EAST, lblFechaSalida, -616, SpringLayout.EAST, getContentPane());
        lblFechaSalida.setHorizontalAlignment(SwingConstants.RIGHT);
        getContentPane().add(lblFechaSalida);

        tfFechaSalida = new JFormattedTextField(UtilidadesFecha.getFormato());
        springLayout.putConstraint(SpringLayout.NORTH, tfFechaSalida, -3, SpringLayout.NORTH, lblFechaIngreso);
        springLayout.putConstraint(SpringLayout.WEST, tfFechaSalida, 12, SpringLayout.EAST, lblFechaSalida);
        springLayout.putConstraint(SpringLayout.EAST, tfFechaSalida, -540, SpringLayout.EAST, getContentPane());
        getContentPane().add(tfFechaSalida);
        tfFechaSalida.setColumns(10);

        JLabel lblCantidadPersonas = new JLabel("Cant. Personas:");
        springLayout.putConstraint(SpringLayout.NORTH, lblCantidadPersonas, 0, SpringLayout.NORTH, lblFechaIngreso);
        springLayout.putConstraint(SpringLayout.WEST, lblCantidadPersonas, 24, SpringLayout.EAST, tfFechaSalida);
        springLayout.putConstraint(SpringLayout.EAST, lblCantidadPersonas, -389, SpringLayout.EAST, getContentPane());
        lblCantidadPersonas.setHorizontalAlignment(SwingConstants.RIGHT);
        getContentPane().add(lblCantidadPersonas);

        tfCantPersonas = new JTextField();
        springLayout.putConstraint(SpringLayout.NORTH, tfCantPersonas, -3, SpringLayout.NORTH, lblFechaIngreso);
        springLayout.putConstraint(SpringLayout.WEST, tfCantPersonas, 6, SpringLayout.EAST, lblCantidadPersonas);
        springLayout.putConstraint(SpringLayout.EAST, tfCantPersonas, -331, SpringLayout.EAST, getContentPane());
        getContentPane().add(tfCantPersonas);
        tfCantPersonas.setColumns(10);
        
        JLabel titulo = new JLabel("RESERVACIONES");
        springLayout.putConstraint(SpringLayout.NORTH, titulo, 10, SpringLayout.NORTH, getContentPane());
        springLayout.putConstraint(SpringLayout.SOUTH, titulo, 24, SpringLayout.NORTH, getContentPane());
        springLayout.putConstraint(SpringLayout.EAST, titulo, -401, SpringLayout.EAST, getContentPane());
        titulo.setForeground(new Color(0, 0, 139));
        titulo.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        titulo.setHorizontalAlignment(SwingConstants.RIGHT);
        getContentPane().add(titulo);
        
        tfdocumento = new JTextField();
        springLayout.putConstraint(SpringLayout.NORTH, tfdocumento, -3, SpringLayout.NORTH, lblCliente);
        springLayout.putConstraint(SpringLayout.WEST, tfdocumento, 6, SpringLayout.EAST, lblCliente);
        getContentPane().add(tfdocumento);
        tfdocumento.setColumns(10);
        
        tfNombre = new JTextField();
        springLayout.putConstraint(SpringLayout.WEST, lblTelefono, 30, SpringLayout.EAST, tfNombre);
        springLayout.putConstraint(SpringLayout.NORTH, tfNombre, -3, SpringLayout.NORTH, lblNombre);
        springLayout.putConstraint(SpringLayout.WEST, tfNombre, 306, SpringLayout.WEST, getContentPane());
        tfNombre.setEditable(false);
        tfNombre.setColumns(10);
        getContentPane().add(tfNombre);
        
        tfTelefono = new JTextField();
        springLayout.putConstraint(SpringLayout.NORTH, tfTelefono, -3, SpringLayout.NORTH, lblNombre);
        springLayout.putConstraint(SpringLayout.WEST, tfTelefono, 6, SpringLayout.EAST, lblTelefono);
        tfTelefono.setEditable(false);
        tfTelefono.setColumns(10);
        getContentPane().add(tfTelefono);
        
        lblNHabitacin = new JLabel("N\u00B0 Habitaci\u00F3n");
        springLayout.putConstraint(SpringLayout.WEST, lblFechaIngreso, 0, SpringLayout.WEST, lblNHabitacin);
        springLayout.putConstraint(SpringLayout.WEST, lblNHabitacin, 10, SpringLayout.WEST, getContentPane());
        springLayout.putConstraint(SpringLayout.SOUTH, lblNHabitacin, -436, SpringLayout.SOUTH, getContentPane());
        springLayout.putConstraint(SpringLayout.EAST, lblNHabitacin, -895, SpringLayout.EAST, getContentPane());
        lblNHabitacin.setHorizontalAlignment(SwingConstants.RIGHT);
        getContentPane().add(lblNHabitacin);
        
        tfnroHab = new JTextField();
        springLayout.putConstraint(SpringLayout.EAST, tfdocumento, 0, SpringLayout.EAST, tfnroHab);
        springLayout.putConstraint(SpringLayout.WEST, tfnroHab, 6, SpringLayout.EAST, lblNHabitacin);
        springLayout.putConstraint(SpringLayout.NORTH, tfnroHab, -3, SpringLayout.NORTH, lblNHabitacin);
        tfnroHab.setColumns(10);
        getContentPane().add(tfnroHab);
        
        scrollPane = new JScrollPane();
        springLayout.putConstraint(SpringLayout.NORTH, scrollPane, 246, SpringLayout.NORTH, getContentPane());
        springLayout.putConstraint(SpringLayout.WEST, scrollPane, 10, SpringLayout.WEST, getContentPane());
        springLayout.putConstraint(SpringLayout.EAST, scrollPane, -157, SpringLayout.EAST, getContentPane());
        getContentPane().add(scrollPane);
        
        table = new JTable();
        scrollPane.setViewportView(table);
        
        separator = new JSeparator();
        springLayout.putConstraint(SpringLayout.NORTH, separator, 182, SpringLayout.NORTH, getContentPane());
        springLayout.putConstraint(SpringLayout.WEST, separator, 0, SpringLayout.WEST, getContentPane());
        springLayout.putConstraint(SpringLayout.EAST, separator, 984, SpringLayout.WEST, getContentPane());
        getContentPane().add(separator);
        
        btnAgregar = new BotonesJtoollbarPersonalizado();
        springLayout.putConstraint(SpringLayout.NORTH, btnAgregar, 0, SpringLayout.NORTH, scrollPane);
        springLayout.putConstraint(SpringLayout.WEST, btnAgregar, 6, SpringLayout.EAST, scrollPane);
        springLayout.putConstraint(SpringLayout.EAST, btnAgregar, -35, SpringLayout.EAST, getContentPane());
        btnAgregar.setForeground(Color.BLUE);
        btnAgregar.setText("Agregar");
        getContentPane().add(btnAgregar);
        
        btnAnular = new BotonesJtoollbarPersonalizado();
        springLayout.putConstraint(SpringLayout.WEST, btnAnular, 6, SpringLayout.EAST, scrollPane);
        springLayout.putConstraint(SpringLayout.SOUTH, btnAnular, -146, SpringLayout.SOUTH, getContentPane());
        springLayout.putConstraint(SpringLayout.EAST, btnAnular, 0, SpringLayout.EAST, btnAgregar);
        btnAnular.setForeground(Color.BLUE);
        btnAnular.setText("Anular");
        getContentPane().add(btnAnular);
        
        lblNewLabel = new JLabel((String) null);
        springLayout.putConstraint(SpringLayout.NORTH, lblNewLabel, 44, SpringLayout.NORTH, getContentPane());
        springLayout.putConstraint(SpringLayout.WEST, lblNewLabel, 192, SpringLayout.WEST, getContentPane());
        getContentPane().add(lblNewLabel);
        
        btnBuscarCliente = new JButton("...");
        springLayout.putConstraint(SpringLayout.WEST, lblNombre, 37, SpringLayout.EAST, btnBuscarCliente);
        springLayout.putConstraint(SpringLayout.NORTH, btnBuscarCliente, -4, SpringLayout.NORTH, lblCliente);
        getContentPane().add(btnBuscarCliente);
        
        btnNuevo = new BotonesJtoollbarPersonalizado();
        springLayout.putConstraint(SpringLayout.SOUTH, scrollPane, -6, SpringLayout.NORTH, btnNuevo);
        springLayout.putConstraint(SpringLayout.NORTH, btnNuevo, 549, SpringLayout.NORTH, getContentPane());
        btnNuevo.setText("Nuevo");
        getContentPane().add(btnNuevo);
        
        btnbuscarNroH = new JButton("...");
        springLayout.putConstraint(SpringLayout.WEST, btnbuscarNroH, 153, SpringLayout.WEST, getContentPane());
        springLayout.putConstraint(SpringLayout.EAST, tfnroHab, -6, SpringLayout.WEST, btnbuscarNroH);
        springLayout.putConstraint(SpringLayout.SOUTH, separator, -23, SpringLayout.NORTH, btnbuscarNroH);
        springLayout.putConstraint(SpringLayout.NORTH, btnbuscarNroH, -4, SpringLayout.NORTH, lblNHabitacin);
        getContentPane().add(btnbuscarNroH);
        
        lblFuncionario = new JLabel("Funcionario");
        springLayout.putConstraint(SpringLayout.EAST, lblCliente, 0, SpringLayout.EAST, lblFuncionario);
        springLayout.putConstraint(SpringLayout.EAST, lblFuncionario, -915, SpringLayout.EAST, getContentPane());
        springLayout.putConstraint(SpringLayout.SOUTH, lblFuncionario, -26, SpringLayout.NORTH, lblFechaIngreso);
        lblFuncionario.setHorizontalAlignment(SwingConstants.RIGHT);
        getContentPane().add(lblFuncionario);
        
        tfdocumentoF = new JTextField();
        springLayout.putConstraint(SpringLayout.NORTH, tfdocumentoF, -3, SpringLayout.NORTH, lblFuncionario);
        springLayout.putConstraint(SpringLayout.WEST, tfdocumentoF, 6, SpringLayout.EAST, lblFuncionario);
        springLayout.putConstraint(SpringLayout.EAST, tfdocumentoF, -837, SpringLayout.EAST, getContentPane());
        tfdocumentoF.setColumns(10);
        getContentPane().add(tfdocumentoF);
        
        btnBuscarFunc = new JButton("...");
        springLayout.putConstraint(SpringLayout.EAST, btnBuscarCliente, 0, SpringLayout.EAST, btnBuscarFunc);
        springLayout.putConstraint(SpringLayout.WEST, btnBuscarFunc, 18, SpringLayout.EAST, tfdocumentoF);
        springLayout.putConstraint(SpringLayout.NORTH, btnBuscarFunc, -4, SpringLayout.NORTH, lblFuncionario);
        getContentPane().add(btnBuscarFunc);
        
        lbtelefonoF = new JLabel("Telefono:");
        springLayout.putConstraint(SpringLayout.EAST, lbtelefonoF, -406, SpringLayout.EAST, getContentPane());
        springLayout.putConstraint(SpringLayout.NORTH, lbtelefonoF, 0, SpringLayout.NORTH, lblFuncionario);
        lbtelefonoF.setHorizontalAlignment(SwingConstants.RIGHT);
        getContentPane().add(lbtelefonoF);
        
        tftelefonoF = new JTextField();
        springLayout.putConstraint(SpringLayout.NORTH, tftelefonoF, -3, SpringLayout.NORTH, lblFuncionario);
        springLayout.putConstraint(SpringLayout.WEST, tftelefonoF, 11, SpringLayout.EAST, lbtelefonoF);
        tftelefonoF.setEditable(false);
        tftelefonoF.setColumns(10);
        getContentPane().add(tftelefonoF);
        
        lbnombreF = new JLabel("Nombre");
        springLayout.putConstraint(SpringLayout.EAST, lblNombre, 0, SpringLayout.EAST, lbnombreF);
        springLayout.putConstraint(SpringLayout.NORTH, lbnombreF, 0, SpringLayout.NORTH, lblFuncionario);
        springLayout.putConstraint(SpringLayout.EAST, lbnombreF, -692, SpringLayout.EAST, getContentPane());
        lbnombreF.setHorizontalAlignment(SwingConstants.RIGHT);
        getContentPane().add(lbnombreF);
        
        tfnombreF = new JTextField();
        springLayout.putConstraint(SpringLayout.WEST, tfnombreF, 15, SpringLayout.EAST, lbnombreF);
        springLayout.putConstraint(SpringLayout.EAST, tfnombreF, -500, SpringLayout.EAST, getContentPane());
        springLayout.putConstraint(SpringLayout.EAST, tfNombre, 0, SpringLayout.EAST, tfnombreF);
        springLayout.putConstraint(SpringLayout.WEST, lbtelefonoF, 30, SpringLayout.EAST, tfnombreF);
        springLayout.putConstraint(SpringLayout.NORTH, tfnombreF, -3, SpringLayout.NORTH, lblFuncionario);
        tfnombreF.setFont(tfNombre.getFont());
        tfnombreF.setForeground(tfNombre.getForeground());
        tfnombreF.setBackground(tfNombre.getBackground());        tfnombreF.setEditable(false);
        tfnombreF.setColumns(10);
        getContentPane().add(tfnombreF);
        
        btnSalir = new BotonesJtoollbarPersonalizado();
        springLayout.putConstraint(SpringLayout.NORTH, btnSalir, 549, SpringLayout.NORTH, getContentPane());
        springLayout.putConstraint(SpringLayout.WEST, btnSalir, 716, SpringLayout.WEST, getContentPane());
        springLayout.putConstraint(SpringLayout.EAST, btnSalir, -152, SpringLayout.EAST, getContentPane());
        btnSalir.setText("Salir");
        getContentPane().add(btnSalir);
        
        btnquitar = new BotonesJtoollbarPersonalizado();
        springLayout.putConstraint(SpringLayout.NORTH, btnquitar, 48, SpringLayout.SOUTH, btnAgregar);
        springLayout.putConstraint(SpringLayout.SOUTH, btnquitar, -245, SpringLayout.SOUTH, getContentPane());
        springLayout.putConstraint(SpringLayout.NORTH, btnAnular, 38, SpringLayout.SOUTH, btnquitar);
        springLayout.putConstraint(SpringLayout.WEST, btnquitar, 6, SpringLayout.EAST, scrollPane);
        springLayout.putConstraint(SpringLayout.EAST, btnquitar, 0, SpringLayout.EAST, btnAgregar);
        btnquitar.setForeground(Color.BLUE);
        btnquitar.setText("Quitar");
        getContentPane().add(btnquitar);
        
        tfFecha = new JFormattedTextField(UtilidadesFecha.getFormato());
        springLayout.putConstraint(SpringLayout.WEST, titulo, 228, SpringLayout.EAST, tfFecha);
        springLayout.putConstraint(SpringLayout.NORTH, tfFecha, 10, SpringLayout.NORTH, getContentPane());
        tfFecha.setEditable(false);
        getContentPane().add(tfFecha);
        tfFecha.setColumns(10);
        
        JLabel lblFecha = new JLabel("Fecha");
        springLayout.putConstraint(SpringLayout.NORTH, lblCliente, 39, SpringLayout.SOUTH, lblFecha);
        springLayout.putConstraint(SpringLayout.NORTH, lblFecha, 12, SpringLayout.NORTH, getContentPane());
        springLayout.putConstraint(SpringLayout.EAST, lblFecha, -929, SpringLayout.EAST, getContentPane());
        springLayout.putConstraint(SpringLayout.WEST, tfFecha, 20, SpringLayout.EAST, lblFecha);
        lblFecha.setHorizontalAlignment(SwingConstants.RIGHT);
        getContentPane().add(lblFecha);
        
        btnguardar = new BotonesJtoollbarPersonalizado();
        springLayout.putConstraint(SpringLayout.NORTH, btnguardar, 6, SpringLayout.SOUTH, scrollPane);
        springLayout.putConstraint(SpringLayout.EAST, btnNuevo, -25, SpringLayout.WEST, btnguardar);
        springLayout.putConstraint(SpringLayout.EAST, btnguardar, -43, SpringLayout.WEST, btnSalir);
        btnguardar.setText("Guardar");
        getContentPane().add(btnguardar);
        
        lblCantDias = new JLabel("Cant. dias:");
        springLayout.putConstraint(SpringLayout.NORTH, lblCantDias, 0, SpringLayout.NORTH, lblNHabitacin);
        lblCantDias.setHorizontalAlignment(SwingConstants.RIGHT);
        getContentPane().add(lblCantDias);
        
        tfCantDias = new JTextField();
        springLayout.putConstraint(SpringLayout.WEST, tfCantDias, 307, SpringLayout.WEST, getContentPane());
        springLayout.putConstraint(SpringLayout.EAST, lblCantDias, -15, SpringLayout.WEST, tfCantDias);
        springLayout.putConstraint(SpringLayout.NORTH, tfCantDias, -3, SpringLayout.NORTH, lblNHabitacin);
        tfCantDias.setColumns(10);
        getContentPane().add(tfCantDias);
        
        lblPrecio = new JLabel("Servicio Precio");
        springLayout.putConstraint(SpringLayout.WEST, lblPrecio, 456, SpringLayout.WEST, getContentPane());
        springLayout.putConstraint(SpringLayout.EAST, tfCantDias, -85, SpringLayout.WEST, lblPrecio);
        springLayout.putConstraint(SpringLayout.NORTH, lblPrecio, 0, SpringLayout.NORTH, lblNHabitacin);
        lblPrecio.setHorizontalAlignment(SwingConstants.RIGHT);
        getContentPane().add(lblPrecio);
        
        JLabel JlableTotal = new JLabel("Costo Total:");
        springLayout.putConstraint(SpringLayout.NORTH, JlableTotal, 6, SpringLayout.SOUTH, scrollPane);
        springLayout.putConstraint(SpringLayout.WEST, JlableTotal, 10, SpringLayout.WEST, getContentPane());
        JlableTotal.setForeground(Color.BLUE);
        springLayout.putConstraint(SpringLayout.WEST, btnNuevo, 299, SpringLayout.EAST, JlableTotal);
        JlableTotal.setHorizontalAlignment(SwingConstants.RIGHT);
        JlableTotal.setFont(new Font("Times New Roman", Font.PLAIN, 25));
        getContentPane().add(JlableTotal);
        
        lblCostoTotal = new JLabel("0.0");
        springLayout.putConstraint(SpringLayout.WEST, lblCostoTotal, 10, SpringLayout.WEST, getContentPane());
        springLayout.putConstraint(SpringLayout.SOUTH, lblCostoTotal, 0, SpringLayout.SOUTH, btnNuevo);
        springLayout.putConstraint(SpringLayout.EAST, lblCostoTotal, -295, SpringLayout.WEST, btnNuevo);
        lblCostoTotal.setForeground(Color.BLACK);
        lblCostoTotal.setHorizontalAlignment(SwingConstants.CENTER);
        lblCostoTotal.setFont(new Font("Tahoma", Font.PLAIN, 25));
        getContentPane().add(lblCostoTotal);
        
        btnbuscarServicio = new JButton("...");
        springLayout.putConstraint(SpringLayout.NORTH, btnbuscarServicio, -4, SpringLayout.NORTH, lblNHabitacin);
        getContentPane().add(btnbuscarServicio);
        
        tfprecioservicio = new JTextField();
        springLayout.putConstraint(SpringLayout.WEST, btnbuscarServicio, 6, SpringLayout.EAST, tfprecioservicio);
        springLayout.putConstraint(SpringLayout.NORTH, tfprecioservicio, -3, SpringLayout.NORTH, lblNHabitacin);
        springLayout.putConstraint(SpringLayout.WEST, tfprecioservicio, 6, SpringLayout.EAST, lblPrecio);
        tfprecioservicio.setColumns(10);
        getContentPane().add(tfprecioservicio);
    }
    
	public JLabel getLblCantDias() {
		return lblCantDias;
	}
	public void setLblCantDias(JLabel lblCantDias) {
		this.lblCantDias = lblCantDias;
	}
	public JTextField getTfCantDias() {
		return tfCantDias;
	}
	public void setTfCantDias(JTextField tfCantDias) {
		this.tfCantDias = tfCantDias;
	}
	
	public JLabel getLblPrecio() {
		return lblPrecio;
	}
	public void setLblPrecio(JLabel lblPrecio) {
		this.lblPrecio = lblPrecio;
	}
	public JTextField getTfprecioservicio() {
		return tfprecioservicio;
	}
	public void setTfprecioservicio(JTextField tfprecioservicio) {
		this.tfprecioservicio = tfprecioservicio;
	}
	public BotonesJtoollbarPersonalizado getBtnAnular() {
		return btnAnular;
	}
	public void setBtnAnular(BotonesJtoollbarPersonalizado btnAnular) {
		this.btnAnular = btnAnular;
	}
	public JLabel getLblFuncionario() {
		return lblFuncionario;
	}
	public void setLblFuncionario(JLabel lblFuncionario) {
		this.lblFuncionario = lblFuncionario;
	}
	public JTextField getTfdocumentoF() {
		return tfdocumentoF;
	}
	public void setTfdocumentoF(JTextField tfdocumentoF) {
		this.tfdocumentoF = tfdocumentoF;
	}
	public JButton getBtnBuscarFunc() {
		return btnBuscarFunc;
	}
	public void setBtnBuscarFunc(JButton btnBuscarFunc) {
		this.btnBuscarFunc = btnBuscarFunc;
	}
	
	public JLabel getLbtelefonoF() {
		return lbtelefonoF;
	}
	public void setLbtelefonoF(JLabel lbtelefonoF) {
		this.lbtelefonoF = lbtelefonoF;
	}
	public JTextField getTftelefonoF() {
		return tftelefonoF;
	}
	public void setTftelefonoF(JTextField tftelefonoF) {
		this.tftelefonoF = tftelefonoF;
	}
	public JFormattedTextField getTfFechaIngreso() {
		return tfFechaIngreso;
	}
	public void setTfFechaIngreso(JFormattedTextField tfFechaIngreso) {
		this.tfFechaIngreso = tfFechaIngreso;
	}
	public JFormattedTextField getTfFechaSalida() {
		return tfFechaSalida;
	}
	public void setTfFechaSalida(JFormattedTextField tfFechaSalida) {
		this.tfFechaSalida = tfFechaSalida;
	}
	public JTextField getTfCantPersonas() {
		return tfCantPersonas;
	}
	public void setTfCantPersonas(JTextField tfCantPersonas) {
		this.tfCantPersonas = tfCantPersonas;
	}

	public Reservas getReserva() {
		return reserva;
	}
	public void setReserva(Reservas reserva) {
		this.reserva = reserva;
	}
	public JLabel getLblNombre() {
		return lblNombre;
	}
	public void setLblNombre(JLabel lblNombre) {
		this.lblNombre = lblNombre;
	}
	
	public JLabel getLblCliente() {
		return lblCliente;
	}
	public void setLblCliente(JLabel lblCliente) {
		this.lblCliente = lblCliente;
	}
	public JLabel getLblTelefono() {
		return lblTelefono;
	}
	public void setLblTelefono(JLabel lblTelefono) {
		this.lblTelefono = lblTelefono;
	}
	public JTextField getTfCliente() {
		return tfdocumento;
	}
	public void setTfCliente(JTextField tfCliente) {
		this.tfdocumento = tfCliente;
	}
	public JTextField getTFuncionario() {
		return tfdocumentoF;
	}
	public void setTFuncionario(JTextField tfFuncionario) {
		this.tfdocumento = tfFuncionario;
	}
	public JTextField getTfNombre() {
		return tfNombre;
	}
	public void setTfNombre(JTextField tfNombre) {
		this.tfNombre = tfNombre;
	}
	
	public JTextField getTfTelefono() {
		return tfTelefono;
	}
	public void setTfTelefono(JTextField tfTelefono) {
		this.tfTelefono = tfTelefono;
	}
	public JLabel getLblNHabitacin() {
		return lblNHabitacin;
	}
	public void setLblNHabitacin(JLabel lblNHabitacin) {
		this.lblNHabitacin = lblNHabitacin;
	}
	public JTextField getTfnroHab() {
		return tfnroHab;
	}
	public void setTfnroHab(JTextField tfnroHab) {
		this.tfnroHab = tfnroHab;
	}
	public JTable getTable() {
		return table;
	}
	public void setTable(JTable table) {
		this.table = table;
	}
	public JSeparator getSeparator() {
		return separator;
	}
	public void setSeparator(JSeparator separator) {
		this.separator = separator;
	}

	public JScrollPane getScrollPane() {
		return scrollPane;
	}
	public void setScrollPane(JScrollPane scrollPane) {
		this.scrollPane = scrollPane;
	}
	
	public BotonesJtoollbarPersonalizado getBtnCancelar() {
		return btnAnular;
	}
	public void setBtnCancelar(BotonesJtoollbarPersonalizado btnCancelar) {
		this.btnAnular = btnCancelar;
	}

	public JLabel getLblNewLabel() {
		return lblNewLabel;
	}
	public void setLblNewLabel(JLabel lblNewLabel) {
		this.lblNewLabel = lblNewLabel;
	}
	public JButton getButton() {
		return btnBuscarCliente;
	}
	public void setButton(JButton button) {
		this.btnBuscarCliente = button;
	}
	public JButton getBtnBuscarCliente() {
		return btnBuscarCliente;
	}
	public void setBtnBuscarCliente(JButton btnBuscarCliente) {
		this.btnBuscarCliente = btnBuscarCliente;
	}
	public BotonesJtoollbarPersonalizado getBtnNuevo() {
		return btnNuevo;
	}
	public void setBtnNuevo(BotonesJtoollbarPersonalizado btnNuevo) {
		this.btnNuevo = btnNuevo;
	}
	public JTextField getTfdocumento() {
		return tfdocumento;
	}
	public void setTfdocumento(JTextField tfdocumento) {
		this.tfdocumento = tfdocumento;
	}
	public JButton getBtnbuscarNroH() {
		return btnbuscarNroH;
	}
	public void setBtnbuscarNroH(JButton btnbuscarNroH) {
		this.btnbuscarNroH = btnbuscarNroH;
	}
	public JLabel getLbnombreF() {
		return lbnombreF;
	}
	public void setLbnombreF(JLabel lbnombreF) {
		this.lbnombreF = lbnombreF;
	}
	public JTextField getTfnombreF() {
		return tfnombreF;
	}
	public void setTfnombreF(JTextField tfnombreF) {
		this.tfnombreF = tfnombreF;
	}

	public BotonesJtoollbarPersonalizado getBtnSalir() {
		return btnSalir;
	}
	public void setBtnSalir(BotonesJtoollbarPersonalizado btnSalir) {
		this.btnSalir = btnSalir;
	}
	public BotonesJtoollbarPersonalizado getBtnAgregar() {
		return btnAgregar;
	}
	public void setBtnAgregar(BotonesJtoollbarPersonalizado btnAgregar) {
		this.btnAgregar = btnAgregar;
	}
	public BotonesJtoollbarPersonalizado getBtnquitar() {
		return btnquitar;
	}
	public void setBtnquitar(BotonesJtoollbarPersonalizado btnquitar) {
		this.btnquitar = btnquitar;
	}
	public JTextField getTfFecha() {
		return tfFecha;
	}
	public void setTfFecha(JTextField tfFecha) {
		this.tfFecha = tfFecha;
	}
	public BotonesJtoollbarPersonalizado getBtnguardar() {
		return btnguardar;
	}
	public void setBtnguardar(BotonesJtoollbarPersonalizado btnguardar) {
		this.btnguardar = btnguardar;
	}

	public JButton getBtnbuscarServicio() {
		return btnbuscarServicio;
	}
	public void setBtnbuscarServicio(JButton btnbuscarServicio) {
		this.btnbuscarServicio = btnbuscarServicio;
	}
	public JLabel getLblCostoTotal() {
		return lblCostoTotal;
	}
	public void setLblCostoTotal(JLabel lblCostoTotal) {
		this.lblCostoTotal = lblCostoTotal;
	}
}
