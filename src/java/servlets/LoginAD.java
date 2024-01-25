/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import dao.app.apps_scheme.AppsSchemeDAO;
import dao.app.apps_scheme_apps.AppsSchemeAppsDAO;
import dao.app.apps_user.AppsUserDAO;
import dao.app.apps_user_scheme_apps.AppsUserSchemeApps;
import dao.app.apps_user_scheme_apps.AppsUserSchemeAppsDAO;
import java.io.IOException;
import java.io.PrintWriter;
import static java.lang.System.out;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.LogManager;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import static servlets.Login.login;

/**
 *
 * @author jyacelga
 */
public class LoginAD extends HttpServlet {

    private static final Logger Applicationstasks = Logger.getLogger("WStasks");
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
       static {
        PropertyConfigurator.configure("/opt/log4j.properties");
    }
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        try (PrintWriter out = response.getWriter()) {

            String username = request.getParameter("username");
            String password = request.getParameter("password");
            Login instance = new Login();
            boolean result = login(username, password);

            AppsUserDAO user = new AppsUserDAO();
            user.findByAppsUser(username).get(0).getApps_full_names();
            String full_names = user.findByAppsUser(username).get(0).getApps_full_names();
            String perfil = user.findByAppsUser(username).get(0).getApps_descrition_user();
            String id_user=user.findByAppsUser(username).get(0).getId().toString();

            AppsSchemeDAO sche = new AppsSchemeDAO();
            AppsUserDAO userapp = new AppsUserDAO();

            AppsUserSchemeAppsDAO usr_schem = new AppsUserSchemeAppsDAO();

            System.out.println("id_usuairo: "+id_user);
            
            List<AppsUserSchemeApps> total = usr_schem.findByAppsIdAppsUser(id_user);

            AppsSchemeAppsDAO sch_apps = new AppsSchemeAppsDAO();
            List<String> schems = new ArrayList<>();
            for (int y = 0; y < total.size(); y++) {
                schems.add(sche.findById(Integer.parseInt(sch_apps.findById(Integer.parseInt(total.get(y).getId_apps_scheme_apps())).getId_apps_scheme())).getDescription_scheme());
            }
            Set<String> hashSet = new HashSet<String>(schems);
            schems.clear();
            schems.addAll(hashSet);

            ArrayList<String> sxhems = new ArrayList();

            sxhems = (ArrayList) schems;
            for (String s : schems) {
                System.out.println("Esquemas asignados " + s + " al usuario " + full_names);
                Applicationstasks.info(LoginAD.class.getName() + " - Esquemas asignados " + s + " al usuario " + full_names);
            }
            String error = "";
            if ((result) && (total.size() > 0)) {
                //System.out.println("ingrese aqui active correcto y con perfil");
                request.getSession().setAttribute("nombre", full_names);
                request.setAttribute("esquemas", schems);
                request.setAttribute("username", username);
                request.getSession().setAttribute("perfil", perfil);
                request.getRequestDispatcher("menu.jsp").forward(request, response);
            } else {
                if (total.size() == 0) {
                    error = "Usuario no tiene perfil asignado";
                }
                if (!result) {
                    error = "Usuario y/o clave invalidos";
                }
                request.setAttribute("error", error);
                request.getRequestDispatcher("error.jsp").forward(request, response);
            }
            out.close();
        } catch (Exception ex) {
            out.println(ex.toString());
               Applicationstasks.info(LoginAD.class.getName() + " - ERROR  " + ex.toString());
             request.setAttribute("error", "El usuario no existe");
            request.getRequestDispatcher("error.jsp").forward(request, response);
        }

    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
