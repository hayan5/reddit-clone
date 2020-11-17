package com.ayan.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.ayan.config.EmailConfig;
import com.ayan.exception.SpringRedditException;
import com.ayan.model.NotificationEmail;

@Service
public class MailService {
	
	@Autowired
	private EmailConfig emailConfig;
	
	@Autowired
	private MailContentBuilder mailContentBuilder;
	
	
	public JavaMailSender getJavaMailSender() {
		JavaMailSenderImpl mailSenderImpl = new JavaMailSenderImpl();
		mailSenderImpl.setHost(emailConfig.getHost());
		mailSenderImpl.setPort(emailConfig.getPort());
		mailSenderImpl.setUsername(emailConfig.getUsername());
		mailSenderImpl.setPassword(emailConfig.getPassword());
		return mailSenderImpl;
	}

	@Async
	void sendVerificationEmail(NotificationEmail notificationEmail, String token) {
		
		JavaMailSender mailSender = getJavaMailSender();
		MimeMessagePreparator messagePreparator = mimeMessage -> {
			MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);
			messageHelper.setFrom("springreddit@emai.com");
			messageHelper.setTo(notificationEmail.getRecipient());
			messageHelper.setSubject(notificationEmail.getRecipient());
			
			String content = mailContentBuilder.buildVerificationEmail(notificationEmail.getBody(),token);
			messageHelper.setText(content, true);
		};
		
		try {
			mailSender.send(messagePreparator);
		} catch (MailException e) {
			throw new SpringRedditException("Exception occured when sending mail to " +
					notificationEmail.getRecipient());
		}
	}
	
}
