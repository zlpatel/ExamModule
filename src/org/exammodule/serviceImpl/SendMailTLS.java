package org.exammodule.serviceImpl;
import java.util.Properties;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
 
public class SendMailTLS {
 
	public void sendEmail(String emailId, String data, String input) {
 
		System.out.println("sending mail...");
		//TODO zeel: create a gmail account and provide username and password here.
		final String username = "synnovationbank@gmail.com";
		final String password = "bank@secure";
 
		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");
 
		Session session = Session.getInstance(props,
		  new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		  });
 
		try {
 
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress("synnovationbank@gmail.com"));
			message.setRecipients(Message.RecipientType.TO,
				InternetAddress.parse(emailId));
			if(input.equals("Reminder")){
				message.setSubject("Reminder for question bank");
				message.setText("Reminder for finishing the question :"
					+ data);
			}
			 
			Transport.send(message);
 
			System.out.println("Done");
 
		} catch (MessagingException e) {
			//throw new RuntimeException(e);
			System.out.println("Exception in SendMailTLS"+e);
		}
	}
	
	public void sendMailWithAttachment(String emailId, String data,String filePath,String fileName,String input){
		final String username = "synnovationbank@gmail.com";
		final String password = "bank@secure";
 
		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");
 
		Session session = Session.getInstance(props,
		  new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		  });
 
		try {
 
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress("synnovationbank@gmail.com"));
			message.setRecipients(Message.RecipientType.TO,
				InternetAddress.parse(emailId));
			
			if(input.equals("PKI")){
				message.setSubject("Synnovation Bank");
				BodyPart messageBodyPart = new MimeBodyPart();

		         // Now set the actual message
		         messageBodyPart.setText("PKI Certificate" +data);

		         // Create a multipar message
		         Multipart multipart = new MimeMultipart();

		         // Set text message part
		         multipart.addBodyPart(messageBodyPart);
		         
		         messageBodyPart = new MimeBodyPart();
		        
		         DataSource source = new FileDataSource(filePath);
		         messageBodyPart.setDataHandler(new DataHandler(source));
		         messageBodyPart.setFileName(fileName);
		         multipart.addBodyPart(messageBodyPart);
		         
		         message.setContent(multipart);
			}
			
 
			Transport.send(message);
 
			System.out.println("Done");
 
		} catch (MessagingException e) {
			//throw new RuntimeException(e);
			System.out.println("Exception in SendMailTLS"+e);
		}
	}
}