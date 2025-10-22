package com.david.tfg.controllers;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.david.tfg.services.UserService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/CLIENTE") 
public class CustomerRestController extends UserRestController {

    public CustomerRestController(UserService service) {
        super(service);
    }
}
