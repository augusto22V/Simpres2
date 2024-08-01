package py.edu.facitec.Simpres2.Buscadores;

import java.util.List;

import javax.swing.JDialog;
import javax.swing.JTextField;

import py.edu.facitec.Simpres2.DAO.ServicioDao;
import py.edu.facitec.Simpres2.Interfaces.InterfaceServicio;
import py.edu.facitec.Simpres2.entidades.Servicios;
import py.edu.facitec.Simpres2.tablas.ModeloTablaServicio;

import javax.swing.JTable;
import javax.swing.JScrollPane;

import java.awt.BorderLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class BuscadorServicio extends JDialog {

    private JTextField tfBuscador;
    private JTable table;
    private ServicioDao servicioDao;
    private List<Servicios> servicios;
    private InterfaceServicio interfaceServicio;
    private ModeloTablaServicio modeloTablaServicios;

    public void setInterfaceServicio(InterfaceServicio interfaceServicio) {
        this.interfaceServicio = interfaceServicio;
    }

    public BuscadorServicio() {
        setTitle("Buscador de Servicios");
        setBounds(100, 100, 500, 500);
        setModal(true);
        setLocationRelativeTo(this);

        tfBuscador = new JTextField();
        tfBuscador.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                if (e.getKeyChar() == KeyEvent.VK_ENTER) {
                    filtrarServicio();
                }
            }
        });
        getContentPane().add(tfBuscador, BorderLayout.NORTH);
        tfBuscador.setColumns(10);

        JScrollPane scrollPane = new JScrollPane();
        getContentPane().add(scrollPane, BorderLayout.CENTER);

        modeloTablaServicios = new ModeloTablaServicio();
        table = new JTable();
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    seleccionarRegistro(table.getSelectedRow());
                }
            }
        });
        table.setModel(modeloTablaServicios);
        scrollPane.setViewportView(table);

        servicioDao = new ServicioDao();
        filtrarServicio();
    }

    private void filtrarServicio() {
        servicios = servicioDao.filtrarServicios(tfBuscador.getText());
        modeloTablaServicios.setLista(servicios);
    }

    private void seleccionarRegistro(int index) {
        if (index < 0) return;
        interfaceServicio.seleccionarServicio(servicios.get(index));
        dispose();
    }
}
