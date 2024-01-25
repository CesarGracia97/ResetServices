package ent.querys;


import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import java.util.logging.Level;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import java.sql.CallableStatement;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.ResultSet;
import java.util.Iterator;
import java.util.Properties;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class Querys {

    //	private static final Logger logger;
    private static final Logger ResendComammds = Logger.getLogger("ResendComammds");

    private EntityManager getEntityManager() {
        return EntityManagerHelper.getEntityManager();
    }

    public List findUsersAppsScheme(String userApp, String scheme) {
        System.out.println("Datos de llegada a findUsersAppsScheme: Usuario= " + userApp + "\n Esquema=" + scheme);
        Query query;
        try {
            final String queryString
                    = "select e.id, e.apps_user, e.apps_full_names,a.description_scheme,b.name_application\n"
                    + "from apps_scheme a, apps_applications b, apps_scheme_apps c, apps_user_scheme_apps d,apps_user e\n"
                    + "where c.id_apps_scheme=a.id\n"
                    + "and c.id_apps_apps=b.id\n"
                    + "and c.id=d.id_apps_scheme_apps\n"
                    + "and d.id_apps_user=e.id\n"
                    + "and a.description_scheme='" + scheme + "'\n"
                    + "and e.apps_user='" + userApp + "';";
            /*      
                    = "SELECT b.id, b.apps_user,b.apps_full_names, d.description_scheme,e.name_application \n"
                    + "FROM apps_user_scheme_apps a, apps_user b,apps_scheme_apps c, apps_scheme d, apps_applications e\n"
                    + "where a.id_apps_user=b.id\n"
                    + "and a.id_apps_scheme_apps=c.id_apps_scheme\n"
                    + "and c.id_apps_scheme=d.id\n"
                    + "and c.id_apps_apps=e.id\n"
                    + "and d.description_scheme='" + scheme + "'\n"
                    + "and b.apps_user='" + userApp + "';";
            
             */

            query = getEntityManager().createNativeQuery(queryString);
            System.out.println(query.getResultList().size()+"  Aplicaciones asignadas en el esquema de " + scheme);

            return query.getResultList();
        } catch (RuntimeException re) {
            EntityManagerHelper.log("find all failed findUsersApps", Level.SEVERE, re);
            throw re;
        }
    }

    public List<String> findIdUser(String userApp) {
        System.out.println("dato en la variable " + userApp);

        Query query;
        try {
            final String queryString
                    = "SELECT distinct(b.id)\n "
                    //+ "b.id, b.apps_user,b.apps_full_names, d.description_scheme,e.name_application\n"
                    //+ "distinct(b.apps_full_names)\n"
                    + "FROM apps_user_scheme_apps a, apps_user b,apps_scheme_apps c, apps_scheme d, apps_applications e\n"
                    + "where a.id_apps_user=b.id\n"
                    + "and a.id_apps_scheme_apps=c.id_apps_scheme\n"
                    + "and c.id_apps_scheme=d.id\n"
                    + "and c.id_apps_apps=e.id\n"
                    + "and b.apps_user='" + userApp + "';";
            query = getEntityManager().createNativeQuery(queryString);
//            System.out.println("Resultado count: " + userApp + "  >>>>" + query.getSingleResult());
//            String retor = query.getSingleResult().toString();
//            

            return query.getResultList();
        } catch (RuntimeException re) {
            EntityManagerHelper.log("find all failed findUsersApps", Level.SEVERE, re);
            throw re;
        }
    }

}
