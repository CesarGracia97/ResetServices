<%-- 
    Document   : login
    Created on : 14/04/2022, 09:27:42 AM
    Author     : jyacelga
--%>


<%@page contentType="text/html" pageEncoding="UTF-8" session="true"%>
<%
    session.invalidate();
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <title>Acceso al Sistema</title>
        <link rel="stylesheet" type="text/css" href="./css/styleLogin.css" />
        <style type="text/css"> 
            #capa1{ position:absolute;
                    z-index:1;
                    color: white;
                    right:245px 
            } 
        </style>
    </head>
    <body onload="this.login.username.focus()" >
        <br/>
        <br/>
        <fieldset id="login">
            <!--
            <legend>Sistema de tareas en APPs</legend>
   <form method="post" name="login" action="<%=request.getContextPath()%>/LoginAD">
          
            -->
            <h4>Ingreso de Usuarios</h4>
            <form method="post" name="login" action="<%=request.getContextPath()%>/LoginAD">
                <p class="clearfix"><label for="username">Usuario</label>
                    <input name="username" id="username" type="text" value="" /></p>
                <p class="clearfix"><label for="password">Clave</label>
                    <input name="password" id="password" type="password" value="" />
                </p>
                <p class="clearfix check">

                    <input name="submit" id="submit" type="submit" value="" /></p>
                <h3><label for="remember" id="remlabel"><%
                    if (request.getParameter("error") != null) {
                        out.println(request.getParameter("error"));
                    }
                        %></label></h3>
            </form>
        </fieldset>

    </body>
</html>