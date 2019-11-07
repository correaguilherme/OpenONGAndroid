package dao;

import dao.base.BaseDao;
import dao.interfaces.IBeneficiadoDAO;
import java.io.Serializable;
import java.util.List;
import model.Beneficiado;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

public class BeneficiadoDAO extends BaseDao<Beneficiado, Long>
        implements IBeneficiadoDAO, Serializable {

    @Override
    public Beneficiado pesquisarPorId(Long id, Session session) throws HibernateException {
        return (Beneficiado) session.get(Beneficiado.class, id);
    }

    @Override
    public List<Beneficiado> pesquisarTodos(Session session) throws HibernateException {
        Query consulta = session.createQuery("from Beneficiado");
        return consulta.list();
    }

    @Override
    public List<Beneficiado> pesquisarPorNome(String nome, Session session) throws HibernateException {
        Criteria criteria = session.createCriteria(Beneficiado.class);
        criteria.add(Restrictions.like("nome", "%" + nome + "%"));
        List<Beneficiado> beneficiados = criteria.list();

        return beneficiados;
    }
}
