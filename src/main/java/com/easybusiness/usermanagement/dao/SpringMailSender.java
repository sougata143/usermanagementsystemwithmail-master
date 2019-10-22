package com.easybusiness.usermanagement.dao;

import java.io.File;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

@Component
public class SpringMailSender {
	
	@Autowired
	JavaMailSender sender;
	
	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	public void sendSimpleMail(String from, String to, String subject, String body) {
		SimpleMailMessage mail = new SimpleMailMessage();
		
		mail.setFrom(from);
		mail.setTo(to);
		mail.setSubject(subject);
		mail.setText(body);
		
		logger.info("sending.....");
		
		sender.send(mail);
		
		logger.info("Done");
		
	}
	
	public void sendMailWithAttachment(String from, String to, String subject, String body, String pathToAttachment) 
										throws MessagingException {
		MimeMessage mail = sender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(mail, true);
		
		helper.setTo(to);
		helper.setFrom(from);
		helper.setSubject(subject);
		helper.setText(body);
		
		FileSystemResource file = new FileSystemResource(new File(pathToAttachment));
		helper.addAttachment("Sending", file);
		
		sender.send(mail);
		
	}

}
