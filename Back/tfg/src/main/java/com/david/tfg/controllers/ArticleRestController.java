package com.david.tfg.controllers;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import com.david.tfg.services.ArticleService;

@CrossOrigin(origins = "*")
@RestController
public class ArticleRestController {
    //Inyectamos el servicio
    private final ArticleService service;

    public ArticleRestController(ArticleService service) {
        this.service = service;
    }
}
