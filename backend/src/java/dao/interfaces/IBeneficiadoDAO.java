package dao.interfaces;

import java.util.List;
import model.Beneficiado;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import dao.base.IBaseDao;

public interface IBeneficiadoDAO extends IBaseDao<Beneficiado, Long> {
    
    List<Beneficiado> pesquisarTodos(Session session) throws HibernateException;
    
    List<Beneficiado> pesquisarPorNome(String nome,Session session) throws HibernateException;
}
