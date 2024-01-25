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
public class Tomcat {

    private String Resultado = "";
    GetCommandResult commandResp = new GetCommandResult();
    private static final org.apache.log4j.Logger Applicationstasks = org.apache.log4j.Logger.getLogger("WStasks");
    
       static {
        PropertyConfigurator.configure("/opt/log4j.properties");
    }

    public String stop(String user, String host, String password, String rut, String app) throws JSchException, IOException {
        String idPro = "", command = "";
        //     System.out.println("\nDATOS DEL STOP:\nuser: " + user + " host: " + host + " password: " + password + " ruta: " + rut);
        Applicationstasks.info(Tomcat.class.getName() + " - DATOS DEL STOP:\nuser: " + user + " host: " + host + " password: " + password + " ruta: " + rut);
        String res_stop = "";

        if (app.contains("Ems")) {
            idPro = commandResp.getIdProcess("Produccion",user, host, password, "pgrep -fl [j]ava|grep " + rut + " |cut -d\" \" -f -1");
            //    System.out.println("\nSe va a matar el proceso " + idPro + " del usuario  " + app);
            Applicationstasks.info(Tomcat.class.getName() + " - Se va a matar el proceso " + idPro + " del usuario  " + user);
        } else if (user.contains("queue")) {
            command = rut + "/apache-activemq-5.7.0/bin/activemq stop";
            res_stop = commandResp.slow(user, host, password, command);
            if (res_stop.contains("FINISHED")) {
                Resultado = app + "--IS RUNNING NOW&&";
            } else {
                Resultado = app + "--IS DOWN&&";
            }
        } else {
            idPro = commandResp.getIdProcess("Produccion",user, host, password, "pgrep -fl [j]ava|grep " + rut + " |cut -d\" \" -f -1");
            //     System.out.println("\nSe va a matar el proceso " + idPro + " del usuario  " + app);
            Applicationstasks.info(Tomcat.class.getName() + " - Se va a matar el proceso " + idPro + " del usuario  " + app);
        }
        if (idPro.length() > 2) {
            command = "/bin/kill -9 " + idPro;
            res_stop = commandResp.nomal(user, host, password, command);
            if (res_stop.contains("null") || Resultado.equals("null")) {
                Resultado = app + "--IS DOWN&&";
            } else {
                Resultado = app + "--IS RUNNING&&";
            }
        } else {
            Resultado = app + "--IS DOWN&&";
        }
        //      System.out.println("Resultado_stop " + Resultado);
        Applicationstasks.info(Tomcat.class.getName() + " - Resultado_stop " + Resultado);
        return Resultado;
    }

    public String start(String user, String host, String password, String rut, String app, String esqu) throws JSchException, IOException {
        String idPro = "", command = "";
        //  System.out.println("\nDATOS DEL START:\nuser: " + user + " host: " + host + " password: " + password + " ruta: " + rut + " app: " + app + " esqu: " + esqu);
        Applicationstasks.info(Tomcat.class.getName() + " - DATOS DEL START:\nuser: " + user + " host: " + host + " password: " + password + " ruta: " + rut + " app: " + app + " esqu: " + esqu);
        String res_start = "";
        if (esqu.contains("Desa")) {
            command = "/u02/script/ems_test_desa.sh desa start";
        } else if (esqu.contains("Test")) {
            command = "/u02/script/ems_test_desa.sh test start";
        } else {
            command = "sudo service jboss-as-standalone.sh start";
        }

        idPro = commandResp.getIdProcess("Produccion",user, host, password, "pgrep -fl [j]ava|grep " + rut + " |cut -d\" \" -f -1");
        // System.out.println("\nId del proceso de " + app + " " + idPro + "  " + idPro.length());
        Applicationstasks.info(Tomcat.class.getName() + " - Id del proceso de " + app + " " + idPro + "  " + idPro.length());

        if (idPro.length() < 2) {
            if (app.equals("Ems") || user.contains("root")) {
                res_start = commandResp.slow(user, host, password, command);
                //     System.out.println("\nRESULTADO a comparar: " + res_start);

                if (res_start.contains("redhat-4) starting")) {
                    Resultado = app + "--IS RUNNING NOW&&";
                } else {
                    Resultado = app + "--IS DOWN&&";
                }
            } else if (app.contains("queue")) {
                command = rut + "/apache-activemq-5.7.0/bin/activemq start\n";
                res_start = commandResp.slow(user, host, password, command);
                if (res_start.contains("FINISHED") || res_start.contains("api.pid' (pid")) {
                    Resultado = app + "--IS RUNNING NOW&&";
                } else {
                    Resultado = app + "--IS DOWN&&";
                }

            } else {
                if (esqu.contains("Produccion") && app.contains("api")) {
                    command = rut + "/apache-tomcat-8.0.18/bin/startup.sh\n";
                } else if (esqu.contains("Produccion") && app.contains("www")) {
                    command = rut + "/apache-tomcat-8.5.75/bin/startup.sh\n";
                } else {
                    command = rut + "/apache-tomcat-7.0.50/bin/startup.sh\n";
                }

                res_start = commandResp.slow(user, host, password, command);
                if (res_start.contains("Tomcat started")) {
                    Resultado = app + "--IS RUNNING NOW&&";
                } else {
                    Resultado = app + "--IS DOWN&&";
                }
            }
        } else {
            Resultado = app + "--IS RUNNING NOW&&";
        }
        //  System.out.println("Resultado_start " + Resultado.replaceAll("OK###", ""));
        Applicationstasks.info(Tomcat.class.getName() + " - Resultado_start " + Resultado.replaceAll("OK###", ""));
        return Resultado;
    }
}
