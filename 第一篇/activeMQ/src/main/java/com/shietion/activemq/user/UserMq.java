package com.shietion.activemq.user;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.DeliveryMode;
import javax.jms.Destination;
import javax.jms.MessageConsumer;
import javax.jms.MessageProducer;
import javax.jms.ObjectMessage;
import javax.jms.Session;

import org.apache.activemq.ActiveMQConnectionFactory;

import com.shietion.activemq.model.UserInfoModel;

public class UserMq {

	ConnectionFactory connectionFactory;

	// Connection ：JMS 客户端到JMS Provider 的连接
	Connection connection = null;
	// Session： 一个发送或接收消息的线程
	Session session;
	// Destination ：消息的目的地;消息发送给谁.
	Destination destination;
	// MessageProducer：消息发送者
	MessageProducer producer;

	private static UserMq sender = null;

	private static final String DEFAULT_BROKERURL = "tcp://localhost:61616";

	private static final String USER_QUEUE = "user_queue";

	private UserMq() {
		init();
	}

	public static synchronized UserMq getUserMq() {
		if (null == sender) {
			sender = new UserMq();
		}
		return sender;
	}

	public void sendUserInfo(UserInfoModel user) {
		try {
			// 获取操作连接
			session = connection.createSession(Boolean.TRUE, Session.AUTO_ACKNOWLEDGE);
			// 获取session注意参数值xingbo.xu-queue是一个服务器的queue，须在在ActiveMq的console配置
			destination = session.createQueue(USER_QUEUE);

			// 得到消息生成者【发送者】
			producer = session.createProducer(destination);

			// 设置不持久化，此处学习，实际根据项目决定
			producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
			ObjectMessage objMessage = session.createObjectMessage();
			objMessage.setObject(user);
			producer.send(objMessage);
			System.out.println("用户 "+user.toString()+"进入队列");
			session.commit();
		} catch (Exception e) {

		}
	}

	public void init() {
		connectionFactory = new ActiveMQConnectionFactory(DEFAULT_BROKERURL);
		try {
			connection = connectionFactory.createConnection();
			// 启动
			connection.start();

		} catch (Exception e) {

		}
	}

	public void close() {
		try {
			if (null != connection) {
				connection.close();
			}

		} catch (Throwable ignore) {
		}
	}

	public void getUserInfo() {
		// TODO Auto-generated method stub
		try {
			// 获取操作连接
			session = connection.createSession(Boolean.FALSE, Session.AUTO_ACKNOWLEDGE);
			// 获取session注意参数值xingbo.xu-queue是一个服务器的queue，须在在ActiveMq的console配置
			destination = session.createQueue(USER_QUEUE);
			MessageConsumer consumer = session.createConsumer(destination);
			ObjectMessage message = (ObjectMessage) consumer.receive(100000);
			if (null != message) {
				UserInfoModel user = (UserInfoModel) message.getObject();
				System.out.println("收到消息" + user.toString());
			}
		} catch (Exception e) {

		}
	}
}
