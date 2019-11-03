package dao.base;

import model.ParceiroDeNegocio;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {

    private static final SessionFactory sessionFactory;

    static {
        try {
            Configuration cfg = new Configuration();
            cfg.addAnnotatedClass(ParceiroDeNegocio.class);
            
            cfg.configure("/dao/base/hibernate.cfg.xml");
            StandardServiceRegistryBuilder build
                    = new StandardServiceRegistryBuilder().
                            applySettings(cfg.getProperties());

            sessionFactory = cfg.buildSessionFactory(build.build());
        } catch (Throwable ex) {
            System.err.println("Erro ao criar fábrica de conexão." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static Session abrirSessao() {
        return sessionFactory.openSession();
    }
}
