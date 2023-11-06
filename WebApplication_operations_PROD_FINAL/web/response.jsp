<%-- 
    Document   : menu
    Created on : 14/04/2022, 10:31:35 AM
    Author     : jyacelga
--%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

    <%@ page language="java" pageEncoding="ISO-8859-1"%>
    <%@ page import="servlets.*" %>
    <%@ page session="true"%>
    <%@page import="java.util.*"%>

    <head>
        <meta http-equiv="content-type" content="text/html; charset=utf-8" />
        <title>Procesos en aplicaciones</title>
        <link href="css/style.css" rel="stylesheet" type="text/css" media="screen" />

        <style type="text/css"> 
            #capa1{ position:absolute;
                    z-index:1;
                    color: white;
                    right:245px 
            } 
        </style>
    </head>
    <%
        String nombre = (session.getAttribute("nombre")).toString();
        String perfil = (session.getAttribute("perfil")).toString();
    %>

    <%@page session="true"%>

    <%
        String usuario = "";
        HttpSession sesionOk = request.getSession();
        if (session.getAttribute("nombre") == null) {
    %>
    <jsp:forward page="index.jsp">
        <jsp:param name="error" value="Es obligatorio identificarse" />
    </jsp:forward>
    <%
        } else {
            usuario = (String) sesionOk.getAttribute("nombre");
        }
    %>
    <body>


        <div id="wrapper">
            <div id="wrapper2">
                <div id="header">
                    <div id="logo">
                        <h1><img src="images/logo_blanco.png" alt="" class="left" /></h1>
                    </div>
                    <div id="menu" ><div id="capa1" >
                        </div>
                    </div>
                </div>
                <div id="page">
                    <div id="content">
                        <div class="post">
                            <div id="userData" align="left">
                                <img src="images/User-icon.png"/>               	
                                <%out.println(nombre);%>
                            </div>
                            <div id="userData" align="left">
                                &emsp;&emsp;&emsp;<%out.println(perfil);%>
                            </div> 
                            <h2 class="title"><img src="images/Search-icon.png"/><a href="#">Aplicaciones</a></h2>
                            <%
                                SshRemote resultado = new SshRemote();
                                String esquema = "";
                                String aplicacion = "";
                                String elemento = "";
                                String actividad = "";
                                esquema = request.getParameter("esquema");
                                aplicacion = request.getParameter("aplicacion");
                                elemento = request.getParameter("elemento").toString();
                                actividad = request.getParameter("actividad").toString();
                                System.out.println("Desde el JSP esquema " + esquema + " aplicacion " + aplicacion + " elemento  " + elemento + " actividad  " + actividad);
                                String[] respu = resultado.teminalComand(request.getParameter("esquema"), request.getParameter("aplicacion"), request.getParameter("elemento"), request.getParameter("actividad"), nombre).split("###");

                                if (respu[0].equals("OK")) {
                            %>
                            <div class="entry"> 
                                Resultado de comando ejecutado en el servidor
                                <br/>
                                <br/>
                                <form method="post"  id="Form1">
                                    <div align="center">
                                        <table cellspacing='0' id='mytable' border='1'>
                                            <tr>
                                                <th scope='col' bgcolor='#DDE0FC'>Esquema</th>
                                                <th scope='col' bgcolor='#DDE0FC'>Aplicacion</th>
                                                <th scope='col' bgcolor='#DDE0FC'>Objeto</th>
                                                <th scope='col' bgcolor='#DDE0FC'>Accion</th>
                                                <th scope='col' bgcolor='#DDE0FC'>Respuesta</th>
                                            </tr>
                                            <tr>
                                                <td scope='col' ALIGN=CENTER><%out.print(request.getParameter("esquema"));%> </td>
                                                <td scope='col' ALIGN=CENTER><%out.print(request.getParameter("aplicacion"));%> </td>
                                                <td scope='col' ALIGN=CENTER><%out.print(request.getParameter("elemento"));%> </td>
                                                <td scope='col' ALIGN=CENTER><%out.print(request.getParameter("actividad"));%> </td>
                                                <td scope='col' ALIGN=CENTER BGCOLOR='greenYellow'><%out.print(respu[0]);%></td>
                                            </tr>
                                        </table>  
                                        </br>
                                    </div>
                                    Resultado del comando en la aplicacion               
                                    </br>
                                    </br>
                                    <textarea id='resultado' name='resultado' rows='6' cols='100'  disabled='true' style='resize:none'> <%out.print(respu[1]);%></textarea>
                                    <%
                                        }
                                        if (respu[0].equals("FALLIDO")) {
                                    %>
                                    <div class="entry"> 
                                        Resultado de comando ejecutado en el servidor
                                        <br/>
                                        <br/>
                                        <form method="post"  id="Form1">
                                            <div align="center">
                                                <table cellspacing='0' id='mytable' border='1'>
                                                    <tr>
                                                        <th scope='col' bgcolor='#DDE0FC'>Esquema</th>
                                                        <th scope='col' bgcolor='#DDE0FC'>Aplicacion</th>
                                                        <th scope='col' bgcolor='#DDE0FC'>Objeto</th>
                                                        <th scope='col' bgcolor='#DDE0FC'>Accion</th>
                                                        <th scope='col' bgcolor='#DDE0FC'>Respuesta</th>
                                                    </tr>
                                                    <tr>
                                                        <td scope='col' ALIGN=CENTER><%out.print(request.getParameter("esquema"));%> </td>
                                                        <td scope='col' ALIGN=CENTER><%out.print(request.getParameter("aplicacion"));%> </td>
                                                        <td scope='col' ALIGN=CENTER><%out.print(request.getParameter("elemento"));%> </td>
                                                        <td scope='col' ALIGN=CENTER><%out.print(request.getParameter("actividad"));%> </td>
                                                        <td scope='col' ALIGN=CENTER BGCOLOR='RED'><%out.print(respu[0]);%></td>
                                                    </tr>
                                                </table>  </br>
                                            </div>
                                            Resultado del comando en la aplicacion               
                                            </br>
                                            </br>
                                            <textarea id='resultado' name='resultado' rows='6' cols='100'  disabled='true' style='resize:none'> <%out.print(respu[1]);%></textarea>
                                            <%
                                                }
                                                if (respu[0].equals("ALL")) {
                                                    String[] st_all = respu[1].toString().split("&&");
                                            %>
                                            <div class="entry"> 
                                                Resultado de comando ejecutado en el servidor
                                                <br/>
                                                <br/>
                                                <form method="post"  id="Form1">
                                                    <div align="center">
                                                        <table cellspacing='0' id='mytable' border='1'>
                                                            <tr>
                                                                <th scope='col' bgcolor='#DDE0FC'>Esquema</th>
                                                                <th scope='col' bgcolor='#DDE0FC'>Aplicacion</th>
                                                                <th scope='col' bgcolor='#DDE0FC'>Objeto</th>
                                                                <th scope='col' bgcolor='#DDE0FC'>Accion</th>
                                                                <th scope='col' bgcolor='#DDE0FC'>Respuesta</th>
                                                            </tr>
                                                            <tr>
                                                                <td scope='col' ALIGN=CENTER><%out.print(request.getParameter("esquema"));%> </td>
                                                                <td scope='col' ALIGN=CENTER><%out.print(request.getParameter("aplicacion"));%> </td>
                                                                <td scope='col' ALIGN=CENTER><%out.print(request.getParameter("elemento"));%> </td>
                                                                <td scope='col' ALIGN=CENTER><%out.print(request.getParameter("actividad"));%> </td>
                                                                <td scope='col' ALIGN=CENTER BGCOLOR='yellowgreen'>OK</td>
                                                            </tr>
                                                        </table>  </br>
                                                    </div>
                                                    Resultado del comando en la aplicacion               
                                                    </br>
                                                    </br>
                                                    <div align="center">
                                                        <table cellspacing='0' id='mytable' border='1'>
                                                            <tr>
                                                                <th scope='col' bgcolor='#DDE0FC'>Aplicacion</th>
                                                                <th scope='col' bgcolor='#DDE0FC'>Objeto</th>
                                                                <th scope='col' bgcolor='#DDE0FC'>Estado</th>
                                                            </tr>
                                                            <%
                                                                for (int w = 0; w < st_all.length; w++) {
                                                                    System.out.println("varios " + st_all[w].toString());
                                                                    String[] appps = st_all[w].toString().split("--");
                                                            %>
                                                            <tr>
                                                                <td scope='col' ALIGN=CENTER><%out.print(request.getParameter("aplicacion"));%> </td>
                                                                <td scope='col' ALIGN=CENTER><%out.print(appps[0]);%> </td>
                                                                <% if (appps[1].contains("RUNNING") || appps[1].contains("REFRESHED") || appps[1].contains("STOPED") || appps[1].contains("STARTED") || appps[1].contains("ABORTED")) {
                                                                %>
                                                                <td scope='col' ALIGN=CENTER BGCOLOR='YELLOWGREEN'><%out.print(appps[1]);%> </td>
                                                                <%} else {
                                                                %>
                                                                <td scope='col' ALIGN=CENTER BGCOLOR='RED'><%out.print(appps[1]);%> </td>
                                                                <% }
                                                                %>
                                                            </tr>                                                        
                                                            <%  }
                                                            %>
                                                        </table>  </br>
                                                    </div>


                                                    <%
                                                        }
                                                    %>
                                                    <!--
                                                                                                        Resultado del comando en la aplicacion               
                                                                                                        </br>
                                                                                                        </br>
                                                                                                        <textarea id='resultado' name='resultado' rows='6' cols='100'  disabled='true' style='resize:none'> <%out.print(respu[1]);%></textarea>
                                                    -->
                                                    <ul >
                                                        <li id="search">
                                                            <div id="nComand">
                                                                <form action="/menu.jsp">
                                                                <input name="submit" type="reset" value ="Back" onclick = 'window.history.back();' />
                                                                </form>
                                                            </div>
                                                        </li>
                                                    </ul> 
                                                </form>     
                                                <br/>
                                            </div>
                                    </div>
                            </div>
                            <!-- end #content -->
                            <div id="sidebar">
                                <div id="logOutData" align="right">
                                    <a href="index.jsp"><img src="images/Log-Out-icon.png" title="Salir"/></a>
                                </div>
                            </div>
                            <!-- end #sidebar -->
                            <div style="clear: both;"></div>
                            <!-- end #widebar -->
                        </div>
                        <!-- end #page -->
                    </div>
                    <!-- end #wrapper2 -->
                    <div id="footer">
                        <p><img src="images/footer.gif"/></br>Sistema de control de Apps - Titan </br>(c) Xtrim TVCABLE</p>
                    </div>
                </div>
                <!-- end #wrapper -->
                </body>
                </html>
