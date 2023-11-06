/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.actions;

import com.jcraft.jsch.JSchException;
import java.io.IOException;
import org.apache.log4j.PropertyConfigurator;
import servlets.GetCommandResult;

/**
 *
 * @author jyacelga
 */
public class DpsS {

    private String Resultado = "";
    GetCommandResult commandResp = new GetCommandResult();
    private static final org.apache.log4j.Logger Applicationstasks = org.apache.log4j.Logger.getLogger("WStasks");

       static {
        PropertyConfigurator.configure("/opt/logj4.properties");
    }
    public String start(String user, String host, String password, String rut, String app) throws JSchException, IOException {
        String idPro = "", command = "";
        //     System.out.println("\nDATOS DEL START:\nuser: " + user + " host: " + host + " password: " + password + " ruta: " + rut + " APP: " + app);
        Applicationstasks.info(DpsS.class.getName() + " - DATOS DEL START:\nuser: " + user + " host: " + host + " password: " + password + " ruta: " + rut + " APP: " + app);
       // command = rut + "/versions/6.6.1/cbin/dps.sh start " + app;
        
        if (app.contains("ipb")) {
            command = rut + "/versions/6.4.3/cbin/dps.sh start "  + " " + app;

        } else {
            command = rut + "/versions/6.6.1/cbin/dps.sh start " + " " + app;
        }

        
        Applicationstasks.info(DpsS.class.getName() + " - DATOS DEL START:\nuser: " + user + " host: " + host + " password: " + password + " ruta: " + rut + " APP: " + app);
        Resultado = commandResp.slow(user, host, password, command + "\n");
        Applicationstasks.info(DpsS.class.getName() + " - Resultado: " + Resultado);
        if (Resultado.contains("started.") || Resultado.contains("this same $DPS_SID")|| Resultado.contains("working")) {
            Resultado = app + "--IS STARTED&&";
        } else {
            Resultado = app + "--IS FAIL&&";
        }

        return Resultado;
    }

    public String states(String user, String host, String password, String rut, String app, String accion) throws JSchException, IOException {
        String idPro = "", command = "";
        //       System.out.println("\nDATOS DEL STATES:\nuser: " + user + " host: " + host + " password: " + password + " ruta: " + rut + " objeto: " + app);
        Applicationstasks.info(DpsS.class.getName() + " - DATOS DEL STATES:\nuser: " + user + " host: " + host + " password: " + password + " ruta: " + rut + " objeto: " + app);
        if (app.contains("ipb")) {
            command = rut + "/versions/6.4.3/cbin/dps.sh " + accion + " " + app;

        } else {
            command = rut + "/versions/6.6.1/cbin/dps.sh " + accion + " " + app;
        }

        Resultado = commandResp.nomal(user, host, password, command);

        if (accion.equals("status")) {
            if (Resultado.contains("working")) {
                Resultado = app + "--IS RUNNING NOW&&";
            } else {
                Resultado = app + "--IS DOWN&&";
            }
        }
        if (accion.equals("abort")) {
            if (Resultado.contains("STARTED")) {
                Resultado = app + "--IS ABORTED&&";
            } else {
                Resultado = app + "--IS FAIL&&";
            }
        }
        if (accion.equals("refreshcache")) {
            if (Resultado.contains("refreshed succesfuly")) {
                Resultado = app + "--IS REFRESHED&&";
            } else {
                Resultado = app + "--IS FAIL&&";
            }
        }

        return Resultado;
    }

    public String stop(String user, String host, String password, String rut, String app, String back) throws JSchException, IOException {
        String idPro = "", command = "";
        //    System.out.println("\nDATOS DEL STATES:\nuser: " + user + " host: " + host + " password: " + password + " ruta: " + rut + " objeto: " + app);
        Applicationstasks.info(DpsS.class.getName() + " - DATOS DEL STATES:\nuser: " + user + " host: " + host + " password: " + password + " ruta: " + rut + " objeto: " + app);
        command = rut + "/versions/6.6.1/cbin/dps.sh stop " + app;

        Resultado = commandResp.nomal(user, host, password, command);

        if ((Resultado.contains("....ok"))||(Resultado.contains("FILES .DAT BACKUPED"))) {
            Resultado = app + "--IS STOPED&&";
        } else {
            Resultado = app + "--IS FAIL&&";
        }
        commandResp.slow(user, host, password, back + app);
        return Resultado;
    }
}
