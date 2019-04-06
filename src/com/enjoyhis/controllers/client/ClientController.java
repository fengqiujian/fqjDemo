package com.enjoyhis.controllers.client;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.enjoyhis.annotation.AuthPassport;

@Controller
public class ClientController {

	@Autowired
	JmsTemplate jmsTemplate_patient;

	@AuthPassport
	@RequestMapping(value = "/api/chat/send_imgmsg.json", method = RequestMethod.POST)
	public void sendImgmsg(HttpServletRequest request, HttpServletResponse response) {

	}

	@RequestMapping(value = "/api/chat/sendmsg.json", method = RequestMethod.GET)
	public void sendImgmsg1(HttpServletRequest request, HttpServletResponse response) throws JMSException {
		jmsTemplate_patient.send(new MessageCreator() {
			public Message createMessage(Session session) throws JMSException {
				return session.createTextMessage("hello");
			}
		});

		/**
		 * try { Thread.sleep(3000); } catch (InterruptedException e) { // TODO Auto-generated
		 * catch block e.printStackTrace(); }
		 * 
		 * for(int i=0;i<10;i++){ TextMessage message = (TextMessage)jmsTemplate_patient.receive();
		 * 
		 * //message.acknowledge(); if(message != null) System.out.println(message.getText()); else
		 * System.out.println("null"); }
		 */

	}

}
