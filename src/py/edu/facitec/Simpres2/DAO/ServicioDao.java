package py.edu.facitec.Simpres2.DAO;

import java.util.List; 

import org.hibernate.query.Query;

import py.edu.facitec.Simpres2.entidades.Servicios;

public class ServicioDao extends GenericDAO<Servicios> {

	public ServicioDao() {
		super(Servicios.class);
	}

	public List<Servicios> filtrarServicios(String filtro) {
		iniciarTransaccion();
		String sql = "from Servicios where UPPER(descripcion) like :filtro";
		Query<Servicios> query = getSession().createQuery(sql);
		query.setParameter("filtro", "%" + filtro.toUpperCase() + "%");
		List<Servicios> lista = query.getResultList();
		commit();
		return lista;
	}

}
