package py.edu.facitec.Simpres2.DAO;

import org.hibernate.Query;  
import py.edu.facitec.Simpres2.entidades.Habitacion;

import java.util.List;


import javax.persistence.EntityManager;

public class HabitacionDAO extends GenericDAO<Habitacion> {

	private static final String JpaUtil = null;
	public HabitacionDAO() {
		super(Habitacion.class);
	}

	public List<Habitacion> filtrarHabitaciones(String filtro) {
	    iniciarTransaccion();
	    String sql = "from Habitacion where UPPER(tipo) like :filtro or UPPER(numero) like :filtro or UPPER(observaciones) like :filtro order by Id_hab";
	    Query<Habitacion> query = getSession().createQuery(sql);
	    query.setParameter("filtro", "%" + filtro.toUpperCase() + "%");
	    List<Habitacion> lista = query.getResultList();
	    commit();
	    return lista;
	}


	  public List<Habitacion> filtrarPorTipo(String dTipo, String hTipo, String order) {
	        List<Habitacion> lista = null;
	        try {
	            iniciarTransaccion();

	            String sql = "from Habitacion where UPPER(tipo) BETWEEN :dTipo and :hTipo order by " + order.toLowerCase();
	            Query<Habitacion> query = getSession().createQuery(sql, Habitacion.class);

	            query.setParameter("dTipo", dTipo.toUpperCase());
	            query.setParameter("hTipo", hTipo.toUpperCase() + "zzzzz");

	            lista = query.getResultList();
	            commit();
	        } catch (Exception e) {
	            e.printStackTrace();
	            rollback();
	        }
	        return lista;
	    }
}
