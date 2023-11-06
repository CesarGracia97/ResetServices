/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import com.jcraft.jsch.JSchException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.PropertyConfigurator;

/**
 *
 * @author jyacelga
 */
public class Response extends HttpServlet {

       private static final org.apache.log4j.Logger Applicationstasks = org.apache.log4j.Logger.getLogger("WStasks");

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
        PropertyConfigurator.configure("/opt/logj4.properties");
    }
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, JSchException, InterruptedException, Exception {
        response.setContentType("text/html;charset=UTF-8");
    
          try (PrintWriter out = response.getWriter()) {
            SshRemote resultado = new SshRemote();
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Respuesta acciones</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<form id='resp'>");
            out.println("<script>document.resp.resultado.disabled = true;");
            out.println("document.resp.resultado.readOnly = true;</script>");
            String[] respu = resultado.teminalComand(request.getParameter("esquema"), request.getParameter("aplicacion"), request.getParameter("elemento"), request.getParameter("actividad"),request.getParameter("nombre")).split("###");
            if (respu[0].equals("OK")) {
                out.println("<h2 class='title'>Comando ejecutado</h2>");
                out.println("<table cellspacing='0' id='mytable' border='1'>");
                out.println("<tr>");
                out.println("<th scope='col' bgcolor='#DDE0FC'>Esquema</th>");
                out.println("<th scope='col' bgcolor='#DDE0FC'>Aplicacion</th>");
                out.println("<th scope='col' bgcolor='#DDE0FC'>Objeto</th>");
                out.println("<th scope='col' bgcolor='#DDE0FC'>Accion</th>");
                out.println("<th scope='col' bgcolor='#DDE0FC'>Respuesta</th>");
                out.println("</tr>");
                out.println("<tr>");
                out.println("<td scope='col' ALIGN=CENTER>" + request.getParameter("esquema") + "</td>");
                out.println("<td scope='col' ALIGN=CENTER>" + request.getParameter("aplicacion") + "</td>");
                out.println("<td scope='col' ALIGN=CENTER>" + request.getParameter("elemento") + "</td>");
                out.println("<td scope='col' ALIGN=CENTER>" + request.getParameter("actividad") + "</td>");
                out.println("<td scope='col' ALIGN=CENTER BGCOLOR='greenyellow'>" + respu[0] + "</td>");
                out.println("</tr>");
                out.println("</table>  <br><br><br><br>");
            } else {
                out.println("<script>");
                out.println("alert('ERROR con la ejecucion del comando " + respu[0] + "')");
                out.println("</script>");
                out.println("<h2 class='title'>Comando ejecutado</h2>");

                out.println("<table cellspacing='0' id='mytable' border='1'>");
                out.println("<tr>");
                out.println("<th scope='col' bgcolor='#DDE0FC'>Esquema</th>");
                out.println("<th scope='col' bgcolor='#DDE0FC'>Aplicacion</th>");
                out.println("<th scope='col' bgcolor='#DDE0FC'>Objeto</th>");
                out.println("<th scope='col' bgcolor='#DDE0FC'>Accion</th>");
                out.println("<th scope='col' bgcolor='#DDE0FC'>Respuesta</th>");
                out.println("</tr>");
                out.println("<tr>");
                out.println("<td scope='col' ALIGN=CENTER>" + request.getParameter("esquema") + "</td>");
                out.println("<td scope='col' ALIGN=CENTER>" + request.getParameter("aplicacion") + "</td>");
                out.println("<td scope='col' ALIGN=CENTER>" + request.getParameter("elemento") + "</td>");
                out.println("<td scope='col' ALIGN=CENTER>" + request.getParameter("actividad") + "</td>");
                out.println("<td scope='col' ALIGN=CENTER BGCOLOR='RED'>" + respu[0] + "</td>");
                out.println("</tr>");
                out.println("</table>  <br><br>");
            }
            out.println("<h2 class='title'>Respuesta del comando</h2>");
            out.println("<textarea id='resultado' name='resultado' rows='10' cols='80'  disabled='true' style='resize:none'>" + respu[1] + "</textarea>");
            out.println("   <input name='submit' type='reset' value = 'Back' onclick = 'window.history.back ();limpiarFormulario();' />  "); //onclick = 'window.history.back ();'
            out.println("</body>");
            out.println("</html>");
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
        try {
            processRequest(request, response);
        } catch (Exception ex) {
            Logger.getLogger(Response.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        try {
            processRequest(request, response);
        } catch (Exception ex) {
            Logger.getLogger(Response.class.getName()).log(Level.SEVERE, null, ex);
        }
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
