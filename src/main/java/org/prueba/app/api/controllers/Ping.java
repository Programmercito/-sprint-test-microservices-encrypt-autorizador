package org.prueba.app.api.controllers;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.util.Base64;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.prueba.app.util.RsaDecrypt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Programmercito <devtecpro.org>
 */
@RestController
@RequestMapping(path = "/api")
public class Ping {

    @GetMapping("/check")
    public String api() {
        RsaDecrypt decry = new RsaDecrypt();
        try {
            PublicKey pub = decry.getPublicKey("publica.key");
            PrivateKey priv= decry.getPrivateKey("llave.key");
            String prueba="hola";
            byte[] enc = decry.enc(prueba.getBytes(),pub);
            String pru=new String(enc);
            pru=Base64.getEncoder().encodeToString(enc);
            System.out.println(pru);
            byte[] enc2 = decry.decrypt(pru.getBytes(),priv);
            String pru1=new String(enc2);
            System.out.println(pru1);
                 
        } catch (IOException ex) {
            Logger.getLogger(Ping.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(Ping.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvalidKeySpecException ex) {
            Logger.getLogger(Ping.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "Ok";
    }

}
