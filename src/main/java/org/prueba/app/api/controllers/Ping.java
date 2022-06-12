package org.prueba.app.api.controllers;

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
        return "Ok";
    }
}
