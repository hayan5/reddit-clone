package com.ayan.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import static com.ayan.util.Constants.ACTIVATION_EMAIL;

@Service
public class MailContentBuilder {
	
	private TemplateEngine templateEngine;
	
	@Autowired 
	public MailContentBuilder(TemplateEngine templateEngine) {
		this.templateEngine = templateEngine;
	}
	
	public String buildVerificationEmail(String message, String token) {
		Context context = new Context();
		context.setVariable("message", message);
		String url = ACTIVATION_EMAIL + "/" + token;
		context.setVariable("url", url);
		return templateEngine.process("verificationEmailTemplate", context);
	}
}
