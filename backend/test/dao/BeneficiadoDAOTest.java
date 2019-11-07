package dao;

import dao.base.HibernateUtil;
import java.util.List;
import model.Beneficiado;
import org.hibernate.Query;
import org.hibernate.Session;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import org.junit.Test;

public class BeneficiadoDAOTest {

    public BeneficiadoDAOTest() {
    }

    @Test
    public void testSalvar() {
        Beneficiado beneficiado = addBeneficiado();
        assertNotNull(beneficiado.getId());
    }

    @Test
    public void testAlterar() {
        Beneficiado beneficiado = primeiroRegistroDoBancoDeDados();
        beneficiado.setNome("Nome alterado");
        Session session = HibernateUtil.abrirSessao();
        new BeneficiadoDAO().salvarOuAlterar(beneficiado, session);
        Beneficiado beneficiadoAlterado = new BeneficiadoDAO().pesquisarPorId(beneficiado.getId(), session);
        session.close();
        assertEquals(beneficiado.getNome(), beneficiadoAlterado.getNome());
    }

    @Test
    public void testPesquisarPorId() {
        Beneficiado beneficiado = primeiroRegistroDoBancoDeDados();
        Session session = HibernateUtil.abrirSessao();
        Beneficiado beneficiadoPesquisado = new BeneficiadoDAO().pesquisarPorId(beneficiado.getId(), session);
        session.close();
        assertNotNull(beneficiadoPesquisado);
    }
    /*
    @Test
    public void excluir() {
        Beneficiado beneficiado = primeiroRegistroDoBancoDeDados();
        Session session = HibernateUtil.abrirSessao();
        new BeneficiadoDAO().excluir(beneficiado, session);
        Beneficiado beneficiadoExcluido = new BeneficiadoDAO().pesquisarPorId(beneficiado.getId(), session);
        session.close();
        assertNull(beneficiadoExcluido);
    }*/

    @Test
    public void testPesquisarPorNome() {
        primeiroRegistroDoBancoDeDados();
        Session session = HibernateUtil.abrirSessao();
        List<Beneficiado> beneficiados = new BeneficiadoDAO().pesquisarPorNome("Thiago", session);

        session.close();
        assertNotNull(beneficiados);
    }

    private Beneficiado primeiroRegistroDoBancoDeDados() {
        return novoBeneficiado();
    }

    private static Beneficiado addBeneficiado() {
        Beneficiado beneficiado = getBeneficiado();

        Session session = HibernateUtil.abrirSessao();
        new BeneficiadoDAO().salvarOuAlterar(beneficiado, session);
        session.close();
        assertNotNull(beneficiado.getId());

        return beneficiado;
    }

    public static Beneficiado novoBeneficiado() {
        Session session = HibernateUtil.abrirSessao();
        Query consulta = session.createQuery("from Beneficiado");
        consulta.setMaxResults(1);
        Beneficiado beneficiado = (Beneficiado) consulta.uniqueResult();
        session.close();
        if (beneficiado == null) {
            beneficiado = addBeneficiado();
        }
        return beneficiado;
    }

    public static Beneficiado getBeneficiado() {
        return new Beneficiado("Joaozinho", "joaozinho@hotmail.com", "(48)99999999", "(48)996501990", "033.88.890-05");
    }
}
