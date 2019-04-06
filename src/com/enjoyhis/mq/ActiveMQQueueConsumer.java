package com.enjoyhis.mq;

import java.net.MalformedURLException;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSONException;
import com.enjoyhis.pojo.HisMqMsg;
import com.enjoyhis.service.HisMqService;
import com.enjoyhis.util.LogUtils;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class ActiveMQQueueConsumer {

	@Autowired
	HisMqService hisMqService;

	// 已自动注入
	String brokerURL;

	// 已自动注入
	String qname;

	// connection的工厂
	private ConnectionFactory factory;
	// 连接对象
	private Connection connection;
	// 一个操作会话
	private Session session;
	// 目的地，其实就是连接到哪个队列，如果是点对点，那么它的实现是Queue，如果是订阅模式，那它的实现是Topic
	private Destination destination;
	// 消费者，就是接收数据的对象
	private MessageConsumer consumer;
	
	Logger log = LogUtils.CLIENT_TRACE;

	public void init() {
		log.info(brokerURL + " " + qname + " init ......");
		try {
			// 根据用户名，密码，url创建一个连接工厂
			factory = new ActiveMQConnectionFactory("", "", brokerURL);
			// 从工厂中获取一个连接
			connection = factory.createConnection();
			// 测试过这个步骤不写也是可以的，但是网上的各个文档都写了
			connection.start();
			// 创建一个session
			// 第一个参数:是否支持事务，如果为true，则会忽略第二个参数，被jms服务器设置为SESSION_TRANSACTED
			// 第二个参数为false时，paramB的值可为Session.AUTO_ACKNOWLEDGE，Session.CLIENT_ACKNOWLEDGE，DUPS_OK_ACKNOWLEDGE其中一个。
			// Session.AUTO_ACKNOWLEDGE为自动确认，客户端发送和接收消息不需要做额外的工作。哪怕是接收端发生异常，也会被当作正常发送成功。
			// Session.CLIENT_ACKNOWLEDGE为客户端确认。客户端接收到消息后，必须调用javax.jms.Message的acknowledge方法。jms服务器才会当作发送成功，并删除消息。
			// DUPS_OK_ACKNOWLEDGE允许副本的确认模式。一旦接收方应用程序的方法调用从处理消息处返回，会话对象就会确认消息的接收；而且允许重复确认。
			session = connection.createSession(false, Session.CLIENT_ACKNOWLEDGE);

			// 创建一个到达的目的地，可以理解为（key）
			destination = session.createQueue(qname);
			// 根据session，创建一个接收者对象
			consumer = session.createConsumer(destination);

			// 实现一个消息的监听器
			// 实现这个监听器后，以后只要有消息，就会通过这个监听器接收到
			consumer.setMessageListener(new MessageListener() {
				@Override
				public void onMessage(Message message) {
					try {
						// 获取到接收的数据
						String text = ((TextMessage) message).getText();
						System.out.println(text);

						while (true) {
							try {
								boolean processres = process(text);
								if (processres)
									break;
								else {
									Thread.sleep(3000);
								}
							} catch (Exception e) {
								e.printStackTrace();
								Thread.sleep(3000);
							}
						}

						message.acknowledge();
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
		} catch (JMSException e) {
			e.printStackTrace();
		}
	}

	protected boolean process(String text) throws MalformedURLException {
		// 消息正常处理返回正确
		HisMqMsg msgPatient = null;
		try {
			msgPatient = new Gson().fromJson(text, new TypeToken<HisMqMsg>() {
			}.getType());
		} catch (JSONException ej) {
			log.error("JSONException:" + text);
			return true;
		}
		boolean processJTPatient = hisMqService.processJT(msgPatient);
		log.info("activeMQ patientMqService.processJTPatient(msgPatient)返回：" + processJTPatient);
		return processJTPatient;
	}

	public void stopConsumer() {
		try {
			consumer.close();
			connection.stop();
			connection.close();
		} catch (JMSException e) {
			e.printStackTrace();
		}
	}

	public String getBrokerURL() {
		return brokerURL;
	}

	public void setBrokerURL(String brokerURL) {
		this.brokerURL = brokerURL;
	}

	public String getQname() {
		return qname;
	}

	public void setQname(String qname) {
		this.qname = qname;
	}

}
