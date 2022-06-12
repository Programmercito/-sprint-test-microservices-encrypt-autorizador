package org.prueba.app.model.entities;

import java.io.Serializable;
import lombok.ToString;

/**
 *
 * @author Programmercito <devtecpro.org>
 */
@ToString
public class MessageEncrypt implements Serializable{
private String message;    

    /**
     * @return the message
     */
    public String getMessage() {
        return message;
    }

    /**
     * @param message the message to set
     */
    public void setMessage(String message) {
        this.message = message;
    }
}
