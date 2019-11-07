package server;

import com.google.gson.Gson;
import dao.BeneficiadoDAO;
import dao.base.HibernateUtil;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import model.Beneficiado;
import model.list.BeneficiadoList;
import org.hibernate.Session;

@Path("/beneficiado")
public class BeneficiadoServer {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public BeneficiadoList getBeneficiados() {
        Session session = HibernateUtil.abrirSessao();
        List<Beneficiado> beneficiados = new BeneficiadoDAO().pesquisarTodos(session);
        session.close();
        return new BeneficiadoList(beneficiados);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{id}")
    public Beneficiado getBeneficiado(@PathParam("id") Long id) {
        BeneficiadoDAO beneficiadoDAO = new BeneficiadoDAO();
        Session session = HibernateUtil.abrirSessao();
        Beneficiado beneficiado = beneficiadoDAO.pesquisarPorId(id, session);
        session.close();
        return beneficiado;
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public boolean cadastrar(String body) {
        Gson gson = new Gson();
        Session session = HibernateUtil.abrirSessao();
        Beneficiado beneficiado = gson.fromJson(body, Beneficiado.class);
        BeneficiadoDAO beneficiadoDAO = new BeneficiadoDAO();
        boolean add = beneficiadoDAO.salvarOuAlterar(beneficiado, session);
        session.close();
        return add;
    }

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Path("{id}")
    public Boolean alterar(@PathParam("id") Long id, @FormParam("dado") String dadosJSON) {
        Session session = HibernateUtil.abrirSessao();
        Gson gson = new Gson();
        Beneficiado beneficiado = gson.fromJson(dadosJSON, Beneficiado.class);

        if (id == beneficiado.getId() || beneficiado.getId() == null) {
            beneficiado.setId(id);
        } else {
            return false;
        }

        Boolean res = new BeneficiadoDAO().salvarOuAlterar(beneficiado, session);
        session.close();
        return res;
    }
}
