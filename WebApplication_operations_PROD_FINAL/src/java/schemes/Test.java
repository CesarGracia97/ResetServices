/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package schemes;

import app.actions.DpsS;
import app.actions.Jboss;
import app.actions.Tomcat;
import com.jcraft.jsch.JSchException;
import java.io.IOException;
import org.apache.log4j.PropertyConfigurator;
import servlets.GetCommandResult;

/**
 *
 * @author jyacelga
 */
public class Test {

    private static final org.apache.log4j.Logger Applicationstasks = org.apache.log4j.Logger.getLogger("WStasks");

       static {
        PropertyConfigurator.configure("/opt/logj4.properties");
    }
    public String proccessComand(String aplicacion, String accion, String objeto, String WSuser) throws JSchException, IOException, InterruptedException, Exception {
        //List Resultado = new ArrayList();
        GetCommandResult commandResp = new GetCommandResult();
        String host = "";
        String host_ems = "";
        String user = "";
        String password = "";
        String terminal = "";
        String back = "";
        String Resultado = "";
        String command = "";
        String ruta_ems = "";
        String ruta_toms = "";
        Tomcat toms = new Tomcat();
        DpsS dps = new DpsS();
        Jboss jboss = new Jboss();

        
        
        if (aplicacion.equals("EMS")) {
            host = "192.168.21.179:8080";
            String app = "";

            if (objeto.equals("Web_services")) {
                app = "Tomcat9test";
            } else if (objeto.equals("Ordenes_Cupos")) {
                app = "daemonEmsworkOrdersTEST";
            }
            if (objeto.contains("todos")) {
                Resultado = "ALL###";
                String tempo = "";
                String[] emsApps = "Tomcat9test,daemonEmsworkOrdersTEST,".split(",");
                for (int op = 0; op < emsApps.length; op++) {
                    if (accion.contains("status")) {
                        String result_status = "";
                        result_status = commandResp.getWinService(host, accion, emsApps[op], WSuser);
//                        System.out.println("Resultado del objeto 1: " + emsApps[op] + " -- " + result_status);
                        if (result_status.equals("0")) {
                            tempo = emsApps[op] + "--IS RUNNING";
                        } else {
                            tempo =  emsApps[op] + "--IS DOWN";
                        }
                    } else {

                        if (commandResp.getWinService(host, accion, emsApps[op], WSuser).equals("0")) {
                            if (accion.equals("start")) {
                                tempo = emsApps[op] + "--IS RUNNING";
                            } else {
                                tempo = emsApps[op] + "--IS DOWN";
                            }
                        } else {
                            tempo = emsApps[op] + "--IS DOWN";
                        }
                    }
                    Resultado += tempo+"&&";
                    Applicationstasks.info(Desarrollo.class.getName() + " - WSuser= " + WSuser + " Resultado del STATUS-ALL: " + Resultado);
                }

                //fin if todos
            } else {
                String result_status = "";
//                if (accion.equals("status")) {
                result_status = commandResp.getWinService(host, accion, app, WSuser);
                if (result_status.equals("0")) {
                    if (accion.equals("start") || accion.equals("status")) {
                        Resultado = objeto + "--IS RUNNING";
                    } else {
                        Resultado = objeto + "--IS DOWN";
                    }
                } else {
                    Resultado = objeto + "--IS DOWN";
                }
                Resultado = "ALL###" + Resultado;
                Applicationstasks.info(Desarrollo.class.getName() + " - WSuser= " + WSuser + " Resultado del STATES: " + Resultado);
            }
        }
        
        if (aplicacion.equals("DPS")) {
            host = "192.168.21.189";
            user = "dps";
            password = "dps";
            back = "/u01/dps/scripts/backup.sh  ";

            String rut = "/u01/dps/BSQATEST/";

            if (accion.equals("clearcache")) {
                accion = "refreshcache";
            }
            if (accion.equals("start")) {
                Resultado = dps.start(user, host, password, rut, objeto);
            } else if (accion.equals("stop")) {
                Resultado = dps.stop(user, host, password, rut, objeto, back);
            } else {
                Resultado = dps.states(user, host, password, rut, objeto, accion);
            }
            Resultado = "ALL###" + Resultado;
        }
        if (aplicacion.equals("JBOSS")) {
            host = "192.168.21.189";
            user = "jboss";
            password = "jboss";
            String script = "";
            String rut_jboss = "/u01/jboss/script";
            if (objeto.contains("todos")) {
                if (accion.contains("status")) {
                    String jbossC1 = commandResp.getIdProcess(user, host, password, "pgrep -fl [j]ava|grep centest |cut -d' ' -f -1");
                    //   String jbossCS = commandResp.getIdProcess(user, host, password, "pgrep -fl [j]ava|grep scheduler |cut -d\" \" -f -1");
                    int stat = 0;
                    if ((jbossC1.length() > 2)) {
                        stat = 2;
                        Resultado = "ALL###TEST--IS RUNNING";
                    }
                    if ((jbossC1.length() < 2)) {
                        stat = 0;
                        Resultado = "ALL###TEST--IS DOWN";
                    }
                    //   System.out.println("Resultado del STATUS-ALL: " + Resultado.replaceAll("null", ""));
                    Applicationstasks.info(Test.class.getName() + " - WSuser= " + WSuser + " Resultado del STOP-ALL: " + Resultado);

                } else {
//                    String[] start = "test".split(",");
                    Resultado = "ALL###";
                    String result_start = "";
                    result_start = jboss.start_stop(user, host, password, rut_jboss, "/cm.sh CC1", accion, objeto);

                    Resultado += result_start;
                    //      System.out.println("Resultado del STATES: " + Resultado);
                    Applicationstasks.info(Test.class.getName() + " - WSuser= " + WSuser + " Resultado del STATES: " + Resultado);
                }

            } else {
                if (accion.equals("status")) {
                    String jbossAL = "";
                    jbossAL = commandResp.getIdProcess(user, host, password, "ps -aux | grep java|grep qatest |cut -d\" \" -f -1");
//                    jbossAL = commandResp.getIdProcess(user, host, password, "pgrep -fl [j]ava|grep qatest |cut -d' ' -f -1");

                    if (jbossAL.length() > 2) {
                        Resultado = "ALL###" + objeto + "--IS RUNNING";
                    } else {
                        Resultado = "ALL###" + objeto + "--IS DOWN";
                    }
                } else {
                    Resultado = jboss.start_stop(user, host, password, rut_jboss, "/cm.sh CC1", accion, objeto);
                    Resultado = "ALL###" + Resultado;
                    //        System.out.println("Resultado del STATES: " + Resultado);
                    Applicationstasks.info(Test.class.getName() + " - WSuser= " + WSuser + " Resultado del STATES: " + Resultado);
                }
            }
        }

        /*procesar FSM - aplicaciones (ems, www, mobile, queue, api)*/
        if (aplicacion.equals("FSM")) {
            ruta_toms = "/u01/BSQATEST/";
            ruta_ems = "/u02/jboss-eap-6.1";
            host = "192.168.21.187";
            host_ems = "192.168.21.152";
            if (objeto.equals("www")) {
                user = "fsmwww";
                password = "fsmwww123456";
            } else if (objeto.equals("mobile")) {
                user = "fsmmobile";
                password = "fsmmobile123456";
            } else if (objeto.equals("api")) {
                user = "fsmapi";
                password = "fsmapi123456";
            } else if (objeto.equals("queue")) {
                user = "fsmqueue";
                password = "fsmqueue";
            } 
//            else if (objeto.equals("Ems")) {
//                user = "root";
//                password = "cable12";
//            }
            if (objeto.contains("todos")) {
                if (accion.contains("status")) {
//                    String jbossEms = commandResp.getIdProcess("root", host_ems, "cable12", "pgrep -fl [j]ava|grep " + ruta_ems + " |cut -d\" \" -f -1");
                    String tomMob = commandResp.getIdProcess("fsmmobile", host, "fsmmobile123456", "pgrep -fl [j]ava|grep " + ruta_toms + "fsmmobile/ |cut -d\" \" -f -1");
                    String tomWww = commandResp.getIdProcess("fsmwww", host, "fsmwww123456", "pgrep -fl [j]ava|grep " + ruta_toms + "fsmwww/ |cut -d\" \" -f -1");
                    String tomQue = commandResp.getIdProcess("fsmqueue", host, "fsmqueue", "pgrep -fl [j]ava|grep " + ruta_toms + "fsmqueue/ |cut -d\" \" -f -1");
                    String tomApi = commandResp.getIdProcess("fsmapi", host, "fsmapi123456", "pgrep -fl [j]ava|grep " + ruta_toms + "fsmapi/ |cut -d\" \" -f -1");
                    //System.out.println("IDs_FSM:\nEMS:" + jbossEms + "\nMOBILE:" + tomMob + "\nWWW:" + tomWww + "\nQUEUE:" + tomQue + "\nAPI:" + tomApi);
                    Applicationstasks.info(Test.class.getName() + " - WSuser= " + WSuser + " Resultado del STATES: " + Resultado);
                    Resultado = "ALL###";
//                    if ((jbossEms.length() > 2)) {
//                        Resultado += "EMS--IS RUNNING&&";
//                    }
                    if ((tomMob.length() > 2)) {
                        Resultado += "Mobile--IS RUNNING&&";
                    }
                    if ((tomWww.length() > 2)) {
                        Resultado += "Www--IS RUNNING&&";
                    }
                    if ((tomQue.length() > 2)) {
                        Resultado += "Queue--IS RUNNING&&";
                    }
                    if ((tomApi.length() > 2)) {
                        Resultado += "Api--IS RUNNING&&";
                    }
//                    if ((jbossEms.length() < 2)) {
//                        Resultado += "EMS--IS DOWN&&";
//                    }
                    if ((tomMob.length() < 2)) {
                        Resultado += "Mobile--IS DOWN&&";
                    }
                    if ((tomWww.length() < 2)) {
                        Resultado += "Wwww--IS DOWN&&";
                    }
                    if ((tomQue.length() < 2)) {
                        Resultado += "Queue--IS DOWN&&";
                    }
                    if ((tomApi.length() < 2)) {
                        Resultado += "Api--IS DOWN&&";
                    }
                    //          System.out.println("Resultado del STATUS-ALL: " + Resultado.replaceAll("null", ""));
                    Applicationstasks.info(Test.class.getName() + " - WSuser= " + WSuser + " Resultado del STATUS-ALL: " + Resultado.replaceAll("null", ""));

                } else if (accion.equals("start")) {
                    String[] stop = "api,queue,mobile,www,Ems".split(",");
                    Resultado = "ALL###";
                    String result_start = "";
                    for (int op = 0; op < stop.length; op++) {
                        if (stop[op].equals("www")) {
                            user = "fsmwww";
                            password = "fsmwww123456";
                        } else if (stop[op].equals("mobile")) {
                            user = "fsmmobile";
                            password = "fsmmobile123456";
                        } else if (stop[op].equals("api")) {
                            user = "fsmapi";
                            password = "fsmapi123456";
                        } else if (stop[op].equals("queue")) {
                            user = "fsmqueue";
                            password = "fsmqueue";
                        } 
//                        else if (stop[op].equals("Ems")) {
//                            user = "root";
//                            password = "cable12";
//                        }
//                        if (stop[op].contains("Ems")) {
//                            result_start = toms.start(user, host_ems, password, ruta_ems, stop[op], "Test");
//                        } else {
                            
                            result_start = toms.start(user, host, password, ruta_toms + user, stop[op], "Test");
//                        }
                        Resultado += result_start;
                    }
                    Resultado.replaceAll("OK###", "");
                    // System.out.println("Resultado del START-ALL: " + Resultado);
                    Applicationstasks.info(Test.class.getName() + " - WSuser= " + WSuser + " Resultado del START-ALL: " + Resultado);
                } else if (accion.equals("stop")) {
                    String[] stop = "www,mobile,queue,api".split(",");
                    Resultado = "ALL###";
                    String result_stop = "";
                    for (int op = 0; op < stop.length; op++) {
                        if (stop[op].equals("www")) {
                            user = "fsmwww";
                            password = "fsmwww123456";
                        } else if (stop[op].equals("mobile")) {
                            user = "fsmmobile";
                            password = "fsmmobile123456";
                        } else if (stop[op].equals("api")) {
                            user = "fsmapi";
                            password = "fsmapi123456";
                        } else if (stop[op].equals("queue")) {
                            user = "fsmqueue";
                            password = "fsmqueue";
                        }
//                        else if (stop[op].equals("Ems")) {
//                            user = "root";
//                            password = "cable12";
//                        }
//                        if (stop[op].contains("Ems")) {
//                            result_stop = toms.stop(user, host_ems, password, ruta_ems, stop[op]);
//                        } else {
                            
                            result_stop = toms.stop(user, host, password, ruta_toms + user, stop[op]);
//                        }
                        if (result_stop.contains("DOWN")) {
                            Resultado += result_stop;
                        } else {
                            Resultado += result_stop;
                        }
                    }
                    Resultado.replaceAll("null", "").replaceAll("OK###", "");
                    //          System.out.println("Resultado del STOP-ALL: " + Resultado);
                    Applicationstasks.info(Test.class.getName() + " - WSuser= " + WSuser + " Resultado del STOP-ALL: " + Resultado);
                }
            } else {
                if (accion.equals("status")) {
                    if ((objeto.equals("mobile")) || (objeto.equals("www")) || (objeto.equals("api") || (objeto.equals("queue")))) {
                        String jbossAL = "";
//                        if (objeto.equals("Ems")) {
//                            jbossAL = commandResp.getIdProcess(user, host_ems, password, "pgrep -fl [j]ava|grep " + ruta_ems + " |cut -d\" \" -f -1");
//                        } else {
                            
                            jbossAL = commandResp.getIdProcess(user, host, password, "pgrep -fl [j]ava|grep " + ruta_toms + user + " |cut -d\" \" -f -1");
//                        }
                        if (jbossAL.length() > 2) {
                            Resultado = "ALL###" + objeto + "--IS RUNNING";
                        } else {
                            Resultado = "ALL###" + objeto + "--IS DOWN";
                        }
                    }
                } else {
                    if (accion.equals("start")) {
                        if (objeto.equals("www")) {
                            user = "fsmwww";
                            password = "fsmwww123456";
                        } else if (objeto.equals("mobile")) {
                            user = "fsmmobile";
                            password = "fsmmobile123456";
                        } else if (objeto.equals("api")) {
                            user = "fsmapi";
                            password = "fsmapi123456";
                        } else if (user.equals("queue")) {
                            user = "fsmqueue";
                            password = "fsmqueue";
                        } 
//                        else if (user.equals("Ems")) {
//                            user = "root";
//                            password = "cable12";
//                        }
//                        if (objeto.contains("Ems")) {
//                            Resultado = toms.start(user, host_ems, password, ruta_ems, objeto, "Test");
//                        } else {
                            Resultado = toms.start(user, host, password, ruta_toms + user, objeto, "Test");
//                        }
                        if (Resultado.contains("RUNNING")) {
                            Resultado = "ALL###" + Resultado;
                        } else {
                            Resultado = "ALL###" + Resultado;
                        }
                    }
                    if (accion.equals("stop")) {
                        if (user.equals("www")) {
                            user = "fsmwww";
                            password = "fsmwww";
                        } else if (user.equals("mobile")) {
                            user = "fsmmobile";
                            password = "fsmmobile";
                        } else if (user.equals("api")) {
                            user = "fsmapi";
                            password = "fsmapi";
                        } else if (user.equals("queue")) {
                            user = "fsmqueue";
                            password = "fsmqueue";
                        } 
//                        else if (user.equals("Ems")) {
//                            user = "root";
//                            password = "cable12";
//                        }
//                        if (objeto.contains("Ems")) {
//                            Resultado = toms.stop(user, host_ems, password, ruta_ems, objeto);
//                        } else {
                            Resultado = toms.stop(user, host, password, ruta_toms + user, objeto);
//                        }
                        if (Resultado.contains("DOWN")) {
                            Resultado = "ALL###" + Resultado;
                        } else {
                            Resultado = "ALL###" + Resultado;
                        }
                    }
                }
            }
        }
        Applicationstasks.info(Test.class.getName() + " - WSuser= " + WSuser + " Resultado TEST: " + Resultado);

        return Resultado;
    }

}
