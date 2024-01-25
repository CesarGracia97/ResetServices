/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import app.actions.winServices;
import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.ChannelShell;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import static java.lang.System.out;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

/**
 *
 * @author jyacelga
 */
public class GetCommandResult {

    private static final Logger Applicationstasks = Logger.getLogger("WStasks");
    winServices winser = new winServices();

    static {
        PropertyConfigurator.configure("/opt/log4j.properties");
    }

    public String nomal(String user, String host, String password, String command) {
        String result = null, cmd = null;

        InputStream in = null;
        //  FileOutputStream fos = new FileOutputStream("/ErrLog");
        com.jcraft.jsch.Session jschsess = null;

        try {
            JSch jsch = new JSch();
            Properties conf = new Properties();
            conf.put("StrictHostKeyChecking", "no");
            jschsess = jsch.getSession(user, host);
            jschsess.setConfig(conf);
            jschsess.setPort(22);
            jschsess.setPassword(password);
            jschsess.connect();
            Channel channel = jschsess.openChannel("exec");
            ((ChannelExec) channel).setPty(true);
            ((ChannelExec) channel).setCommand(command);
            channel.setInputStream(null);
            // ((ChannelExec) channel).setErrStream(fos);
            in = channel.getInputStream();
            channel.connect();

            byte[] tmp = new byte[1024];
            while (true) {
                while (in.available() > 0) {
                    int i = in.read(tmp, 0, 1024);
                    if (i < 0) {
                        break;
                    }
                    //     out.print(new String(tmp, 0, i));
                    result = new String(tmp, 0, i);
                    Applicationstasks.info(GetCommandResult.class.getName() + " - RESULT " + result);

                }
                if (channel.isClosed()) {
                    if (in.available() > 0) {
                        int i = in.read(tmp, 0, 1024);
                        out.print(new String(tmp, 0, i));
                    }
                    if (channel.getExitStatus() == 0) {
                        out.println("Command has been posted to the server.");
                        cmd = "OK";
                    } else {
                        cmd = "FALLIDO";
                    }

                    break;
                }
                try {
                    Thread.sleep(1000);
                } catch (Exception ee) {
//                    out.println("</br></br>");
//                    out.println("Error. " + ee.toString());
                    result = ee.toString();
                }

                //    Thread.sleep(20000);
            }
            channel.disconnect();
            jschsess.disconnect();
            //           System.out.println("+++++++++++\n" + result);
            //           System.out.println(cmd);
        } catch (Exception e) {
            result = e.toString();
        }
        return cmd + result;
    }

    public String slow(String user, String host, String password, String command) throws JSchException, IOException {
        JSch jsch = new JSch();
        List result = new ArrayList();
        int port = 22;
        Session session = jsch.getSession(user, host, port);
        session.setPassword(password);
        session.setConfig("StrictHostKeyChecking", "no");
        session.connect();
        Channel channel = session.openChannel("shell");
        OutputStream ops = channel.getOutputStream();
        PrintStream ps = new PrintStream(ops, true);
        channel.connect();
        InputStream input = channel.getInputStream();
        String[] div = command.split("\n");
        for (int e = 0; e < div.length; e++) {
            ps.println(div[e]);
            //      System.out.println("comandos " + div[e]);
            Applicationstasks.info(GetCommandResult.class.getName() + " - Comandos " + div[e]);
        }
        ps.close();
        int SIZE = 1024;
        byte[] tmp = new byte[SIZE];
        int v = 0;
        while (true) {
            while (input.available() > 0) {
                int i = input.read(tmp, 0, SIZE);
                if (i < 0) {
                    //         System.out.print("--" + new String(tmp, 0, i) + "***");
                    break;
                }
//                System.out.print("--" + new String(tmp, 0, i) + "***");
                //            Applicationstasks.info(GetCommandResult.class.getName() + " --" + new String(tmp, 0, i) + "***");
                result.add(new String(tmp, 0, i));
            }
            if (channel.isClosed()) {
                //       System.out.println("exit-status: " + channel.getExitStatus());
                Applicationstasks.info(GetCommandResult.class.getName() + " - exit-status: " + channel.getExitStatus());
                break;
            }
            try {
                Thread.sleep(1000);
                //      System.out.print("\n" + v++ + " tamaño " + result.size());
                Applicationstasks.info(GetCommandResult.class.getName() + " - VALOR " + v++ + " tamaño " + result.size());
                if ((result.size() == 2) && (v > 20)) {
                    break;
                }
                if ((result.size() == 1) && (v > 10)) {
                    break;
                }
                if (v >= 48) {
                    break;
                }
                if (v >= 20 && result.size() > 66) {
                    break;
                }
            } catch (Exception ee) {
                //           System.out.println(ee);
                Applicationstasks.info(GetCommandResult.class.getName() + " - ERROR " + ee);
            }
        }
        //   System.out.println("\nTamaño " + result.size());
        Applicationstasks.info(GetCommandResult.class.getName() + " - Tamaño " + result.size());
        channel.disconnect();
        session.disconnect();
        String resp = "";

        for (int er = 0; er < result.size(); er++) {
            resp += result.get(er);
        }
        // System.out.println("SLOW >>> " + resp);
        Applicationstasks.info(GetCommandResult.class.getName() + " - SLOW >>> " + resp);
        return resp;
    }

    public String getIdProcess(String rama, String user, String host, String password, String command) throws JSchException, IOException {
        String result = "";
        InputStream in = null;
        com.jcraft.jsch.Session jschsess = null;

        try {
            JSch jsch = new JSch();
            Properties conf = new Properties();
            conf.put("StrictHostKeyChecking", "no");
            jschsess = jsch.getSession(user, host);
            jschsess.setConfig(conf);
            jschsess.setPort(22);
            jschsess.setPassword(password);
            jschsess.connect();
            Channel channel = jschsess.openChannel("exec");
            ((ChannelExec) channel).setPty(false);
            ((ChannelExec) channel).setCommand(command);
            channel.setInputStream(null);
            ((ChannelExec) channel).setErrStream(System.err);

            in = channel.getInputStream();
            channel.connect();

            byte[] tmp = new byte[1024];
            int v = 0;
            while (true) {
                while (in.available() > 0) {
                    int i = in.read(tmp, 0, 1024);
                    if (i < 0) {
                        break;
                    }
                    result = new String(tmp, 0, i);

                }
                if (channel.isClosed()) {
                    if (in.available() > 0) {
                        int i = in.read(tmp, 0, 1024);
                    }
                    if (channel.getExitStatus() == 0) {
                    }
                    break;
                }
                try {
                    Thread.sleep(1000);
                } catch (Exception ee) {
                    System.out.println("-------------------------------------------------");
                    System.out.println("GetCommandResult - Error Detectado (TC Interno)");
                    System.out.println("GetCommandResult - Rama: "+rama);
                    System.out.println("GetCommandResult - Aplicacion: "+user);
                    System.out.println("Razon del Error: "+ee);
                    System.out.println("-------------------------------------------------");
                    Applicationstasks.info(GetCommandResult.class.getName() + " - ERROR >>> " + ee);
                }
            }
            channel.disconnect();
            jschsess.disconnect();
        } catch (Exception e) {
            System.out.println("-------------------------------------------------");
            System.out.println("GetCommandResult - Error Detectado (TC Externo)");
            System.out.println("GetCommandResult - Rama: "+rama);
            System.out.println("GetCommandResult - Aplicacion: "+user);
            System.out.println("Razon del Error: "+e);
            System.out.println("-------------------------------------------------");
            Applicationstasks.info(GetCommandResult.class.getName() + " - ERROR >>> " + e);
        }
        return result;
    }

    public String getWinService(String server, String action, String servicename, String WSuser) throws IOException, InterruptedException, Exception {
        return winser.winServices(server, action, servicename,WSuser);
    }
}
