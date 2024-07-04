package PASp;

import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import java.util.Properties;



public class SendEmail
{
 public static void main(String[] args) {
	 	
	 String usermail = "";
	 //recivers email
	 String to = "kavindutushanthaherath@gmail.com";
	 //senders email
	 String from = "mailtrap@kavindu.co";
	 
	 //provide mailtraps username
	 final String username = "api";
	 final String password = "7f301afac224f0bc23d1cb57e0071201";
	 
	 //provide mailtraps host address
	 String host = "live.smtp.mailtrap.io";
	 
	
	 
	 Properties props = new Properties();
	 props.put("mail.smtp.auth", "true");
	 props.put("mail.smtp.starttls.enable", "true");
	 props.put("mail.smtp.host", host);
	 props.put("mail.smtp.port", "2525");
	 
	 // create the session object
	 
	 Session session = Session.getInstance(props,
			 
			 new Authenticator() {
		 @Override
		 protected PasswordAuthentication getPasswordAuthentication() {
			 return new PasswordAuthentication (username,password);
		 }
	 });
	 
	 try {
	
	 Message message =  new MimeMessage(session);
	 message.setFrom(new InternetAddress(from));
	 message.setRecipient(Message.RecipientType.TO,  new InternetAddress((to)));
	 
	 message.setSubject("PAS Application updates");
	 message.setText("Hello test 3");
	 
	 Transport.send(message);
	 } catch (MessagingException e) {
		 throw new RuntimeException(e);
	 }
	 System.out.println("Email sent successfully");
	 
 	
	 
	 
	 
	 
 }
	
}	