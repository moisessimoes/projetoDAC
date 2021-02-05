package br.ifpb.dac.projeto.utils;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


public class EnviarEmail { // NÃO FUNCIONA...
	
	/*Eu tentei implementar a funcionalidade de enviar e-mail, mas infelizmente nao consegui :-(
	 * 
	 * Seria uma funcionalidade a parte, ela nao esta na descricao do projeto.
	 * */
	
	
	private static final String EMAIL_REMETENTE = "emailDoRemetente"; //email da "empresa" (remetente@gmail.com)
	
	private static final String SENHA_REMETENTE = "senha";
	
	public EnviarEmail() {

	}
	
	
	public boolean enviarEmail(String assunto, String mensagem, String emailDestino) {
		
		Properties props = new Properties();

		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.socketFactory.port", "465");
		props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.port", "465");

		Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
			
		        protected PasswordAuthentication getPasswordAuthentication() {
		            return new PasswordAuthentication(EMAIL_REMETENTE, SENHA_REMETENTE);
		        }
		    });

		session.setDebug(true);

		boolean enviou = false;

		try {

		    Message message = new MimeMessage(session);
		    message.setFrom(new InternetAddress(EMAIL_REMETENTE));
		    
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(emailDestino.toLowerCase()));
		    message.setSubject(assunto);
		    message.setText(mensagem);

		    Transport.send(message);

		    System.out.println("EMAIL ENVIADO COM SUCESSO!!!");
		    
		    enviou = true;
		    
		} catch (MessagingException e) {
			enviou = false;
		    throw new RuntimeException(e);
		}

		return enviou;
	}
}

//2º forma de enviar e-mail

//Email email = new SimpleEmail();
//
//email.setHostName("smtp.gmail.com");
//email.setSmtpPort(465);
//email.setAuthenticator(new DefaultAuthenticator(EMAIL_REMETENTE, SENHA_REMETENTE));
//email.setSSLOnConnect(true);
//email.setDebug(true);
//
//boolean enviou = false;
//
//try {
//	
//	email.setFrom(EMAIL_REMETENTE);
//	email.setSubject(assunto);
//	email.setMsg(mensagem);
//	email.addTo(emailDestino);
//	email.send();
//	
//	enviou = true;
//	
//} catch (EmailException e) {
//	enviou = false;
//	e.printStackTrace();
//}
//
//return enviou;

