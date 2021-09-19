package com.Infra;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;



public class MailGeneration {
	
	public static String to="",cc="",subject="",attachmentPath="";
	public static String userName="qaautomationrun@planful.com";

	
	public static void sendMail(String mailType) throws Exception
	{
		subject="Orange Core Regression Test Suite on  Release";
		attachmentPath=HTMLPreparation.generateMail(mailType);
		System.out.println("attachment path");
		triggerSendMail();
		System.out.println("Mail trigerred");
	}
	
	public static void  triggerSendMail()throws MessagingException, IOException, GeneralSecurityException {
    	try {
    		Properties props = new Properties();
    		String host = "smtp.gmail.com";

    		props.put("mail.smtp.starttls.enable", "true");

    		props.put("mail.smtp.host", host);

    		props.put("mail.smtp.user", "malbariamit006@gmail.com");

    		props.put("mail.smtp.password", "Amit007@gmail");

    		props.put("mail.smtp.port", "587");

    		props.put("mail.smtp.auth", "true");
            Session session = Session.getDefaultInstance(props, null);
            MimeMessage email = new MimeMessage(session);
            email.setSubject(subject);
            email.setFrom(new InternetAddress(userName));
            email.addRecipients(Message.RecipientType.TO, to);;
            email.addRecipients(Message.RecipientType.CC, cc);
            Multipart multipart = new MimeMultipart();
            MimeBodyPart messageBodyPart = new MimeBodyPart();
            DataSource source = new FileDataSource(attachmentPath);
            messageBodyPart.setDataHandler(new DataHandler(source));
            multipart.addBodyPart(messageBodyPart);
            email.setContent(multipart);
            //Triggering Mail
            
            Transport transport = session.getTransport("smtp");

            transport.connect(host, "malbariamit006@gmail.com", "Amit007@gmail");

            transport.sendMessage(email, email.getAllRecipients());

            transport.close();
            
            //HA.Utilities.OAuth.Oauth_trigger_mail(userName,email);
           
    	}
    	
		catch (Exception e){
			/*
			 * e.printStackTrace(); mail.logger.error(e);
			 * Driver.getLogger().info("Unable to send please contact your administrator");
			 */
			}
        
    }
	
	public static void main(String[] args) throws Exception {
		sendMail("start");
	}

}
