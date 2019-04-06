package com.enjoyhis.service;

import java.math.BigDecimal;
import java.net.MalformedURLException;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Service;

import com.enjoyhis.pojo.HisMqMsg;
import com.enjoyhis.util.HisMqEnum;
import com.enjoyhis.util.JSONUtils;
import com.enjoyhis.util.LogUtils;

@Service
public class HisMqService {

	@Autowired
	JmsTemplate jmsTemplatePatient;

	@Autowired
	private HisPatientService hisPatientService;// 患者service
	
	Logger log = LogUtils.CLIENT_TRACE;

	public void mqMsgToJt(Long id, HisMqEnum hisEnumToJt) {
		log.info("id, hisMqEnum", id, hisEnumToJt);
		HisMqMsg hisMqMsg = new HisMqMsg(id, "fyToJt" + id, hisEnumToJt);
		final String txtmsg = JSONUtils.toJSONString(hisMqMsg);
		MessageCreator messageCreator = new MessageCreator() {
			public Message createMessage(Session session) throws JMSException {
				return session.createTextMessage(txtmsg);
			}
		};
		jmsTemplatePatient.send(messageCreator);
	}
	public void mqMsgToJt(Long id,BigDecimal payAmount,Integer status) {
		HisMqMsg hisMqMsg = new HisMqMsg(id, "fyToJtUpateaPatiment",payAmount,status);
		final String txtmsg = JSONUtils.toJSONString(hisMqMsg);
		MessageCreator messageCreator = new MessageCreator() {
			public Message createMessage(Session session) throws JMSException {
				return session.createTextMessage(txtmsg);
			}
		};
		jmsTemplatePatient.send(messageCreator);
	}

	public boolean processJT(HisMqMsg hisMqMsg) throws MalformedURLException {
		if (hisMqMsg.getObjname().equals("fyToJt" + hisMqMsg.getId())) {
			return hisPatientService.insertFy2Jt(hisMqMsg.getId(), hisMqMsg.getHisMqEnum());
		}else if(hisMqMsg.getObjname().equals("fyToJtUpateaPatiment")){
			return hisPatientService.insertFy2Jt(hisMqMsg.getId(), hisMqMsg.getPayAmount(), hisMqMsg.getStatus());
		}
		return false;
	}
}
