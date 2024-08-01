package py.edu.facitec.Simpres2.DAO;

import java.util.List; 

import org.hibernate.query.Query;

import py.edu.facitec.Simpres2.entidades.Funcionario;

public class FuncionarioDAO extends GenericDAO<Funcionario> {

	public FuncionarioDAO() {
		super(Funcionario.class);
	}

	@SuppressWarnings("unchecked")
	public List<Funcionario> filtrarFuncionarios(String filtro) {
		iniciarTransaccion();
		String sql = "from Funcionario where UPPER(nombre) like :filtro or UPPER(apellido) like :filtro or UPPER(documento) like :filtro order by id";
		Query<Funcionario> query = getSession().createQuery(sql);
		query.setParameter("filtro", "%" + filtro.toUpperCase() + "%");
		List<Funcionario> lista = query.getResultList();
		commit();
		return lista;
	}

	public Funcionario verificarCedula(String ci) {
		iniciarTransaccion();
		String sql = "from Funcionario where UPPER(documento) like :filtro";
		Query<Funcionario> query = getSession().createQuery(sql);
		query.setParameter("filtro", ci.toUpperCase());
		Funcionario funcionario;
		try {
			funcionario = query.getSingleResult();
		} catch (Exception e) {
			funcionario = null;
		}
		commit();
		return funcionario;
	}
	  public List<Funcionario> filtroListadoFuncionarios(String dNombre, String hNombre, String dApellido, String hApellido, String order) {
	        iniciarTransaccion();

	        String sql = "from Funcionario where UPPER(nombre) BETWEEN :dNombre and :hNombre "
	                + "and UPPER(apellido) BETWEEN :dApellido and :hApellido "
	                + "order by " + order.toLowerCase();

	        Query<Funcionario> query = getSession().createQuery(sql);

	        query.setParameter("dNombre", dNombre.toUpperCase());
	        query.setParameter("hNombre", hNombre.toUpperCase() + "zzzzz");
	        query.setParameter("dApellido", dApellido.toUpperCase());
	        query.setParameter("hApellido", hApellido.toUpperCase() + "zzzzz");

	        List<Funcionario> lista = query.getResultList();
	        commit();
	        return lista;
	    }

}
