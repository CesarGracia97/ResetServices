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
public class Produccion {

    private static final org.apache.log4j.Logger Applicationstasks = org.apache.log4j.Logger.getLogger("WStasks");

    static {
        PropertyConfigurator.configure("/opt/log4j.properties");
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
        String dps1 = "";
        String rut = "";

        Tomcat toms = new Tomcat();
        DpsS dps = new DpsS();
        Jboss jboss = new Jboss();

        if (aplicacion.equals("Aprov_Daemons")) {
            host = "192.168.21.205";
            String app = "";

            if (objeto.equals("Activaciones_gpon")) {
                app = "actgpon";
            }
            if (objeto.equals("Activaciones_IWAY")) {
                app = "actintraway";
            }
            if (objeto.equals("Activaciones_otros")) {
                app = "actotros";
            }
            if (objeto.equals("Del_ONU Flujo")) {
                app = "delonuflow";
            }
            if (objeto.equals("Ippv_comands")) {
                app = "ippv";
            }
            if (objeto.equals("Reconecciones_gpon")) {
                app = "recgpon";
            }
            if (objeto.equals("Reconecciones_IWAY")) {
                app = "recintraiway";
            }
            if (objeto.equals("Reconecciones_otros")) {
                app = "recotros";
            }
            if (objeto.equals("Suspenciones_gpon")) {
                app = "susgpon";
            }
            if (objeto.equals("Suspenciones_IWAY")) {
                app = "susintraiway";
            }
            if (objeto.equals("Suspenciones_otros")) {
                app = "susotros";
            }
            if (objeto.equals("Terminaciones_gpon")) {
                app = "tergpon";
            }
            if (objeto.equals("Terminaciones_IWAY")) {
                app = "terintraiway";
            }
            if (objeto.equals("Terminaciones_otros")) {
                app = "terotros";
            }

            if (objeto.contains("Todos")) {
                Resultado = "ALL###";
                String tempo = "";
                String[] emsApps = "actgpon,actintraway,actotros,delonuflow,ippv,recgpon,recintraiway,recotros,susgpon,susintraiway,susotros,tergpon,terintraiway,terotros,".split(",");
                for (int op = 0; op < emsApps.length; op++) {
                    if (accion.contains("status")) {
                        String result_status = "";
                        result_status = commandResp.getWinService(host, accion, emsApps[op], WSuser);
//                        System.out.println("Resultado del objeto 1: " + emsApps[op] + " -- " + result_status);
                        if (result_status.equals("0")) {
                            tempo = emsApps[op] + "--IS RUNNING";
                        } else {
                            tempo = emsApps[op] + "--IS DOWN";
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
                    Resultado += tempo + "&&";
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
        if (aplicacion.equals("EMS")) {
            host = "192.168.21.179:8080";
            String app = "";

            if (objeto.equals("Web_services")) {
                app = "Tomcat9";
            } else if (objeto.equals("Ordenes_Cupos")) {
                app = "daemonEmsworkOrdersPROD";
            }
            if (objeto.contains("todos")) {
                Resultado = "ALL###";
                String tempo = "";
                String[] emsApps = "Tomcat9,daemonEmsworkOrdersPROD,".split(",");
                for (int op = 0; op < emsApps.length; op++) {
                    if (accion.contains("status")) {
                        String result_status = "";
                        result_status = commandResp.getWinService(host, accion, emsApps[op], WSuser);
//                        System.out.println("Resultado del objeto 1: " + emsApps[op] + " -- " + result_status);
                        if (result_status.equals("0")) {
                            tempo = emsApps[op] + "--IS RUNNING";
                        } else {
                            tempo = emsApps[op] + "--IS DOWN";
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
                    Resultado += tempo + "&&";
                    Applicationstasks.info(Desarrollo.class.getName() + " - WSuser= " + WSuser + " Resultado del STATUS-ALL: " + Resultado);
                }

                //fin if todos
            } else {
                String result_status = "";
               
                //// SOLO ESTOPARA NO DETENER SERVICIOS  ///
                if (!accion.equals("stop")) {
                    result_status = commandResp.getWinService(host, accion, app, WSuser);
                }else{
                    result_status = "0";
                }
                /////
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
            host = "192.168.21.147";
            back = "/u01/scripts/backup.sh ";

            if (accion.equals("clearcache")) {
                accion = "refreshcache";
            }
            if (objeto.equals("ipb")) {
                user = "dpsipb";
                password = "ca123";
                rut = "/u01/dpsipb";
            } else {
                user = "dpsbs";
                password = "dpsprod66";
                rut = "/u01/dpsbs";
            }

            if (objeto.contains("todos")) {
                Resultado = "ALL###";
//                String[] dpsS = "ipb1,".split(",");
                String[] dpsS = "rating1,dunning1,fin1,invdem1,ipb1".split(",");
                String result_start = "";
                for (int e = 0; e < dpsS.length; e++) {
                    if (dpsS[e].equals("ipb1")) {
                        user = "dpsipb";
                        password = "ca123";
                        rut = "/u01/dpsipb";
                    } else {
                        user = "dpsbs";
                        password = "dpsprod66";
                        rut = "/u01/dpsbs";
                    }
                    result_start = dps.states(user, host, password, rut, dpsS[e], accion);
                    Resultado += result_start;
                }
                //        System.out.println("Resultado del STATES: " + Resultado);
                Applicationstasks.info(Produccion.class.getName() + " - WSuser= " + WSuser + " Resultado del STATES: " + Resultado);
            } else {
                if (accion.equals("start")) {
                    Resultado = dps.start(user, host, password, rut, objeto + "1");
                } else if (accion.equals("stop")) {
                    Resultado = dps.stop(user, host, password, rut, objeto + "1", back);
                } else {
                    Resultado = dps.states(user, host, password, rut, objeto + "1", accion);
                }
                Resultado = "ALL###" + Resultado;
            }
        }

// CUSTOMER MANAGEMENT --- JBOSS
        if (aplicacion.equals("JBOSS")) {
            host = "192.168.21.175";
            user = "jboss";
            password = "jb0ss";
            String script = "", cm_instance = "", desc = "";
            String rut_jboss = "/u02/app/jboss/scripts";

            if (objeto.contains("8080")) {
                cm_instance = "cm-6.6.1-linux-x86_64-1";
                desc = "/cm_web.sh CC1";
            } else if (objeto.contains("8081")) {
                cm_instance = "cm-6.6.1-linux-x86_64-2";
                desc = "/cm_web.sh CC2";
            } else if (objeto.contains("8082")) {
                cm_instance = "cm-6.6.1-linux-x86_64-3";
                desc = "/cm_web.sh CC3";
            } else if (objeto.contains("8083")) {
                cm_instance = "cm-6.6.1-linux-x86_64-4";
                desc = "/cm_web.sh CC4";
            } else if (objeto.contains("8084")) {
                cm_instance = "cm-6.6.1-linux-x86_64-5";
                desc = "/cm_web.sh CC5";
            } else if (objeto.contains("8085")) {
                cm_instance = "cm-6.6.1-linux-x86_64-6";
                desc = "/cm_web.sh CC6";
            } else if (objeto.contains("Schelduler")) {
                cm_instance = "cm-6.6.1-linux-x86_64-scheduler";
                desc = "/cm_web.sh CCS";
            }

            if (objeto.contains("Todos")) {
                Resultado = "ALL###";
                String[] ports = "8080,8081,8082,8083,8084,8085,Schelduler".split(",");
                String[] descr = "CC1,CC2,CC3,CC4,CC5,CC6,CCS".split(",");
                String[] ruts = "cm-6.6.1-linux-x86_64-1,cm-6.6.1-linux-x86_64-2,cm-6.6.1-linux-x86_64-3,cm-6.6.1-linux-x86_64-4,cm-6.6.1-linux-x86_64-5,cm-6.6.1-linux-x86_64-6,cm-6.6.1-linux-x86_64-scheduler".split(",");
                if (accion.contains("status")) {
                    String jbossCom = "";
                    String resp_jbos = "";
                    for (int jb = 0; jb < ruts.length; jb++) {
                        jbossCom = commandResp.getIdProcess("Produccion",user, host, password, "pgrep -a [j]ava|grep " + ruts[jb] + " |cut -d\" \" -f -1");
                        //             System.out.println("Id del " + ruts[jb] + ": " + jbossCom);
                        Applicationstasks.info(Produccion.class.getName() + " - WSuser= " + WSuser + " Id del " + ruts[jb] + ": " + jbossCom);
                        if (jbossCom.length() > 2) {
                            resp_jbos = ports[jb] + "--IS RUNNING&&";
                        } else {
                            resp_jbos = ports[jb] + "--IS DOWN&&";
                        }
                        Resultado += resp_jbos;
                    }
                    //  System.out.println("Resultado del STATES: " + Resultado);
                    Applicationstasks.info(Produccion.class.getName() + " - WSuser= " + WSuser + " Resultado del STATES: " + Resultado);
                } else {
                    String result_start = "";
                    for (int jb1 = 0; jb1 < ruts.length; jb1++) {
                        result_start = jboss.start_stop(user, host, password, rut_jboss, script, accion, descr[jb1]);
                        Resultado += result_start;
                    }
                    //          System.out.println("Resultado del START-STOP: " + Resultado);
                    Applicationstasks.info(Produccion.class.getName() + " - WSuser= " + WSuser + " Resultado del START-STOP: " + Resultado);
                }
            } else {
                String jbossAL = "";
                jbossAL = commandResp.getIdProcess("Produccion",user, host, password, "pgrep -a [j]ava|grep " + cm_instance + " |cut -d\" \" -f -1");
                //      System.out.println("Id del " + objeto + ": " + jbossAL);
                Applicationstasks.info(Produccion.class.getName() + " - WSuser= " + WSuser + " Id del " + objeto + ": " + jbossAL);

                if (accion.equals("status")) {
                    if (jbossAL.length() > 2) {
                        Resultado = "ALL###" + objeto + "--IS RUNNING";
                    } else {
                        Resultado = "ALL###" + objeto + "--IS DOWN";
                    }
                } else {
                    Resultado = jboss.start_stop(user, host, password, rut_jboss, desc, accion, objeto);
                    Resultado = "ALL###" + Resultado;
                    //               System.out.println("Resultado del START-STOP: " + Resultado);
                    Applicationstasks.info(Produccion.class.getName() + " - WSuser= " + WSuser + " Resultado del START-STOP: " + Resultado);
                }
            }
        }

        //procesar FSM - aplicaciones (ems, www, mobile, queue, api)
        if (aplicacion.equals("FSM")) {
            ruta_toms = "/u01/";
            ruta_ems = "/u02/jboss-eap-6.1";
            host_ems = "192.168.21.179";
            if (objeto.equals("www")) {
                host = "192.168.21.184";
                user = "fsmwww";
                password = "fsmwww";
            } else if (objeto.equals("mobile")) {
                host = "192.168.21.185";
                user = "fsmmobile";
                password = "fsmmobile";
            } else if (objeto.equals("api")) {
                host = "192.168.21.186";
                user = "fsmapi";
                password = "fsmapi";
            } else if (objeto.equals("queue")) {
                host = "192.168.21.186";
                user = "fsmqueue";
                password = "fsmqueue";
            }
//            else if (objeto.equals("Ems")) {
//                user = "root";
//                password = "cable12";
//            }
            if (objeto.contains("todos")) {
                if (accion.contains("status")) {
//                    String jbossEms = commandResp.getIdProcess("root", host_ems, "cable12", "pgrep -fl [j]ava|grep " + ruta_ems + " |cut -d\" \" -f -1       fsmapi | cut -d' ' -f1 | cut -c1");
                    String tomMob = commandResp.getIdProcess("Produccion","fsmmobile", "192.168.21.185", "fsmmobile123456", "pgrep -fl [j]ava|grep " + ruta_toms + "fsmmobile |cut -d\" \" -f -1");
                    String tomWww = commandResp.getIdProcess("Produccion","fsmwww", "192.168.21.184", "fsmwww123456", "pgrep -fl [j]ava|grep " + ruta_toms + "fsmwww |cut -d\" \" -f -1");
                    String tomQue = commandResp.getIdProcess("Produccion","fsmqueue", "192.168.21.186", "fsmqueue123456", "pgrep -fl [j]ava|grep " + ruta_toms + "fsmqueue |cut -d\" \" -f -1");
                    String tomApi = commandResp.getIdProcess("Produccion","fsmapi", "192.168.21.186", "fsmapi123456", "pgrep -fl [j]ava|grep " + ruta_toms + "fsmapi |cut -d\" \" -f -1");
                    //            System.out.println("IDs_FSM:\nEMS:" + jbossEms + "\nMOBILE:" + tomMob + "\nWWW:" + tomWww + "\nQUEUE:" + tomQue + "\nAPI:" + tomApi);
                    Applicationstasks.info(Produccion.class.getName() + " - WSuser= " + WSuser + " IDs_FSM: MOBILE:" + tomMob + " WWW:" + tomWww + " QUEUE:" + tomQue + " API:" + tomApi);
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
                    //        System.out.println("Resultado del STATUS-ALL: " + Resultado.replaceAll("null", ""));
                    Applicationstasks.info(Produccion.class.getName() + " - WSuser= " + WSuser + " Resultado del STATUS-ALL: " + Resultado.replaceAll("null", ""));

                } else if (accion.equals("start")) {
                    String[] stop = "api,queue,mobile,www".split(",");
                    Resultado = "ALL###";
                    String result_start = "";
                    for (int op = 0; op < stop.length; op++) {
                        if (stop[op].equals("www")) {
                            host = "192.168.21.184";
                            user = "fsmwww";
                            password = "fsmwww";
                        } else if (stop[op].equals("mobile")) {
                            host = "192.168.21.185";
                            user = "fsmmobile";
                            password = "fsmmobile";
                        } else if (stop[op].equals("api")) {
                            host = "192.168.21.186";
                            user = "fsmapi";
                            password = "fsmapi";
                        } else if (stop[op].equals("queue")) {
                            host = "192.168.21.186";
                            user = "fsmqueue";
                            password = "fsmqueue";
                        }
//                        else if (stop[op].equals("Ems")) {
//                            user = "root";
//                            password = "cable12";
//                        }
                        if (stop[op].contains("Ems")) {
                            result_start = toms.start(user, host_ems, password, ruta_ems, stop[op], "Produccion");
                        } else {
                            result_start = toms.start(user, host, password, ruta_toms + user, stop[op], "Produccion");
                        }
                        Resultado += result_start;
                    }
                    Resultado.replaceAll("OK###", "");
                    //       System.out.println("Resultado del START-ALL: " + Resultado);
                    Applicationstasks.info(Produccion.class.getName() + " - WSuser= " + WSuser + " Resultado del START-ALL: " + Resultado);
                } else if (accion.equals("stop")) {
                    String[] stop = "Ems,www,mobile,queue,api".split(",");
                    Resultado = "ALL###";
                    String result_stop = "";
                    for (int op = 0; op < stop.length; op++) {
                        if (stop[op].equals("www")) {
                            host = "192.168.21.184";
                            user = "fsmwww";
                            password = "fsmwww";
                        } else if (stop[op].equals("mobile")) {
                            host = "192.168.21.185";
                            user = "fsmmobile";
                            password = "fsmmobile";
                        } else if (stop[op].equals("api")) {
                            host = "192.168.21.186";
                            user = "fsmapi";
                            password = "fsmapi123456";
                        } else if (stop[op].equals("queue")) {
                            host = "192.168.21.186";
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
                        //}
                        if (result_stop.contains("DOWN")) {
                            Resultado += result_stop;
                        } else {
                            Resultado += result_stop;
                        }
                    }
                    Resultado.replaceAll("null", "").replaceAll("OK###", "");
                    //         System.out.println("Resultado del STOP-ALL: " + Resultado);
                    Applicationstasks.info(Produccion.class.getName() + " - WSuser= " + WSuser + " Resultado del STOP-ALL: " + Resultado);
                }
            } else {
                if (accion.equals("status")) {
                    if ((objeto.equals("mobile")) || (objeto.equals("www")) || (objeto.equals("api") || (objeto.equals("queue")))) {
                        String jbossAL = "";
//                        if (objeto.equals("Ems")) {
//                            jbossAL = commandResp.getIdProcess(user, host_ems, password, "pgrep -fl [j]ava|grep " + ruta_ems + " |cut -d\" \" -f -1");
//                        } else {
                        jbossAL = commandResp.getIdProcess("Produccion",user, host, password, "pgrep -fl [j]ava|grep " + ruta_toms + user + " |cut -d\" \" -f -1");
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
                            host = "192.168.21.184";
                            user = "fsmwww";
                            password = "fsmwww";
                        } else if (objeto.equals("mobile")) {
                            host = "192.168.21.185";
                            user = "fsmmobile";
                            password = "fsmmobile";
                        } else if (objeto.equals("api")) {
                            host = "192.168.21.186";
                            user = "fsmapi";
                            password = "fsmapi";
                        } else if (objeto.equals("queue")) {
                            host = "192.168.21.186";
                            user = "fsmqueue";
                            password = "fsmqueue";
                        }
//                        else if (user.equals("Ems")) {
//                            user = "root";
//                            password = "cable12";
//                        }
//                        if (objeto.contains("Ems")) {
//                            Resultado = toms.start(user, host_ems, password, ruta_ems, objeto, "Produccion");
//                        } else {
                        Resultado = toms.start(user, host, password, ruta_toms + user, objeto, "Produccion");
//                        }
                        if (Resultado.contains("RUNNING")) {
                            Resultado = "ALL###" + Resultado;
                        } else {
                            Resultado = "ALL###" + Resultado;
                        }
                    }
                    if (accion.equals("stop")) {
                        if (objeto.equals("www")) {
                            host = "192.168.21.184";
                            user = "fsmwww";
                            password = "fsmwww";
                        } else if (objeto.equals("mobile")) {
                            host = "192.168.21.185";
                            user = "fsmmobile";
                            password = "fsmmobile";
                        } else if (objeto.equals("api")) {
                            host = "192.168.21.186";
                            user = "fsmapi";
                            password = "fsmapi";
                        } else if (objeto.equals("queue")) {
                            host = "192.168.21.186";
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
        Applicationstasks.info(Produccion.class.getName() + " - WSuser= " + WSuser + " Resultado PROD " + Resultado);

        return Resultado;
    }

}
