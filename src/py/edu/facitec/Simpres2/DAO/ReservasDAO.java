package py.edu.facitec.Simpres2.DAO;

import java.util.List;

import org.hibernate.Query;

import py.edu.facitec.Simpres2.entidades.Cliente;
import py.edu.facitec.Simpres2.entidades.Reservas;

public class ReservasDAO extends GenericDAO<Reservas> {

    public ReservasDAO() {
        super(Reservas.class);
    }
    public List<Reservas> filtroInformeReserva(String dNombre, String hNombre) {
        iniciarTransaccion();
        
        String sql = "from Reservas where UPPER(cliente.nombre) BETWEEN :dNombre and :hNombre";
        
        Query<Reservas> query = getSession().createQuery(sql);
        
        query.setParameter("dNombre", dNombre.toUpperCase());
        query.setParameter("hNombre", hNombre.toUpperCase() + "zzzzz");
        
        List<Reservas> lista = query.getResultList();
        commit();
        return lista;
    }




}
