package com.nextech.erp.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.nextech.erp.dto.EmailSend;

@Controller
@RequestMapping("/sendemail")
public class SendEmailController {
	@Autowired
	private JavaMailSender mailSender;
	
	@RequestMapping(value = "/sendEmail.do",method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, headers = "Accept=application/json")
	public String doSendEmail(@Valid @RequestBody EmailSend emailSend,HttpServletRequest request) {
		// takes input from e-mail form
/*		String recipientAddress = request.getParameter("recipient");
		String subject = request.getParameter("subject");
		String message = request.getParameter("message");
		emailSend.setRecipientAddress(recipientAddress);
		emailSend.setSubject(subject);
		emailSend.setMessage(message);
		// prints debug info
		System.out.println("To: " + recipientAddress);
		System.out.println("Subject: " + subject);
		System.out.println("Message: " + message);*/
		
		// creates a simple e-mail object
		SimpleMailMessage email = new SimpleMailMessage();
		email.setTo(emailSend.getRecipientAddress());
		email.setSubject(emailSend.getSubject());
		email.setText(emailSend.getMessage());
		
		// sends the e-mail
		mailSender.send(email);
		// forwards to the view named "Result"
		return "Result";
	}
}
