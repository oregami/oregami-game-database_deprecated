package org.oregami.util;

import javax.mail.internet.MimeMessage.RecipientType;

import org.codemonkey.simplejavamail.Email;
import org.codemonkey.simplejavamail.Mailer;
import org.oregami.dropwizard.MailConfiguration;

public class MailHelper {

	MailConfiguration mailConfiguration;

	private static MailHelper instance = null;
	
	private MailHelper(MailConfiguration mailConfiguration) {
		this.mailConfiguration = mailConfiguration;
	}

	public static void init(MailConfiguration mailConfiguration) {
		instance = new MailHelper(mailConfiguration);//mailConfiguration.getPort(), mailConfiguration.getHost(), mailConfiguration.getUsername(), mailConfiguration.getPassword());
	}
	
	public static MailHelper instance() {
		if (instance==null) {
			throw new RuntimeException("MailHelper must be initialized");
		}
		return instance;
	}
	
	public void sendMail(String recipient, String subject, String text) {
		final Email email = new Email();

		email.setFromAddress("Oregami", "mail@oregami.org");
		email.setSubject(subject);
		email.addRecipient(recipient, recipient, RecipientType.TO);
		email.setText(text);

		new Mailer(mailConfiguration.getHost(), Integer.parseInt(mailConfiguration.getPort()), mailConfiguration.getUsername(), mailConfiguration.getPassword()).sendMail(email);

	}
}
