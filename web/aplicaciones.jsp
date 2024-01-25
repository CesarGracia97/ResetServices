
<%@page import="ent.querys.Aplications"%>
<%@page import="dao.app.apps_applicationsByscheme.appsBo"%>
<%@page import="ent.querys.Querys"%>
<%@page contentType="text/html" pageEncoding="UTF-8" language="java" %>
<%@page import="java.util.*" %>

<html>
    <head>

    </head>
    <body>
        <%
            String id_esque = null;
            String id_user = null;
            id_esque = request.getParameter("id_esque");
            id_user = session.getAttribute("username").toString();
            List lst_detpro = new ArrayList();
            appsBo apps_bo = new appsBo();
            lst_detpro = apps_bo.getAplicaciones(id_user, id_esque);
            out.write("<select name=aplicacion id=aplicacion onchange='cargarElementos(this.value)'>");
            out.write("<option value=''>Seleccione la aplicacion</option>");
            for (int i = 0; i < lst_detpro.size(); i++) {
               out.write("<option value=" + lst_detpro.get(i).toString() + ">" + lst_detpro.get(i).toString() + "</option>");
            }
            out.write("</select>");
            
        %>
    </body>
</html>