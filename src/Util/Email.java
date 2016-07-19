package Util;

import java.util.Properties;

import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class Email {
	public static void SendEmail(String to, String from, String sub, 
			String content, boolean bodyIsHTML) throws MessagingException {
		Properties props = new Properties();
        props.put("mail.transport.protocol", "smtps");
        props.put("mail.smtps.host", "smtp.gmail.com");
        props.put("mail.smtp.port", 465);
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.quitwait", "false");
        //NOTE: Session object is part of javax.mail.Session
        javax.mail.Session session = javax.mail.Session.getDefaultInstance(props);
        
        Message message = new MimeMessage(session);
        message.setSubject(sub);
        if (bodyIsHTML) {
        message.setContent(content, "text/html");
        } else {
        message.setText(content);
        }
        Address fromAddress = new InternetAddress(from);
        Address toAddress = new InternetAddress(to);
        message.setFrom(fromAddress);
        message.setRecipient(Message.RecipientType.TO, toAddress);
        
        Transport transport = session.getTransport();
        transport.connect("navreet.kaur10@gmail.com","");
        transport.sendMessage(message, message.getAllRecipients());
        transport.close();
		
	}

}
