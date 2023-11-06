<%-- 
    Document   : menu
    Created on : 14/04/2022, 10:31:35 AM
    Author     : jyacelga
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page session="true"%>
<%@page language="java" import="servlets.*"%>
<%@page import="java.util.*" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <Script src="AJAX/ajax_esquema.js"></Script>

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
        String usuario = "";
        session.setAttribute("username", request.getAttribute("username"));
        HttpSession sesionOk = request.getSession();
        if (session.getAttribute("nombre") == null) {
    %>
    <jsp:forward page="index.jsp">
        <jsp:param name="error" value="Es obligatorio identificarse" />
    </jsp:forward>
    <%  } else {
            usuario = (String) sesionOk.getAttribute("nombre");
        }
    %>
    <script type="text/javascript">
        function cargarElementos() {
            var myElement = document.getElementById('esquema'),
                    myElementValue = myElement.value;
            if (myElementValue == 'Produccion') {
                var listaElementos = {
                    JBOSS: ["Todos", "Schelduler", "8080", "8081", "8082", "8083", "8084", "8085"],
                    DPS: ["rating", "invdem", "dunning", "fin", "ipb", "todos"],
                    FSM: ["www", "mobile", "api", "queue", "todos"],
                    EMS: ["Web_services", "Ordenes_Cupos"],
                    Aprov_Daemons: ["Todos","Activaciones_gpon","Activaciones_IWAY","Activaciones_otros","Del_ONU Flujo","Ippv_comands","Reconecciones_gpon","Reconecciones_IWAY","Reconecciones_otros","Suspenciones_gpon","Suspenciones_IWAY","Suspenciones_otros","Terminaciones_gpon","Terminaciones_IWAY","Terminaciones_otros"]
                }
            }

            if (myElementValue == 'Desarrollo') {
                var listaElementos = {
                    JBOSS: ["todos", "scheduler", "desa"],
                    DPS: ["bsdesa"],
                    FSM: ["www", "mobile", "api", "queue", "todos"],
                    EMS: ["Web_services", "Ordenes_Cupos", "todos"]
                    
                }
            }

            if (myElementValue == 'Test') {
                var listaElementos = {
                    JBOSS: ["test"],
                    DPS: ["centest"],
                    FSM: ["www", "mobile", "api", "queue", "todos"],
                    EMS: ["Web_services", "Ordenes_Cupos", "todos"]
                    
                }
            }

            var aplicacions = document.getElementById('aplicacion');
            var elementos = document.getElementById('elemento');
            var aplicacionSeleccionada = aplicacions.value;
            elementos.innerHTML = '<option value="">Seleccione un objeto...</option>'

            if (aplicacionSeleccionada !== "") {
                aplicacionSeleccionada = listaElementos[aplicacionSeleccionada];
                aplicacionSeleccionada.sort();
                aplicacionSeleccionada.forEach(function (elemento) {
                    var opcion = document.createElement('option');
                    opcion.value = elemento;
                    opcion.text = elemento;
                    elementos.add(opcion);
                });
            }
        }


        function cargarActividades() {
            var listaactividades = {
                JBOSS: ["stop", "start", "status"],
                DPS: ["stop", "start", "status", "abort", "clearcache"],
                FSM: ["stop", "start", "status"],
                EMS: ["stop", "start", "status"],
                Aprov_Daemons: ["stop", "start", "status"],
            }
            var aplicacions = document.getElementById('aplicacion');
            var actividads = document.getElementById('actividad');
            var aplicacionSeleccionada = aplicacions.value;
            actividads.innerHTML = '<option value="">Seleccione una actividad</option>'
            if (aplicacionSeleccionada !== "") {
                aplicacionSeleccionada = listaactividades[aplicacionSeleccionada];
                aplicacionSeleccionada.sort();
                aplicacionSeleccionada.forEach(function (actividad) {
                    var opcion = document.createElement('option');
                    opcion.value = actividad;
                    opcion.text = actividad;
                    actividads.add(opcion);
                });
            }
        }

        function OnChange(sel) {
            divC = document.getElementById("nComand");
            divC.style.display = "";
        }

        function limpiarFormulario() {
            document.getElementById("Form1").reset();
            divC = document.getElementById("nComand");
            divC.style.display = "none";
        }



    </script>

    <body onload="limpiarFormulario();">

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
                            <div class="entry"> 
                                <div id="entry"> 
                                Seleccione las aplicaciones y tareas
                                </div> 
                                <br/>
                                <br/>
                                <br/>
                                <jsp:useBean id="speed" class="java.lang.String" scope="request" />
                                <jsp:useBean id="force" class="java.lang.String" scope="request" />
                                <jsp:useBean id="force1" class="java.lang.String" scope="request" />

                                <script type="text/javascript">
                                    function ShowLoading(e) {
                                        document.getElementById('todo').style.display = "none";
                                        document.getElementById('logOutData').style.display = "none"; 
                                        var div = document.createElement('div');
                                        var img = document.createElement('img');
                                        img.src = 'images/1.gif';
                                        div.innerHTML = "Loading...<br />";
                                        div.style.cssText = 'position: fixed; top: 20%; left: 40%; z-index: 5000; width: 200px; text-align: center; background:white';
                                        // div.style.cssText = 'position: fixed; top: 5%; left: 40%; z-index: 5000; width: 422px; text-align: center; background:white; border: 1px solid #000';
                                        div.appendChild(img);
                                        document.body.appendChild(div);
                                        return true;
                                        // These 2 lines cancel form submission, so only use if needed.
                                        //window.event.cancelBubble = true;
                                        //e.stopPropagation();
                                    }

                                </script>
                                <form  method="post" action="response.jsp" id="Form1" onsubmit="ShowLoading()">
                                    <!--
                                                                        <form method="post" action="response.jsp" id="Form1">
                                    -->
                                    <div align="center" id="todo">
                                        <table class="default">
                                            <tr>
                                                <th>Esquema:</th>
                                                <th>Aplicaciones:</th>
                                                <th>Elemento:</th>
                                                <th>Actividad:</th>
                                            </tr>
                                            <tr>
                                                <td>
                                                    <select name="esquema" id="esquema"  onchange="getDetalleEsquema(this.value)">
                                                        <option value="">Seleccione la Esquema</option>
                                                        <%
                                                            ArrayList schemes = (ArrayList) request.getAttribute("esquemas");
                                                            for (int y = 0; y < schemes.size(); y++) {
                                                        %>
                                                        <option value="<%=schemes.get(y)%>"><%=schemes.get(y)%></option>
                                                        <%
                                                            }
                                                        %>
                                                    </select>
                                                </td>
                                                <td id="result_detalle">
                                                    <select name="aplicacion"  id="aplicacion" onchange="cargarElementos(this.value)">
                                                        <option value="">Seleccione la aplicacion</option>
                                                    </select>
                                                </td>
                                                <td>
                                                    <select name="elemento" id="elemento" onchange="cargarActividades(this.value)">
                                                        <option value="cargar_actividades();">Seleccione el objeto
                                                    </select>
                                                </td>
                                                <td>
                                                    <select name="actividad" id="actividad"  onChange="OnChange(this)">
                                                        <option value="cargar_actividades();">Seleccione Actividad
                                                    </select>
                                                </td>
                                            </tr>
                                            <tr>
                                                <td height="26"></td>
                                                <td>
                                                    <ul >
                                                        <li id="search">
                                                            <div id="nComand" style="display:none;" >
                                                                <!--
                                                          <p:growl id="growl" life="9000" style="display:none;" />
                                                                 <p:commandButton value="Ejecutar" type="submit" id="withIcon"  update="growl" icon="ui-icon-circle-check" />
                                                                -->  

                                                                <input  name="submit" type="submit" value="Procesar" />

                                                                <!--
                                                                       <script>
                                                                           function myFunction() {
                                                                               alert("The form was submitted");
                                                                           }
                                                                       </script>
                                                                -->  
                                                            </div>
                                                            <div>    
                                                                <input type="button" onclick="limpiarFormulario()" value="Limpiar formulario" style="background-color:greenyellow" >
                                                            </div>
                                                        </li>
                                                    </ul>    
                                                </td>
                                            </tr>
                                        </table>
                                    </div>             
                                </form>                                
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
