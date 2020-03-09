package ecommerce.mail;

import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;

public class SendEmail {

	public void mail(String mail) {
		// change accordingly
		String to = mail;

		// change accordingly
		String from = "noreply@prova.com";

		// or IP address
		String host = "smtp.gmail.com";

		// mail id
		final String username = "tets.ecomm@gmail.com";

		// correct password for gmail id
		final String password = "asdfghjkl.";

		System.out.println("TLSEmail Start");
		// Get the session object

		// Get system properties
		Properties properties = System.getProperties();

		// Setup mail server
		properties.setProperty("mail.smtp.host", host);

		// SSL Port
		properties.put("mail.smtp.port", "465");

		// enable authentication
		properties.put("mail.smtp.auth", "true");

		// SSL Factory
		properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");

		// creating Session instance referenced to
		// Authenticator object to pass in
		// Session.getInstance argument
		Session session = Session.getDefaultInstance(properties, new javax.mail.Authenticator() {

			// override the getPasswordAuthentication
			// method
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		});

		try {
			MimeMessage message = new MimeMessage(session);
			
			// header field of the header.
			message.setFrom(new InternetAddress(from));
			String html = "\n<a href='http://localhost:8080\\backend\\bin\\src\\main\\webapp\\index.html'>Change password</a>";
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
			message.setSubject("Reset Password");
			message.setText(html, "UTF-8", "html");

			// Send message
			Transport.send(message);
			System.out.println("Email inviata");
		} catch (MessagingException mex) {
			mex.printStackTrace();
		}
	}

}