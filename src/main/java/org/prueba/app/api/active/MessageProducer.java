package org.prueba.app.api.active;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;
import lombok.extern.slf4j.Slf4j;
import org.prueba.app.model.entities.MessageEncrypt;

/**
 *
 * @author Programmercito <devtecpro.org>
 */
@Component
@Slf4j
public class MessageProducer {

	@Autowired
	private JmsTemplate jmsTemplate;

	public void sendTo(String destination, MessageEncrypt mensaje) {
		jmsTemplate.convertAndSend(destination, mensaje);
		log.info("Producer> Message Sent");
	}
}