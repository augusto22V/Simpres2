package py.edu.facitec.Simpres2.DAO;
 
import java.util.List; 

import org.hibernate.Query;
/*import java.util.List;  

import org.hibernate.Query;*/
import org.hibernate.Session;

import py.edu.facitec.Simpres2.util.*;;

public abstract class GenericDAO<T> {

	protected Class<T> clase;

	public GenericDAO(Class<T> clase) {
		this.clase = clase;
	}

	protected Session getSession() {
		return ConnectionHelper.getSessionFactory().getCurrentSession();
	}

	protected void iniciarTransaccion() {
		if (!getSession().getTransaction().isActive())
			getSession().beginTransaction();
	}

	public void commit() {
		getSession().flush();
		getSession().getTransaction().commit();
	}

	public void rollback() {
		getSession().getTransaction().rollback();
	}

	public void guardar(T entity) throws Exception {
		iniciarTransaccion();
		getSession().saveOrUpdate(entity);
	}

	public void eliminar(T entity) throws Exception {
		iniciarTransaccion();
		getSession().delete(entity);
	}

	public T recuperarPorId(int id) {
		iniciarTransaccion();
		T result = getSession().get(clase, id);
		commit();
		return result;
	}

	public List<T> recuperarTodos() {
		iniciarTransaccion();
		String sql = "from " + clase.getName() + " order by id";
		Query<T> query = getSession().createQuery(sql);
		List<T> lista = query.getResultList();
		commit();
		return lista;
	}

	public void inizializarTabla(String tabla) {
		iniciarTransaccion();
		String sql = "TRUNCATE TABLE " + tabla + " CASCADE";
		Query query = getSession().createNativeQuery(sql);
		query.executeUpdate();
	}

}
