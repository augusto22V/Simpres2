package py.edu.facitec.Simpres2.principal;

import java.awt.BorderLayout;     
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import componentes.Botones;
import componentes.JpanelPrincipal;
import py.edu.facitec.Simpres2.vista.InformeReservaVentana;
import py.edu.facitec.Simpres2.vista.ListadoClientesVentana;
import py.edu.facitec.Simpres2.vista.ListadoFuncionariosVentana;
import py.edu.facitec.Simpres2.vista.ListadoHabitacionVentana;
import py.edu.facitec.Simpres2.vista.VentanaCliente;
import py.edu.facitec.Simpres2.vista.VentanaFuncionario;
import py.edu.facitec.Simpres2.vista.VentanaHabitacion;
import py.edu.facitec.Simpres2.vista.VentanaReserva;
import py.edu.facitec.Simpres2.vista.VentanaServicio;

import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JMenu;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Button;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class VentanaPrincipal extends JFrame {

	private JpanelPrincipal contentPane;
	private Botones btnsServicios;
	private Botones btnsReservas;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaPrincipal frame = new VentanaPrincipal();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public VentanaPrincipal() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setMaximumSize(new Dimension(1080,720));
		setExtendedState(MAXIMIZED_BOTH);
		setLocationRelativeTo(this);
		setTitle("Hotel Simpres");
		
		
		
		contentPane = new JpanelPrincipal();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBorderPainted(false);
		menuBar.setBounds(0, 0, 1366, 21);
		contentPane.add(menuBar);
		menuBar.setForeground(Color.BLACK);
		
		JMenu mnGod = new JMenu("Registros");
		mnGod.setForeground(Color.BLACK);
		menuBar.add(mnGod);
		
		JMenuItem mntmCliente = new JMenuItem("Clientes");
		mntmCliente.addActionListener(new ActionListener() {
			
			    public void actionPerformed(ActionEvent arg0) {
			        VentanaCliente vCliente = new VentanaCliente();
			        vCliente.setLocationRelativeTo(VentanaPrincipal.this);
	    	        vCliente.setUpControlador();

			        vCliente.setVisible(true);
			    }
			
		});
		mnGod.add(mntmCliente);
		
		JMenuItem mntmFuncionario = new JMenuItem("Funcionarios");
		mntmFuncionario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				VentanaFuncionario vFuncionario = new VentanaFuncionario();
		        vFuncionario.setLocationRelativeTo(VentanaPrincipal.this);
		        vFuncionario.setUpControlador();
		        vFuncionario.setVisible(true);
			}
		});
		mnGod.add(mntmFuncionario);
		
		JMenuItem mntmHabitacion = new JMenuItem("Habitacion");
		mntmHabitacion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentanaHabitacion vHabitacion = new VentanaHabitacion();
				vHabitacion.setLocationRelativeTo(VentanaPrincipal.this);
				vHabitacion.setUpControlador();
				vHabitacion.setVisible(true);
			}
		});
		
		mnGod.add(mntmHabitacion);
		
		JMenu mnMovimientos = new JMenu("Movimientos");
		mnMovimientos.setForeground(Color.BLACK);
		
		menuBar.add(mnMovimientos);
		
		JMenuItem mntmHabitaciones = new JMenuItem("Reservas");
		mntmHabitaciones.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentanaReserva vReserva = new VentanaReserva();
				vReserva.setLocationRelativeTo(VentanaPrincipal.this);
    	        vReserva.setUpControlador();
    	        vReserva.setVisible(true);
			}
		});
		
		mnMovimientos.add(mntmHabitaciones);
		
		JMenu mnListado = new JMenu("Listado");
		mnListado.setForeground(Color.BLACK);
		menuBar.add(mnListado);
		
		JMenuItem mntmReservas = new JMenuItem("Clientes");
		mntmReservas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ListadoClientesVentana lcliente = new ListadoClientesVentana();
				lcliente.setLocationRelativeTo(VentanaPrincipal.this);
    	        lcliente.setUpController();
    	        lcliente.setVisible(true);
				
			}
		});
		mnListado.add(mntmReservas);
		
		JMenuItem mntmlFuncionarios = new JMenuItem("Funcionarios");
		mntmlFuncionarios.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ListadoFuncionariosVentana lfuncionario = new ListadoFuncionariosVentana();
				lfuncionario.setLocationRelativeTo(VentanaPrincipal.this);
				lfuncionario.setUpController();
				lfuncionario.setVisible(true);
				
			}
		});
		mnListado.add(mntmlFuncionarios);
		
		JMenuItem mntmNewMenuItem_1 = new JMenuItem("Habitaciones");
		mntmNewMenuItem_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ListadoHabitacionVentana lhabitacion = new ListadoHabitacionVentana();
				lhabitacion.setLocationRelativeTo(VentanaPrincipal.this);
				lhabitacion.setUpController();
				lhabitacion.setVisible(true);
				
			}
		});
		mnListado.add(mntmNewMenuItem_1);
		
		JMenu mnXx = new JMenu("Informes");
		mnXx.setForeground(Color.BLACK);
		menuBar.add(mnXx);
		
		JMenuItem mntmOcupacionHabitaciones = new JMenuItem("Informe Reservas");
		mntmOcupacionHabitaciones.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				InformeReservaVentana informeR= new InformeReservaVentana();
				informeR.setLocationRelativeTo(VentanaPrincipal.this);
				informeR.setUpController();
				informeR.setVisible(true);
			}
		});
		mnXx.add(mntmOcupacionHabitaciones);
		
		JMenu mnConficuraci = new JMenu("Configuraci\u00F3n");
		mnConficuraci.setForeground(Color.BLACK);
		menuBar.add(mnConficuraci);
		
		JMenuItem mntmGeneral = new JMenuItem("General");
		mnConficuraci.add(mntmGeneral);
		
		JMenuItem mntmUsuario = new JMenuItem("Usuario");
		mnConficuraci.add(mntmUsuario);
		
		btnsServicios = new Botones();
		btnsServicios.setForeground(Color.BLACK);
		btnsServicios.addActionListener(new ActionListener() {
			  public void actionPerformed(ActionEvent arg0) {
			        VentanaServicio vservicio = new VentanaServicio();
			        vservicio.setLocationRelativeTo(VentanaPrincipal.this);
	    	        vservicio.setUpControlador();
			        vservicio.setVisible(true);
			    }
		});
		
		btnsServicios.setBackground(Color.LIGHT_GRAY);
		btnsServicios.setLocation(122, 65);
		btnsServicios.setText("Servicios");
		
		contentPane.add(btnsServicios);
	    
	    btnsReservas = new Botones();
	    btnsReservas.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent arg0) {
	    	
	    	        VentanaReserva vReserva = new VentanaReserva();
	    	        vReserva.setLocationRelativeTo(VentanaPrincipal.this);
	    	        vReserva.setUpControlador();
	    	        vReserva.setVisible(true);
	    	    }
	    	
	    	
	    });
	    btnsReservas.setBackground(Color.LIGHT_GRAY);
	    btnsReservas.setForeground(new Color(0, 0, 0));
	    btnsReservas.setLocation(232, 65);
	    btnsReservas.setText("Reservas");
	    
	    contentPane.add(btnsReservas);
	    contentPane.add(new JPanel(), BorderLayout.CENTER);
	    
	    Botones btnsCliente = new Botones();
	    btnsCliente.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {

    	        VentanaCliente vcliente = new VentanaCliente();
    	        vcliente.setLocationRelativeTo(VentanaPrincipal.this);
    	        vcliente.setUpControlador();
    	        vcliente.setVisible(true);
	    		
	    	}
	    });
	    btnsCliente.setText("Cliente");
	    btnsCliente.setForeground(Color.BLACK);
	    btnsCliente.setBackground(Color.LIGHT_GRAY);
	    btnsCliente.setBounds(10, 65, 100, 80);
	    contentPane.add(btnsCliente);

		Botones botones = new Botones();
		
	}
}
