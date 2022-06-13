package org.prueba.app.api.active;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import org.springframework.stereotype.Component;
import lombok.extern.slf4j.Slf4j;
import org.prueba.app.model.entities.MessageEncrypt;

import org.prueba.app.util.RsaUtil;
import org.springframework.jms.annotation.JmsListener;

/**
 *
 * @author Programmercito <devtecpro.org>
 */
@Component
@Slf4j
public class ProducerConsumer {

    @JmsListener(destination = "${activemq.destination}", containerFactory = "jmsFactory")
    public void processToDo(MessageEncrypt mensaje) {
        log.info("Consumer> " + mensaje);
        log.info("desencryptando ");
        String decryptedString="Formato incorrecto";
        try {
            decryptedString = RsaUtil.decrypt(mensaje.getMessage(), RsaUtil.privateKey);
        } catch (IllegalBlockSizeException ex) {
            Logger.getLogger(ProducerConsumer.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvalidKeyException ex) {
            Logger.getLogger(ProducerConsumer.class.getName()).log(Level.SEVERE, null, ex);
        } catch (BadPaddingException ex) {
            Logger.getLogger(ProducerConsumer.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(ProducerConsumer.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoSuchPaddingException ex) {
            Logger.getLogger(ProducerConsumer.class.getName()).log(Level.SEVERE, null, ex);
        }
        log.info("resultado> "+decryptedString);
       
    }

}
