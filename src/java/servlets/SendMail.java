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

import java.util.Properties;
import javax.mail.Message;
import javax.mail.Message.RecipientType;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class SendMail {

    public static String host = "192.168.21.196";
    public static String toplus = "jyacelga@tvcable.com.ec";
//   public static String cco = "jyacelga@tvcable.com.ec";
 // public static String cco = "sbarragan2@tvcable.com.ec";

    public void sendMail(String from, String m_subject, String m_body) {

        try {
            Session m_Session;
            Message m_simpleMessage;
            InternetAddress m_fromAddress;
            InternetAddress m_toAddress;
            Properties m_properties;
            m_properties = new Properties();
            m_properties.put("mail.smtp.host", host);
            m_Session = Session.getInstance(m_properties);
            m_simpleMessage = new MimeMessage(m_Session);
            m_fromAddress = new InternetAddress(from);
            m_toAddress = new InternetAddress(toplus);
            m_simpleMessage.setFrom(m_fromAddress);
            m_simpleMessage.setRecipient(RecipientType.TO, m_toAddress);
            //m_simpleMessage.setRecipients(Message.RecipientType.CC, InternetAddress.parse(cco));
            m_simpleMessage.setSubject(m_subject);
            m_simpleMessage.setContent(m_body, "text/html");
            Transport.send(m_simpleMessage);
        } catch (MessagingException ex) {
            ex.printStackTrace();
        }
    }

}
