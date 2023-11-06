var xmlHttp

function getDetalleEsquema(id_esque){
    xmlHttp=new XMLHttpRequest();
    if (xmlHttp==null){
        alert ("Tu navegador no soporta AJAX!");
        return;
    }
    var url = "aplicaciones.jsp";
    url = url + "?id_esque=" + id_esque;
    xmlHttp.onreadystatechange = resultado_detalle;
    xmlHttp.open("GET",url,true);
    xmlHttp.send(null);
}

function resultado_detalle(){
    if (xmlHttp.readyState==4){
        document.getElementById("result_detalle").innerHTML=xmlHttp.responseText;
    }
}

