/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

/**
 *
 * @author jyacelga
 */
import schemes.Desarrollo;
import servlets.sshRemoteUserInfo;
import com.jcraft.jsch.*;
import java.io.ByteArrayInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import static java.lang.System.in;
import static java.lang.System.out;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import org.apache.log4j.PropertyConfigurator;
import schemes.Produccion;
import schemes.Test;

public class SshRemote {

    static SendMail send_mail = new SendMail();
    private static final org.apache.log4j.Logger Applicationstasks = org.apache.log4j.Logger.getLogger("WStasks");
   static {
        PropertyConfigurator.configure("/opt/log4j.properties");
    }
    public String teminalComand(String esquema, String aplicacion, String objeto, String accion, String user) throws JSchException, IOException, InterruptedException, Exception {
        GetCommandResult commandResp = new GetCommandResult();
        Desarrollo des_ans = new Desarrollo();
        Test test_ans = new Test();
        Produccion produ_ans = new Produccion();
        //  List result = new ArrayList();
        String Resultado = "";
        String Resultado1 = "";
        // System.out.print("Datos de entrada\n");
        // System.out.print(" *Esquema=" + esquema + "\n *Aplicacion=" + aplicacion + "\n *Objeto=" + objeto + "\n *Accion=" + accion);
        Applicationstasks.info(SshRemote.class.getName() + " - Datos de entrada");
        Applicationstasks.info(SshRemote.class.getName() + " - User= " + user + "  Esquema=" + esquema + "  Aplicacion=" + aplicacion + " Objeto=" + objeto + " Accion=" + accion);
        if (esquema.equals("Desarrollo")) {
            Resultado = des_ans.proccessComand(aplicacion, accion, objeto, user);
        } else if (esquema.equals("Test")) {
            Resultado = test_ans.proccessComand(aplicacion, accion, objeto, user);
        } else if (esquema.equals("Produccion")) {
            Resultado = produ_ans.proccessComand(aplicacion, accion, objeto, user);
        }

        String correo = Resultado;
        Resultado1 = Resultado;

        // correo.substring(6, correo.length());
//        System.out.println("respuesta del corte: " + correo.substring(6, correo.length()));

        String[] st_all = correo.substring(6, correo.length()).toString().split("&&");
        String html = "";
        html = "<tr>"
                + "<strong><td scope ='col' ALIGN = CENTER> Esquema </td>"
                + "<td scope ='col' ALIGN = CENTER> Accion </td>"
                + "<td scope ='col' ALIGN = CENTER> App </td>"
                + "<td scope ='col' ALIGN = CENTER> Resultado </td></strong>"
                + "</tr>";
        for (int w = 0; w < st_all.length; w++) {
            String[] appps = st_all[w].toString().split("--");
            html += "<tr>"
                    + "<td scope ='col' ALIGN = CENTER> " + esquema + " </td>"
                    + "<td scope ='col' ALIGN = CENTER> " + accion + " </td>"
                    + "<td scope ='col' ALIGN = CENTER> " + appps[0].toString() + " </td>"
                    + "<td scope ='col' ALIGN = CENTER> " + appps[1].toString() + " </td>"
                    + "</tr>";
        }

        System.out.println("Envio email con el detalle de ejecucion de stop");
        send_mail.sendMail("TAREAS_APLICACIONES <notificacionhelpdesksistemas@tvcable.com.ec>", "tareas ejecutadas", "<html><body><div align=center><table class=MsoNormalTable border=0 cellspacing=0 cellpadding=0 width=460 style=\'width:645.0pt;background:#F0F7FB;border-collapse:collapse;mso-yfti-tbllook:1184\'>    "
                + "<tr style=\'mso-yfti-irow:0;mso-yfti-firstrow:yes;height:27.0pt\'>      "
                + "<td colspan=2 style=\'border:solid #A5C7E4 1.0pt;mso-border-alt:solid #A5C7E4 .75pt;background:RED;padding:2.25pt 2.25pt 2.25pt 2.25pt;height:27.0pt\'>      "
                + "<p class=MsoNormal1 align=center style=\'text-align:center\'><span class=style211>       "
                + "<span style=\'font-size:10.5pt; font-family:&quot;Verdana&quot;, &quot;sans-serif&quot;\'> "
                + "<strong>Tareas en apps</strong> </span> </span>"
                + "<span style=\'font-size:8.5pt;font-family:  \'Verdana\',\'sans-serif\'\'>       </span>        </p>      </td></tr>"
                + "<tr style=\'mso-yfti-irow:1;height:21.75pt\'><td style=\'border:solid #A5C7E4 1.0pt;border-top:none;mso-border-top-alt:solid #A5C7E4.75pt;mso-border-alt:solid #A5C7E4 .75pt;padding:2.25pt 2.25pt 2.25pt 2.25pt; height:21.75pt\'><p class=MsoNormal1><strong><span style=\'font-size:8pt;font-family:\'Verdana\',\'sans-serif\'\'>Descripci&oacuten:</span></strong><span style=\'font-size:8pt;font-family:\'Verdana\',\'sans-serif\'\'> </span>      </p>      </td>"
                + "<td style=\'border-top:none;border-left:none;border-bottom:solid #A5C7E4 1.0pt; border-right:solid #A5C7E4 1.0pt;mso-border-top-alt:solid #A5C7E4 .75pt;  mso-border-left-alt:solid #A5C7E4 .75pt;mso-border-alt:solid #A5C7E4 .75pt; padding:2.25pt 2.25pt 2.25pt 2.25pt;height:21.75pt\'>  "
                + "<p class=MsoNormal1><span style=\'font-family: &quot;Verdana&quot;, &quot;sans-serif&quot;; font-size: 8.5pt\'>Se ejecuto las siguientes tareas en las aplicaciones</span></p>"
                + "</td>"
                + "</tr>"
                + "<tr style=\'mso-yfti-irow:2;mso-yfti-lastrow:yes\'>"
                + "<td style=\'border:solid #A5C7E4 1.0pt;border-top:none;mso-border-top-alt:solid #A5C7E4 .75pt;mso-border-alt:solid #A5C7E4 .75pt;padding:2.25pt 2.25pt 2.25pt 2.25pt\'>  <p class=MsoNormal1>  <strong>"
                + "<span style=\'font-size:8.5pt;font-family:\'Verdana\',\'sans-serif\'\'>Listado >></span> </strong>  <span style=\'font-size:8.5pt;font-family:\'Verdana\',\'sans-serif\'\'>      </span>      </p>"
                + "</td>"
                + "<td style=\'border-top:none;border-left:none;border-bottom:solid #A5C7E4 1.0pt;border-right:solid #A5C7E4 1.0pt;mso-border-top-alt:solid #A5C7E4 .75pt;  mso-border-left-alt:solid #A5C7E4 .75pt;mso-border-alt:solid #A5C7E4 .75pt; padding:2.25pt 2.25pt 2.25pt 2.25pt\'>"
                + "<div align=center>"
                + "<strong>" + user + "</strong>"
                + "</div>"
                + "</br>"
                + "<div align=center>"
                + "<table border=1>"
                + html
                + "</table>"
                + "</div>"
                + "</td>"
                + "</tr>"
                + "<BR></table>"
                + "</div>"
                + "</body>"
                + "</html>");

        Applicationstasks.info(SshRemote.class.getName() + "Resultado total " + Resultado.replaceAll("actintraway", "Activaciones_IWAY"));

        return Resultado1.replaceAll("actgpon", "Activaciones_gpon").replaceAll("actotros", "Activaciones_otros").replaceAll("delonuflow", "Del_ONU Flujo").replaceAll("ippv", "Ippv_comands").replaceAll("recgpon", "Reconecciones_gpon").replaceAll("recintraiway", "Reconecciones_IWAY").replaceAll("recotros", "Reconecciones_otros").replaceAll("susgpon", "Suspenciones_gpon").replaceAll("susintraiway", "Suspenciones_IWAY").replaceAll("susotros", "Suspenciones_otros").replaceAll("tergpon", "Terminaciones_gpon").replaceAll("terintraiway", "Terminaciones_IWAY").replaceAll("terotros", "Terminaciones_otros").replaceAll("actintraway", "Activaciones_IWAY");
    }
}
