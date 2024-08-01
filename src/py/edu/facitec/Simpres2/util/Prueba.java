package py.edu.facitec.Simpres2.util;

import py.edu.facitec.Simpres2.DAO.ClienteDao;
import py.edu.facitec.Simpres2.entidades.Cliente;

public class Prueba {
	public static void main(String[] args) {
		Cliente cliente = new Cliente();
		cliente.setNombre("a");
		cliente.setApellido("Valdez");
		cliente.setCorreo("valdez@gmail.com");
		cliente.setDocumento("12355");
		cliente.setDireccion("a");

		ClienteDao clienteDAO = new ClienteDao();
		try {
			clienteDAO.guardar(cliente);
			clienteDAO.commit();

		} catch (Exception e) {
			clienteDAO.rollback();
			e.printStackTrace();
		}

	}

}
