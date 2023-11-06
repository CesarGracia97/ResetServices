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
public class Jboss {

    private String Resultado = "";
    GetCommandResult commandResp = new GetCommandResult();
    private static final org.apache.log4j.Logger Applicationstasks = org.apache.log4j.Logger.getLogger("WStasks");
   static {
        PropertyConfigurator.configure("/opt/logj4.properties");
    }
    public String start_stop(String user, String host, String password, String rut, String app, String accion, String object) throws JSchException, IOException {
        String idPro = "", command = "";
        //    System.out.println("\nDATOS DEL START-STOP:\nuser: " + user + " host: " + host + " password: " + password + " ruta: " + rut + " APP: " + app+" accion "+accion+" object "+object);
        Applicationstasks.info(Jboss.class.getName() + " - DATOS DEL START-STOP:\nuser: " + user + " host: " + host + " password: " + password + " ruta: " + rut + " APP: " + app + " accion " + accion + " object " + object);

        command = rut + app + " " + accion;
    //    System.out.println(command);
        Applicationstasks.info(Jboss.class.getName() + " - Comand: " + command);
        Resultado = commandResp.slow(user, host, password, command);
        if (Resultado.contains("Program is already running") || Resultado.contains("Starting of CM") || Resultado.contains("starting Comarch CM finished")) {
            Resultado = object + "--IS RUNNING&&";
        } else {
            Resultado = object + "--IS DOWN&&";
        }

        return Resultado;
    }

}
