/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import dao.app.apps_applications.AppsApplicationsDAO;
import dao.app.apps_scheme.AppsScheme;
import dao.app.apps_scheme.AppsSchemeDAO;
import dao.app.apps_user.AppsUserDAO;
import dao.app.apps_scheme_apps.AppsSchemeApps;
import dao.app.apps_scheme_apps.AppsSchemeAppsDAO;
import dao.app.apps_user_scheme_apps.AppsUserSchemeApps;
import dao.app.apps_user_scheme_apps.AppsUserSchemeAppsDAO;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.NamingException;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;
import ent.querys.Querys;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author jyacelga
 */
public class Login {

    static final String LDAP_URL = "ldap://192.168.21.42:389/DC=tvcable,DC=com,DC=ec";

    public static boolean login(String username, String password) {

      //  System.out.println("llegue aca");
        Hashtable env = new Hashtable();

        env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
        env.put(Context.SECURITY_PRINCIPAL, username.toUpperCase() + "@xtrim.com.ec");
        env.put(Context.PROVIDER_URL, LDAP_URL);

        env.put(Context.SECURITY_AUTHENTICATION, "simple");
        env.put(Context.SECURITY_CREDENTIALS, password);
        try {
            DirContext ctx = new InitialDirContext(env);

            return true;
        } catch (NamingException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public static void main(String[] args) {

//        AppsUserDAO userapp = new AppsUserDAO();
//        AppsUserDAO user = new AppsUserDAO();
//        String full_names = user.findByAppsUser("sbarragan").get(0).getId().toString();
//        AppsUserSchemeAppsDAO usr_schem = new AppsUserSchemeAppsDAO();
//        List<AppsUserSchemeApps> total = usr_schem.findByAppsIdAppsUser(userapp.findByAppsUser("sbarragan").get(0).getId().toString());
//
//        System.out.println("tamaño  >>> " + total.size());

        //     List resultado = new ArrayList();
        // List resultado1 = new ArrayList();
        //   Querys lista = new Querys();
        // resultado = lista.findUsersAppsScheme("jyacelga","Produccion");
        //resultado1 = lista.findIdUser("jyacelga");
        // System.out.println("tamaño  >>> " + resultado.get(0));
        // System.out.println("tamaño  >>> " + resultado1.get(0).toString().replaceAll("[^\\w\\s]", ""));
        /*
//        for (int x = 0; x < resultado.size(); x++) {
//            System.out.println("tamaño  >>> " + resultado.get(x));
//        }
        AppsSchemeDAO sche = new AppsSchemeDAO();
        // System.out.println("AppsSchemeDAO " + sche.findById(1).getDescription_scheme());
        AppsApplicationsDAO apps = new AppsApplicationsDAO();
        AppsUserDAO user = new AppsUserDAO();
        //System.out.println("AppsApplicationsDAO  >>> " + apps.findById(user.findByAppsUser("jyacelga").get(0).getId()).getDescription_application().toString());

        AppsUserSchemeAppsDAO usr_schem = new AppsUserSchemeAppsDAO();
        List<AppsUserSchemeApps> total = usr_schem.findByAppsIdAppsUser(user.findByAppsUser("jyacelga").get(0).getId().toString());

        AppsSchemeAppsDAO sch_apps = new AppsSchemeAppsDAO();
        System.out.println("tamaño apps >>> " + total);

        // List schems = new ArrayList();
        List<String> schems = new ArrayList<>();
        for (int y = 0; y < total.size(); y++) {
            //    System.out.println("Id_apps_scheme_apps >>> " + total.get(y).getId_apps_scheme_apps()+"  schem "+  sche.findById(Integer.parseInt(sch_apps.findById(Integer.parseInt(total.get(y).getId_apps_scheme_apps())).getId_apps_scheme())).getDescription_scheme() + "  apps  " + apps.findById(Integer.parseInt(sch_apps.findById(Integer.parseInt(total.get(y).getId_apps_scheme_apps())).getId_apps_apps())).getName_application());
            //       System.out.println("  schem " + sche.findById(Integer.parseInt(sch_apps.findById(Integer.parseInt(total.get(y).getId_apps_scheme_apps())).getId_apps_scheme())).getDescription_scheme());
            schems.add(sche.findById(Integer.parseInt(sch_apps.findById(Integer.parseInt(total.get(y).getId_apps_scheme_apps())).getId_apps_scheme())).getDescription_scheme());
        }
        //  schems.clear();

        Set<String> hashSet = new HashSet<String>(schems);
        schems.clear();
        schems.addAll(hashSet);

        System.out.println("tamaño schems >>> " + schems.size());

        System.out.printf("%n%nEsquemas sin repetición%n");
        for (String s : schems) {
            System.out.println(s);
        }
         */

 /*
        AppsUserDAO user = new AppsUserDAO();
        System.out.println("ApplicationUserDAO_id >>> " + user.findById(1).getApps_full_names());
        System.out.println("ApplicationUserDAO_user >>> " + user.findByAppsUser("jyacelga").get(0).getApps_full_names());      
        System.out.println("ApplicationUserDAO_user >>> " + user.findByAppsUser("jyacelga").get(0).getId());

        AppsSchemeAppsDAO user_apps = new AppsSchemeAppsDAO();
        System.out.println("AppsSchemeAppsDAO >>> " + user_apps.findById(1).getId_apps_scheme());

        AppsUserSchemeAppsDAO usr_schem = new AppsUserSchemeAppsDAO();

        System.out.println("AppsUserSchemesDAO >>> " + usr_schem.findById(1).getId_apps_user());
         */
 
        if (login("jyacelga", "Javicho1999")) {
            System.out.println("acceso correcto");
        }
    }
}
